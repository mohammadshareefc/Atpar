public class Staticexample {
    // Static field
    public static int staticField = 10;

    // Non-static field
    public int nonStaticField = 20;

    // Static method
    public static void staticMethod() {
        System.out.println("This is a static method");
    }

    // Non-static method
    public void nonStaticMethod() {
        System.out.println("This is a non-static method");
    }

    public static void main(String[] args) {
        // Accessing static members
        System.out.println(Staticexample.staticField); // Output: 10
        Staticexample.staticMethod(); // Output: This is a static method

        // Creating instances
        Staticexample obj1 = new Staticexample();
        Staticexample obj2 = new Staticexample();

        // Accessing non-static members
        System.out.println(obj1.nonStaticField); // Output: 20
        obj1.nonStaticMethod(); // Output: This is a non-static method
        System.out.println(obj2.nonStaticField); // Output: 20
        obj2.nonStaticMethod(); // Output: This is a non-static method

        // Modifying static field
        Staticexample.staticField = 30;
        System.out.println(obj1.staticField); // Output: 30
        System.out.println(obj2.staticField); // Output: 30

        // Modifying non-static field
        obj1.nonStaticField = 40;
        System.out.println(obj1.nonStaticField); // Output: 40
        System.out.println(obj2.nonStaticField); // Output: 20
    }
}

