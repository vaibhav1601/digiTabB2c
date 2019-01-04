package z_aksys.solutions.digiappequitybb.ViewModel;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import z_aksys.solutions.digiappequitybb.Adapter.MultiViewTypeAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.NewsResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.model.Model;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static android.content.ContentValues.TAG;
import static z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil.getVideoId;
import static z_aksys.solutions.digiappequitybb.utils.Constants.IMAGE_TYPE;
import static z_aksys.solutions.digiappequitybb.utils.Constants.VideoKEY;
import static z_aksys.solutions.digiappequitybb.utils.Constants.YOUTUBE_TYPE;

public class ItemAdapter extends PagedListAdapter<NewsResponse.News, RecyclerView.ViewHolder> {

    private static DiffUtil.ItemCallback<NewsResponse.News> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NewsResponse.News>() {
                @Override
                public boolean areItemsTheSame(NewsResponse.News oldItem, NewsResponse.News newItem) {
                    return oldItem.getNews_id() == newItem.getNews_id();
                }

                @Override
                public boolean areContentsTheSame(NewsResponse.News oldItem, NewsResponse.News newItem) {
                    return oldItem.equals(newItem);
                }
            };
    int total_types;
    MediaPlayer mPlayer;
    private Context mCtx;
    private List<NewsResponse.News> newsResponseList;
    private boolean fabStateVolume = false;
    private OnClickVideo onClickVideo;
    private String[] arrOfStr;
    private String newsurl, title;
    private String videoId;


    public ItemAdapter(Context mCtx, List<NewsResponse.News> newsResponseList, Observer<PagedList<NewsResponse.News>> fragment) {
        super(DIFF_CALLBACK);
        this.newsResponseList = newsResponseList;
        // total_types = newsResponseList.size();
        this.onClickVideo = ((OnClickVideo) fragment);
        this.mCtx = mCtx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {

            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
                return new MultiViewTypeAdapter.ImageTypeViewHolder(view);

            case Model.YOUTUBE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtubelayout, parent, false);
                return new MultiViewTypeAdapter.YouTubeTypeViewHolder(view);
        }
        return null;


    }

    @Override
    public int getItemViewType(int position) {

        switch (newsResponseList.get(position).getType()) {
            case "image":
                return IMAGE_TYPE;
            case "video":
                return YOUTUBE_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final NewsResponse.News object = newsResponseList.get(listPosition);
        if (ObjectUtils.isNotNull(object)) {
            switch (object.getType()) {

                case "image":
                    ((MultiViewTypeAdapter.ImageTypeViewHolder) holder).title.setText(object.getTitle());
                    ((MultiViewTypeAdapter.ImageTypeViewHolder) holder).count.setText(object.getSource_link());

                    Glide.with(mCtx).load(object.getImage()).into(((MultiViewTypeAdapter.ImageTypeViewHolder) holder).thumbnail);

                    newsurl = object.getSource_link();
                    title = object.getTitle();

                    ((MultiViewTypeAdapter.ImageTypeViewHolder) holder).overflow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showPopupMenu(((MultiViewTypeAdapter.ImageTypeViewHolder) holder).overflow);
                        }
                    });

                    ((MultiViewTypeAdapter.ImageTypeViewHolder) holder).rl_news.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                onClickVideo.newsUrl(object.getSource_link(), object.getNews_id());
                            } catch (ClassCastException exception) {
                                exception.printStackTrace();
                            }

                        }
                    });

                    break;

                case "video":

                    if (!ObjectUtils.isEmpty(object.getSource_link())) {
                        videoId = getVideoId(object.getSource_link());
                        System.out.println("position " + listPosition + "videoID" + videoId);
                    }

                    ((MultiViewTypeAdapter.YouTubeTypeViewHolder) holder).videoTitle.setText(object.getTitle());

                    //((YouTubeTypeViewHolder) holder).count.setText(Html.fromHtml(object.getPublish_date()));
                    ((MultiViewTypeAdapter.YouTubeTypeViewHolder) holder).count.setText(Html.fromHtml("Youtube.com"));

                    Glide.with(mCtx).load(object.getImage()).into(((MultiViewTypeAdapter.YouTubeTypeViewHolder) holder).thumbnail);


                    ((MultiViewTypeAdapter.YouTubeTypeViewHolder) holder).videoThumbnailImageView.initialize(VideoKEY, new YouTubeThumbnailView.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                            //when initialization is sucess, set the video id to thumbnail to load
                            youTubeThumbnailLoader.setVideo(videoId);

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


                    ((MultiViewTypeAdapter.YouTubeTypeViewHolder) holder).videoPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                onClickVideo.VideoId(getVideoId(object.getSource_link()), object.getNews_id());
                            } catch (ClassCastException exception) {
                                exception.printStackTrace();
                            }


                        }
                    });


                    break;

            }
        }

    }

    private void showPopupMenu(ImageView overflow) {

        PopupMenu popup = new PopupMenu(mCtx, overflow);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.news_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {


        public TextView title, count;
        public ImageView thumbnail, overflow;
        public RelativeLayout rl_news;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.count = (TextView) itemView.findViewById(R.id.count);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.overflow = (ImageView) itemView.findViewById(R.id.overflow);
            this.rl_news = (RelativeLayout) itemView.findViewById(R.id.rl_news);


        }

    }

    public static class YouTubeTypeViewHolder extends RecyclerView.ViewHolder {

        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle, videoDuration, count;
        public ImageView videoPlay;
        public ImageView thumbnail;


        public YouTubeTypeViewHolder(View itemView) {
            super(itemView);
            this.videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            this.videoTitle = itemView.findViewById(R.id.title);
            this.videoPlay = itemView.findViewById(R.id.video_duration_label);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.count = itemView.findViewById(R.id.count);

        }


    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, newsurl);
                    mCtx.startActivity(Intent.createChooser(shareIntent, title));
                    return true;

                default:
            }
            return false;
        }
    }


}
