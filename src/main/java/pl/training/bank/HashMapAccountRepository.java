package pl.training.bank;

import java.util.*;

public class HashMapAccountRepository implements AccountRepository {

    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account save(Account account) {
        accounts.put(account.getNumber(), account);
        return account;
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return Optional.ofNullable(accounts.get(number));
    }
}
