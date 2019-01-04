package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.model.MyServerData;
import z_aksys.solutions.digiappequitybb.model.Question;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.AngelSharedPrefance;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;


public class QuestionFragment extends Fragment {

    LinearLayout answersContainer;
    int currentPageNr;
    int currnetPos;
    MyServerData myServerData;
    private String questionArray;
    private AngelSharedPrefance sharedPrefManager;
    private List<LearnResponse.questions> questionsArrayList;
    private Button b1, b2, b3, b4;


    public QuestionFragment() {

    }

    public QuestionFragment(MyServerData myServerData) {
        this.myServerData = myServerData;
        sharedPrefManager = new AngelSharedPrefance(App.getContext());
        questionArray = "";
        questionArray = sharedPrefManager.getHealthAPI("Questions");
        questionsArrayList = new ArrayList<>();
        questionsArrayList = AngelPitchUtil.deSerializeResponseList(questionArray, LearnResponse.questions.class);


    }

    @Override
    public View onCreateView(LayoutInflater lInflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = null;
        if (!ObjectUtils.isEmpty(questionsArrayList)) {
            currentPageNr = getArguments().getInt("position");

            //initialize some variables
            final Question currentQuestion = myServerData.getQuestion(currentPageNr);
            rootView = lInflater.inflate(R.layout.quiz_activity_fragment, container, false);
            //initialize questionText
            TextView question = (TextView) rootView.findViewById(R.id.questionText);
            question.setMovementMethod(new ScrollingMovementMethod());
            question.setText(currentQuestion.getQuestionText());
            b1 = (Button) rootView.findViewById(R.id.answerA);
            b2 = (Button) rootView.findViewById(R.id.answerB);
            b3 = (Button) rootView.findViewById(R.id.answerC);
            b4 = (Button) rootView.findViewById(R.id.answerD);
            answersContainer = (LinearLayout) rootView.findViewById(R.id.answers_container);
            String[] answers = currentQuestion.getAllAnswersText();

            b1.setText(answers[0]);
            b2.setText(answers[1]);
            b3.setText(answers[2]);
            b4.setText(answers[3]);

          /*  for (int i = 0; i < answersContainer.getChildCount(); i++) {
                RelativeLayout checkboxContainer = (RelativeLayout) answersContainer.getChildAt(i);
                Button cb = (Button) checkboxContainer.getChildAt(0);
                cb.setMovementMethod(new ScrollingMovementMethod());
                cb.setText(answers[i]);
                cb.setSelected(currentQuestion.isChecked(i));*/


               /* if (myServerData.getTestState().equals("finished")) {
                    cb.setEnabled(false);
                    //check if answer is right or wrong
                    if (currentQuestion.isCorrectAnswer(i)) {
                        cb.setTextColor(Color.parseColor("#4DAD47"));
                    } else {
                        cb.setTextColor(Color.parseColor("#CE0B0B"));
                        cb.setTypeface(null, Typeface.BOLD);
                    }
                } else {
                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView cb = ((TextView) v);
                            int number = Integer.parseInt(cb.getHint().toString());

                            // cb.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_done_green_24dp,0);
                            currentQuestion.setChecked(number, cb.isPressed());
                            Log.d("position", "number" + number + "ischecked" + cb.isSelected());
                        }
                    });


                }*/

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentQuestion.resetChecked();
                    TextView cb = ((TextView) v);
                    int number = Integer.parseInt(cb.getHint().toString());
                    currentQuestion.setChecked(number, cb.isPressed());
                    Log.d("position", "number" + number + "ischecked" + cb.isSelected());
                    b1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    b2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentQuestion.resetChecked();
                    TextView cb = ((TextView) v);
                    int number = Integer.parseInt(cb.getHint().toString());
                    currentQuestion.setChecked(number, cb.isPressed());
                    Log.d("position", "number" + number + "ischecked" + cb.isSelected());
                    b1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    b3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                }
            });

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentQuestion.resetChecked();
                    TextView cb = ((TextView) v);
                    int number = Integer.parseInt(cb.getHint().toString());
                    currentQuestion.setChecked(number, cb.isPressed());
                    Log.d("position", "number" + number + "ischecked" + cb.isSelected());
                    b1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    b4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                }
            });

            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentQuestion.resetChecked();
                    TextView cb = ((TextView) v);
                    int number = Integer.parseInt(cb.getHint().toString());
                    currentQuestion.setChecked(number, cb.isPressed());
                    b1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red_24dp, 0);
                    b4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    Log.d("position", "number" + number + "ischecked" + cb.isSelected());
                }
            });
        }


        //initialize answers
        return rootView;


    }


}
