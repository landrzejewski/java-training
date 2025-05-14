package pl.training.module06.repository;

import pl.training.module06.common.Page;
import pl.training.module06.common.PageRequest;
import pl.training.module06.model.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class HashMapAccountRepository implements AccountRepository {

    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account save(Account account) {
        accounts.put(account.getNumber(), account);
        return account;
    }

    @Override
    public Page<Account> findAll(PageRequest pageRequest) {
        var accountList = new ArrayList<>(accounts.values());
        var startIndex = pageRequest.offest();
        var endIndex = Math.min(startIndex + pageRequest.size(), accountList.size());
        var totalPages = (long) Math.ceil((double) accountList.size() / pageRequest.size());
        var items = accountList.subList(startIndex, endIndex);
        return new Page<>(items, totalPages);
    }

    @Override
    public Stream<Account> findAllStream() {
        return accounts.values().stream();
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return Optional.ofNullable(accounts.get(number));
    }

}
