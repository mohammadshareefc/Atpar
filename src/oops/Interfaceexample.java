package oops;


interface printable{
    void print();
}
class Interfaceexample implements printable{
    public void print(){System.out.println("Hello");}

    public static void main(String args[]){
        Interfaceexample obj = new Interfaceexample();
        obj.print();
    }
}
