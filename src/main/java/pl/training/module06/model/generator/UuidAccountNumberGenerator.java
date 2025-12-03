package pl.training.module06.model.generator;

import java.util.UUID;

public class UuidAccountNumberGenerator implements AccountNumberGenerator {

    @Override
    public String next() {
        return UUID.randomUUID().toString();
    }

}
