package pl.training.module04_05.model.generator;

import java.util.UUID;

public class UuidAccountNumberGenerator implements AccountNumberGenerator {

    @Override
    public String next() {
        return UUID.randomUUID().toString();
    }

}
