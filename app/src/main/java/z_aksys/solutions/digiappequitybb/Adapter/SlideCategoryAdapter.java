package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import z_aksys.solutions.digiappequitybb.R;

public class SlideCategoryAdapter extends RecyclerView.Adapter<SlideCategoryAdapter.ViewHolder> {

    private Context mContext;
    private HashMap<Integer, String> mSlideCategories;
    private SlideIndexAdapter mSlideIndexAdapter;
    private int currentlySelectedCategory;

    public SlideCategoryAdapter(Context mContext, HashMap<Integer, String> slideCategories, SlideIndexAdapter slideIndexAdapter, int currentlySelectedCategory) {
        this.mContext = mContext;
        this.mSlideCategories = slideCategories;
        this.mSlideIndexAdapter = slideIndexAdapter;
        this.currentlySelectedCategory = currentlySelectedCategory;
    }

    public void updateSlideCategories(HashMap<Integer, String> categories) {
        this.mSlideCategories = categories;
        notifyDataSetChanged();
    }

    public void updateCurrentlySelectedCategory(int currentlySelectedSlide) {
        this.currentlySelectedCategory = currentlySelectedSlide;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_slide_category_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvCategoryName.setText(mSlideCategories.get(position));

        if (position == currentlySelectedCategory) {
            holder.tvCategoryName.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_800));
        } else {
            holder.tvCategoryName.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideIndexAdapter.updateCurrentlySelectedCategory(position);
                currentlySelectedCategory = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSlideCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategoryName;
        View container;

        public ViewHolder(View view) {
            super(view);
            container = view;
            this.tvCategoryName = view.findViewById(R.id.tv_category_name);
        }
    }
}