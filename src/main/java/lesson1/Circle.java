package lesson1;

public class Circle implements Shape{

    private double diameter;

    public Circle(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public double perimeter() {
        return 3.14 * diameter;
    }
}
