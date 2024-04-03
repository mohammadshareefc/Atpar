class PrintingMachine extends Thread {
    private char startChar;
    private char endChar;

    public PrintingMachine(char startChar, char endChar) {
        this.startChar = startChar;
        this.endChar = endChar;
    }

    public void run() {
        for (char c = startChar; c <= endChar; c++) {
            try {
                Thread.sleep(500); // Simulate printing time
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(Thread.currentThread().getName() + ": " + c);
        }
    }
}

public class ThreadSleepExample {
    public static void main(String[] args) {
        PrintingMachine machineA = new PrintingMachine('a', 'e');
        PrintingMachine machineB = new PrintingMachine('A', 'E');

        machineA.setName("Machine A");
        machineB.setName("Machine B");

        machineA.start();
        try{
            machineA.join();
        }
        catch(Exception e){

        }
        machineB.start();
    }
}
