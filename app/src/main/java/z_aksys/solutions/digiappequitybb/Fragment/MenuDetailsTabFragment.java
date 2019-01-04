package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.R;


/**
 * Created by root on 10/10/17.
 */

public class MenuDetailsTabFragment extends Fragment {

    ArrayList<String> indiListTemp = new ArrayList<>();
    private String pos;


    public MenuDetailsTabFragment() {

    }

    public MenuDetailsTabFragment(String pos) {

        this.pos = pos;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.custom_tab, container, false);
        initialiseResources();
        setData();
        return rootView;
    }

    private void setData() {
    }

    private void initialiseResources() {
    }


}
