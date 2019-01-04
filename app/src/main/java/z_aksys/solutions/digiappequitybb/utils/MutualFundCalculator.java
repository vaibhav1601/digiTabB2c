package z_aksys.solutions.digiappequitybb.utils;

import android.util.Log;

public class MutualFundCalculator {

    private final float UPFRONTINCOMEPERCENTAGE = .005f;
    private final float TRAILINCOMEPERCENTAGE = .005f;
    private final float growthInAum = .15f;
    private final float trailConstant = 1.15f;

    private final float GROWTHINAUMFORSIP = 1.075f;
    private final float GROWTHINAUMLUMPSUM = 1.15f;
    private final float UPFRONTINCOMEPERCENTAGELUMPSUM = .01f;
    private final float UPFRONTINCOMEPERCENTAGESIP = .005f;

    public float calculateSubBrokerEarning(int investmentAmount, int tenureInYears) {

        // Investment Amount is in lakhs
        investmentAmount = investmentAmount * 100000;
        float income = 0;
        int year = 1;
        float aum = 0;
        float trail = 0;
        while (year <= tenureInYears) {
            float upfront = UPFRONTINCOMEPERCENTAGE * investmentAmount;

            if (year == 1) {
                aum = (aum * (1 + growthInAum)) + (investmentAmount + ((investmentAmount / 2) * growthInAum));
                trail = TRAILINCOMEPERCENTAGE * aum;
            } else {
                trail = ((aum + (aum * growthInAum)) + (investmentAmount * trailConstant)) * TRAILINCOMEPERCENTAGE;
                aum = (aum * (1 + growthInAum)) + (investmentAmount + ((investmentAmount / 2) * growthInAum));
            }


            float incomeThisYear = upfront + trail;
            income += incomeThisYear;
            Log.d("calc", "Year " + year + ": Upfront-" + upfront + ", Trail-" + (trail) + ", AUM-" + aum + ", Income: " + incomeThisYear);
            year++;
        }
        return income;
    }

    public long calculateSubBrokerSIPEarningYearly(int numberOfSIPsInMonth, long investmentAmount, int tenureInMonths) {
        return calculateSubBrokerMFEarningYearly(numberOfSIPsInMonth, investmentAmount, tenureInMonths, GROWTHINAUMFORSIP, UPFRONTINCOMEPERCENTAGESIP);
    }

    public long calculateSubBrokerSIPEarningMonthly(int numberOfSIPs, int investmentAmount, int tenureInMonths) {
        return (calculateSubBrokerSIPEarningYearly(numberOfSIPs, investmentAmount, tenureInMonths) / 12);
    }

    public long calculateSubBrokerLumpsumEarningYearly(int numberOfLumpsums, long investmentAmount, int tenureInMonths) {
        return calculateSubBrokerMFEarningYearly(numberOfLumpsums, investmentAmount, tenureInMonths, GROWTHINAUMLUMPSUM, UPFRONTINCOMEPERCENTAGELUMPSUM);
    }

    public long calculateSubBrokerLumpsumEarningMonthly(int numberOfLumpsums, long investmentAmount, int tenureInMonths) {
        return calculateSubBrokerLumpsumEarningYearly(numberOfLumpsums, investmentAmount, tenureInMonths) / 12;
    }

    private long calculateSubBrokerMFEarningYearly(int numberOfProductsInMonth, long investmentAmount, int tenureInMonth, float aumGrowthYearly, float upFrontEarnings) {
        long totalInvestmentInYear = 12 * numberOfProductsInMonth * investmentAmount;
        float aumAfterGrowth = totalInvestmentInYear * aumGrowthYearly;

        float upfront = totalInvestmentInYear * upFrontEarnings;
        float trail = aumAfterGrowth * TRAILINCOMEPERCENTAGE;

        Log.d("calc", "totalInvestmentInYear= " + totalInvestmentInYear + "upfront= " + upfront + "trail= " + trail);

        return Math.round(upfront + trail);
    }
}
