package consistency;

import java.sql.*;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Thread5 extends Thread{
    @Override
    public void run() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try(Statement statement = connection.createStatement()) {
                final String retrieve = """ 
                select id from doctor where id = 2;
                """;
                findById(statement);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                }
                findById(statement);
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

    private void findById(Statement statement) throws SQLException {
        try(ResultSet resultSet = statement.executeQuery("select * from doctor where id = 2;\n")){
            while(resultSet.next()) {
                System.out.println("id: " + resultSet.getInt(1));
                System.out.println("name: " + resultSet.getString(2));
                System.out.println("email: " + resultSet.getString(3));
            }
        }
    }
}
