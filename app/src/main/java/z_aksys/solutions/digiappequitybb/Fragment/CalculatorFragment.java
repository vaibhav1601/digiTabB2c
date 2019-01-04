package z_aksys.solutions.digiappequitybb.Fragment;

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

public class CalculatorFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> albumList;
    private TabLayout tabLayout;
    private ViewPagerNoSwipe viewPager;


    public CalculatorFragment() {
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
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_calculators, container, false);

        viewPager = (ViewPagerNoSwipe) view.findViewById(R.id.viewpager_calculators);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_calculators);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        return view;
    }

    private void setupViewPager(ViewPagerNoSwipe viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), 1);
        adapter.addFrag(new InvestmentCalculatorFragment(), "INVESTMENT CALCULATOR");
        adapter.addFrag(new RevenueModelFragment(), "REVENUE MODEL");
        viewPager.setAdapter(adapter);
    }


    private void setupTabIcons() {

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_document, null);
        tabTwo.setText("REVENUE MODEL");
        tabTwo.setTextSize(14);
        tabTwo.getMaxWidth();
        //  tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_devices_other_black_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_document, null);
        tabOne.setText("INVESTMENT CALCULATOR");
        tabOne.setTextSize(14);
        tabOne.getMaxWidth();
        //tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_view_list_black_24dp, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);
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
