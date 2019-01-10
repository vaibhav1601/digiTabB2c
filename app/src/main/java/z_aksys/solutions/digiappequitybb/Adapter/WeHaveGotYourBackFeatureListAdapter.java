package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.ShareDocumentResponse;
import z_aksys.solutions.digiappequitybb.model.WeHaveGotYourBackFeature;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;

public class WeHaveGotYourBackFeatureListAdapter extends RecyclerView.Adapter<WeHaveGotYourBackFeatureListAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<WeHaveGotYourBackFeature> features;
    private ArrayList<WeHaveGotYourBackFeature> featuresToShow;
    private int mSelectedCategory;
    private int mSelectedSlide;

    public WeHaveGotYourBackFeatureListAdapter(Context mContext, ArrayList<WeHaveGotYourBackFeature> shareDocumentArrayList, int selectedCategory, int selectedSlide) {
        this.mContext = mContext;
        this.features = shareDocumentArrayList;
        this.mSelectedCategory= selectedCategory;
        this.mSelectedSlide= selectedSlide;
        this.featuresToShow= new ArrayList<>();
        filterFeatures(selectedCategory, selectedSlide);
    }

    public void updateSelection(int selectedCategory, int mSelectedSlide){
        filterFeatures(selectedCategory, mSelectedSlide);
        notifyDataSetChanged();
    }

    private void filterFeatures(int selectedCategory, int selectedSlide){
        featuresToShow.clear();
        for (WeHaveGotYourBackFeature weHaveGotYourBackFeature: features){
            if (weHaveGotYourBackFeature.getCategoryId()== selectedCategory
                    && weHaveGotYourBackFeature.getSlideIndex()== selectedSlide){
                featuresToShow.add(weHaveGotYourBackFeature);
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_we_have_got_your_back_feature, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final WeHaveGotYourBackFeature weHaveGotYourBackFeature = featuresToShow.get(position);

        holder.tvFeatureTitle.setText(weHaveGotYourBackFeature.getTitle());
        holder.ivFeatureIcon.setImageResource(weHaveGotYourBackFeature.getImageId());
    }

    @Override
    public int getItemCount() {
        return featuresToShow.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvFeatureTitle;
        ImageView ivFeatureIcon;

        public MyViewHolder(View view) {
            super(view);
            this.tvFeatureTitle = itemView.findViewById(R.id.tv_feature_title);
            this.ivFeatureIcon = itemView.findViewById(R.id.iv_feature_thumbnail);
        }
    }
}