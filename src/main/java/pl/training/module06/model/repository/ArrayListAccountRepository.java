package pl.training.module06.model.repository;

import pl.training.module06.model.Account;

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
    public Optional<Account> findByNumber(String accountNumber) {
        /*for (Account account : accounts) {
            if (account.hasNumber(accountNumber)) {
                return Optional.of(account);
            }
        }
        return Optional.empty();*/

        return accounts.stream()
                .filter(account -> account.hasNumber(accountNumber))
                .findFirst();
    }

    @Override
    public Page findAll(PageRequest pageRequest) {
        var startIndex = pageRequest.index() * pageRequest.size();
        var endIndex = Math.min(startIndex + pageRequest.size(), accounts.size() - startIndex);
        var totalPages = (long) Math.ceil((double) count() / pageRequest.size());
        var items = accounts.subList(startIndex, endIndex);
        return new Page(items, totalPages);
    }

    @Override
    public Stream<Account> findAll() {
        return accounts.stream();
    }

    @Override
    public long count() {
        return accounts.size();
    }

}
