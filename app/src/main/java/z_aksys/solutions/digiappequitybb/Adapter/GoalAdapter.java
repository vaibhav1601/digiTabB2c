package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.EmployeeProfileResponse;
import z_aksys.solutions.digiappequitybb.custmization.CircularSeekBar;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder> {


    private Context mContext;
    private List<String> goalList;
    private String goal;
    private EmployeeProfileResponse.employee employee;


    public GoalAdapter(Context mContext, List<String> goalList, EmployeeProfileResponse.employee employee, Fragment fragment) {
        this.mContext = mContext;
        this.goalList = goalList;
        this.employee = employee;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.demo1, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final String goal = goalList.get(position);


        int post = holder.getPosition();

        if (!TextUtils.isEmpty(goal)) {

            if (ObjectUtils.isNotNull(employee)) {
                if (post == 0) {

                    holder.txt_heading.setText("Acquisition - Pure Online");
                    holder.txt_achived.setText(employee.getFo_achie_per());
                    holder.txt_target.setText(employee.getCost_tag());
                    holder.txt_goal_score.setText(employee.getTotal_ac_achie());
                    holder.seekbar.setProgress(20);
                    holder.seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
                    holder.seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.red_900));
                }

                if (post == 1) {


                    holder.txt_heading.setText("Margin");
                    holder.txt_achived.setText(employee.getMargin_achie_per());
                    holder.txt_target.setText(employee.getMargin_tgt());
                    holder.txt_goal_score.setText(employee.getTotal_margin_achie());
                    holder.seekbar.setProgress(20);
                    holder.seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
                    holder.seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.red_900));
                }


                if (post == 2) {


                    holder.txt_heading.setText("WMS");
                    holder.txt_achived.setText(employee.getMargin_achie());
                    holder.txt_target.setText(employee.getMargin_tgt());
                    holder.txt_goal_score.setText(employee.getTotal_wms_achie());
                    holder.seekbar.setProgress(20);
                    holder.seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
                    holder.seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.red_900));

                }


                if (post == 3) {


                    holder.txt_heading.setText("ONLINE Fund Transfer");
                    holder.txt_achived.setText(employee.getOnline_fund_achie());
                    holder.txt_target.setText(employee.getOnline_tgt());
                    holder.txt_goal_score.setText(employee.getTotal_fund_transfer());
                    holder.seekbar.setProgress(50);
                    holder.seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
                    holder.seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.green_color));
                }


                if (post == 4) {


                    holder.txt_heading.setText("FO Activation");
                    holder.txt_achived.setText(employee.getFo_ac_chie());
                    holder.txt_target.setText(employee.getFo_achie_per());
                    holder.txt_goal_score.setText(employee.getTotal_fo_activation());
                    holder.seekbar.setProgress(50);
                    holder.seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
                    holder.seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.green_color));
                }


                if (post == 5) {


                    holder.txt_heading.setText("Mutual Fund  - ic_sip_earnings");
                    holder.txt_achived.setText(employee.getSip_achie());
                    holder.txt_target.setText(employee.getSip_tgt());
                    holder.txt_goal_score.setText(employee.getTotal_sip());
                    holder.seekbar.setProgress(50);
                    holder.seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
                    holder.seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.green_color));
                }

            }

        }


    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircularSeekBar seekbar;
        AppCompatTextView txt_percent, txt_heading, txt_achived, txt_target, txt_goal_score;

        public MyViewHolder(View view) {
            super(view);

            seekbar = (CircularSeekBar) view.findViewById(R.id.progress_circular);
            txt_heading = (AppCompatTextView) view.findViewById(R.id.txt_heading);
            txt_percent = (AppCompatTextView) view.findViewById(R.id.txt_percent);
            txt_achived = (AppCompatTextView) view.findViewById(R.id.txt_achived);
            txt_target = (AppCompatTextView) view.findViewById(R.id.txt_target);
            txt_goal_score = (AppCompatTextView) view.findViewById(R.id.txt_goal_score);
            seekbar.getProgress();
            seekbar.setProgress(50);
            seekbar.setCircleColor(App.getContext().getResources().getColor(R.color.grey));
            seekbar.setCircleProgressColor(App.getContext().getResources().getColor(R.color.green_color));
            seekbar.setIsTouchEnabled(false);

        }
    }
}