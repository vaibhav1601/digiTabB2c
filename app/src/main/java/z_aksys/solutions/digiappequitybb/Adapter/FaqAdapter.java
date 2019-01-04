package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.FaqResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClicklistener;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.MyViewHolder>  {


    private Context mContext;
    private List<FaqResponse.faq> faqList;
    private OnClicklistener onClicklistener;


    public FaqAdapter(Context mContext, List<FaqResponse.faq> faqList, Fragment fragment) {
        this.mContext = mContext;
        this.faqList = faqList;
        this.onClicklistener = ((OnClicklistener) fragment);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_faq, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final FaqResponse.faq object = faqList.get(position);


        holder.textView.setText(object.getName());
        Glide.with(mContext).load(object.getImage()).into(holder.imageView);


        holder.llFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicklistener.lessonId(object.getTopic_id());
            }
        });


    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        AppCompatImageView imageView;
        TextView textView;
        LinearLayoutCompat llFaq;


        public MyViewHolder(View view) {
            super(view);


            this.imageView = view.findViewById(R.id.img_faq);
            this.textView = view.findViewById(R.id.txt_faqname);
            this.llFaq = view.findViewById(R.id.ll_faqview);

        }
    }
}