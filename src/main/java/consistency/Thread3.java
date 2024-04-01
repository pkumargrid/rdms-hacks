package consistency;

import java.sql.*;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Thread3 extends Thread {
    @Override
    public void run() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value);
            try(Statement statement = connection.createStatement()) {
                //done lot of job
                int a = 1/0;
                final String change = """
                        INSERT INTO doctor (name, email, password, health_care_provider_id) VALUES
                        ('Doctor3', 'doctor3@example.com', 'doctorpassword3', 1);
                        """;
                statement.executeUpdate(change);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
