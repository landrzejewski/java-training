package pl.training.module04_05;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private int counter = 0;

    @Override
    public String next() {
        return "" + ++counter;
    }

}
