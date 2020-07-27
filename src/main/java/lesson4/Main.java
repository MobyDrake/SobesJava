package lesson4;

public class Main {
    public static void main(String[] args) {
        MyRepository<User> repository = new MyRepositoryImp<>(User.class);
        User user = repository.getById(1L);
        System.out.println(user.getId());
        System.out.println(user.getName());
    }
}
