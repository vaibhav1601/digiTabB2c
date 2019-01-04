package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.Activity.PitchActivity;
import z_aksys.solutions.digiappequitybb.Database.PitchData;
import z_aksys.solutions.digiappequitybb.Fragment.SlideIndexFragment;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.model.PitchSlide;

public class SlideIndexAdapter extends RecyclerView.Adapter<SlideIndexAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<PitchSlide> slides;
    private ArrayList<PitchSlide> filteredSlides;
    private SlideIndexFragment mSlideIndexFragment;
    private int currentlySelectedSlide;
    private int currentlySelectedCategory;
    private int currentlySelectedLanguage = PitchData.LANG_ENG;

    public SlideIndexAdapter(Context context, ArrayList<PitchSlide> slides, SlideIndexFragment slideIndexFragment, int currentlySelectedSlide) {
        this.mContext = context;
        this.slides = slides;
        this.filteredSlides = slides;
        this.mSlideIndexFragment = slideIndexFragment;
        this.currentlySelectedSlide = currentlySelectedSlide;

        try {
            this.currentlySelectedCategory = slides.get(currentlySelectedSlide).category;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCurrentlySelectedSlide(int currentlySelectedSlide) {
        this.currentlySelectedSlide = currentlySelectedSlide;
        notifyDataSetChanged();
    }

    public void updateSlides(ArrayList<PitchSlide> slides) {
        this.slides = slides;
        notifyDataSetChanged();
    }

    public void updateCurrentlySelectedCategory(int currentlySelectedCategory) {
        this.currentlySelectedCategory = currentlySelectedCategory;
        this.filteredSlides = filterSlides(currentlySelectedCategory, this.slides);
        notifyDataSetChanged();
        mSlideIndexFragment.tvNumberOfSlides.setText("Slides (" + filteredSlides.size() + ")");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_slide_navigation_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvSlideTitle.setText(filteredSlides.get(position).title.get(currentlySelectedLanguage));

        if (filteredSlides.get(position).id == getSlideIdByPosition(currentlySelectedSlide)) {
            holder.tvSlideTitle.setBackgroundColor(mContext.getResources().getColor(R.color.blue_color));
        } else {
            holder.tvSlideTitle.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        }

        if (filteredSlides.get(position).slideThumbnailPath != null
                && !filteredSlides.get(position).slideThumbnailPath.isEmpty()) {
            holder.ivSlideThumbnail.setImageBitmap(getBitmapFromAssets(filteredSlides.get(position).slideThumbnailPath));
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slideIndex = getPositionOfSlide(filteredSlides.get(position));
                ((PitchActivity) mContext).onSlideSelectecOnNavigationMenu(slideIndex);
                currentlySelectedSlide = slideIndex;
                notifyDataSetChanged();
            }
        });


    }

    // Custom method to get assets folder image as bitmap
    private Bitmap getBitmapFromAssets(String fileName) {
        /*
            AssetManager
                Provides access to an application's raw asset files.
        */

        /*
            public final AssetManager getAssets ()
                Retrieve underlying AssetManager storage for these resources.
        */
        AssetManager am = mContext.getAssets();
        InputStream is = null;
        try {
            /*
                public final InputStream open (String fileName)
                    Open an asset using ACCESS_STREAMING mode. This provides access to files that
                    have been bundled with an application as assets -- that is,
                    files placed in to the "assets" directory.

                    Parameters
                        fileName : The name of the asset to open. This name can be hierarchical.
                    Throws
                        IOException
            */
            is = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
            BitmapFactory
                Creates Bitmap objects from various sources, including files, streams, and byte-arrays.
        */

        /*
            public static Bitmap decodeStream (InputStream is)
                Decode an input stream into a bitmap. If the input stream is null, or cannot
                be used to decode a bitmap, the function returns null. The stream's
                position will be where ever it was after the encoded data was read.

                Parameters
                    is : The input stream that holds the raw data to be decoded into a bitmap.
                Returns
                    The decoded bitmap, or null if the image data could not be decoded.
        */
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }

    private int getPositionOfSlide(PitchSlide pitchSlide) {
        int position = -1;

        for (PitchSlide currentSlide : slides) {
            position++;
            if (currentSlide.id == pitchSlide.id) {
                return position;
            }
        }

        return -1;
    }

    private int getSlideIdByPosition(int position) {
        return slides.get(position).id;
    }

    public void updateSelectedLanguage(int selectedLanguage) {
        this.currentlySelectedLanguage = selectedLanguage;
        notifyDataSetChanged();
    }

    private ArrayList<PitchSlide> filterSlides(int categoryId, ArrayList<PitchSlide> originalList) {
        ArrayList<PitchSlide> filterList = new ArrayList<>();
        for (PitchSlide currentPitchSlide : originalList) {
            if (currentPitchSlide.category == categoryId) {
                filterList.add(currentPitchSlide);
            }
        }

        return filterList;
    }

    @Override
    public int getItemCount() {
        return filteredSlides.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSlideTitle;
        View container;
        ImageView ivSlideThumbnail;

        public ViewHolder(View view) {
            super(view);
            container = view;
            this.tvSlideTitle = view.findViewById(R.id.tv_slide_title);
            this.ivSlideThumbnail = view.findViewById(R.id.iv_slide_thumbnail);
        }
    }
}