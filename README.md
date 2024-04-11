# Database Management Tasks

## Task 1: Database Inconsistency Demonstration

### Case Description

In this task, we aim to demonstrate how the database state can become inconsistent due to non-transactional behaviour.

### Behavior

- Thread1 attempts to insert a new record with a new ID.
- An arithmetic exception occurs during the transaction.
- The transaction is rolled back, and the attempted changes are reverted.
- Thread1's attempt to delete the ID after the exception fails due to the rollback.
- Thread4 attempts to update the same record as Thread1, but its changes are also rolled back due to Thread1's rollback.
- In non-transactional behaviour, Thread3 operates without using transactions and eventually allows Thread4 to successfully update the record.

### Conclusion

This scenario highlights the importance of proper transaction management and handling exceptions to ensure database consistency and integrity in multi-threaded environments.


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
