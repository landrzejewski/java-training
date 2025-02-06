package pl.training;

import pl.training.bank.*;

import java.util.Objects;

public class Application {

    private static final String FIRST_ACCOUNT_NUMBER = "0000000001";
    private static final String SECOND_ACCOUNT_NUMBER = "0000000002";

    public static void main(String[] args) {
        var account = new Account(FIRST_ACCOUNT_NUMBER);
        var secondAccount = new Account(SECOND_ACCOUNT_NUMBER);

        AccountRepository repository = new ArrayAccountRepository();
        var bank = new Bank("Java Bank", repository);
        bank.add(account);
        bank.add(secondAccount);

        try {
            var money = new Money(-1, Currency.PLN);
            bank.deposit(FIRST_ACCOUNT_NUMBER, money);
        } catch (AccountNotFoundException | RuntimeException e) {
            System.out.println("Exception: " + e);
            return;
        } finally {
            System.out.println("After deposit");
        }

        bank.printReport();


        // equality of objects
        var boxOne = new Box(1);
        var boxTwo = new Box(1);
        System.out.println(boxOne == boxTwo);
        System.out.println(boxOne.equals(boxTwo));

        int a = 1;
        long b = a;

        Shape circle = new Circle();
        Shape square = new Rectangle();

        /*if (circle instanceof Circle) {
            Circle c = (Circle) square;
            System.out.println(c.getArea());
        }*/

        if (square instanceof Square s) {
            System.out.println(s.name);
        }



        var app = new Application();
        app.printShapeInfo(circle);
        app.printShapeInfo(square);
    }

    public  void printShapeInfo(Shape shape) {
        System.out.println(shape.getInfo());
    }

}

class Box {
    private final int value;

    public Box(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return value == box.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

interface Drawable {

    default void draw() {

    }

}

abstract class Shape implements Drawable {
    protected final String name = "shape";

    public String getInfo() {
        return "Name: " + name;
    }

    public abstract double getArea();

}

class Circle extends Shape {

    @Override
    public String getInfo() {
        return super.getInfo() + " (Circle)";
    }

    @Override
    public double getArea() {
        return 10.0;
    }

}

class Rectangle extends Shape {

    public String getInfo() {
        return super.getInfo() + " (Rectangle)";
    }

    @Override
    public double getArea() {
        return 0;
    }

    public String getColor() {
        return "Red";
    }

}

class Square extends Rectangle {

    @Override
    public String getInfo() {
        return super.getInfo() + " (Square)";
    }

}
