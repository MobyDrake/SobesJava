package lesson1;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .setFirstName("bob")
                .setAge(10)
                .createPerson();
        System.out.println(person.getFirstName());

    }
}
