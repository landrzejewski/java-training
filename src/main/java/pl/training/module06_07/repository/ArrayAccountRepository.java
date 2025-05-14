package pl.training.module06_07.repository;

import pl.training.module06_07.common.Page;
import pl.training.module06_07.common.PageRequest;
import pl.training.module06_07.model.Account;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class ArrayAccountRepository implements AccountRepository {

    private static final int SIZE_MULTIPLIER = 2;

    private Account[] accounts = new Account[5];
    private int index = 0;

    @Override
    public Account save(Account account) {
        ensureCapacity();
        accounts[index++] = account;
        return account;
    }

    private void ensureCapacity() {
        if (index == accounts.length - 1) {
            var newAccounts = new Account[accounts.length * SIZE_MULTIPLIER];
            System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
            accounts = newAccounts;
        }
    }

    @Override
    public Page<Account> findAll(PageRequest pageRequest) {
        var startIndex = pageRequest.offest();
        var endIndex = Math.min(startIndex + pageRequest.size(), accounts.length);
        var totalPages = (long) Math.ceil((double) accounts.length / pageRequest.size());
        var items = Arrays.asList(Arrays.copyOfRange(accounts, startIndex, endIndex));
        return new Page<>(items, totalPages);
    }

    @Override
    public Stream<Account> findAllStream() {
        return Arrays.stream(Arrays.copyOfRange(accounts, 0, index));
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return findAllStream()
                .filter(account -> account.hasNumber(number))
                .findFirst();
    }

}
