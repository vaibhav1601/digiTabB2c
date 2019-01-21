package z_aksys.solutions.digiappequitybb.Response;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsResponse {


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
    @SerializedName("news")
    private ArrayList<News> news;

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Entity(tableName = "News")
    public static class News {
        @PrimaryKey(autoGenerate = false)
        @NonNull
        @SerializedName("news_id")
        private String news_id;
        @SerializedName("title")
        private String title;
        @SerializedName("publish_date")
        private String publish_date;
        @SerializedName("description")
        private String description;
        @SerializedName("publish")
        private String publish;
        @SerializedName("type")
        private String type;
        @SerializedName("image")
        private String image;
        @SerializedName("youtube_id")
        private String youtube_id;
        @SerializedName("source")
        private String source;
        @SerializedName("source_link")
        private String source_link;


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getYoutube_id() {
            return youtube_id;
        }

        public void setYoutube_id(String youtube_id) {
            this.youtube_id = youtube_id;
        }


        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublish_date() {
            return publish_date;
        }

        public void setPublish_date(String publish_date) {
            this.publish_date = publish_date;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSource_link() {
            return source_link;
        }

        public void setSource_link(String source_link) {
            this.source_link = source_link;
        }


        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

}
