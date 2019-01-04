package z_aksys.solutions.digiappequitybb.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.AngelSharedPrefance;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

public class MyServerData {
    private static MyServerData myInstance = null;
    private static LinkedHashMap<String, Object> myCategories;
    //test states: notStarted, inProgress, finished
    private static String testState = "notStarted";
    private int totalQuestions;
    private String questionArray;
    private AngelSharedPrefance sharedPrefManager;
    private List<LearnResponse.questions> questionsArrayList;


    public MyServerData() {


        sharedPrefManager = new AngelSharedPrefance(App.getContext());
        questionArray = "";
        questionArray = sharedPrefManager.getHealthAPI("Questions");


        if (!TextUtils.isEmpty(questionArray)) {
            questionsArrayList = new ArrayList<>();
            questionsArrayList = AngelPitchUtil.deSerializeResponseList(questionArray, LearnResponse.questions.class);

        }
        //initialize questions from server, shared preferences, files etc.
        //questions are hardcoded here for testing

        if (!ObjectUtils.isEmpty(questionsArrayList)) {
            Question[] FirstCategory = new Question[questionsArrayList.size()];
            Question[] SecondCategory = new Question[questionsArrayList.size()];
            String q = "";
            String[] a = new String[4];
            String aa;
            Boolean[] r = new Boolean[4];

            for (int i = 0; i < questionsArrayList.size(); i++) {
                q = questionsArrayList.get(i).getTitle();
                a[0] = questionsArrayList.get(i).getOption_a();
                a[1] = questionsArrayList.get(i).getOption_b();
                a[2] = questionsArrayList.get(i).getOption_c();
                a[3] = questionsArrayList.get(i).getOption_d();
                aa = questionsArrayList.get(i).getAnswer();

                if (aa.equalsIgnoreCase("option_a")) {
                    r[0] = true;
                } else {
                    r[0] = false;

                }

                if (aa.equalsIgnoreCase("option_b")) {
                    r[1] = true;

                } else {
                    r[1] = false;
                }

                if (aa.equalsIgnoreCase("option_c")) {
                    r[2] = true;

                } else {
                    r[2] = false;
                }

                if (aa.equalsIgnoreCase("option_d")) {
                    r[3] = true;

                } else {
                    r[3] = false;
                }

                Question question = new Question(q, a, r);
                FirstCategory[i] = question;
            }

            myCategories = new LinkedHashMap<String, Object>();
            myCategories.put("FirstCategory", FirstCategory);
            //myCategories.put("SecondCategory", SecondCategory);

            for (String entry : myCategories.keySet()) {
                totalQuestions += ((Question[]) myCategories.get(entry)).length;
            }

        }

    }


    public String getTestState() {
        return testState;
    }

    public void setTestState(String testState) {
        MyServerData.testState = testState;
    }


    public LinkedHashMap<String, Object> getAllQuestions() {
        return myCategories;
    }

    public ArrayList<String> getCategoryList() {


            ArrayList<String> myList = new ArrayList<String>();
            myList.addAll(myCategories.keySet());
            return myList;
    }

    public Question[] getCategory(String category) {
        return (Question[]) myCategories.get(category);
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public Question getQuestion(int questionNr) {

        //return last question if 0
        if (questionNr == 0) {
            String[] keys = myCategories.keySet().toArray(new String[myCategories.size()]);
            String theLastKey = keys[keys.length - 1];
            Question[] lastCategory = (Question[]) myCategories.get(theLastKey);
            return lastCategory[lastCategory.length - 1];
        }
        //return first question if index is the last +1
        if (questionNr == totalQuestions + 1) {
            String[] keys = myCategories.keySet().toArray(new String[myCategories.size()]);
            String theFirstKey = keys[0];
            Question[] lastCategory = (Question[]) myCategories.get(theFirstKey);
            return ((Question[]) myCategories.entrySet().iterator().next().getValue())[0];
        }
        //return question
        Iterator<Map.Entry<String, Object>> it = myCategories.entrySet().iterator();
        while (it.hasNext()) {
            Question[] category = (Question[]) it.next().getValue();
            if (questionNr <= category.length) {
                return category[questionNr - 1];
            } else {
                questionNr -= category.length;
            }
        }
        //if number is higher or lower than expected -> return first category first question
        return ((Question[]) myCategories.entrySet().iterator().next().getValue())[0];
    }

    public String getQuestionCategory(int questionNr) {
        Iterator<Map.Entry<String, Object>> it = myCategories.entrySet().iterator();
        String name = null;

        //return last category
        if (questionNr == 0) {
            String[] keys = myCategories.keySet().toArray(new String[myCategories.keySet().size()]);
            return keys[keys.length - 1];
        }
        if (questionNr == totalQuestions + 1) {
            String[] keys = myCategories.keySet().toArray(new String[myCategories.keySet().size()]);
            return keys[0];
        }
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            if (questionNr > ((Question[]) entry.getValue()).length) {
                questionNr -= ((Question[]) entry.getValue()).length;
            } else {
                name = entry.getKey().toString();
                break;
            }
        }
        return name;
    }

    public int getFirstQuestionNumberFromCategory(String category) {
        Iterator<Map.Entry<String, Object>> it = myCategories.entrySet().iterator();
        int questionNumber = 1;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!next.getKey().equals(category)) {
                questionNumber += ((Question[]) next.getValue()).length;
            } else {
                break;
            }
        }
        return questionNumber;
    }

    public int getQuestionListNumber(String category, int questionNumber) {
        if (questionNumber >= ((Question[]) myCategories.get(category)).length) {
            return 0;
        }
        questionNumber++;
        Iterator<Map.Entry<String, Object>> it = myCategories.entrySet().iterator();
        while (it.hasNext()) {
            String checkedCategory = it.next().getKey();
            if (checkedCategory == category) {
                return questionNumber;
            } else {
                questionNumber += ((Question[]) myCategories.get(checkedCategory)).length;
            }
        }
        return questionNumber;
    }

    public void clearAnswers() {
        for (String currentKey : myCategories.keySet()) {
            Question[] Category = (Question[]) myCategories.get(currentKey);
            for (int i = 0; i < Category.length; i++) {
                Category[i].resetChecked();
            }
        }
    }


}
