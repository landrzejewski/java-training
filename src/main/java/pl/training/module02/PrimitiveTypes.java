void main() {
    // nazwa_typu nazwa_zmiennej [= wartość];

    boolean isValid = true;
    System.out.println("Is valid: " +  isValid);

    /*
    int age;
    // System.out.println("Age: " + age); // błąd kompilacji - zmienna nie ma przypisanej wartości
    */

    int age;
    age = 20;
    System.out.println("Age: " +  age);

    long bigValue = 1_000_000_000_000L;  // int mieści się long, podkreślenie jest ignorowane
    int value = (int) bigValue; // jawna konwersja z potencjalnym ryzykiem utraty informacji
    long otherBigValue = value; // to jest bezpieczne, niejawna konwewrsja

    double price = 10.50;
    // float temperature = (float) 12.5;
    float temperature = 12.5F; // jawne wskazanie typu literału
}
