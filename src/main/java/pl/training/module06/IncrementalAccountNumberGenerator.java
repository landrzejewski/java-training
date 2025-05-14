package pl.training.module06;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private int counter = 0;

    @Override
    public String next() {
        return String.format("%012d", ++counter);
    }

}
