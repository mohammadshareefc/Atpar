/**Write a java program to get the required Pattern according to the size of input given.
 Sample Input 1:
 line_1: 5**/

package Dailyworkingques;

import java.util.Scanner;

public class Pattern {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the size of the pattern: ");
        int size = s.nextInt();

        // Generating the number pattern
        generateNumberPattern(size);
    }

    public static void generateNumberPattern(int size) {
        for(int i=1;i<=size;i++){
            for(int j=size;j>=i;j--){
                System.out.print("*");

            }
            System.out.println();
        }
        for(int i=2;i<=size;i++){
            for(int j=0;j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}



