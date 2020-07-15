package lesson1;

public class Square implements Shape {

    private double height;

    public Square(double height) {
        this.height = height;
    }

    @Override
    public double perimeter() {
        return height * 4;
    }
}
