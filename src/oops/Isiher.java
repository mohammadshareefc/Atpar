package oops;
class Isiher{
    int cups=5;
}
class Virat extends Isiher {
    float salary= 12.5f;


    public static void main(String[] args) {
        Virat v = new Virat();
        System.out.println("ipl cups=" + v.cups);
        System.out.println("ipl salary=" + v.salary);

    }
}

