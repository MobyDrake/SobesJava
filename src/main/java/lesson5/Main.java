package lesson5;


import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        List<Student> students;

        for (int i = 0; i < 1000; i++) {
            dao.save(new Student("Bob - " + (i+1), i));
        }

        students = dao.findAll();

        for (Student st : students) {
            System.out.println(st);
        }


        dao.delete(students);

    }
}
