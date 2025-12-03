package pl.training.module04_05.model.generator;

public interface AccountNumberGenerator {

    String next();

    default String getVersion() {
        return "1.0.0";
    }

}
