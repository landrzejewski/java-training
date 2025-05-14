package pl.training.module06_07.repository;

import pl.training.module06_07.common.Page;
import pl.training.module06_07.common.PageRequest;
import pl.training.module06_07.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ArrayListAccountRepository implements AccountRepository {

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account save(Account account) {
        accounts.add(account);
        return account;
    }

    @Override
    public Page<Account> findAll(PageRequest pageRequest) {
        var startIndex = pageRequest.offest();
        var endIndex = Math.min(startIndex + pageRequest.size(), accounts.size());
        var totalPages = (long) Math.ceil((double) accounts.size() / pageRequest.size());
        var items = accounts.subList(startIndex, endIndex);
        return new Page<>(items, totalPages);
    }

    @Override
    public Stream<Account> findAllStream() {
        return accounts.stream();
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return findAllStream()
                .filter(account -> account.hasNumber(number))
                .findFirst();
    }

}
