package lesson5;

import org.hibernate.Session;

import java.util.List;

public class StudentDao {

    public Student findById(Long id) {
        return HibernateSessionFactory.getSessionFactory()
                .openSession()
                .get(Student.class, id);
    }

    public void save(Student student) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    public void saveAll(List<Student> students) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (Student st : students) {
            session.save(st);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void update(Student student) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Student student) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Long id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Student st = session.get(Student.class, id);
        session.delete(st);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(List<Student> students) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for(Student st : students) {
            session.delete(st);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Student> findAll() {
        return HibernateSessionFactory.getSessionFactory()
                .openSession()
                .createQuery("select a from Student a", Student.class).list();
    }
}
