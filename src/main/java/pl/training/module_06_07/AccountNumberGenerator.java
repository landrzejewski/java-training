package pl.training.module_06_07;

public interface AccountNumberGenerator {

    String next();

    default String getVersion() {
        return "1.0.0";
    }

}
