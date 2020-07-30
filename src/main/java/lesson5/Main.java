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

        //сохранение
        Student student = new Student("Miky", 4);
        dao.save(student);

        //поиск по имени
        student = dao.findByName("Bob - 3");
        System.out.println(student);

        //обновление
        student.setName("Vova");
        dao.update(student);


        //получение всех записей
        students = dao.findAll();

        for (Student st : students) {
            System.out.println(st);
        }

        //удаление всех записей
        students = dao.findAll();
        dao.delete(students);

    }
}
