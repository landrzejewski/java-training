package pl.training.module06.model.repository;

import pl.training.module06.model.Account;

import java.util.*;

public class TreeMapAccountRepository implements AccountRepository {

    private final Map<String, Account> accounts = new TreeMap<>();

    @Override
    public Account save(Account account) {
        accounts.put(account.getNumber(), account);
        return account;
    }

    @Override
    public Optional<Account> findByNumber(String accountNumber) {
        var account = accounts.get(accountNumber);
        return Optional.ofNullable(account);
    }

    @Override
    public Page findAll(PageRequest pageRequest) {
        var startIndex = pageRequest.index() * pageRequest.size();
        var endIndex = Math.min(startIndex + pageRequest.size(), accounts.size() - startIndex);
        var totalPages = (long) Math.ceil((double) count() / pageRequest.size());
        var items = ((List<Account>) accounts.values()).subList(startIndex, endIndex);
        return new Page(items, totalPages);
    }

    @Override
    public long count() {
        return accounts.size();
    }

}
