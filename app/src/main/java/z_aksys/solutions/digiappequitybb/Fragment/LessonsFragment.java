package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z_aksys.solutions.digiappequitybb.Adapter.LessonsAdapter;
import z_aksys.solutions.digiappequitybb.Dao.LearnDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickLessons;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;


public class LessonsFragment extends Fragment implements OnClickLessons {


    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recycler_view_lessons;
    private LessonsAdapter lessonsAdapter;
    private ProgressDialog progressDialog;
    private TextView txtlearn;
    private List<LearnResponse.lessons> lessonsArrayList = new ArrayList<>();
    private List<LearnResponse.questions> questionsArrayList;
    private String topicId;
    private PitchRoomDatabase pitchRoomDatabase;
    private LearnDao learnDao;
    private TextView txt_score_card;
    private SeekBar seekBar2;


    //  private String ids;


    public LessonsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_news_upadte, container, false);

        recycler_view_lessons = view.findViewById(R.id.recycler_view_lessions);
        txt_score_card = view.findViewById(R.id.txt_score_card);
        seekBar2 = view.findViewById(R.id.seekBar2);
        txtlearn = (TextView) view.findViewById(R.id.txtlearn);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler_view_lessons.setLayoutManager(mLayoutManager);
        recycler_view_lessons.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recycler_view_lessons.setItemAnimator(new DefaultItemAnimator());

        txtlearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        seekBar2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        setData();


        return view;
    }

    private void setData() {


        Bundle bundle = getArguments();

        if (ObjectUtils.isNotNull(bundle)) {


            if (bundle.containsKey("ID")) {
                //ObjectUtils.getIntFromString(id=bundle.getString("ID"));

                topicId = bundle.getString("ID");

            }


        }


        new AsyncTaskRunner(getActivity(), pitchRoomDatabase, learnDao).execute();


    }

    @Override
    public void lessonId(String Id, int position) {
        Bundle bundle = new Bundle();
        // bundle.putSerializable(Constants.lESSIONS, new ArrayList<LearnResponse.lessons>(lessonsArrayList));
        // bundle.putSerializable(Constants.QUESTIONS, new ArrayList<LearnResponse.questions>(questionsArrayList));
        bundle.putString("lessionID", Id);
        bundle.putString("topicID", topicId);
        bundle.putInt("position", position);
        LessonsViewer fragment2 = new LessonsViewer();
        FragmentManager fragmentManager = getFragmentManager();
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

    private void callAdaptor() {

        if (!ObjectUtils.isEmpty(lessonsArrayList)) {

            lessonsAdapter = new LessonsAdapter(getContext(), lessonsArrayList, this, topicId);
            lessonsAdapter.notifyDataSetChanged();
            recycler_view_lessons.setAdapter(lessonsAdapter);

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

        //List<LearnResponse.lessons> lessonsArrayList = new ArrayList<>();
        PitchRoomDatabase pitchRoomDatabase;
        LearnDao learnDao;
        FragmentActivity fragment;


        public AsyncTaskRunner(FragmentActivity activity, PitchRoomDatabase pitchRoomDatabase, LearnDao learnDao) {

            this.fragment = activity;
            this.pitchRoomDatabase = pitchRoomDatabase;
            this.learnDao = learnDao;

        }


        @Override
        protected Boolean doInBackground(String... strings) {

            lessonsArrayList= new ArrayList<>();
            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            learnDao = pitchRoomDatabase.learnDao();
            //  lessonsArrayList = learnDao.getlessonsWithId(ObjectUtils.getStringFromInt(id));
            lessonsArrayList = learnDao.getlessons(topicId);
            System.out.println("Lessionslist" + lessonsArrayList.size());
            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            callAdaptor();
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }

    }


}
