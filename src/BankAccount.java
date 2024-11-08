import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {
    private long balance;
    private final String accountHolder;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public String getAccountHolder() {
        return accountHolder;
    }

    public BankAccount(String accountHolder, long initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Read the balance using a read lock
    public long getBalance() {
        lock.readLock().lock();
        try {
            return balance;
        } finally {
            lock.readLock().unlock();
        }
    }

    // Deposit using a write lock
    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        lock.writeLock().lock();
        try {
            //if (amount > 0) {
            balance += amount;
            System.out.println(accountHolder + " deposited " + amount + ". New balance: " + balance);
            // }
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Withdraw using a write lock
    public void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        lock.writeLock().lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(accountHolder + " withdrew " + amount + ". New balance: " + balance);
            } else {
                throw new InsufficientFundsException("Insufficient balance: " + balance);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Transfer using write locks for both accounts
    public void transfer(BankAccount targetAccount, long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be positive.");
        }
        if (this == targetAccount) return;

        // Ensure locks are acquired in a consistent order to prevent deadlock
        BankAccount first = (System.identityHashCode(this) < System.identityHashCode(targetAccount)) ? this : targetAccount;
        BankAccount second = (this == first) ? targetAccount : this;

        first.lock.writeLock().lock();
        second.lock.writeLock().lock();
        try {
            if (amount <= balance) {
                this.balance -= amount;
                targetAccount.balance += amount;
                System.out.println(accountHolder + " transferred " + amount + " to " + targetAccount.accountHolder + ". New balance: " + balance);
            } else {
                throw new InsufficientFundsException("Insufficient balance: " + balance);
            }
        } finally {
            first.lock.writeLock().unlock();
            second.lock.writeLock().unlock();
        }
    }
}
