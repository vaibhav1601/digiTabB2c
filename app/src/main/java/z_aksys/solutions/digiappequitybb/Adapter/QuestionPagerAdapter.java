package z_aksys.solutions.digiappequitybb.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import z_aksys.solutions.digiappequitybb.Fragment.QuestionFragment;
import z_aksys.solutions.digiappequitybb.model.MyServerData;


public class QuestionPagerAdapter extends FragmentStatePagerAdapter {
    MyServerData myServerData;


    public QuestionPagerAdapter(FragmentManager fm, MyServerData myServerData) {
        super(fm);
        this.myServerData = myServerData;

    }


    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        QuestionFragment questionFragment = new QuestionFragment(myServerData);
        questionFragment.setArguments(bundle);
        return questionFragment;
    }

    @Override
    public int getCount() {
        return myServerData.getTotalQuestions() + 1;
    }


}
