package pl.training.module02;

public class ReferenceTypes {

    public static void main(String[] args) {
        String name = "John"; // alokacja pamięci może nie być przeprowadzona, jeśli tekst jest już w pamięci
        String secondName = "John";
        String lastName = new String("Smith"); // new zawsze wymusza alokację pamięci

        int age = 6;
        String text = "Ala ma " + age + " lat";
        System.out.println(text);

        int firstValue = 1;
        int secondValue = 2;
        System.out.println("Result: " + firstValue + secondValue);
        System.out.println("Result: " + (firstValue + secondValue));
        System.out.println(firstValue + secondValue + " is result");

        Account firstAccount = new Account(); // domyślny konstruktor, wygenerowany automatycznie
        firstAccount.number = "123456789";
        System.out.println("First account number: " + firstAccount.number);
        Account myAccount = firstAccount;
        System.out.println("My account number: " + myAccount.number);

        Account secondAccount = new Account();
        secondAccount.number = "11111111";
        System.out.println("Second account number: " + secondAccount.number);
        secondAccount.deposit(1_000);
        secondAccount.withdraw(500);
        secondAccount.printBalance();

        var referenceTypes = new ReferenceTypes();

        int score = 5;
        int myScore = score; // utworzenie kopii zmiennej
        myScore = myScore + 1;
        referenceTypes.passByValue(score);
        System.out.println("Score: " + score);
        System.out.println("My score: " + myScore);

        Account testAccount = new Account();
        // Account testAccount2 = testAccount; // utworzenie kopii referencji do obiektu
        referenceTypes.passByReference(testAccount);
        System.out.println("Account balance: " + testAccount.balance);

        {
            int x = 1;
            testAccount.deposit(100);
        }

        var user = new User("John", "Smith", 25);
        user.printInfo();


    }

    public void passByValue(int value) { // przekazanie kopii zmiennej
        value = value + 1; // zwykle nie jest dobrą praktyką modyfikowanie otrzymanych parametrów
        System.out.println("Value: " + value);
    }

    public void passByReference(Account account) { // przekazanie kopii referencji do obiektu
        account.deposit(500);
        System.out.println("Account balance: " + account.balance);
    }

}
