package z_aksys.solutions.digiappequitybb.utils;

import android.util.Log;

public class InsuranceCalculator {

    private final float MOTOR_INSURANCE_BROKERAGE = .1f;
    private final float HEALTH_INSURANCE_BROKERAGE = .15f;
    private final float LIFE_INSURANCE_BROKERAGE = .2f;

    public float calculateMotorInsuranceEarning(int investmentAmount, int numberOfPremiums) {
        return investmentAmount*numberOfPremiums*MOTOR_INSURANCE_BROKERAGE;
    }

    public float calculateHealthInsuranceEarning(int investmentAmount, int numberOfPremiums) {
        return investmentAmount*numberOfPremiums*HEALTH_INSURANCE_BROKERAGE;
    }

    public float calculateLifeInsuranceEarning(int investmentAmount, int numberOfPremiums) {
        return investmentAmount*numberOfPremiums*LIFE_INSURANCE_BROKERAGE;
    }
}
