package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaqResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("page")
    private String page;
    @SerializedName("limit")
    private String limit;
    @SerializedName("hasNext")
    private int hasNext;
    @SerializedName("faq")
    private ArrayList<faq> faqs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public ArrayList<faq> getFaqs() {
        return faqs;
    }

    public void setFaqs(ArrayList<faq> faqs) {
        this.faqs = faqs;
    }

    public static class faq {

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
    }


}
