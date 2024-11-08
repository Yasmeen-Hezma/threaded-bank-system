import java.util.Random;

public class CustomerTask implements Runnable {

    private final BankAccount account1;
    private final BankAccount account2;
    private final int numActions;
    private final Random random = new Random();

    public CustomerTask(BankAccount account1, BankAccount account2,int numActions) {
        this.account1 = account1;
        this.account2 = account2;
        this.numActions=numActions;
    }

    @Override
    public void run() {
        // Each thread performs 5 random operations
        for (int i = 0; i < numActions; i++) {
            int action = random.nextInt(3);
            long amount = 50 + random.nextInt(200);
            try {
                switch (action) {
                    case 0 -> account1.deposit(amount);
                    case 1 -> account1.withdraw(amount);
                    case 2 -> account1.transfer(account2, amount);
                }
            }catch (InsufficientFundsException | InvalidAmountException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Display the final balance of account1 at the end of the task
        System.out.println("Final balance of " + account1.getAccountHolder() + "'s account: " + account1.getBalance());
    }

}
