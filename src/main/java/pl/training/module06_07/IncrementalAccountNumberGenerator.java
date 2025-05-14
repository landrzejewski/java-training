package pl.training.module06_07;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private int counter = 0;

    @Override
    public String next() {
        return "" + ++counter;
    }

}
