package z_aksys.solutions.digiappequitybb.model;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class Model {


    public static final int IMAGE_TYPE = 0;
    public static final int YOUTUBE_TYPE = 1;


    public int type;
    public int data;
    public String text;
    private String videoId, title, duration;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
