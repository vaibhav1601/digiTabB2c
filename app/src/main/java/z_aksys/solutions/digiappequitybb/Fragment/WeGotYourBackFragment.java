package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.Adapter.WeHaveGotYourBackFeatureListAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.model.WeHaveGotYourBackFeature;

;

public class WeGotYourBackFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rvFeatureList;
    private int mSelectedCategory;

    private ArrayList<WeHaveGotYourBackFeature> features;
    private WeHaveGotYourBackFeatureListAdapter mWeHaveGotYourBackFeatureListAdapter;

    private TextView tv24x7Services, tvExtensiveOffering, tvTrainingAssistance, tvUnmatchedSupport;
    View navigationPanel, vDot1, vDot2;

    public WeGotYourBackFragment() {
        // Required empty public constructor

        features= new ArrayList<>();

        features.add(new WeHaveGotYourBackFeature("Real time tracking", R.drawable.real_time_tracking, 0, 0));
        features.add(new WeHaveGotYourBackFeature("Daily Payouts Facility", R.drawable.daily_payouts_facility, 0, 0));
        features.add(new WeHaveGotYourBackFeature("Dealer's dashboard", R.drawable.dealers_dashboard, 0, 0));
        features.add(new WeHaveGotYourBackFeature("Risk Management System", R.drawable.risk_management_system, 0, 0));
        features.add(new WeHaveGotYourBackFeature("Instant account opening", R.drawable.instant_account_opening, 1, 0));
        features.add(new WeHaveGotYourBackFeature("Free Tele-calling and SMS support*", R.drawable.free_telecalling_and_sms_support, 1, 0));
        features.add(new WeHaveGotYourBackFeature("Multiple investment options", R.drawable.mulitple_investment_options, 1, 0));
        features.add(new WeHaveGotYourBackFeature("Frequent updates on new products", R.drawable.frequent_updates_on_new_products, 1, 0));
        features.add(new WeHaveGotYourBackFeature("Periodic training", R.drawable.periodic_training, 2, 0));
        features.add(new WeHaveGotYourBackFeature("Sales pitch app", R.drawable.sales_pitch_app, 2, 0));
        features.add(new WeHaveGotYourBackFeature("Marketing collateral", R.drawable.marketing_collateral, 2, 0));
        features.add(new WeHaveGotYourBackFeature("Free social media advertisement", R.drawable.free_social_media_advertisement, 3, 0));
        features.add(new WeHaveGotYourBackFeature("Engagement programmes", R.drawable.engagement_programmes, 3, 0));
        features.add(new WeHaveGotYourBackFeature("Customized website", R.drawable.customized_website, 3, 0));
        features.add(new WeHaveGotYourBackFeature("Maximum Intraday Leverage/Limits*", R.drawable.maximum_intraday_leverage_limits, 2, 1));
        features.add(new WeHaveGotYourBackFeature("Bracket Order Punching and Stop-Loss feature on ODIN", R.drawable.bracket_order_punching_and_stop_loss_feature_on_odin, 2, 1));
        features.add(new WeHaveGotYourBackFeature("Unmatched Margin funding and exposure", R.drawable.unmatched_margin_funding_and_exposure, 2, 1));
        features.add(new WeHaveGotYourBackFeature("Free research and advisory support", R.drawable.free_research_and_advisory_support, 3, 1));
        features.add(new WeHaveGotYourBackFeature("Dedicated relationship manager", R.drawable.dedicated_relationship_manager, 3, 1));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_we_got_your_back, container, false);

        tv24x7Services= view.findViewById(R.id.tb_24x7_services);
        tvExtensiveOffering= view.findViewById(R.id.tb_extensive_offerings);
        tvTrainingAssistance= view.findViewById(R.id.tb_training_assistance);
        tvUnmatchedSupport= view.findViewById(R.id.tb_unmatched_support);

        tv24x7Services.setOnClickListener(this);
        tvExtensiveOffering.setOnClickListener(this);
        tvTrainingAssistance.setOnClickListener(this);
        tvUnmatchedSupport.setOnClickListener(this);

        rvFeatureList= view.findViewById(R.id.rv_feature_list);
        rvFeatureList.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        navigationPanel= view.findViewById(R.id.navigation_panel);
        vDot1= view.findViewById(R.id.nav_dot1);
        vDot2= view.findViewById(R.id.nav_dot2);

        vDot1.setOnClickListener(this);
        vDot2.setOnClickListener(this);

        mWeHaveGotYourBackFeatureListAdapter= new WeHaveGotYourBackFeatureListAdapter(getActivity(), features, 0, 0);
        rvFeatureList.setAdapter(mWeHaveGotYourBackFeatureListAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tb_24x7_services:
                mWeHaveGotYourBackFeatureListAdapter.updateSelection(0,0);
                mSelectedCategory=0;
                navigationPanel.setVisibility(View.GONE);
                break;
            case R.id.tb_extensive_offerings:
                mWeHaveGotYourBackFeatureListAdapter.updateSelection(1,0);
                mSelectedCategory=1;
                navigationPanel.setVisibility(View.GONE);
                break;
            case R.id.tb_training_assistance:
                mWeHaveGotYourBackFeatureListAdapter.updateSelection(2,0);
                mSelectedCategory=2;
                navigationPanel.setVisibility(View.VISIBLE);
                break;
            case R.id.tb_unmatched_support:
                mWeHaveGotYourBackFeatureListAdapter.updateSelection(3,0);
                mSelectedCategory= 3;
                navigationPanel.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_dot1:
                mWeHaveGotYourBackFeatureListAdapter.updateSelection(mSelectedCategory,0);
                vDot1.setBackgroundColor(getActivity().getResources().getColor(R.color.blue_800));
                vDot2.setBackgroundColor(getActivity().getResources().getColor(R.color.blue_200));
                break;
            case R.id.nav_dot2:
                vDot1.setBackgroundColor(getActivity().getResources().getColor(R.color.blue_200));
                vDot2.setBackgroundColor(getActivity().getResources().getColor(R.color.blue_800));
                mWeHaveGotYourBackFeatureListAdapter.updateSelection(mSelectedCategory,1);
                break;
                default:
                    break;
        }
    }
}
