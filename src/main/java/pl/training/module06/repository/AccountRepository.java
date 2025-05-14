package pl.training.module06.repository;

import pl.training.module06.common.Page;
import pl.training.module06.common.PageRequest;
import pl.training.module06.model.Account;

import java.util.Optional;
import java.util.stream.Stream;

public interface AccountRepository {

    Account save(Account account);

    Page<Account> findAll(PageRequest pageRequest);

    Stream<Account> findAllStream();

    Optional<Account> findByNumber(String number);

}
