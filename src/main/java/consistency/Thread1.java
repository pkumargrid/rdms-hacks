package consistency;

import java.sql.*;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Thread1 extends Thread{

    @Override
    public void run() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value);
            connection.setAutoCommit(false);
            try(Statement statement = connection.createStatement()) {
                //done lot of job
                final String change = """
                        INSERT INTO doctor (name, email, password, health_care_provider_id) VALUES
                        ('Doctor3', 'doctor3@example.com', 'doctorpassword3', 1);
                        """;
                statement.executeUpdate(change);
                int a = 1/0;
                final String update = """
                        delete from doctor where id = %d
                        """.formatted(3);
                statement.executeUpdate(update);
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
