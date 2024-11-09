# Threaded Bank System

A simple Java application demonstrating basic multi-threading concepts using `ReadWriteLock` to handle concurrent access to bank account data. This project simulates a banking system where multiple threads (customers) perform transactions such as deposits, withdrawals, and transfers on shared bank accounts.

## Features

- **Concurrent Transactions**: Simulates multiple threads performing transactions on bank accounts.
- **Synchronization**: Ensures thread-safe balance updates using `ReadWriteLock`.
- **Custom Exception Handling**: Implements custom exceptions for handling insufficient funds and invalid transaction amounts.

## Project Structure

- **`BankSystem`**: Main class that sets up bank accounts and initiates transactions using an `ExecutorService`.
- **`BankAccount`**: Represents a bank account with synchronized methods for deposits, withdrawals, and transfers.
- **`CustomerTask`**: A runnable task that simulates random actions (deposit, withdrawal, transfer) by a customer.
- **`InsufficientFundsException`**: Custom exception for handling insufficient balance during transactions.
- **`InvalidAmountException`**: Custom exception for handling invalid transaction amounts.

## Setup Instructions

### Prerequisites

Ensure you have the following installed on your machine:

- Java Development Kit (JDK)

### Clone the Repository

To clone the repository and navigate to the project directory:

```bash
git clone https://github.com/Yasmeen-Hezma/threaded-bank-system.git
cd threaded-bank-system/src
```
## Run the Application

To run the `BankSystem` application, execute the following command:

```bash
java BankSystem
```
### Output example:
```
Alice withdrew 79. New balance: 921
Bob withdrew 227. New balance: 1273
Bob withdrew 95. New balance: 1178
Alice deposited 61. New balance: 982
Alice withdrew 122. New balance: 860
Bob transferred 211 to Alice. New balance: 967
Final balance of Alice's account: 1071
Final balance of Bob's account: 967
All transactions completed.
```
