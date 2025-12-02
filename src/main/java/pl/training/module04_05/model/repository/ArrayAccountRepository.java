package pl.training.module04_05.model.repository;

import pl.training.module04_05.model.Account;

public class ArrayAccountRepository implements AccountRepository {

    private Account[] accounts = new Account[5];
    private int index;

    @Override
    public Account save(Account account) {
        accounts[index++] = account; // todo capacity
        return account;
    }

    @Override
    public Account findByNumber(String accountNumber) {
        for(Account account : accounts) {
            if (account.hasNumber(accountNumber)) {
                return account;
            }
        }
        return null; // todo return null
    }

    @Override
    public Account[] findAll() {
        var result = new Account[index];
        System.arraycopy(accounts, 0, result, 0, index);
        return result;
    }

    @Override
    public long count() {
        return index;
    }

}
