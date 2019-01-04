package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import z_aksys.solutions.digiappequitybb.R;

public class BestRecommendationFragment extends Fragment implements View.OnClickListener {

    private TextView tabSip;
    private TextView tabTax;
    private TextView tabLumpsum;
    private TextView tvPercentOthers;
    private TextView tvPercentAngel;
    private TextView tvPercentageHigher;


    private final int TAB_SIP=0;
    private final int TAB_TAX=1;
    private final int TAB_LUMPSUM= 2;

    private String[] othersPercentages= {"11.6%", "14.8%", "14.8%"};
    private String[] anglePercentages= {"13.1%", "15.6%", "17.9%"};
    private String[] angleHigher= {"1.5%", "0.8%", "3.1%"};

    private ImageView icArqInfo;

    public BestRecommendationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_best_recommendation, container, false);

        tabSip= view.findViewById(R.id.tab_sip);
        tabTax= view.findViewById(R.id.tab_tax_savings);
        tabLumpsum= view.findViewById(R.id.tab_lumpsum);

        tabSip.setOnClickListener(this);
        tabTax.setOnClickListener(this);
        tabLumpsum.setOnClickListener(this);

        tvPercentAngel= view.findViewById(R.id.tv_angle_percent);
        tvPercentOthers = view.findViewById(R.id.tv_others_percent);
        tvPercentageHigher= view.findViewById(R.id.tv_percentage_higher);

        icArqInfo= view.findViewById(R.id.ic_arq_info);
        icArqInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog aboutArq = new Dialog(getActivity());
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View aboutArqView = inflater.inflate(R.layout.dialog_about_arq, null);
                aboutArq.setContentView(aboutArqView);

                WebView wvAboutArq = aboutArqView.findViewById(R.id.wv_about_arq);
                wvAboutArq.loadUrl("file:///android_asset/slides/img/about_arq.svg");
                wvAboutArq.getSettings().setLoadWithOverviewMode(true);
                wvAboutArq.getSettings().setUseWideViewPort(true);
                wvAboutArq.setInitialScale(90);

                ImageView img_close = aboutArqView.findViewById(R.id.img_close);
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        aboutArq.dismiss();
                    }
                });

                aboutArq.show();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

        tabSip.setBackgroundColor(getActivity().getResources().getColor(R.color.grey_300));
        tabTax.setBackgroundColor(getActivity().getResources().getColor(R.color.grey_300));
        tabLumpsum.setBackgroundColor(getActivity().getResources().getColor(R.color.grey_300));

        switch (v.getId()){
            case R.id.tab_sip:
                selectTab(TAB_SIP);
                tabSip.setBackgroundColor(getActivity().getResources().getColor(R.color.chart_color4));
                break;
            case R.id.tab_tax_savings:
                selectTab(TAB_TAX);
                tabTax.setBackgroundColor(getActivity().getResources().getColor(R.color.chart_color4));
                break;
            case R.id.tab_lumpsum:
                selectTab(TAB_LUMPSUM);
                tabLumpsum.setBackgroundColor(getActivity().getResources().getColor(R.color.chart_color4));
                break;
                default:
                    break;

        }
    }

    private void selectTab(int selectedTab){
        tvPercentOthers.setText(othersPercentages[selectedTab]);
        tvPercentAngel.setText(anglePercentages[selectedTab]);
        tvPercentageHigher.setText(angleHigher[selectedTab]);
    }
}
