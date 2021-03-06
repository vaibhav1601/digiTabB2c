package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.FaqDetailsResponse;
import z_aksys.solutions.digiappequitybb.utils.CTEventLog;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

public class FaqDetailsAdapter extends RecyclerView.Adapter<FaqDetailsAdapter.MyViewHolder> {


    private static int currentPosition = 0;
    private Context mContext;
    private List<FaqDetailsResponse.faq_detail> faqDetailList;


    public FaqDetailsAdapter(Context mContext, List<FaqDetailsResponse.faq_detail> faqDetailList, Fragment fragment) {
        this.mContext = mContext;
        this.faqDetailList = faqDetailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_faq_details, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final FaqDetailsResponse.faq_detail object = faqDetailList.get(position);

        if (ObjectUtils.isNotNull(object)) {
            holder.textViewName.setText(Html.fromHtml(object.getQuestion()));


            holder.txtAns.setText(Html.fromHtml(object.getAnswer()));

            holder.textViewName.setBackgroundColor(Color.WHITE);
            holder.textViewName.setTextColor(App.getContext().getResources().getColor(R.color.black));
            holder.textViewName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);

            Glide.with(mContext).load(object.getImage()).into(holder.imageView);

            Glide.with(mContext)
                    .load(object.getImage())
                    .error(R.drawable.angel_logo)
                    .override(100, 100)
                    .into(holder.imageView);

            holder.linearLayout.setVisibility(View.GONE);

            //if the position is equals to the item position which is to be expanded
            if (currentPosition == position) {
                //creating an animation
                Animation slideDown = AnimationUtils.loadAnimation(mContext, R.anim.slide_up);

                //toggling visibility
                holder.linearLayout.setVisibility(View.VISIBLE);
                holder.textViewName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                holder.textViewName.setTextColor(App.getContext().getResources().getColor(R.color.white));

                holder.textViewName.setBackgroundColor(App.getContext().getResources().getColor(R.color.blue_bestpolicy));

                //adding sliding effect
                holder.linearLayout.startAnimation(slideDown);
            }

            holder.textViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //getting the position of the item to expand it
                    currentPosition = position;

                    if (faqDetailList.get(position) != null) {
                        CTEventLog.getInstance(mContext).logFaqViewed(faqDetailList.get(position).getFaq_id(), faqDetailList.get(position).getTopic_id());
                    }
                    //reloding the list
                    notifyDataSetChanged();
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return faqDetailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView textViewName, txtAns;
        ImageView imageView;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            txtAns = (TextView) itemView.findViewById(R.id.txt_ans);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

        }
    }
}