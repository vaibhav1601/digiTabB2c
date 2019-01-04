package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickLessons;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;


public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.MyViewHolder> {


    private Context mContext;
    private List<LearnResponse.lessons> lessonsArrayList;
    private OnClickLessons onClickLessons;

    private String id;


    public LessonsAdapter(Context mContext, List<LearnResponse.lessons> lessonsArrayList, Fragment fragment, String id) {
        this.mContext = mContext;
        this.lessonsArrayList = lessonsArrayList;
        this.id = id;
        this.onClickLessons = ((OnClickLessons) fragment);
    }

    @Override
    public LessonsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adaptor_lessions, parent, false);

        return new LessonsAdapter.MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final LessonsAdapter.MyViewHolder holder, final int position) {

        int i = Integer.parseInt(id);

        final LearnResponse.lessons object = lessonsArrayList.get(position);

        if (ObjectUtils.isNotNull(object)) {

            holder.title.setText(object.getName());

            if (!TextUtils.isEmpty(object.getIs_completed())) {
                if (object.getIs_completed().equalsIgnoreCase("1")) {
                    holder.txtstaus.setText("completed");

                    holder.txtstaus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_completed, 0, 0, 0);

                    holder.txtstaus.setTextColor(App.getContext().getResources().getColor(R.color.green_color));

                } else if (object.getIs_completed().equalsIgnoreCase("0")) {
                    holder.txtstaus.setText("open");

                    holder.txtstaus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pending, 0, 0, 0);
                    holder.txtstaus.setTextColor(App.getContext().getResources().getColor(R.color.red_900));

                } else if (object.getIs_completed().equalsIgnoreCase("2")) {
                    holder.txtstaus.setText("Pending");
                    holder.txtstaus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pending, 0, 0, 0);
                    holder.txtstaus.setTextColor(App.getContext().getResources().getColor(R.color.red_900));


                } else {

                    holder.txtstaus.setText(" ");

                }

            }

            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onClickLessons.lessonId(object.getLesson_id(), position);
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return lessonsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, txtstaus;

        public RelativeLayout rl;


        public MyViewHolder(View view) {
            super(view);


            this.title = itemView.findViewById(R.id.txt_lessions_header);
            this.txtstaus = itemView.findViewById(R.id.txt_status);
            this.rl = itemView.findViewById(R.id.rl_lessions);

        }
    }
}
