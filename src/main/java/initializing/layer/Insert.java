package initializing.layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Insert {


    public static void insert() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value)) {
            try(Statement statement = connection.createStatement()) {
                final String insert_sql = """
                -- Inserting dummy values into the admin table
                INSERT INTO admin (name, email, password) VALUES
                        ('Admin1', 'admin1@example.com', 'adminpassword1');

                -- Inserting dummy values into the health_care_provider table
                INSERT INTO health_care_provider (admin_id) VALUES
                        (1);
                        
                -- Inserting dummy values into the doctor table
                INSERT INTO doctor (name, email, password, health_care_provider_id) VALUES
                        ('Doctor1', 'doctor1@example.com', 'doctorpassword1', 1),
                ('Doctor2', 'doctor2@example.com', 'doctorpassword2', 1);
                """;
                statement.execute(insert_sql);
            }
        }
    }

}
