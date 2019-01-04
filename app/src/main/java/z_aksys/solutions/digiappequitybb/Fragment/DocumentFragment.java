package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Adapter.DocumentAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareDocumentResponse;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class DocumentFragment extends Fragment {
    RecyclerView.LayoutManager mLayoutManager;
    PitchServices pitchServices;
    private RecyclerView recycler_view_document;
    private DocumentAdapter documentAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<ShareDocumentResponse.share_document> shareDocumentArrayList;
    private int firstPage = 0;
    private int pageLimit = 10;
    private boolean isLoading = true;
    private int pastVisibleitem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewtueshole = 100;
    private int hasNext;

    public DocumentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_document, container, false);

        recycler_view_document = view.findViewById(R.id.recycler_view_document);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        recycler_view_document.setLayoutManager(mLayoutManager);
        recycler_view_document.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(30), true));
        recycler_view_document.setItemAnimator(new DefaultItemAnimator());
        pitchServices = RetrofitClient.getInstance().getApi();

        if (AngelPitchUtil.checkConnection(getActivity())) {
            getShareDetails();

        } else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }


        return view;
    }


    private void getShareDetails() {

        showProgress();
        Call<ShareDocumentResponse> myResponsecall = pitchServices.getShareDocument("application/json", appKey, appSec, user, firstPage, pageLimit);
        myResponsecall.enqueue(new Callback<ShareDocumentResponse>() {
            @Override
            public void onResponse(Call<ShareDocumentResponse> call, Response<ShareDocumentResponse> response) {

                if (response.isSuccessful()) {
                    hideProgress();
                    shareDocumentArrayList = new ArrayList<>();
                    shareDocumentArrayList = response.body().getShare_documents();
                    hasNext = response.body().getHasNext();

                    if (!ObjectUtils.isEmpty(shareDocumentArrayList)) {
                        callAdaptor();
                    }


                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<ShareDocumentResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });


        recycler_view_document.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisibleitem = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }

                    }

                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleitem + viewtueshole) && hasNext > 0) {
                        pageLimit++;
                        callPagination();
                        isLoading = true;
                    }

                }
            }
        });


    }

    private void callPagination() {

        showProgress();
        Call<ShareDocumentResponse> myResponsecall = pitchServices.getShareDocument("application/json", appKey, appSec, user, firstPage, pageLimit);

        myResponsecall.enqueue(new Callback<ShareDocumentResponse>() {
            @Override
            public void onResponse(Call<ShareDocumentResponse> call, Response<ShareDocumentResponse> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    shareDocumentArrayList = new ArrayList<>();
                    shareDocumentArrayList = response.body().getShare_documents();

                    if (!ObjectUtils.isEmpty(shareDocumentArrayList)) {
                        callAdaptor();
                    }



                }

            }

            @Override
            public void onFailure(Call<ShareDocumentResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });

    }


    private void callAdaptor() {

        documentAdapter = new DocumentAdapter(getContext(), shareDocumentArrayList, this);
        documentAdapter.notifyDataSetChanged();
        recycler_view_document.setAdapter(documentAdapter);

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void showProgress() {
        if (isAdded() && (progressDialog == null || (progressDialog != null && progressDialog.isShowing() == false))) {
            progressDialog = ProgressDialog.show(getActivity(), "", "Please wait.", true);
        }
    }

    public void hideProgress() {
        if (isAdded() && progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        } else {
            return;
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount - 20; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount - 20; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing - 10;
                }
                outRect.bottom = spacing + 20; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


}
