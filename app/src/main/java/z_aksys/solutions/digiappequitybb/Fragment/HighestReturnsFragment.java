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

import z_aksys.solutions.digiappequitybb.Activity.PitchActivity;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.OnSwipeTouchListener;

public class HighestReturnsFragment extends Fragment {

    private String slideUrl;
    private WebView wvSlide;
    private Button btnTopPerformer;
    private int[] performerImageArray= {R.drawable.pt1, R.drawable.pt2, R.drawable.pt3, R.drawable.pt4, R.drawable.pt5};

    public HighestReturnsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_highest_returns, container, false);

        slideUrl= getArguments().getString("slide_url");
        wvSlide= view.findViewById(R.id.wv_slide);

        wvSlide.getSettings().setLoadWithOverviewMode(true);
        wvSlide.getSettings().setUseWideViewPort(true);
        wvSlide.loadUrl(slideUrl);

        wvSlide.setOnTouchListener(new SwipeListener(getActivity()));

        btnTopPerformer= view.findViewById(R.id.btn_top_performer);
        btnTopPerformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog topPerformerDialog = new Dialog(getActivity());
                topPerformerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View topPerformerView = inflater.inflate(R.layout.dialog_top_performers, null);
                topPerformerDialog.setContentView(topPerformerView);

                ViewPager vpPerformer= topPerformerView.findViewById(R.id.vp_performer);
                vpPerformer.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return performerImageArray.length;
                    }

                    @Override
                    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                        return view==o;
                    }

                    @NonNull
                    @Override
                    public Object instantiateItem(@NonNull ViewGroup container, int position) {
                        ImageView ivSlide= new ImageView(getContext());
                        ivSlide.setImageResource(performerImageArray[position]);
                        container.addView(ivSlide);
                        return ivSlide;
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
                        topPerformerDialog.dismiss();
                    }
                });

                topPerformerDialog.show();
            }
        });

        return view;
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
