public class Methods {
    public static void main(String args[])
    {
        Methods m= new Methods();
        m.sayHello();
        m.addNumbers(3,2);
        m.addNumbers(4,6);
        int k = m.muiyNumbers(5,6);
        System.out.println(k);
    }
        void sayHello(){
            System.out.println("Hello");
    }
    void addNumbers(int a,int b){
            System.out.println(a+b);
        }
        int muiyNumbers(int a,int b){
        int x = a*b;
        return x;
    }
    }

