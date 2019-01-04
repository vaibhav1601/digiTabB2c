package z_aksys.solutions.digiappequitybb.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "QuestionStatusModel")
public class QuestionStatusModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int question_id;
    private String emp_number;
    private String topic_id;
    private String lesson_id;
    private String score;
    private String status;


    @NonNull
    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(@NonNull int question_id) {
        this.question_id = question_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }
}
