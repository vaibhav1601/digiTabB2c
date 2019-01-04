package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaqDetailsResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("start")
    private String start;
    @SerializedName("limit")
    private String limit;
    @SerializedName("hasNext")
    private int hasNext;
    @SerializedName("faq_detail")
    private ArrayList<faq_detail> faq_details;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public ArrayList<faq_detail> getFaq_details() {
        return faq_details;
    }

    public void setFaq_details(ArrayList<faq_detail> faq_details) {
        this.faq_details = faq_details;
    }


    public static class faq_detail {
        @SerializedName("topic_id")
        private String topic_id;
        @SerializedName("name")
        private String name;
        @SerializedName("image")
        private String image;
        @SerializedName("sort_order")
        private String sort_order;
        @SerializedName("publish")
        private String publish;
        @SerializedName("faq_id")
        private String faq_id;
        @SerializedName("question")
        private String question;
        @SerializedName("answer")
        private String answer;


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

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
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
