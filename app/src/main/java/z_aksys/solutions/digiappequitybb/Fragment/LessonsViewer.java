package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Activity.QuizActivity;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Dao.LearnDao;
import z_aksys.solutions.digiappequitybb.Dao.QuestionDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Request.QuestionRequest;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.Response.QuestionResponse;
import z_aksys.solutions.digiappequitybb.model.MyServerData;
import z_aksys.solutions.digiappequitybb.model.QuestionStatus;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.AngelSharedPrefance;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;

public class LessonsViewer extends Fragment {

    public PitchRoomDatabase pitchRoomDatabase;
    public QuestionDao questionDao;
    public LearnDao learnDao;
    public int count;
    PitchServices pitchServices;
    private List<LearnResponse.lessons> lessonsArrayList;
    private List<LearnResponse.questions> questionsArrayList;
    private String lessionName;
    private ImageView img_lessions_view;
    private TextView txt_discription;
    private TextView btn_master_topic;
    private TextView txtlearnview, topic;
    private TextView btn_next;
    private TextView btn_previous;
    private String lessionId;
    private String topicId;
    private int position;
    private String answer = "";
    private int i = 0;
    private ProgressDialog progressDialog;
    private MyServerData myServerData;
    private AngelSharedPrefance sharedPrefManager;
    private String nextlessionId;

    public LessonsViewer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.lessions_viewer, container, false);

        img_lessions_view = (ImageView) view.findViewById(R.id.img_lessions_view);
        txt_discription = (TextView) view.findViewById(R.id.txt_lessionsviewer);
        topic = (TextView) view.findViewById(R.id.topic);
        btn_master_topic = (TextView) view.findViewById(R.id.btn_master);
        btn_next = (TextView) view.findViewById(R.id.btn_after);
        btn_previous = (TextView) view.findViewById(R.id.btn_before);
        txtlearnview = (TextView) view.findViewById(R.id.txtlearnview);
        pitchServices = RetrofitClient.getInstance().getApi();
        setData();
        sharedPrefManager = new AngelSharedPrefance(App.getContext());
        myServerData = new MyServerData();

        txtlearnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncNextRunner(getActivity()).execute();

            }
        });


        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncPreviousRunner(getActivity()).execute();

            }
        });

        return view;
    }

    private void setData() {

        Bundle bundle = getArguments();

        if (ObjectUtils.isNotNull(bundle)) {

            if (bundle.containsKey("lessionID")) {

                lessionId = bundle.getString("lessionID");

            }

            if (bundle.containsKey("topicID")) {
                topicId = bundle.getString("topicID");

            }

            if (bundle.containsKey("position")) {
                position = bundle.getInt("position");

            }

            new AsyncTaskRunner(getActivity()).execute();


        }


        btn_master_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               /* MyServerData.getInstance().setTestState("inProgress");
                startActivity(new Intent(getActivity(), QuizActivity.class).putExtra("lessionId", lessionId,"topicId",topicId));

*/
                sharedPrefManager.setHelathAPI("Questions", "");
                sharedPrefManager.setHelathAPI("LessionsName", "");
                sharedPrefManager.setHelathAPI("LessionsName", lessionName);
                sharedPrefManager.setHelathAPI("Questions", AngelPitchUtil.toJson(questionsArrayList));

                Intent Quiz = new Intent(App.getContext(), QuizActivity.class);
                Quiz.putExtra("lessionId", lessionId);
                Quiz.putExtra("topicId", topicId);
                myServerData.setTestState("inProgress");
                startActivity(Quiz);

                /*for (int i = 0; i < questionsArrayList.size(); i++) {
                    showQuestionsDialog(getContext(), questionsArrayList.get(i));

                }
*/
            }
        });


    }

    private void setValue() {

        if (!ObjectUtils.isEmpty(lessonsArrayList)) {

            topic.setText(lessonsArrayList.get(0).getName());
            Glide.with(getActivity()).load(lessonsArrayList.get(0).getImage()).into(img_lessions_view);
            txt_discription.setText(Html.fromHtml(lessonsArrayList.get(0).getDescription()));


        }


    }

    public void showQuestionsDialog(final Context context, final LearnResponse.questions questions) {

        if (ObjectUtils.isNotNull(questions)) {

            final Dialog alert = new Dialog(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View alertDlgView = inflater.inflate(R.layout.dialog_questions, null);
            AppCompatTextView txt_heading = (AppCompatTextView) (alertDlgView).findViewById(R.id.txt_heading);
            final AppCompatTextView option_1 = (AppCompatTextView) (alertDlgView).findViewById(R.id.option_1);
            final AppCompatTextView option_2 = (AppCompatTextView) (alertDlgView).findViewById(R.id.option_2);
            final AppCompatTextView option_3 = (AppCompatTextView) (alertDlgView).findViewById(R.id.option_3);
            final AppCompatTextView option_4 = (AppCompatTextView) (alertDlgView).findViewById(R.id.option_4);
            final LinearLayout ll_option_1 = (LinearLayout) (alertDlgView).findViewById(R.id.ll_option_1);
            final LinearLayout lloption_2 = (LinearLayout) (alertDlgView).findViewById(R.id.lloption_2);
            final LinearLayout lloption_3 = (LinearLayout) (alertDlgView).findViewById(R.id.lloption_3);
            final LinearLayout lloption_4 = (LinearLayout) (alertDlgView).findViewById(R.id.lloption_4);
            final AppCompatTextView tv_answer = (AppCompatTextView) (alertDlgView).findViewById(R.id.tv_answer);
            final ImageView img_close = (ImageView) (alertDlgView).findViewById(R.id.img_close);

            alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alert.setContentView(alertDlgView);
            alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alert.setCanceledOnTouchOutside(true);

            new AsyncTaskQuestionDelete(getActivity(), topicId, lessionId).execute();


            txt_heading.setText(questions.title);
            option_1.setText(questions.option_a);
            option_2.setText(questions.option_b);
            option_3.setText(questions.option_c);
            option_4.setText(questions.option_d);


            TextView btn_GotIt = (TextView) alert.findViewById(R.id.btn_submit);
            alert.setCancelable(false);
            alert.setCanceledOnTouchOutside(false);
            alert.show();


            ll_option_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    // answer=option_1.getText().toString();
                    answer = "option_a";

                }
            });


            lloption_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    // answer=option_2.getText().toString();

                    answer = "option_b";
                }
            });


            lloption_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    answer = "option_c";

                }
            });


            lloption_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    //answer=option_4.getText().toString();

                    answer = "option_d";


                }
            });

            btn_GotIt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (answer.equalsIgnoreCase(questions.getAnswer())) {

                        pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
                        questionDao = pitchRoomDatabase.questionDao();

                        new AsyncTaskRunnerResult(getActivity(), questions, v.getId()).execute();

                        new AsyncTaskQuestionResult(getActivity()).execute();

                        alert.dismiss();


                    } else {

                        tv_answer.setText(questions.answer_desc);
                        Toast.makeText(getContext(), "your answer is " + questions.answer_desc, Toast.LENGTH_LONG).show();
                        alert.dismiss();
                        //   showQuestion(getContext());
                    }

                }
            });

            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.cancel();

                    // showQuestion(getContext());

                }
            });
        }
    }

    private void callServices() {
        showProgress();

        QuestionRequest questionRequest = new QuestionRequest();
        List<QuestionRequest> questionRequestList = new ArrayList<>();

        int tId = Integer.parseInt(topicId);
        int lId = Integer.parseInt(lessionId);
        questionRequest.setEmp_no("test");
        questionRequest.setTopic_id(tId);
        questionRequest.setLesson_id(lId);
        String mark = String.valueOf(count);

        System.out.println("count in service" + count);

        if (count == 5) {

            questionRequest.setIs_completed(1);
            questionRequest.setScore(mark + "/" + "5");
            System.out.println("states complete " + count);
        } else {

            questionRequest.setIs_completed(2);
            System.out.println("states incomplete " + count);
            questionRequest.setScore(mark + "/" + "5");

        }

        questionRequestList.add(questionRequest);


        Call<QuestionResponse> myResponsecall = pitchServices.postAllQuestion("application/json", appKey, appSec, questionRequestList);

        myResponsecall.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {

                if (response.isSuccessful()) {

                    hideProgress();
                    Toast.makeText(getContext(), "Fail" + response.message(), Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });


    }

    public void showQuestion(Context context) {


        final Dialog alert = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDlgView = inflater.inflate(R.layout.question_score, null);
        AppCompatTextView txt_heading = (AppCompatTextView) (alertDlgView).findViewById(R.id.txt_heading);
        final TextView txtScore = (TextView) (alertDlgView).findViewById(R.id.txtScore);
        final TextView btn_later = (TextView) (alertDlgView).findViewById(R.id.btn_later);
        final TextView btn_retry = (TextView) (alertDlgView).findViewById(R.id.btn_retry);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(alertDlgView);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alert.setCanceledOnTouchOutside(true);
        alert.setCanceledOnTouchOutside(false);
        alert.show();


        btn_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();

                LearnFragment fragment2 = new LearnFragment();

                replaceFragment(fragment2, false);


            }
        });


        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                showQuestionsDialog(getContext(), questionsArrayList.get(i));

            }
        });


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

    private class AsyncTaskRunner extends AsyncTask<String, String, Boolean> {

        FragmentActivity fragment;


        public AsyncTaskRunner(FragmentActivity activity) {

            this.fragment = activity;


        }


        @Override
        protected Boolean doInBackground(String... strings) {

            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            learnDao = pitchRoomDatabase.learnDao();
            lessonsArrayList = new ArrayList<>();
            questionsArrayList = new ArrayList<>();
            lessonsArrayList = learnDao.getlessonsWithId(topicId, lessionId);
            questionsArrayList = learnDao.getQuestionsWithId(lessionId);
            lessionName = learnDao.lessionName(topicId);
            nextlessionId = lessonsArrayList.get(0).getLesson_id();
            Log.d("cuID", "" + nextlessionId);
            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            setValue();
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }

    }

    private class AsyncTaskQuestionDelete extends AsyncTask<String, String, Boolean> {

        String topicId;
        String lessionId;
        int tId;
        FragmentActivity fragment;


        public AsyncTaskQuestionDelete(FragmentActivity activity, String topicId, String lessionId) {

            this.fragment = activity;
            this.lessionId = lessionId;
            this.topicId = topicId;
        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            questionDao = pitchRoomDatabase.questionDao();
            int questionid = questionDao.deleteQuestionStatus(topicId, lessionId);

            System.out.println("deletedquestionid" + questionid);


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private class AsyncTaskRunnerResult extends AsyncTask<String, String, Boolean> {

        FragmentActivity fragment;
        LearnResponse.questions questions;


        public AsyncTaskRunnerResult(FragmentActivity activity, LearnResponse.questions questions, int id) {

            this.fragment = activity;
            this.questions = questions;

        }


        @Override
        protected Boolean doInBackground(String... strings) {


            QuestionStatus questionStatus = new QuestionStatus();
            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            questionDao = pitchRoomDatabase.questionDao();
            questionStatus.setQuestion_id(questions.getQuestion_id());
            questionStatus.setLesson_id(questions.getLesson_id());
            questionStatus.setTopic_id(topicId);
            questionStatus.setScore("1");
            questionStatus.setStatus("done");
            Long questionid = questionDao.insertQuestion(questionStatus);
            System.out.println("questionid" + questionid);


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private class AsyncTaskQuestionResult extends AsyncTask<String, String, Boolean> {


        FragmentActivity fragment;

        public AsyncTaskQuestionResult(FragmentActivity activity) {

            this.fragment = activity;


        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            questionDao = pitchRoomDatabase.questionDao();
            count = questionDao.getNumberOfRows(lessionId, topicId);

            System.out.println("count>>>> " + count);


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();

            callServices();
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private class AsyncNextRunner extends AsyncTask<String, String, Boolean> {

        FragmentActivity fragment;


        public AsyncNextRunner(FragmentActivity activity) {

            this.fragment = activity;


        }


        @Override
        protected Boolean doInBackground(String... strings) {

            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            learnDao = pitchRoomDatabase.learnDao();
            lessonsArrayList = new ArrayList<>();
            questionsArrayList = new ArrayList<>();
            lessonsArrayList = learnDao.getNextlessons(nextlessionId, topicId);
            questionsArrayList = learnDao.getNextquestion(nextlessionId);
            lessionName = learnDao.lessionName(topicId);
            if (!ObjectUtils.isEmpty(lessonsArrayList)) {
                nextlessionId = lessonsArrayList.get(0).getLesson_id();

                Log.d("NxID", "" + nextlessionId);
            }




            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            setValue();
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }

    }

    private class AsyncPreviousRunner extends AsyncTask<String, String, Boolean> {

        FragmentActivity fragment;


        public AsyncPreviousRunner(FragmentActivity activity) {

            this.fragment = activity;


        }


        @Override
        protected Boolean doInBackground(String... strings) {

            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            learnDao = pitchRoomDatabase.learnDao();
            lessonsArrayList = new ArrayList<>();
            questionsArrayList = new ArrayList<>();
            lessonsArrayList = learnDao.getPreviouslessons(nextlessionId, topicId);
            questionsArrayList = learnDao.getPreviousquestion(nextlessionId);
            lessionName = learnDao.lessionName(topicId);

            if(!ObjectUtils.isEmpty(lessonsArrayList))
            {
                nextlessionId = lessonsArrayList.get(0).getLesson_id();
                Log.d("prID", "" + nextlessionId);

            }

            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            setValue();
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }

    }
}