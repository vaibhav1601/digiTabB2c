package z_aksys.solutions.digiappequitybb.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.Adapter.MenuPagerAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.custom.CustomViewPager;

public class DashBoard extends AppCompatActivity {


    private TabLayout slidingTabs;
    private CustomViewPager viewPager;
    private MenuPagerAdapter menuPagerAdapter;
    private ArrayList<String> indiListTemp = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        slidingTabs = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (CustomViewPager) findViewById(R.id.viewPager1);

        indiListTemp.add("vaibhav");
        indiListTemp.add("Vaibhav");
        indiListTemp.add("vaibhav");
        indiListTemp.add("Vaibhav");
        indiListTemp.add("vaibhav");
        indiListTemp.add("Vaibhav");
        indiListTemp.add("vaibhav");
        indiListTemp.add("Vaibhav");

        createAdapterForViewPager();


    }

    private void createAdapterForViewPager() {

        menuPagerAdapter = new MenuPagerAdapter(getSupportFragmentManager(), indiListTemp, this);
        viewPager.setAdapter(menuPagerAdapter);
        menuPagerAdapter.notifyDataSetChanged();
        slidingTabs.setupWithViewPager(viewPager);
    }


}
