package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import z_aksys.solutions.digiappequitybb.CustomWidgets.InvestmentCheckbox;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.CurrencyFormatter;

public class InvestmentCalculatorFragment extends Fragment implements InvestmentCheckbox.CheckChangeListner {

    private TextView tvTotalInvestment;
    private InvestmentCheckbox icbBseCash, icbNseCash, icbNseFnoCash, icbCurrency, icbMcxNcdex;

    public InvestmentCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_investment_calculator, container, false);

        tvTotalInvestment= view.findViewById(R.id.tv_total_investment);
        icbBseCash= view.findViewById(R.id.icb_bse_cash);
        icbNseCash= view.findViewById(R.id.icb_nse_cash);
        icbNseFnoCash= view.findViewById(R.id.icb_nse_fno_cash);
        icbCurrency= view.findViewById(R.id.icb_currency);
        icbMcxNcdex= view.findViewById(R.id.icb_mcx_ncdex);

        icbBseCash.setCheckChangeListner(this);
        icbNseCash.setCheckChangeListner(this);
        icbNseFnoCash.setCheckChangeListner(this);
        icbCurrency.setCheckChangeListner(this);
        icbMcxNcdex.setCheckChangeListner(this);

        onCheckChanged();

        return view;
    }


    @Override
    public void onCheckChanged() {

        long totalInvestment=0;

        if (icbBseCash.getChecked()){
            totalInvestment+= 2360;
        }

        if (icbNseCash.getChecked()){
            totalInvestment+= 2360;
        }

        if (icbNseFnoCash.getChecked()){
            totalInvestment+= 2360;
        }

        if (icbCurrency.getChecked()){
            totalInvestment+= 2360;
        }

        if (icbMcxNcdex.getChecked()){
            totalInvestment+= 2360;
        }

        tvTotalInvestment.setText(CurrencyFormatter.format(totalInvestment));
    }
}
