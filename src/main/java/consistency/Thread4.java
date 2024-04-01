package consistency;

import java.sql.*;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Thread4 extends Thread {
    @Override
    public void run() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try(Statement statement = connection.createStatement()) {
                final String retrieve = """ 
                select id from doctor where id = 3;
                """;
                ResultSet resultSet = statement.executeQuery(retrieve);
                resultSet.next();
                int id = resultSet.getInt(1);
                final String change = """
                        update doctor set email = 'doctor_thread4@gmail.com' where id = %d
                        """.formatted(id);
                statement.executeUpdate(change);
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                e.printStackTrace();
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
