package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShareVideoTopicResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("share_video_topic")
    private ArrayList<share_video_topic> share_video_topics;

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

    public ArrayList<share_video_topic> getShare_video_topics() {
        return share_video_topics;
    }

    public void setShare_video_topics(ArrayList<share_video_topic> share_video_topics) {
        this.share_video_topics = share_video_topics;
    }

    public static class share_video_topic {

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
