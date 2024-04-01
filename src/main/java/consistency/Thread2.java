package consistency;

import java.sql.*;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Thread2 extends Thread{

    @Override
    public void run() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try(Statement statement = connection.createStatement()) {
                final String change = """
                        update doctor set email = 'doctor_thread2@gmail.com' where id = 1
                        """;
                statement.executeUpdate(change);
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    System.out.println("Transaction is being rolled back for " + this.getName());
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}