package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;

import z_aksys.solutions.digiappequitybb.Activity.PitchActivity;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.OnSwipeTouchListener;

public class AdvanceTradingPlatformFragment extends Fragment implements View.OnClickListener {

    private String slideUrl;
    private WebView wvSlide;
    private Button btnTradePlatform, btnMobileApp, btnSpeedPro;
    private String[] advanceTradingPlatformImages = {"file:///android_asset/slides/p1.html", "file:///android_asset/slides/p2.html", "file:///android_asset/slides/p3.html"};
    private String[] advanceTradingPlatformTitle = {"Angle Broking Trade Features", "Angle Broking Mobile App", "Angle Speed Pro Features"};

    public AdvanceTradingPlatformFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_advance_trading_platform, container, false);

        slideUrl= getArguments().getString("slide_url");
        wvSlide= view.findViewById(R.id.wv_slide);

        wvSlide.getSettings().setLoadWithOverviewMode(true);
        wvSlide.getSettings().setUseWideViewPort(true);
        wvSlide.loadUrl(slideUrl);
        wvSlide.setOnTouchListener(new SwipeListener(getActivity()));

        btnTradePlatform= view.findViewById(R.id.btn_trade_platform);
        btnMobileApp= view.findViewById(R.id.btn_mobile_app);
        btnSpeedPro= view.findViewById(R.id.btn_speed_pro);

        btnTradePlatform.setOnClickListener(this);
        btnSpeedPro.setOnClickListener(this);
        btnMobileApp.setOnClickListener(this);

        return view;
    }

    private void showFeaturesDialog(int selectedSlideIndex) {
        final Dialog advanceTradingPlatform = new Dialog(getActivity());
        advanceTradingPlatform.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View advanceTradingPlatformView = inflater.inflate(R.layout.dialog_advance_trading_platform, null);
        advanceTradingPlatform.setContentView(advanceTradingPlatformView);

        TextView tvHeading= advanceTradingPlatform.findViewById(R.id.txt_heading);
        tvHeading.setText(advanceTradingPlatformTitle[selectedSlideIndex]);

        ViewPager vpAdvanceTradingPlatform= advanceTradingPlatformView.findViewById(R.id.vp_performer);
        vpAdvanceTradingPlatform.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return advanceTradingPlatformImages.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                WebView wvPerformer= new WebView(getContext());
                wvPerformer.loadUrl(advanceTradingPlatformImages[position]);
                container.addView(wvPerformer);
                return wvPerformer;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

        vpAdvanceTradingPlatform.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tvHeading.setText(advanceTradingPlatformTitle[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        vpAdvanceTradingPlatform.setCurrentItem(selectedSlideIndex);

        ImageView img_close = advanceTradingPlatform.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advanceTradingPlatform.dismiss();
            }
        });

        advanceTradingPlatform.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_trade_platform:
                showFeaturesDialog( 0);
                break;
            case R.id.btn_mobile_app:
                showFeaturesDialog( 1);
                break;
            case R.id.btn_speed_pro:
                showFeaturesDialog( 2);
                break;
                default:
                    break;
        }
    }

    private class SwipeListener extends OnSwipeTouchListener {

        public SwipeListener(Context ctx) {
            super(ctx);
        }

        @Override
        public void onSwipeLeft() {
            super.onSwipeLeft();
            ((PitchActivity)getActivity()).slideLeft();
        }

        @Override
        public void onSwipeRight() {
            super.onSwipeRight();
            ((PitchActivity)getActivity()).slideRight();
        }
    }
}
