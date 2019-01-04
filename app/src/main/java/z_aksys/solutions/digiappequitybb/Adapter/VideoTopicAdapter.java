package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoTopicResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

public class VideoTopicAdapter extends RecyclerView.Adapter<VideoTopicAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<ShareVideoTopicResponse.share_video_topic> share_video_topics;
    private OnClickVideo onClickVideo;


    public VideoTopicAdapter(Context mContext, ArrayList<ShareVideoTopicResponse.share_video_topic> share_video_topics, Fragment fragment) {
        this.mContext = mContext;
        this.share_video_topics = share_video_topics;
        this.onClickVideo = ((OnClickVideo) fragment);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_video_topic, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ShareVideoTopicResponse.share_video_topic object = share_video_topics.get(position);


        if (ObjectUtils.isNotNull(object)) {
            holder.videoTitle.setText(object.getName());
            holder.videoDuration.setText("www.youtube.com");
            // String url = "https://www.youtube.com/watch?v=" + object.getYoutube_id();
            Glide.with(mContext)
                    .load(object.getImage())
                    .placeholder(R.drawable.angel_logo)
                    .into(holder.thumbnail);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onClickVideo.VideoId(null, object.getTopic_id());
                    // AngelPitchUtil.ShowDialog(mContext, object.getVideo_id(), "video", "https://www.youtube.com/watch?v=" + object.getYoutube_id());
                    /*Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=" + object.getYoutube_id());
                    mContext.startActivity(Intent.createChooser(shareIntent, "Youtube video"));*/
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return share_video_topics == null ? 0 : share_video_topics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle, videoDuration;
        public ImageView videoPlay, shareVideo, thumbnail;


        public MyViewHolder(View view) {
            super(view);

            this.videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            this.videoTitle = itemView.findViewById(R.id.title);
            this.videoPlay = itemView.findViewById(R.id.video_duration_label);
            this.shareVideo = itemView.findViewById(R.id.shareVideo);
            this.videoDuration = itemView.findViewById(R.id.count);
            this.thumbnail = itemView.findViewById(R.id.thumbnail);
            this.cardView = itemView.findViewById(R.id.card_view_videoTopic);

        }
    }
}
