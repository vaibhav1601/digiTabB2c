package z_aksys.solutions.digiappequitybb.utils;

import android.util.Log;

public class AccountCalculator {

    private final long ACCOUNTOPENINGBROKERAGE = 500;

    public long calculateAccountOpeningEarning(int numberOfAccounts) {
        return numberOfAccounts*ACCOUNTOPENINGBROKERAGE;
    }
}
