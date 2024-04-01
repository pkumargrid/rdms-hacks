# Database Management Tasks

## Task 1: Database Inconsistency Demonstration

### Case Description

In this task, we aim to demonstrate how the database state can become inconsistent due to concurrent transactions.

### Implementation

- **Database Schema**:
  - Tables: `admin`, `health_care_provider`, `doctor`
  - See the `Initialize` class in the `initializing.layer.database` package for schema initialization.
- **Dummy Data**:
  - Refer to the `Insert` class for populating the tables with dummy values.
- **Transactions**:
  - **Thread1**:
    - Updates the email of the doctor with id 1 to 'doctor_thread1@gmail.com'.
  - **Thread2**:
    - Updates the email of the doctor with id 1 to 'doctor_thread2@gmail.com'.
  - Both transactions have the isolation level set to `READ_COMMITTED`.

### Behavior

Due to the concurrent nature of transactions, the system may not guarantee the order in which transactions commit, leading to inconsistency in the database state.

### Non-Transactional Scenario

- **Thread3**:
  - Performs intensive work and fails before completing the transaction due to an arithmetic exception.
  - Inserts a new record into the `doctor` table.
- **Thread4**:
  - Attempts to work with the record added by Thread3 but cannot due to the failure of Thread3.

## Task 2: Non-Default Transaction Isolation Levels

### Case Description

In this task, we explore scenarios where non-default transaction isolation levels are needed.

### Default Isolation Level Issue

- **Thread5**:
  - Reads a piece of data and performs intensive work.
  - Reads the same data again but gets different results due to an update by Thread6 in between.

### Correct Isolation Level Demonstration

Setting the transaction isolation level to `REPEATABLE_READ` ensures that Thread5 sees the same record as it was during the initial stage, maintaining consistency.

## Task 3: Indexing for Performance Improvement

### Case Description

In this task, we consider the need for indexing to optimize query performance, especially with a large dataset.

### Implementation

- **Data Population**:
  - Used `Initialize` class to populate tables with millions of rows.
- **Indexing**:
  - Created multi-column indexes on relevant columns (`health_care_provider_id` and `name`).
  - See the `Index` class for index creation.

### Observations

- **Execution Plan**:
  - Without Index: Hash Join with high execution time.
  - With Index: Merge Join with significantly reduced execution time.

## Conclusion

These tasks highlight the importance of proper database management techniques such as transaction management and indexing for ensuring data consistency and optimizing performance.

## How to Run

1. Set your own database credentials in `UserCredentials` and `DatabaseCredentials` classes.
2. Compile and run the Java classes for each task.
3. Observe the behavior and performance of the system in each scenario.

## Contributions

Contributions are welcome! If you find any issues or have suggestions for improvement, feel free to open an issue or create a pull request.
