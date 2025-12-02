package pl.training.module04_05.model.generator;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private long counter;

    @Override
    public String next() {
        return "" + ++counter;
    }

}
