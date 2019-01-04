package z_aksys.solutions.digiappequitybb.Response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class LearnResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("total_learn_score")
    private String total_learn_score;
    @SerializedName("topics")
    private ArrayList<topics> topics;
    @SerializedName("lessons")
    private ArrayList<lessons> lessons;
    @SerializedName("questions")
    private ArrayList<questions> questions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getTotal_learn_score() {
        return total_learn_score;
    }

    public void setTotal_learn_score(String total_learn_score) {
        this.total_learn_score = total_learn_score;
    }

    public ArrayList<topics> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<topics> topics) {
        this.topics = topics;
    }

    public ArrayList<lessons> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<lessons> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<questions> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<questions> questions) {
        this.questions = questions;
    }


    @Entity(tableName = "topics")
    public static class topics {
        @PrimaryKey(autoGenerate = false)
        @NonNull
        @SerializedName("topic_id")
        private String topic_id;
        @SerializedName("name")
        private String name;
        @SerializedName("image")
        private String image;
        @SerializedName("description")
        private String description;
        @SerializedName("publish")
        private String publish;
        @SerializedName("sort_order")
        private String sort_order;
        @SerializedName("total_lessons")
        private String total_lessons;
        @SerializedName("completed_lessons")
        private String completed_lessons;

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public String getTotal_lessons() {
            return total_lessons;
        }

        public void setTotal_lessons(String total_lessons) {
            this.total_lessons = total_lessons;
        }

        public String getCompleted_lessons() {
            return completed_lessons;
        }

        public void setCompleted_lessons(String completed_lessons) {
            this.completed_lessons = completed_lessons;
        }
    }

    @Entity(tableName = "lessons")
    public static class lessons {
        @PrimaryKey(autoGenerate = false)
        @NonNull
        @SerializedName("lesson_id")
        private String lesson_id;
        @SerializedName("topic_id")
        private String topic_id;
        @SerializedName("name")
        private String name;
        @SerializedName("image")
        private String image;
        @SerializedName("description")
        private String description;
        @SerializedName("publish")
        private String publish;
        @SerializedName("is_completed")
        private String is_completed;


        public String getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(String lesson_id) {
            this.lesson_id = lesson_id;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getIs_completed() {
            return is_completed;
        }

        public void setIs_completed(String is_completed) {
            this.is_completed = is_completed;
        }
    }

    @Entity(tableName = "questions")
    public static class questions {
        @PrimaryKey(autoGenerate = false)
        @NonNull
        @SerializedName("question_id")
        public String question_id;
        @SerializedName("lesson_id")
        public String lesson_id;
        @SerializedName("title")
        public String title;
        @SerializedName("option_a")
        public String option_a;
        @SerializedName("option_b")
        public String option_b;
        @SerializedName("option_c")
        public String option_c;
        @SerializedName("option_d")
        public String option_d;
        @SerializedName("answer")
        public String answer;
        @SerializedName("answer_desc")
        public String answer_desc;
        @SerializedName("publish")
        public String publish;
        @SerializedName("sort_order")
        public String sort_order;


        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(String lesson_id) {
            this.lesson_id = lesson_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOption_a() {
            return option_a;
        }

        public void setOption_a(String option_a) {
            this.option_a = option_a;
        }

        public String getOption_b() {
            return option_b;
        }

        public void setOption_b(String option_b) {
            this.option_b = option_b;
        }

        public String getOption_c() {
            return option_c;
        }

        public void setOption_c(String option_c) {
            this.option_c = option_c;
        }

        public String getOption_d() {
            return option_d;
        }

        public void setOption_d(String option_d) {
            this.option_d = option_d;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getAnswer_desc() {
            return answer_desc;
        }

        public void setAnswer_desc(String answer_desc) {
            this.answer_desc = answer_desc;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }
    }


}
