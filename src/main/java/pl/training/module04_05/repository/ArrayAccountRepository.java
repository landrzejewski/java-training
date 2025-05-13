package pl.training.module04_05.repository;

import pl.training.module04_05.model.Account;

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
    public Account[] findAll() {
        var result = new Account[index];
        System.arraycopy(accounts, 0, result, 0, index);
        return result;
    }

    @Override
    public Account findByNumber(String number) {
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            var account = accounts[currentIndex];
            if (account.hasNumber(number)) {
                return account;
            }
        }
        return null;
    }

}
