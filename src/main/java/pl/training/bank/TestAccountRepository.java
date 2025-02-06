package pl.training.bank;

import java.util.Optional;

public class TestAccountRepository implements AccountRepository {

    @Override
    public Account save(Account account) {
        return account;
    }

    @Override
    public Account[] findAll() {
        return new Account[0];
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return Optional.empty();
    }

}
