package Dailyworkingques;

import java.util.Scanner;


public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of the array (N) and the number of rotations (K): ");
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        System.out.print("Enter the array elements: ");
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        // Rotate the array
        rotateArray(array, K);

        // Print the rotated array
        System.out.println("Rotated array:");
        for (int i = 0; i < N; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void rotateArray(int[] array, int K) {
        int N = array.length;
        K = K % N; // To handle cases where K > N

        // Reverse the entire array
        reverseArray(array, 0, N - 1);

        // Reverse the first K elements
        reverseArray(array, 0, K - 1);

        // Reverse the remaining elements
        reverseArray(array, K, N - 1);
    }

    public static void reverseArray(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}

