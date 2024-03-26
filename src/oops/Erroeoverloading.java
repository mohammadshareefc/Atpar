package oops;
class Addition{
    static int add(int a,int b){return a+b;}
}
class Erroeoverloading {
    public static void main(String[] args){
        System.out.println(Addition.add(11,11));//ambiguity
    }}

/**Yes, by method overloading. You can have any number of main methods in a class by
 *  method overloading. But JVM calls main() method which receives string array as
 *  arguments only.**/