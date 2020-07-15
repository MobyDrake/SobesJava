package lesson1;

/**
 1) Implements интервейсов в абстрактном классе + реализовать стандартные версии методов,
    если надо то переопределить в нужных классах, как на пример в Lorry;
 2) Переписать метод open - сделать его таким же как start;
 3) Объединить интерфейсы в один, ибо логика в них одна, и было бы страно что Машина может ехать, а останоаитсья нет;
 4) В Lorry была синтаксическая ошибка - множественное наследование от Car, Moveable, Stopable и класс не может
    наследоваться от интерфейса.
**/
public abstract class Car implements Moveable, Stopable {
    public Engine engine;
    private String color;
    private String name;


    protected void start() {
        System.out.println("Car starting");
    }

    protected void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move(){
        System.out.println("Car is moving");
    }

    @Override
    public void stop(){
        System.out.println("Car is stop");
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
