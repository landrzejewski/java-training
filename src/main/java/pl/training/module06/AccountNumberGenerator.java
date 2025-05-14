package pl.training.module06;

public interface AccountNumberGenerator {

    String next();

    default String getVersion() {
        return "1.0.0";
    }

}
