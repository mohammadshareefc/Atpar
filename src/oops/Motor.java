package oops;

class Motor{
    final int speedlimit;//blank final variable

    Motor(){
        speedlimit=70;
        System.out.println(speedlimit);
    }

    public static void main(String args[]){
        new Motor();
    }
}