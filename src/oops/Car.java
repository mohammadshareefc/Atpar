package oops;

public class Car {
    int id;
    String name;
    Engine engine;

    public Car(int id, String name, Engine engine) {
        this.id = id;
        this.name = name;
        this.engine = engine;
    }

    void display() {
        System.out.println(id + " " + name);
        System.out.println(engine.Head + " " + engine.Bore + " " + engine.motor);
    }

        public static void main (String args[])
        {
            Engine
            engine = new Engine("kia", "seltos", "carnival");

            Car c = new Car(1, "Hundayi",engine);
            Car c2 = new Car(2, "Mobies",engine);

            c.display();
c2.display();
        }
    }


