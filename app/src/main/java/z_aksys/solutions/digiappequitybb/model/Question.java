package z_aksys.solutions.digiappequitybb.model;

import java.util.Arrays;


public class Question {
    private String question;
    private String[] answerText;
    private Boolean[] correctAnswer;


    private Boolean[] checked = new Boolean[4];

    public Question(String question, String[] answerText, Boolean[] correctAnswer) {
        this.question = question;
        this.answerText = answerText.clone();
        this.correctAnswer = correctAnswer.clone();
        this.resetChecked();
    }

    public void setChecked(int number, boolean state) {
        if (number >= 0 && number <= 3) {
            checked[number] = state;
            System.out.println("checkednumber" + (checked[number] = state));
        }
    }

    public void resetChecked() {
        Arrays.fill(checked, false);
    }

    public Boolean isChecked(int number) {
        return checked[number];
    }

    public Boolean isCorrectAnswer(int number) {
        return correctAnswer[number];
    }

    public Boolean[] getUserAnswers() {

        return checked;

    }

    public Boolean[] getAllCorrectAnswers() {
        return correctAnswer;
    }

    public String[] getAllAnswersText() {
        return answerText;
    }

    public String getQuestionText() {
        return question;
    }

}
