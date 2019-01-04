package z_aksys.solutions.digiappequitybb.Fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import z_aksys.solutions.digiappequitybb.Activity.PitchActivity;
import z_aksys.solutions.digiappequitybb.R;

;

public class PitchOneFragment extends Fragment {

    TextView btnNewPartner, btnExpirencePartner;
    private PitchDemoFragment pitchDemoFragment;

    public PitchOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.pitch_first_fragment_, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View decorView = getActivity().getWindow().getDecorView(); // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
        btnNewPartner = (TextView) view.findViewById(R.id.btnnew);
        btnExpirencePartner = (TextView) view.findViewById(R.id.btn_Expirence);

        btnNewPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PitchActivity.class);
                intent.putExtra("isExperiencedInvestor", false);
                startActivity(intent);
            }
        });

        btnExpirencePartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PitchActivity.class);
                intent.putExtra("isExperiencedInvestor", true);
                startActivity(intent);
            }
        });

        return view;
    }

    public void setParentFragment(PitchDemoFragment pitchOneFragment) {
        this.pitchDemoFragment = pitchOneFragment;
    }
}
