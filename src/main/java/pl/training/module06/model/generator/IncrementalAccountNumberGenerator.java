package pl.training.module06.model.generator;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private long counter;

    @Override
    public String next() {
        return String.format("%016d", counter++);
    }

    @Override
    public String getVersion() {
        return AccountNumberGenerator.super.getVersion() + "-1";
    }

}
