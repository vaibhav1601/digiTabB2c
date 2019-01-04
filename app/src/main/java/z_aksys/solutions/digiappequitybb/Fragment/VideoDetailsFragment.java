package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Activity.YoutubePlayerActivity;
import z_aksys.solutions.digiappequitybb.Adapter.VideoDetailsAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

;

public class VideoDetailsFragment extends Fragment implements OnClickVideo {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private VideoDetailsAdapter videoDetailsAdapter;
    private List<ShareVideoResponse.share_video> share_videos;
    private ProgressDialog progressDialog;
    private SearchView simpleSearchView;
    private int firstPage = 0;
    private int pageLimit = 10;
    private boolean isLoading = true;
    private int pastVisibleitem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewtueshole = 100;
    private int hasNext;
    private String topicId;
    private AppCompatTextView txtName;
    private PitchServices pitchServices;


    public VideoDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.video_details_frgament, container, false);

        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        txtName = (AppCompatTextView) view.findViewById(R.id.txtfaqdetails);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_faq_details);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pitchServices = RetrofitClient.getInstance().getApi();
        txtName.setText("Share");

        setData();

        if (AngelPitchUtil.checkConnection(getActivity())) {
            getVideoDetails();

        } else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return view;
    }


    private void setData() {

        Bundle bundle = getArguments();

        if (ObjectUtils.isNotNull(bundle)) {


            if (bundle.containsKey("TopicID")) {
                //ObjectUtils.getIntFromString(id=bundle.getString("ID"));

                topicId = bundle.getString("TopicID");

            }
        }

    }

    private void getVideoDetails() {
        showProgress();

        Call<ShareVideoResponse> myResponsecall = pitchServices.getShareVideo("application/json", appKey, appSec, user, firstPage, pageLimit, ObjectUtils.getIntFromString(topicId));
        myResponsecall.enqueue(new Callback<ShareVideoResponse>() {
            @Override
            public void onResponse(Call<ShareVideoResponse> call, Response<ShareVideoResponse> response) {

                if (response.isSuccessful()) {

                    share_videos = new ArrayList<>();
                    share_videos = response.body().getShare_videos();
                    hasNext = response.body().getHasNext();

                    if (!ObjectUtils.isEmpty(share_videos)) {
                        callAdaptor();
                    }
                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<ShareVideoResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        Call<ShareVideoResponse> myResponsecall = pitchServices.getShareVideo("application/json", appKey, appSec, user, firstPage, pageLimit, ObjectUtils.getIntFromString(topicId));
        myResponsecall.enqueue(new Callback<ShareVideoResponse>() {
            @Override
            public void onResponse(Call<ShareVideoResponse> call, Response<ShareVideoResponse> response) {

                if (response.isSuccessful()) {

                    share_videos = new ArrayList<>();
                    share_videos = response.body().getShare_videos();

                    if (!ObjectUtils.isEmpty(share_videos)) {
                        callAdaptor();
                    }
                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<ShareVideoResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });

    }


    private void callAdaptor() {
        videoDetailsAdapter = new VideoDetailsAdapter(getContext(), share_videos, this);
        videoDetailsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(videoDetailsAdapter);

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


    @Override
    public void VideoId(String url, String Id) {

        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", Id));

    }

    @Override
    public void newsUrl(String url, String Id) {

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
                outRect.bottom = spacing - 10; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
