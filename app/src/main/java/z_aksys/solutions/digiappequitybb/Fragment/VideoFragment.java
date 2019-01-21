package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
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
import z_aksys.solutions.digiappequitybb.Activity.YoutubePlayerActivity;
import z_aksys.solutions.digiappequitybb.Adapter.VideoAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class VideoFragment extends Fragment implements OnClickVideo {

    RecyclerView.LayoutManager mLayoutManager;
    PitchServices pitchServices;
    private RecyclerView recycler_view_video;
    private VideoAdapter videoAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<ShareVideoResponse.share_video> shareVideoArrayList;
    private int firstPage = 0;
    private int pageLimit = 10;
    private boolean isLoading = true;
    private int pastVisibleitem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewtueshole = 100;
    private int hasNext;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_video, container, false);

        recycler_view_video = view.findViewById(R.id.recycler_view_Video);

        mLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler_view_video.setLayoutManager(mLayoutManager);
        recycler_view_video.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recycler_view_video.setItemAnimator(new DefaultItemAnimator());
        pitchServices = RetrofitClient.getInstance().getApi();

        if (AngelPitchUtil.checkConnection(getActivity())) {
            //getShareVideo();

        } else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }


        return view;
    }


    private void getShareVideo() {
        showProgress();
        Call<ShareVideoResponse> myResponsecall = pitchServices.getShareVideo("application/json", appKey, appSec, user, firstPage, pageLimit, 1);
        myResponsecall.enqueue(new Callback<ShareVideoResponse>() {
            @Override
            public void onResponse(Call<ShareVideoResponse> call, Response<ShareVideoResponse> response) {

                if (response.isSuccessful()) {

                    hideProgress();

                    shareVideoArrayList = new ArrayList<>();
                    shareVideoArrayList = response.body().getShare_videos();
                    hasNext = response.body().getHasNext();
                    if (!ObjectUtils.isEmpty(shareVideoArrayList)) {
                        callAdaptor();
                    }


                }

            }

            @Override
            public void onFailure(Call<ShareVideoResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });


        recycler_view_video.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisibleitem = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();

                if (dy > hasNext) {
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

        Call<ShareVideoResponse> myResponsecall = pitchServices.getShareVideo("application/json", appKey, appSec, user, firstPage, pageLimit, 1);

        myResponsecall.enqueue(new Callback<ShareVideoResponse>() {
            @Override
            public void onResponse(Call<ShareVideoResponse> call, Response<ShareVideoResponse> response) {

                if (response.isSuccessful()) {

                    hideProgress();

                    shareVideoArrayList = new ArrayList<>();
                    shareVideoArrayList = response.body().getShare_videos();
                    if (!ObjectUtils.isEmpty(shareVideoArrayList)) {
                        callAdaptor();
                    }


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

        videoAdapter = new VideoAdapter(getContext(), shareVideoArrayList, this);
        videoAdapter.notifyDataSetChanged();
        recycler_view_video.setAdapter(videoAdapter);

    }


    @Override
    public void VideoId(String url, String Id) {


        //start youtube player activity by passing selected video id via intent
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", Id));

    }

    @Override
    public void newsUrl(String url, String Id) {

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
                    //outRect.top = spacing - 10;
                }
                outRect.bottom = spacing - 40; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    //outRect.top = spacing; // item top
                }
            }
        }
    }


}
