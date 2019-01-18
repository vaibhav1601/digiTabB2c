package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import z_aksys.solutions.digiappequitybb.Adapter.VideoTopicAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoTopicResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class VideoFragmentV2 extends Fragment implements OnClickVideo {

    RecyclerView.LayoutManager mLayoutManager;
    PitchServices pitchServices;
    private RecyclerView recycler_view_video;
    private VideoTopicAdapter videoAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<ShareVideoTopicResponse.share_video_topic> share_video_topics;


    public VideoFragmentV2() {
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
            getShareVideo();

        } else {
            callAdaptor();
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }


        return view;
    }


    private void getShareVideo() {
        showProgress();
        Call<ShareVideoTopicResponse> myResponsecall = pitchServices.getVideoTopic("application/json", appKey, appSec, user);
        myResponsecall.enqueue(new Callback<ShareVideoTopicResponse>() {
            @Override
            public void onResponse(Call<ShareVideoTopicResponse> call, Response<ShareVideoTopicResponse> response) {

                if (response.isSuccessful()) {

                    hideProgress();

                    share_video_topics = new ArrayList<>();
                    share_video_topics = response.body().getShare_video_topics();

                    if (!ObjectUtils.isEmpty(share_video_topics)) {
                        callAdaptor();
                    } else {
                        callAdaptor();
                    }


                }

            }

            @Override
            public void onFailure(Call<ShareVideoTopicResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });


    }


    private void callAdaptor() {

        videoAdapter = new VideoTopicAdapter(getContext(), share_video_topics, this);
        videoAdapter.notifyDataSetChanged();
        recycler_view_video.setAdapter(videoAdapter);

    }


    @Override
    public void VideoId(String url, String Id) {


        Bundle bundle = new Bundle();
        //  bundle.putSerializable(Constants.lESSIONS, new ArrayList<LearnResponse.lessons>(lessonsArrayList));
        // bundle.putSerializable(Constants.QUESTIONS, new ArrayList<LearnResponse.questions>(questionsArrayList));
        bundle.putString("TopicID", Id);
        if (!TextUtils.isEmpty(Id)) {
            VideoDetailsFragment fragment2 = new VideoDetailsFragment();
            fragment2.setArguments(bundle);
            replaceFragment(fragment2, true);
        }
    }

    @Override
    public void newsUrl(String url, String id) {

    }


    protected void replaceFragment(Fragment fragmentName, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_left_in,
                R.anim.slide_left_out,
                R.anim.trans_right_in,
                R.anim.trans_right_out);

        if (isAddToBackStack) {
            fragmentTransaction.replace(android.R.id.content,
                    fragmentName,
                    fragmentName.getClass().getSimpleName()).addToBackStack(null);
        } else {
            fragmentTransaction.replace(android.R.id.content,
                    fragmentName,
                    fragmentName.getClass().getSimpleName());
        }
        fragmentTransaction.commitAllowingStateLoss();
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
