package pl.training.module06_07;

import pl.training.module06_07.model.*;
import pl.training.module06_07.repository.AccountRepository;
import pl.training.module06_07.repository.ArrayAccountRepository;
import pl.training.module06_07.repository.ArrayListAccountRepository;
import pl.training.module06_07.repository.HashMapAccountRepository;

import java.util.Currency;

public class Application {

    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("PLN");

    public static void main(String[] args) {
        AccountNumberGenerator accountNumberGenerator =  new IncrementalAccountNumberGenerator(); // new UuidAccountNumberGenerator();
        AccountRepository accountRepository = new HashMapAccountRepository(); // new ArrayListAccountRepository(); // new ArrayAccountRepository();
        var bank = new BankService(accountNumberGenerator, accountRepository);

        //---------------------------------------------------------------------------------------

        var firstAccount = bank.createAccount(DEFAULT_CURRENCY);
        var secondAccount = bank.createAccount(DEFAULT_CURRENCY);

        var amount = Money.of(100, DEFAULT_CURRENCY);
        try {
            bank.deposit(firstAccount.getNumber(), amount);
            bank.transfer(firstAccount.getNumber(), secondAccount.getNumber(), Money.of(10, DEFAULT_CURRENCY));
            bank.withdraw(firstAccount.getNumber(), Money.of(11, DEFAULT_CURRENCY));
        } catch (AccountNotFoundException accountNotFoundException) {
            System.out.println("Account not found: " + accountNotFoundException.getAccountNumber());
        } catch (InsufficientFundsException insufficientFundsException) {
            System.out.println("Insufficient funds");
        }

        System.out.println(bank.generateReport());

    }

}
