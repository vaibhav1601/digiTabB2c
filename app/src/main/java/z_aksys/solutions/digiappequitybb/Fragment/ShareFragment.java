package z_aksys.solutions.digiappequitybb.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import z_aksys.solutions.digiappequitybb.Adapter.NewsAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.custom.ViewPagerNoSwipe;
import z_aksys.solutions.digiappequitybb.model.News;

public class ShareFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> albumList;
    private TabLayout tabLayout;
    private ViewPagerNoSwipe viewPager;


    public ShareFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final View view = inflater.inflate(R.layout.fragment_sharedocument, container, false);
        viewPager = (ViewPagerNoSwipe) view.findViewById(R.id.viewpager_doc);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabsdoc);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        return view;
    }

    private void setupViewPager(ViewPagerNoSwipe viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), 1);
        adapter.addFrag(new DocumentFragment(), "DOCUMENTS");
        adapter.addFrag(new VideoFragmentV2(), "VIDEOS");
        viewPager.setAdapter(adapter);
    }


    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_document, null);
        tabOne.setText("DOCUMENTS");
        tabOne.setTextSize(13);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        tabOne.setTypeface(face);
        tabOne.getMaxWidth();
        tabLayout.getTabAt(0).setCustomView(tabOne);
        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_document, null);
        tabTwo.setText("VIDEOS");
        tabTwo.setTextSize(13);
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        tabTwo.setTypeface(face);
        tabTwo.getMaxWidth();
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager, int i) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
