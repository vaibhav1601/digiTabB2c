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

public class HighestReturnsFragment extends Fragment {

    private String slideUrl;
    private WebView wvSlide;
    private Button btnTopPerformer;
    private String[] performerImageArray= {"file:///android_asset/slides/pt1.svg", "file:///android_asset/slides/pt2.svg", "file:///android_asset/slides/pt3.svg"};

    public HighestReturnsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_better_returns, container, false);

        slideUrl= getArguments().getString("slide_url");
        wvSlide= view.findViewById(R.id.wv_slide);

        wvSlide.getSettings().setLoadWithOverviewMode(true);
        wvSlide.getSettings().setUseWideViewPort(true);
        wvSlide.loadUrl(slideUrl);

        btnTopPerformer= view.findViewById(R.id.btn_top_performer);
        btnTopPerformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog topPerformerDialog = new Dialog(getActivity());
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
                        WebView wvPerformer= new WebView(getContext());
                        wvPerformer.getSettings().setLoadWithOverviewMode(true);
                        wvPerformer.getSettings().setUseWideViewPort(true);
                        wvPerformer.loadUrl(performerImageArray[position]);
                        container.addView(wvPerformer);
                        return wvPerformer;
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
}
