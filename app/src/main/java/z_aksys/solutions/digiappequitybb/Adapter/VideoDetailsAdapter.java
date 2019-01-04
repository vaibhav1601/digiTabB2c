package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static android.content.ContentValues.TAG;
import static z_aksys.solutions.digiappequitybb.utils.Constants.VideoKEY;

public class VideoDetailsAdapter extends RecyclerView.Adapter<VideoDetailsAdapter.MyViewHolder> {


    private static int currentPosition = 0;
    private Context mContext;
    private List<ShareVideoResponse.share_video> shareVideoList;
    private OnClickVideo onClickVideo;


    public VideoDetailsAdapter(Context mContext, List<ShareVideoResponse.share_video> shareVideoList, Fragment fragment) {
        this.mContext = mContext;
        this.shareVideoList = shareVideoList;
        this.onClickVideo = ((OnClickVideo) fragment);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_video_details, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ShareVideoResponse.share_video object = shareVideoList.get(position);

        if (ObjectUtils.isNotNull(object)) {
            holder.textViewName.setText(object.getTitle());

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


        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(mContext, R.anim.slide_up);

            //toggling visibility
            // holder.relativeLayout.setVisibility(View.VISIBLE);
            // holder.textViewName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);

            // holder.textViewName.setBackgroundColor(App.getContext().getResources().getColor(R.color.button_color));

            //adding sliding effect
            // holder.relativeLayout.startAnimation(slideDown);
        } else {

            Animation slideDown = AnimationUtils.loadAnimation(mContext, R.anim.bottom_slide_down);
            // holder.relativeLayout.setVisibility(View.GONE);
            //holder.textViewName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);

            // holder.textViewName.setBackgroundColor(App.getContext().getResources().getColor(R.color.button_color));

            // holder.relativeLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });


        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickVideo.VideoId(object.getYoutube_id(), object.getTopic_id());
            }
        });


    }

    @Override
    public int getItemCount() {
        return shareVideoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle, videoDuration;
        public ImageView videoPlay, shareVideo, thumbnail;
        public TextView textViewName, txtAns;
        public ImageView imageView;
        // public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);

            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.txtAns = (TextView) itemView.findViewById(R.id.txt_ans);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            // this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            this.videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            this.videoTitle = itemView.findViewById(R.id.title);
            this.videoPlay = itemView.findViewById(R.id.video_duration_label);
            this.shareVideo = itemView.findViewById(R.id.shareVideo);
            this.videoDuration = itemView.findViewById(R.id.count);
            this.thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }
}