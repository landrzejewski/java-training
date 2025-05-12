package pl.training.module02;

public class PrimitiveTypes {

    public static void main(String[] args) {
        // nazwa_typu nazwa_zmiennej [= wartość];

        boolean isValid = true;
        System.out.println("Is valid: " + isValid);
        int age;
        // System.out.println("Age: " + age); // błąd kompilacji - zmienna nie ma przypisanej wartości

        long id = 1_000_000_000; // int mieści się long, podkreślenie jest ignorowane
        // int value = id; // long nie mieści się w int
        int myValue = (int) id; // jawna konwersja z potencjalnym ryzykiem utraty informacji

        float result = 10.13F; // jawne wskazanie typu literału
    }

}
