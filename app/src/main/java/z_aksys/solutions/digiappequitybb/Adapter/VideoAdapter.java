package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static android.content.ContentValues.TAG;
import static z_aksys.solutions.digiappequitybb.utils.Constants.VideoKEY;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<ShareVideoResponse.share_video> shareVideoArrayList;
    private OnClickVideo onClickVideo;


    public VideoAdapter(Context mContext, ArrayList<ShareVideoResponse.share_video> shareVideoArrayList, Fragment fragment) {
        this.mContext = mContext;
        this.shareVideoArrayList = shareVideoArrayList;
        this.onClickVideo = ((OnClickVideo) fragment);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_video, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ShareVideoResponse.share_video object = shareVideoArrayList.get(position);

        if (ObjectUtils.isNotNull(object)) {
            holder.videoTitle.setText(object.getTitle());
            holder.videoDuration.setText("www.youtube.com");


            String url = "https://www.youtube.com/watch?v=" + object.getYoutube_id();

            Glide.with(mContext)
                    .load(object.getThumbnail())
                    .placeholder(R.drawable.angel_logo)
                    .into(holder.thumbnail);


            holder.videoThumbnailImageView.initialize(VideoKEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    //when initialization is sucess, set the video id to thumbnail to load
                    youTubeThumbnailLoader.setVideo(object.getYoutube_id());

                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                        @Override
                        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                            //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                            youTubeThumbnailLoader.release();
                        }

                        @Override
                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                            //print or show error when thumbnail load failed
                            Log.e(TAG, "Youtube Thumbnail Error");
                        }
                    });
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    //print or show error when initialization failed
                    Log.e(TAG, "Youtube Initialization Failure");

                }
            });

            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickVideo.VideoId(object.getYoutube_id(), object.getVideo_id());
                }
            });


            holder.shareVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AngelPitchUtil.ShowDialog(mContext, object.getVideo_id(), "video", "https://www.youtube.com/watch?v=" + object.getYoutube_id());
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
        return shareVideoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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

        }
    }
}
