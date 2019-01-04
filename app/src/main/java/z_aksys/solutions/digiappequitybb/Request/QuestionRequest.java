package z_aksys.solutions.digiappequitybb.Request;

import com.google.gson.annotations.SerializedName;

public class QuestionRequest {

    @SerializedName("emp_no")
    private String emp_no;
    @SerializedName("topic_id")
    private int topic_id;
    @SerializedName("lesson_id")
    private int lesson_id;
    @SerializedName("is_completed")
    private int is_completed;
    @SerializedName("score")
    private String score;

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(int is_completed) {
        this.is_completed = is_completed;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }
}
