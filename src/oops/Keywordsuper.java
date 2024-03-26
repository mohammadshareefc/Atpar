package oops;

class Goat{
    String color="white";
}
class sheep extends Goat{
    String color="black";
    void printColor(){
        System.out.println(color);//prints color of Dog class
        System.out.println(super.color);//prints color of Animal class
    }
}
class Keywordsuper{
    public static void main(String args[]){
        sheep s=new sheep();
        s.printColor();
    }}
//We can use super keyword to access the data member or field of parent class.
// It is used if parent class and child class have same fields.