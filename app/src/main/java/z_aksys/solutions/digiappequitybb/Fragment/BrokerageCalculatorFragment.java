package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

import z_aksys.solutions.digiappequitybb.CustomWidgets.RangeEditText;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.InsuranceCalculator;
import z_aksys.solutions.digiappequitybb.utils.MutualFundCalculator;

public class BrokerageCalculatorFragment extends Fragment {

    private RecyclerView rvInvestmentAmountOptions;
    private RangeEditText retInvestmentAmount;
    private RangeEditText retInvestmentTenure;
    private int[] investmentAmountOptions = new int[]{1, 5, 10, 25, 50, 100, 500};
    private int[] investmentTenureOptions = new int[]{5, 10, 15, 20, 25};
    private MutualFundCalculator mutualFundCalculator;
    private InsuranceCalculator insuranceCalculator;
    private int investmentAmount = 0;
    private int investmentTenure = 0;
    private TextView tvMutualFundResult;
    //private TextView tvInsuranceResult;

    public BrokerageCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_brokerage_calculator, container, false);

        mutualFundCalculator = new MutualFundCalculator();
        insuranceCalculator = new InsuranceCalculator();
        //tvInsuranceResult = view.findViewById(R.id.tv_insurance_result);
        tvMutualFundResult = view.findViewById(R.id.tv_mf_result);

        retInvestmentAmount = view.findViewById(R.id.ret_investment_amount);

        retInvestmentAmount.setStep(1);
        retInvestmentAmount.setIncrementOptions(investmentAmountOptions);
        retInvestmentAmount.setLabel("Investment Amount (in $Lakh)");
        retInvestmentAmount.populateIncrementOptionsList();
        retInvestmentAmount.setOnValueChangeListener(new RangeEditText.ValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                investmentAmount = value;
                updateResults(investmentAmount, investmentTenure);
            }
        });
        retInvestmentAmount.setOnFormatIncrementOptionListener(new RangeEditText.FormatIncrementOptionListener() {
            @Override
            public String onFormatOption(int value) {

                // Value is amount in lakhs
                if (value < 100) {
                    return "+"+value + " lakhs";
                } else if (value >= 100) {
                    return "+"+(int) (value / 100) + " crores";
                } else {
                    return "";
                }
            }
        });
        retInvestmentAmount.setValue(1);

        retInvestmentTenure = view.findViewById(R.id.ret_investment_tenure);
        retInvestmentTenure.setIncrementOptions(investmentTenureOptions);
        retInvestmentTenure.setLabel("Tenure (in years)");

        retInvestmentTenure.setStep(5);
        retInvestmentTenure.setOnValueChangeListener(new RangeEditText.ValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                investmentTenure = value;
                updateResults(investmentAmount, investmentTenure);
            }
        });
        retInvestmentTenure.setValue(25);
        return view;
    }

    private void updateResults(int investmentAmount, int investmentTenure) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        tvMutualFundResult.setText(format.format(mutualFundCalculator.calculateSubBrokerEarning(investmentAmount, investmentTenure)));
        //tvInsuranceResult.setText(format.format(insuranceCalculator.calculateSubBrokerEarning(investmentAmount, investmentTenure)));
    }

    /**
     * Sets value of investment textbox
     *
     * @param index Index of investment option selected in array
     */
    public void setValueOfInvestmentAmount(int index) {
        retInvestmentAmount.addValueByIncrementOptionOnIndex(index);
    }

}
