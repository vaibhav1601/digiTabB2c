package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShareVideoResponse {

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
    @SerializedName("share_video")
    private ArrayList<share_video> share_videos;


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

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }


    public ArrayList<share_video> getShare_videos() {
        return share_videos;
    }

    public void setShare_videos(ArrayList<share_video> share_videos) {
        this.share_videos = share_videos;
    }

    public static class share_video {

        @SerializedName("video_id")
        private String video_id;
        @SerializedName("topic_id")
        private String topic_id;
        @SerializedName("title")
        private String title;
        @SerializedName("youtube_id")
        private String youtube_id;
        @SerializedName("sort_order")
        private String sort_order;
        @SerializedName("thumbnail")
        private String thumbnail;


        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYoutube_id() {
            return youtube_id;
        }

        public void setYoutube_id(String youtube_id) {
            this.youtube_id = youtube_id;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }
    }


}
