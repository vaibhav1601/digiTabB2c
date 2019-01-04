package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaqSearchResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("faq_search")
    private ArrayList<faq_search> faq_searches;


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

    public ArrayList<faq_search> getFaq_searches() {
        return faq_searches;
    }

    public void setFaq_searches(ArrayList<faq_search> faq_searches) {
        this.faq_searches = faq_searches;
    }

    public static class faq_search {

        @SerializedName("faq_id")
        private String faq_id;
        @SerializedName("topic_id")
        private String topic_id;
        @SerializedName("question")
        private String question;
        @SerializedName("answer")
        private String answer;
        @SerializedName("publish")
        private String publish;


        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getFaq_id() {
            return faq_id;
        }

        public void setFaq_id(String faq_id) {
            this.faq_id = faq_id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
