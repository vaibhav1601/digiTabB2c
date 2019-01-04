package z_aksys.solutions.digiappequitybb.utils;

public class EquityCalculator {

    private final long AVERAGE_EARNING_PER_ACTIVE_CLIENT= 300;

    public long calculateEquityEarning(int numberOfActiveUsers) {
        return numberOfActiveUsers*AVERAGE_EARNING_PER_ACTIVE_CLIENT;
    }
}
