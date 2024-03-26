package oops;

class Birds{
    Birds(){System.out.println("Birds are flying");}
}
class Pegion extends Birds{
    Pegion(){
        super();
        System.out.println("Pegion is created");
    }
}
class Examplesuper{
    public static void main(String args[]){
        Pegion p=new Pegion();
    }}
