package pl.training.module02;

// import pl.training.module01.HelloWorld;

// import pl.training.module01.*;

import pl.training.module01.HelloWorld;

public class ReferenceTypes {

    Account account;

    void main() {
        String name = "Jan";
        String secondName = "Jan"; // alokacja pamięci może nie być przeprowadzona, jeśli tekst jest już w pamięci

        //secondName = name + " Kowalski";

        String thirdName = new String("Jan"); // new zawsze wymusza alokację pamięci

        var age = 30;
        var text = "Ala ma " + (age + 1) + " lat";
        System.out.println(text);

    /*
    int firstValue = 1;
    int secondValue = 1;
    System.out.println(firstValue == secondValue);
    System.out.println(name == secondName);
    System.out.println(name == thirdName);
    */
        Account firstAccount = new Account();
        firstAccount.number = "00001";
        firstAccount.balance = 100;
        firstAccount.isActive = true;

        var secondAccount = new Account(); // domyślny konstruktor, wygenerowany automatycznie
        secondAccount.number = "00002";
        secondAccount.balance = 300;
        secondAccount.isActive = false;

    /*System.out.println(firstAccount.number);
    System.out.println(secondAccount.number);

    firstAccount = secondAccount;*/

        System.out.println(firstAccount.number);
        System.out.println(secondAccount.number);

        firstAccount.deposit(10);
        secondAccount.withdraw(30);

        System.out.println(firstAccount);
        System.out.println(secondAccount);

        int score = 5;
        int myScore = score; // utworzenie kopii zmiennej
        myScore += 1;
        passByValue(score);
        System.out.println("Score: " + score);
        System.out.println("myScore: " + myScore);

        Account testAccount = new Account();
        passByReference(testAccount);
        System.out.println("pl.training.module02.Account balance: " + testAccount.balance);

        {
            int x = 1;
            System.out.println("X: " + x);
            System.out.println("pl.training.module02.Account balance: " + testAccount.balance);
        }
        // System.out.println("X: " + x);

        var firstUser = new User();
        firstUser.firstName = "Jan";
        firstUser.lastName = "Kowalski";
        firstUser.age = 18;
        firstUser.printInfo();

        // new HelloWorld();

        // System.out.println(account.balance); // NullPointerException
    }

    public void passByValue(int value) { // przekazanie kopii zmiennej
        value = value + 1;  // zwykle nie jest dobrą praktyką modyfikowanie otrzymanych parametrów
        System.out.println("Value: " + value);
    }

    public void passByReference(Account account) {  // przekazanie kopii referencji do obiektu
        account.deposit(10);
        System.out.println("pl.training.module02.Account balance: " + account.balance);
    }

}