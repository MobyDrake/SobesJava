package lesson1;

import lesson1.builder.Person;
import lesson1.shape.Circle;
import lesson1.shape.Shape;
import lesson1.shape.Square;
import lesson1.shape.Triangle;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .setFirstName("bob")
                .setAge(10)
                .createPerson();
        System.out.println(person.getFirstName());

        Square square = new Square(4);
        Circle circle = new Circle(3);
        Triangle triangle = new Triangle(2, 3, 5);

        info(square);
        info(circle);
        info(triangle);

        Car car = new Lorry();
        car.move();
    }

    public static void info(Shape shape) {
        System.out.println(shape.perimeter());
    }
}
