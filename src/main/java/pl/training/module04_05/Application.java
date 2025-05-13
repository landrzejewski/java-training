package pl.training.module04_05;

import pl.training.module04_05.model.Currency;
import pl.training.module04_05.model.InsufficientFundsException;
import pl.training.module04_05.model.Money;
import pl.training.module04_05.repository.AccountRepository;
import pl.training.module04_05.repository.ArrayAccountRepository;

public class Application {

    private static final Currency DEFAULT_CURRENCY = Currency.PLN;

    public static void main(String[] args) {
        AccountNumberGenerator accountNumberGenerator = new IncrementalAccountNumberGenerator();
        AccountRepository accountRepository = new ArrayAccountRepository();
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

        bank.printReport();
    }

}
