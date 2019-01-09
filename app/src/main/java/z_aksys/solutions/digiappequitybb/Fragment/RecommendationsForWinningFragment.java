package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import z_aksys.solutions.digiappequitybb.R;

public class RecommendationsForWinningFragment extends Fragment {

    private String slideUrl;
    private WebView wvSlide;
    private Button btnRecommendations, btnResearchTeam;
    private int[] recommendationImageArray = {R.drawable.tr1, R.drawable.tr2, R.drawable.tr3, R.drawable.tr4, R.drawable.tr5, R.drawable.tr6, R.drawable.tr7, R.drawable.tr8, R.drawable.tr9};
    private int[] researchTeamImageArray = {R.drawable.mayuresh_joshi, R.drawable.vaibhav_agrawal, R.drawable.sameet_chavan};

    public RecommendationsForWinningFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recommendations_for_winning, container, false);

        slideUrl= getArguments().getString("slide_url");
        wvSlide= view.findViewById(R.id.wv_slide);

        wvSlide.getSettings().setLoadWithOverviewMode(true);
        wvSlide.getSettings().setUseWideViewPort(true);
        wvSlide.loadUrl(slideUrl);



        btnRecommendations = view.findViewById(R.id.btn_recommendations);
        btnRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog recommendationsDialog = new Dialog(getActivity());
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View topPerformerView = inflater.inflate(R.layout.dialog_recommendations, null);
                recommendationsDialog.setContentView(topPerformerView);

                ViewPager vpRecommendations= topPerformerView.findViewById(R.id.vp_recommendations);
                vpRecommendations.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return recommendationImageArray.length;
                    }

                    @Override
                    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                        return view==o;
                    }

                    @NonNull
                    @Override
                    public Object instantiateItem(@NonNull ViewGroup container, int position) {
                        ImageView ivPerformer= new ImageView(getContext());
                        ivPerformer.setImageResource(recommendationImageArray[position]);
                        container.addView(ivPerformer);
                        return ivPerformer;
                    }

                    @Override
                    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                        container.removeView((View) object);
                    }
                });

                ImageView img_close = topPerformerView.findViewById(R.id.img_close);
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recommendationsDialog.dismiss();
                    }
                });

                recommendationsDialog.show();
            }
        });

        btnResearchTeam= view.findViewById(R.id.btn_researcher);
        btnResearchTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog researchTeamDailog = new Dialog(getActivity());
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View topPerformerView = inflater.inflate(R.layout.dialog_research_team, null);
                researchTeamDailog.setContentView(topPerformerView);

                ViewPager vpPerformer= topPerformerView.findViewById(R.id.vp_research_team);
                vpPerformer.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return researchTeamImageArray.length;
                    }

                    @Override
                    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                        return view==o;
                    }

                    @NonNull
                    @Override
                    public Object instantiateItem(@NonNull ViewGroup container, int position) {
                        ImageView ivPerformer= new ImageView(getContext());
                        ivPerformer.setImageResource(researchTeamImageArray[position]);
                        container.addView(ivPerformer);
                        return ivPerformer;
                    }

                    @Override
                    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                        container.removeView((View) object);
                    }
                });

                ImageView img_close = topPerformerView.findViewById(R.id.img_close);
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        researchTeamDailog.dismiss();
                    }
                });

                researchTeamDailog.show();
            }
        });

        return view;
    }
}
