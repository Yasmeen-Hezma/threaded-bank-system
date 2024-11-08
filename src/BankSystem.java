import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankSystem {
    public static void main(String[] args) {
        // create two bank accounts
        BankAccount account1 = new BankAccount("Alice", 1000);
        BankAccount account2 = new BankAccount("Bob", 1500);
        int numActions = 3;
        // create an ExecutorService to manage customer threads
        ExecutorService service = Executors.newFixedThreadPool(2);
        // assign tasks to customer
        service.submit(new CustomerTask(account1, account2, numActions));
        service.submit(new CustomerTask(account2, account1, numActions));
        // shutdown the service safely
        service.shutdown();
        try {
            boolean finished = service.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                System.out.println("All transactions completed.");
            } else {
                System.out.println("Timeout reached before completing all transactions.");
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting for transactions to complete.");
        }
    }
}