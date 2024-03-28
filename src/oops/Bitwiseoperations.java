package oops;

public class Bitwiseoperations {
    public static void main(String[] args) {
        int a = 2;
        int b = 4;
        int p = 2;
        int q = 1;
        int x = 6;

        // Performing bitwise AND, OR, and XOR operations
        int resultAnd = a & b;
        int resultOr = a | b;
        int resultXor = a ^ b;

        // Performing left and right bitwise shift operations
        int resultLeftShift = p << q;
        int resultRightShift = p >> q;

        // Performing bitwise NOT operation
        int resultNot = ~x;

        // Printing the results
        System.out.println( resultAnd);
        System.out.println( resultOr);
        System.out.println( resultXor);
        System.out.println( resultLeftShift);
        System.out.println(resultRightShift);
        System.out.println(  resultNot);
    }
}
