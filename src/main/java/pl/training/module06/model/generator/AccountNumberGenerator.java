package pl.training.module06.model.generator;

public interface AccountNumberGenerator {

    String next();

    default String getVersion() {
        return "1.0.0";
    }

}
