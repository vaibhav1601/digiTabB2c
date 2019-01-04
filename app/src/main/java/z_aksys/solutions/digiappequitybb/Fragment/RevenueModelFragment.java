package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

import z_aksys.solutions.digiappequitybb.CustomWidgets.CalculatorWidget;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.AccountCalculator;
import z_aksys.solutions.digiappequitybb.utils.CurrencyFormatter;
import z_aksys.solutions.digiappequitybb.utils.EquityCalculator;
import z_aksys.solutions.digiappequitybb.utils.InsuranceCalculator;
import z_aksys.solutions.digiappequitybb.utils.MutualFundCalculator;

public class RevenueModelFragment extends Fragment {

    private CalculatorWidget cwSIP, cwLumpsum, cwMotor, cwHealth, cwLife, cwAccount, cwEquity;
    private MutualFundCalculator mutualFundCalculator;
    private AccountCalculator accountCalculator;
    private InsuranceCalculator insuranceCalculator;
    private EquityCalculator equityCalculator;

    private long earningsFromSIPMonthly = 0;
    private long earningFromLumpsumMonthly = 0;
    private long earningsFromAccount = 0;
    private long earningFromEquity = 0;
    private long earningsFromMotorInsurance = 0;
    private long earningFromLifeInsurance = 0;
    private long earningsFromHealthInsurance = 0;

    private TextView tvMonthlyEarning;
    private TextView tvYearlyEarning;

    public RevenueModelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_revenue_model, container, false);

        cwSIP = view.findViewById(R.id.cw_sip);
        cwLumpsum = view.findViewById(R.id.cw_lumpsum);
        cwAccount = view.findViewById(R.id.cw_account);
        cwEquity = view.findViewById(R.id.cw_equity);
        cwMotor = view.findViewById(R.id.cw_motor);
        cwHealth = view.findViewById(R.id.cw_health);
        cwLife= view.findViewById(R.id.cw_life);

        mutualFundCalculator = new MutualFundCalculator();
        accountCalculator= new AccountCalculator();
        equityCalculator= new EquityCalculator();
        insuranceCalculator= new InsuranceCalculator();

        tvMonthlyEarning = view.findViewById(R.id.tv_monthly_income);
        tvYearlyEarning = view.findViewById(R.id.tv_annual_income);

        cwSIP.setCalculator(new CalculatorWidget.Calculator() {
            @Override
            public long calculateTotalEarnings(int numbers, int amount, int duration) {
                earningsFromSIPMonthly = mutualFundCalculator.calculateSubBrokerSIPEarningMonthly(numbers, amount, duration);
                updateEarnings();
                return earningsFromSIPMonthly;
            }

            @Override
            public String getSummaryString(int numbers, int amount, int duration) {
                return numbers + " SIP(s) of " + CurrencyFormatter.format(amount) + " for " + duration + " months";
            }
        });

        cwLumpsum.setCalculator(new CalculatorWidget.Calculator() {
            @Override
            public long calculateTotalEarnings(int numbers, int amount, int duration) {
                earningFromLumpsumMonthly = mutualFundCalculator.calculateSubBrokerLumpsumEarningMonthly(numbers, amount, duration);
                updateEarnings();
                return earningFromLumpsumMonthly;
            }

            @Override
            public String getSummaryString(int numbers, int amount, int duration) {

                return numbers + " Lumpsum(s) of " + CurrencyFormatter.format(amount) + " for " + duration + " months";
            }
        });

        cwAccount.setCalculator(new CalculatorWidget.Calculator() {
            @Override
            public long calculateTotalEarnings(int numbers, int amount, int duration) {
                earningsFromAccount= accountCalculator.calculateAccountOpeningEarning(numbers);
                updateEarnings();
                return earningsFromAccount;
            }

            @Override
            public String getSummaryString(int numbers, int amount, int duration) {
                return numbers+" accounts with minimum \u20B92,500 A/c opening fees";
            }
        });

        cwEquity.setCalculator(new CalculatorWidget.Calculator() {
            @Override
            public long calculateTotalEarnings(int numbers, int amount, int duration) {
                earningFromEquity= equityCalculator.calculateEquityEarning(numbers);
                updateEarnings();
                return earningFromEquity;
            }

            @Override
            public String getSummaryString(int numbers, int amount, int duration) {
                return numbers+ " active clients";
            }
        });

        cwMotor.setCalculator(new CalculatorWidget.Calculator() {
            @Override
            public long calculateTotalEarnings(int numbers, int amount, int duration) {
                earningsFromMotorInsurance= (long) insuranceCalculator.calculateMotorInsuranceEarning(amount,numbers);
                updateEarnings();
                return earningsFromMotorInsurance;
            }

            @Override
            public String getSummaryString(int numbers, int amount, int duration) {
                return numbers+" motor insurance for the premium of "+ CurrencyFormatter.format(amount);
            }
        });

        cwLife.setCalculator(new CalculatorWidget.Calculator() {
            @Override
            public long calculateTotalEarnings(int numbers, int amount, int duration) {
                earningFromLifeInsurance= (long) insuranceCalculator.calculateLifeInsuranceEarning(amount, numbers);
                updateEarnings();
                return earningFromLifeInsurance;
            }

            @Override
            public String getSummaryString(int numbers, int amount, int duration) {
                return numbers+" life insurance for the premium of "+ CurrencyFormatter.format(amount);
            }
        });

        cwHealth.setCalculator(new CalculatorWidget.Calculator() {
            @Override
            public long calculateTotalEarnings(int numbers, int amount, int duration) {
                earningsFromHealthInsurance= (long) insuranceCalculator.calculateHealthInsuranceEarning(amount, numbers);
                updateEarnings();
                return earningsFromHealthInsurance;
            }

            @Override
            public String getSummaryString(int numbers, int amount, int duration) {
                return numbers+" health insurance for the premium of "+ CurrencyFormatter.format(amount);
            }
        });

        return view;
    }

    private void updateEarnings() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        tvMonthlyEarning.setText(currencyFormat.format(calculateMonthEarning()));
        tvYearlyEarning.setText(currencyFormat.format(calculateMonthEarning() * 12));
    }

    private long calculateMonthEarning() {
        return earningFromLumpsumMonthly + earningsFromSIPMonthly+ earningFromLifeInsurance+ earningsFromAccount+ earningsFromHealthInsurance+ earningsFromMotorInsurance+ earningFromEquity;
    }
}
