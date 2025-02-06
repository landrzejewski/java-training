package pl.training.bank;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public class ArrayAccountRepository implements AccountRepository, Serializable {

    private static final int SIZE_MULTIPLIER = 2;
    private static final int ZERO_INDEX = 0;

    private Account[] accounts = new Account[5];
    private int index;

    @Override
    public Account save(Account account) {
        if (index == accounts.length - 1) {
            var newAccounts = new Account[accounts.length * SIZE_MULTIPLIER];
            System.arraycopy(accounts, ZERO_INDEX, newAccounts, ZERO_INDEX, accounts.length);
            accounts = newAccounts;
        }
        accounts[index++] = account;
        return account;
    }

    @Override
    public Account[] findAll() {
        var result = new Account[index];
        System.arraycopy(accounts, ZERO_INDEX, result, ZERO_INDEX, index);
        return result;
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        int accountIndex = 0;
        while (accountIndex < index) {
            var account = accounts[accountIndex];
            if (account.hasNumber(number)) {
                return Optional.of(account);
            }
            accountIndex++;
        }
        return Optional.empty();
    }

}
