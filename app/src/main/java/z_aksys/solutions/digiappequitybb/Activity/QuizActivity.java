package z_aksys.solutions.digiappequitybb.Activity;

import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Adapter.QuestionPagerAdapter;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Dao.QuestionModelDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Request.QuestionRequest;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.Response.QuestionResponse;
import z_aksys.solutions.digiappequitybb.custmization.CircularSeekBar;
import z_aksys.solutions.digiappequitybb.model.MyServerData;
import z_aksys.solutions.digiappequitybb.model.Question;
import z_aksys.solutions.digiappequitybb.model.QuestionStatusModel;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.AngelSharedPrefance;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.App.getContext;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;

public class QuizActivity extends AppCompatActivity {
    public PitchRoomDatabase pitchRoomDatabase;
    public QuestionModelDao questionModelDao;
    Spinner spinCategory;
    // TextView questionNr;
    ArrayList<String> allCategories;
    int totalQuestions;
    AlertDialog dialog;
    ViewPager pager;
    QuestionPagerAdapter pagerAdapter;
    MyServerData myServerData;
    AngelSharedPrefance angelSharedPrefance;
    String lessionName;
    private ProgressDialog progressDialog;
    private String lessionId, topicId;
    private PitchServices pitchServices;
    private String questionArray;
    private AngelSharedPrefance sharedPrefManager;
    private List<LearnResponse.questions> questionsArrayList;
    private Button buttonsubmit;
    private TextView txtquestions;
    private String topicName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_view_pager);
        angelSharedPrefance = new AngelSharedPrefance(getContext());
        pitchServices = RetrofitClient.getInstance().getApi();
        sharedPrefManager = new AngelSharedPrefance(getContext());
        topicName = sharedPrefManager.getHealthAPI("topicName");
        questionArray = sharedPrefManager.getHealthAPI("Questions");
        buttonsubmit = (Button) findViewById(R.id.buttonsubmit);
        txtquestions = (TextView) findViewById(R.id.txtlearnquestion);


        myServerData = new MyServerData();

        if (!TextUtils.isEmpty(questionArray)) {
            questionsArrayList = new ArrayList<>();
            questionsArrayList = AngelPitchUtil.deSerializeResponseList(questionArray, LearnResponse.questions.class);

        }


        if (!ObjectUtils.isEmpty(questionsArrayList)) {
            allCategories = new ArrayList<>(myServerData.getCategoryList());
            totalQuestions = myServerData.getTotalQuestions();
            lessionId = getIntent().getStringExtra("lessionId");
            topicId = getIntent().getStringExtra("topicId");


        }

        txtquestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //initialize category spinner

        //set Category spinner callback
      /*  spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentQuestionRealCategory = MyServerData.getInstance().getQuestionCategory(pager.getCurrentItem());
                String selectedCategory = spinCategory.getItemAtPosition(position).toString();
                if (!selectedCategory.equals(currentQuestionRealCategory)) {
                    int firstQuestionNumberFromCategory = MyServerData.getInstance().getFirstQuestionNumberFromCategory(selectedCategory);
                    ViewPager pager = (ViewPager) findViewById(R.id.qPager);
                    pager.setCurrentItem(firstQuestionNumberFromCategory, false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
*/

       /* //initialize number
        questionNr = (TextView) findViewById(R.id.dialog_question_number);
        questionNr.clearFocus();
        //set number callbacks
        questionNr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    v.clearFocus();
                    CharSequence s = questionNr.getText();
                    if (!s.toString().isEmpty()) {
                        Integer questionNumber = Integer.parseInt(s.toString());
                        //avoids out of range indexes
                        if (questionNumber > totalQuestions + 1) {
                            questionNumber = totalQuestions;
                        }
                        if (questionNumber < 0) {
                            questionNumber = 1;
                        }
                        //create looping effect
                        if (questionNumber == totalQuestions + 1) {
                            questionNumber = 1;
                            ((EditText) v).setText("1");
                        }
                        if (questionNumber == 0) {
                            questionNumber = totalQuestions;
                            ((EditText) v).setText(String.valueOf(totalQuestions));
                        }
                        ViewPager pager = (ViewPager) findViewById(R.id.qPager);
                        pager.setCurrentItem(questionNumber, false);
                        questionNr.clearFocus();
                    } else {
                        questionNr.setText("1");
                    }
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return false;
            }
        });*/
        //initialize pager
        pager = (ViewPager) findViewById(R.id.qPager);
        pagerAdapter = new QuestionPagerAdapter(getSupportFragmentManager(), myServerData);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(1, false);

        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer currentQuestion = pager.getCurrentItem();
                pager.setCurrentItem(currentQuestion + 1, false);
                if (currentQuestion <= totalQuestions) {
                    Integer page = currentQuestion + 1;
                    //questionNr.setText(page.toString());
                    // questionNr.clearFocus();
                } else {
                    //questionNr.setText("");
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // imm.hideSoftInputFromWindow(questionNr.getWindowToken(), 0);

                if (totalQuestions == currentQuestion) {
                    buttonsubmit.setText("submit");
                    callResult();

                }
            }
        });



      /*  pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Integer currentQuestion = pager.getCurrentItem();
                //change spinner
                if (!ObjectUtils.isEmpty(questionsArrayList)) {

                    //String currentCategory = myServerData.getQuestionCategory(currentQuestion);
                  //  int categoryPosition = myServerData.getCategoryList().indexOf(currentCategory);
                    // spinCategory.setSelection(categoryPosition);

                    //change numberPicker
                    if (currentQuestion <= 0) {
                        currentQuestion = totalQuestions;
                    }
                    if (currentQuestion > totalQuestions) {
                        currentQuestion = 1;
                    }
                    questionNr.setText(currentQuestion.toString());
                    questionNr.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(questionNr.getWindowToken(), 0);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int totalQuestions = myServerData.getTotalQuestions();
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (pager.getCurrentItem() == totalQuestions + 1) {
                        pager.setCurrentItem(1, false);
                    }
                    if (pager.getCurrentItem() == 0) {
                        pager.setCurrentItem(totalQuestions, false); // false will prevent sliding animation of view pager
                    }
                }
            }
        });*/


    }

    private void callResult() {
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinishTest();
            }
        });


    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rl_lessionviewer,
                fragment,
                fragment.getClass().getSimpleName());

        fragmentTransaction.commitAllowingStateLoss();
    }


    public void FinishTest() {

        showResults();

        /*//check if there are unanswered questions
        if (myServerData.getTestState().equals("inProgress")) {
            ArrayList<String> UnansweredQuestions = new ArrayList<>();
            LinkedHashMap<String, Object> allQuestions = myServerData.getAllQuestions();
            for (Map.Entry category : allQuestions.entrySet()) {
                Question[] questions = (Question[]) category.getValue();
                for (int i = 0; i < questions.length; i++) {


                    System.out.println("question lenth" + questions.length);
                    Boolean[] userAnswers = questions[i].getUserAnswers();
                    if (!Arrays.asList(userAnswers).contains(true)) {
                        String checkedCategory = (String) category.getKey();
                        Integer questionNumberInList = myServerData.getQuestionListNumber(checkedCategory, i);
                        UnansweredQuestions.add(String.valueOf(questionNumberInList));
                    }
                }
            }

            if (UnansweredQuestions.size() > 0) {
                dialog = new AlertDialog.Builder(this)
                        .create();
                LayoutInflater infl = LayoutInflater.from(this);
                dialog.setView(infl.inflate(R.layout.dialog_message, null));
                dialog.show();
                TextView message = (TextView) dialog.findViewById(R.id.message);
                String unfinished = getResources().getString(R.string.unfinished_text);
                String questions = TextUtils.join(",", UnansweredQuestions);
                message.setText(unfinished + "\n" + questions + ".");

                dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showResults();
                    }
                });
            } else {
                showResults();
            }

        } else {
            showResults();
        }
*/

    }

    public void showResults() {
        int animationDuration;
        CircularSeekBar seekbar;
        TextView txt_percent, textView1;
        Button btn_retry, btnlater;
        if (myServerData.getTestState().equals("finished")) {
            animationDuration = 10;
        } else {
            animationDuration = 2000;
        }
        View mainView = LayoutInflater.from(this).inflate(R.layout.quiz_activity_show_results, null);
        LinearLayout mainContainer = (LinearLayout) mainView.findViewById(R.id.mainContainer);
        seekbar = (CircularSeekBar) mainView.findViewById(R.id.progress_circular_mark);
        txt_percent = (TextView) mainView.findViewById(R.id.txt_percent);
        textView1 = (TextView) mainView.findViewById(R.id.textView1);
        btn_retry = (Button) mainView.findViewById(R.id.btnMainMenu);
        btnlater = (Button) mainView.findViewById(R.id.check_results);

        if (!TextUtils.isEmpty(topicName)) {
            textView1.setText(topicName);
        } else {
            textView1.setText(" ");

        }

        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish the test and go to main menu
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                myServerData.setTestState("notStarted");
                myServerData.clearAnswers();

            }
        });


        btnlater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                myServerData.setTestState("notStarted");
                myServerData.clearAnswers();
                Toast.makeText(getBaseContext(), R.string.text_ended, Toast.LENGTH_LONG).show();
            }
        });
        Question[] currentCategory;
        int totalCategoryQuestions;
        int correctCategoryQuestions;
        int totalCorrectQuestions = 0;
        //checking and adding each category
        for (String category : allCategories) {

            lessionName = angelSharedPrefance.getHealthAPI("LessionsName");
            View categoryContainer = LayoutInflater.from(this).inflate(R.layout.quiz_activity_category_results, null);
            TextView categoryName = (TextView) categoryContainer.findViewById(R.id.categoryName);
            //set name
            if (!TextUtils.isEmpty(lessionName)) {
                categoryName.setText(lessionName);
            } else {
                categoryName.setText(category);
            }

            currentCategory = myServerData.getCategory(category);
            totalCategoryQuestions = currentCategory.length;
            //check answers
            correctCategoryQuestions = 0;
            for (int i = 0; i < currentCategory.length; i++) {

                System.out.println("UserChecked" + (Arrays.equals(currentCategory[i].getAllCorrectAnswers(), currentCategory[i].getUserAnswers())));
                Boolean isCorrect = Arrays.equals(currentCategory[i].getAllCorrectAnswers(), currentCategory[i].getUserAnswers());
                if (isCorrect) {
                    correctCategoryQuestions++;
                }
            }
            totalCorrectQuestions += correctCategoryQuestions;

            //set results
            String result = String.valueOf(correctCategoryQuestions) + "/" + String.valueOf(totalCategoryQuestions);
            final ProgressBar progress = (ProgressBar) categoryContainer.findViewById(R.id.progressBar);
            progress.setMax(totalCategoryQuestions * 100);
            seekbar.setMax(totalCategoryQuestions * 100);
            final TextView myResult = (TextView) categoryContainer.findViewById(R.id.categoryResult);
            final String myResultText = "/" + String.valueOf(totalCategoryQuestions);
            txt_percent.setText(result);
            ValueAnimator val = new ValueAnimator();
            val.setObjectValues(0, correctCategoryQuestions * 100);

            seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
            seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.green_color));

            if (String.valueOf(correctCategoryQuestions).equalsIgnoreCase(String.valueOf(totalCategoryQuestions))) {
                btn_retry.setVisibility(View.GONE);
                btnlater.setText("Ok");
            } else {

                btn_retry.setVisibility(View.VISIBLE);


            }

            val.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {


                    myResult.setText(String.valueOf((Integer) animation.getAnimatedValue() / 100) + myResultText);
                    progress.setProgress(((Integer) animation.getAnimatedValue()));
                    seekbar.setProgress(((Integer) animation.getAnimatedValue()));
                }
            });
            val.setDuration(animationDuration);
            val.start();
            if (AngelPitchUtil.checkConnection(getContext())) {
                callServices(String.valueOf(correctCategoryQuestions), String.valueOf(totalCategoryQuestions));
            } else {
                new AsyncTaskRunnerResult(getContext(), String.valueOf(correctCategoryQuestions), String.valueOf(totalCategoryQuestions)).execute();

            }


            mainContainer.addView(categoryContainer);
        }

        //animate results
        final TextView tvTotalResult = (TextView) mainView.findViewById(R.id.myTotalAnswers);

        final AppCompatTextView txt = (AppCompatTextView) mainView.findViewById(R.id.txtquestions);
        final String totalResultS = "/" + String.valueOf(totalQuestions);

        ValueAnimator totalResultsAnimator = new ValueAnimator();
        totalResultsAnimator.setObjectValues(0, totalCorrectQuestions);
        totalResultsAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tvTotalResult.setText(String.valueOf(animation.getAnimatedValue()) + totalResultS);
            }
        });
        totalResultsAnimator.setDuration(animationDuration);
        totalResultsAnimator.start();

        myServerData.setTestState("finished");

        setContentView(mainView);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void callServices(String myResultText, String s) {

//        showProgress();
        List<QuestionRequest> questionRequestList = new ArrayList<>();
        QuestionRequest questionRequest = new QuestionRequest();
        int tId = Integer.parseInt(topicId);
        int lId = Integer.parseInt(lessionId);
        questionRequest.setEmp_no("test");
        questionRequest.setTopic_id(tId);
        questionRequest.setLesson_id(lId);
        String mark = String.valueOf(myResultText);

        System.out.println("count in service" + myResultText);

        if (myResultText.contentEquals(s)) {

            questionRequest.setIs_completed(1);
            questionRequest.setScore(myResultText + "/" + s);

            System.out.println("states complete " + myResultText);
        } else {

            questionRequest.setIs_completed(2);
            System.out.println("states incomplete " + myResultText);
            questionRequest.setScore(myResultText + "/" + s);

        }

        questionRequestList.add(questionRequest);


        Call<QuestionResponse> myResponsecall = pitchServices.postAllQuestion("application/json", appKey, appSec, questionRequestList);

        myResponsecall.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {

                if (response.isSuccessful()) {


                    Toast.makeText(getContext(), " Answer submitted successfully", Toast.LENGTH_LONG).show();
                    // hideProgress();
                    //Toast.makeText(getContext(), "Fail" + response.message(), Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                //hideProgress();

            }
        });


    }


    public void showProgress() {
        if ((progressDialog == null || (progressDialog != null && progressDialog.isShowing() == false))) {
            progressDialog = ProgressDialog.show(getContext(), "", "Please wait.", true);
        }
    }

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        } else {
            return;
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            super.onBackPressed(); //replaced
        }
    }

    private class AsyncTaskRunnerResult extends AsyncTask<String, String, Boolean> {

        Context context;
        String obtaindmark;
        String outOfMark;


        public AsyncTaskRunnerResult(Context context, String s, String s1) {

            this.context = context;
            this.obtaindmark = s;
            this.outOfMark = s1;

        }


        @Override
        protected Boolean doInBackground(String... strings) {

            int tId = Integer.parseInt(topicId);
            int lId = Integer.parseInt(lessionId);


            QuestionStatusModel questionStatusModel = new QuestionStatusModel();
            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            questionModelDao = pitchRoomDatabase.questionModelDao();
            questionStatusModel.setEmp_number("test");
            questionStatusModel.setLesson_id(lessionId);
            questionStatusModel.setTopic_id(topicId);
            questionStatusModel.setScore(obtaindmark + "/" + outOfMark);

            if (obtaindmark.contentEquals(outOfMark)) {
                questionStatusModel.setStatus("1");
            } else {
                questionStatusModel.setStatus("2");
            }

            Long questionid = questionModelDao.insertQuestion(questionStatusModel);
            System.out.println("offline question>>id" + questionid);


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
}

