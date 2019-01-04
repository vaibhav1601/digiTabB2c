package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Adapter.LearnAdapter;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Dao.LearnDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickLessons;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.AngelSharedPrefance;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class LearnFragment extends Fragment implements OnClickLessons {

    public LearnDao learnDao;
    RecyclerView.LayoutManager mLayoutManager;
    PitchRoomDatabase db;
    private RecyclerView recycler_view_learn;
    private LearnAdapter learnAdapter;
    private ProgressDialog progressDialog;
    private List<LearnResponse.topics> topicsArrayList;
    private List<LearnResponse.lessons> lessonsArrayList;
    private List<LearnResponse.questions> questionsArrayList;
    private FrameLayout flid;
    private PitchServices pitchServices;
    private AngelSharedPrefance angelSharedPrefance;
    private TextView txt_score_card;
    private SeekBar seekBar1;
    private Handler scoreHanler;


    public LearnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final View view = inflater.inflate(R.layout.fragment_learn_dash, container, false);
        recycler_view_learn = view.findViewById(R.id.recycler_view_learn);
        txt_score_card = view.findViewById(R.id.txt_score_card);
        seekBar1 = view.findViewById(R.id.seekBar1);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler_view_learn.setLayoutManager(mLayoutManager);
        recycler_view_learn.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recycler_view_learn.setItemAnimator(new DefaultItemAnimator());
        pitchServices = RetrofitClient.getInstance().getApi();
        angelSharedPrefance = new AngelSharedPrefance(App.getContext());

        seekBar1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        if (AngelPitchUtil.checkConnection(getActivity())) {

            getLearnDetails();

        } else {

            new GetLearnTask(getActivity()).execute();

            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

            //callAdaptor();
        }


        String currentDBPath = getActivity().getDatabasePath("PitchRoomDatabase.db").getAbsolutePath();

        Log.d("currentDBPath", "" + currentDBPath);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //new GetNewsTask(getActivity()).execute();
    }


    private void getLearnDetails() {

        showProgress();

        Call<LearnResponse> myResponsecall = pitchServices.getLearn("application/json", appKey, appSec, user);

        myResponsecall.enqueue(new Callback<LearnResponse>() {
            @Override
            public void onResponse(Call<LearnResponse> call, Response<LearnResponse> response) {

                if (response.isSuccessful()) {
                    hideProgress();
                    angelSharedPrefance.setHelathAPI("score", response.body().getTotal_learn_score());
                    topicsArrayList = new ArrayList<>();
                    lessonsArrayList = new ArrayList<>();
                    questionsArrayList = new ArrayList<>();
                    topicsArrayList = response.body().getTopics();
                    lessonsArrayList = response.body().getLessons();
                    questionsArrayList = response.body().getQuestions();
                    new AsyncTaskRunner(getActivity(), topicsArrayList, lessonsArrayList, questionsArrayList).execute();
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
        recycler_view_learn.setAdapter(learnAdapter);

    }


    @Override
    public void lessonId(String Id, int postion) {

        Bundle bundle = new Bundle();
        //  bundle.putSerializable(Constants.lESSIONS, new ArrayList<LearnResponse.lessons>(lessonsArrayList));
        // bundle.putSerializable(Constants.QUESTIONS, new ArrayList<LearnResponse.questions>(questionsArrayList));
        bundle.putString("ID", Id);
        LessonsFragment fragment2 = new LessonsFragment();
        fragment2.setArguments(bundle);
        replaceFragment(fragment2, true);


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

    private void setData() {

        String score = angelSharedPrefance.getHealthAPI("score");

        int x = (int) Math.round(ObjectUtils.getDoubleFromString(score));

        txt_score_card.setText(x + " / 100 %");

        if (seekBar1 != null) {
            seekBar1.setMax(0);
            seekBar1.setMax(100);
            seekBar1.setProgress(x);
        }

       /* scoreHanler.post(new Runnable() {
            @Override
            public void run() {

                if (seekBar1 != null) {
                    seekBar1.setMax(0);
                    seekBar1.setMax(100);
                    seekBar1.setProgress(ObjectUtils.getIntFromString(score));
                }

            }
        });*/
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

    private class AsyncTaskRunner extends AsyncTask<String, String, Boolean> {


        List<LearnResponse.topics> topicsArrayListData = new ArrayList<>();
        List<LearnResponse.lessons> lessonsArrayListData = new ArrayList<>();
        List<LearnResponse.questions> questionsArrayListData = new ArrayList<>();
        FragmentActivity fragment;


        public AsyncTaskRunner(FragmentActivity activity, List<LearnResponse.topics> topicsArrayList, List<LearnResponse.lessons> lessonsArrayList, List<LearnResponse.questions> questionsArrayList) {
            this.topicsArrayListData = topicsArrayList;
            this.lessonsArrayListData = lessonsArrayList;
            this.questionsArrayListData = questionsArrayList;
            this.fragment = activity;


        }

        @Override
        protected Boolean doInBackground(String... params) {

            db = PitchRoomDatabase.getDatabase(fragment);
            learnDao = db.learnDao();
            //deleteRecord();

            if (!ObjectUtils.isEmpty(topicsArrayListData)) {

                for (int i = 0; i < topicsArrayListData.size(); i++) {
                    Long id = learnDao.insertTopic(topicsArrayListData.get(i));
                    Log.d("inserTopicid", "inserid>>" + id);

                }
            }

            if (!ObjectUtils.isEmpty(lessonsArrayListData)) {


                for (int i = 0; i < lessonsArrayListData.size(); i++) {
                    Long id = learnDao.insertLessons(lessonsArrayListData.get(i));
                    Log.d("inserlessionsid", "inserid>>" + id);

                }

            }


            if (!ObjectUtils.isEmpty(questionsArrayListData)) {


                for (int i = 0; i < questionsArrayListData.size(); i++) {
                    Long id = learnDao.insertQuestions(questionsArrayListData.get(i));
                    Log.d("insertquestionsid", "inserid>>" + id);

                }

            }


            // topicsArrayList = learnDao.getAllTopics();

            return true;

        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //callAdaptor();
            // Toast.makeText(getContext(), "on post insert learn", Toast.LENGTH_LONG).show();


            new GetLearnTask(getActivity()).execute();


        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

    private class GetLearnTask extends AsyncTask<String, String, Boolean> {


        //List<NewsResponse.News> newsResponseList = new ArrayList<>();
        FragmentActivity fragment;


        public GetLearnTask(FragmentActivity activity) {
            this.fragment = activity;


        }

        @Override
        protected Boolean doInBackground(String... params) {


            PitchRoomDatabase db = PitchRoomDatabase.getDatabase(fragment);
            learnDao = db.learnDao();
            //deleteRecord();
            topicsArrayList = learnDao.getAllTopics();
            System.out.println("list topic>>>" + topicsArrayList.size());

            return true;

        }


        @Override
        protected void onPostExecute(Boolean result) {

            super.onPostExecute(result);

            // Toast.makeText(getContext(), "on post get learndata", Toast.LENGTH_LONG).show();

            // execution of result of Long time consuming operation
            callAdaptor();
            setData();
            //hideProgress();


        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }


}
