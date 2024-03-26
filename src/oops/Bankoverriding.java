package oops;

//Java Program to demonstrate the real scenario of Java Method Overriding
//where three classes are overriding the method of a parent class.
//Creating a parent class.
class Bank1{
    int getRateOfInterest(){return 0;}
}
//Creating child classes.
class SBI1 extends Bank1{
    int getRateOfInterest(){return 8;}
}

class ICICI1 extends Bank1{
    int getRateOfInterest(){return 7;}
}
class AXIS1 extends Bank1{
    int getRateOfInterest(){return 9;}
}
//Test class to create objects and call the methods
class Bankoverriding{
    public static void main(String args[]){
        SBI s=new SBI();
        ICICI i=new ICICI();
        AXIS a=new AXIS();
        System.out.println("SBI Rate of Interest: "+s.getRateOfInterest());
        System.out.println("ICICI Rate of Interest: "+i.getRateOfInterest());
        System.out.println("AXIS Rate of Interest: "+a.getRateOfInterest());
    }
}


//No, a static method cannot be overridden.
// It can be proved by runtime polymorphism, so we will learn it later.