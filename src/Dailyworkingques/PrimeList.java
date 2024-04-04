package Dailyworkingques;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the upper bound of the range: ");
        int upperBound = scanner.nextInt();

        ArrayList<Integer> primeList = getPrimeNumbersInRange(upperBound);

        System.out.println("Prime numbers between 2 and " + upperBound + ":");
        for (int prime : primeList) {
            System.out.print(prime + " ");
        }
    }

    public static ArrayList<Integer> getPrimeNumbersInRange(int upperBound) {
        ArrayList<Integer> primeList = new ArrayList<>();
        for (int number = 2; number <= upperBound; number++) {
            if (isPrime(number)) {
                primeList.add(number);
            }
        }
        return primeList;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false; // 0 and 1 are not prime numbers
        }
        if (number <= 3) {
            return true; // 2 and 3 are prime numbers
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false; // numbers divisible by 2 or 3 are not prime
        }
        // Check divisibility with numbers from 5 to square root of number
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false; // if divisible by i or i + 2, not prime
            }
        }
        return true;
    }
}

