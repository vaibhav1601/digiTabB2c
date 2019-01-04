package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickLessons;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;


public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.MyViewHolder> {


    public List<LearnResponse.topics> topicsArrayList;
    private Context mContext;
    private OnClickLessons onClickLessons;


    public LearnAdapter(Context mContext, List<LearnResponse.topics> topicsArrayList, Fragment fragment) {
        this.mContext = mContext;
        this.topicsArrayList = topicsArrayList;
        this.onClickLessons = ((OnClickLessons) fragment);
    }

    @Override
    public LearnAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.learn_card_view, parent, false);

        return new LearnAdapter.MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final LearnAdapter.MyViewHolder holder, final int position) {

        final LearnResponse.topics object = topicsArrayList.get(position);

        if (ObjectUtils.isNotNull(object)) {

            holder.title.setText(object.getName());
            holder.discripation.setText(object.getDescription());
            Glide.with(mContext).load(object.getImage()).into(holder.thumbnail_learn);
            holder.txt_mark.setText(object.getCompleted_lessons() + "/" + object.getTotal_lessons());


            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickLessons.lessonId(object.getTopic_id(), position);
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return topicsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, discripation, txt_mark;
        public ImageView thumbnail_learn;
        public RelativeLayout rl;


        public MyViewHolder(View view) {
            super(view);


            this.title = itemView.findViewById(R.id.title);
            this.txt_mark = itemView.findViewById(R.id.txt_mark);
            this.thumbnail_learn = itemView.findViewById(R.id.thumbnail_learn);
            this.discripation = itemView.findViewById(R.id.discripation);
            this.rl = itemView.findViewById(R.id.rl_learn);

        }
    }
}
