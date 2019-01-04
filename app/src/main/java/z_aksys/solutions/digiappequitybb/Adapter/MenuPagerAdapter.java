package z_aksys.solutions.digiappequitybb.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import z_aksys.solutions.digiappequitybb.Fragment.MenuDetailsTabFragment;

/**
 * Created by root on 10/10/17.
 */

public class MenuPagerAdapter extends FragmentStatePagerAdapter {


    Context ctx;
    ArrayList<String> indiListTemp;

    public MenuPagerAdapter(FragmentManager fm, ArrayList<String> indiListTemp, Context context) {
        super(fm);
        this.indiListTemp = indiListTemp;
        this.ctx = context;
    }

    public static boolean indexExists(final List list, final int index) {
        return index >= 0 && index < list.size();
    }

    public static boolean isNotNull(Object o) {
        if (o != null) {
            return true;
        }
        return false;
    }

    /*public void passFragmentdata(IndividualDetails indiObject)
    {
        (new HealthInsuranceIndividualDetailsTabFragment()).passData(indiObject);
    }*/

    @Override
    public Fragment getItem(int position) {

        return new MenuDetailsTabFragment(indiListTemp.get(position));
    }

    @Override
    public int getCount() {
        return indiListTemp.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (indexExists(indiListTemp, position)) {
            // IndividualDetails indiObject = indiListTemp.get(position);
            return indiListTemp.get(position);

        }
        return null;
    }
}
