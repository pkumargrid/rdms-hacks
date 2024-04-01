package initializing.layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Index {
    public static void create() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value)) {
            try(Statement statement = connection.createStatement()) {
                final String create_index = """
                create index if not exists doctor_idx on doctor(health_care_provider_id, name);
                create index if not exists health_idx on health_care_provider(id);
                """;
                statement.executeUpdate(create_index);
            }
        }
    }

    public static void drop() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value)) {
            try(Statement statement = connection.createStatement()) {
                final String drop_index = """
                drop index doctor_idx;
                drop index health_idx;
                """;
                statement.executeUpdate(drop_index);
            }
        }
    }
}
