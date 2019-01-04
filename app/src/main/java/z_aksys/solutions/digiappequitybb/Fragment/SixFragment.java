package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
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
import z_aksys.solutions.digiappequitybb.Adapter.LearnAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class SixFragment extends Fragment {

    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recycler_view_learn;
    private LearnAdapter learnAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<LearnResponse.topics> topicsArrayList;
    private PitchServices pitchServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.demo, container, false);
        pitchServices = RetrofitClient.getInstance().getApi();


        return view;
    }


    private void getTopics() {

        showProgress();

        Call<LearnResponse> myResponsecall = pitchServices.getLearn("application/json", appKey, appSec, user);

        myResponsecall.enqueue(new Callback<LearnResponse>() {
            @Override
            public void onResponse(Call<LearnResponse> call, Response<LearnResponse> response) {

                if (response.isSuccessful()) {
                    hideProgress();
                    topicsArrayList = new ArrayList<>();
                    topicsArrayList = response.body().getTopics();

                    callAdaptor();
                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<LearnResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });


    }


    private void callAdaptor() {

        learnAdapter = new LearnAdapter(getContext(), topicsArrayList, this);
        learnAdapter.notifyDataSetChanged();
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler_view_learn.setLayoutManager(mLayoutManager);
        recycler_view_learn.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recycler_view_learn.setItemAnimator(new DefaultItemAnimator());
        recycler_view_learn.setAdapter(learnAdapter);

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
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
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
