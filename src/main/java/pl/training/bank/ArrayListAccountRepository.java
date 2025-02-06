package pl.training.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ArrayListAccountRepository implements AccountRepository {

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account save(Account account) {
        accounts.add(account);
        return account;
    }

    @Override
    public List<Account> findAll() {
        // return new ArrayList<>(accounts);
        return Collections.unmodifiableList(accounts);
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return accounts.stream()
                .filter(account -> account.hasNumber(number))
                .findFirst();
    }

}
