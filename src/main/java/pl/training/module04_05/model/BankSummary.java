package pl.training.module04_05.model;

public class BankSummary {
    
    private long numberOfAccounts;
    private Money totalBalance;

    public BankSummary(long numberOfAccounts, Money totalBalance) {
        this.numberOfAccounts = numberOfAccounts;
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "BankSummary{" +
                "numberOfAccounts=" + numberOfAccounts +
                ", totalBalance=" + totalBalance +
                '}';
    }

}
