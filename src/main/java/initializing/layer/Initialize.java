package initializing.layer;

import constants.UserCredentials;

import java.sql.*;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Initialize {

    public static void init() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value)) {
            try(Statement statement = connection.createStatement()) {
                final String creating_sql = """ 
                create table if not exists admin(
                    id serial primary key,
                    name varchar(255),
                    email varchar(255),
                    password varchar(255)
                );
                create table if not exists health_care_provider(
                    id serial primary key,
                    admin_id int references admin(id)
                );
                create table if not exists doctor(
                    id serial primary key,
                    name varchar(255),
                    email varchar(255),
                    password varchar(255),
                    health_care_provider_id int references health_care_provider(id) on delete cascade
                );
                """;
                statement.execute(creating_sql);
            }
        }
    }

    public static void init_million() {
        String adminName = "Admin Name";
        String adminEmail = "admin@example.com";
        String adminPassword = "admin_password";
        int numRecords = 1000000;
        String insertAdminQuery = "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)";
        String insertProviderQuery = "INSERT INTO health_care_provider (admin_id) VALUES (?)";
        String insertDoctorQuery = "INSERT INTO doctor (name, email, password, health_care_provider_id) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value);
                PreparedStatement adminStatement = connection.prepareStatement(insertAdminQuery);
                PreparedStatement providerStatement = connection.prepareStatement(insertProviderQuery);
                PreparedStatement doctorStatement = connection.prepareStatement(insertDoctorQuery);
        ) {
            adminStatement.setString(1, adminName);
            adminStatement.setString(2, adminEmail);
            adminStatement.setString(3, adminPassword);
            adminStatement.executeUpdate();
            for (int i = 0; i < numRecords; i++) {
                providerStatement.setInt(1, 1); // Assuming admin_id is 1
                providerStatement.addBatch();
            }
            providerStatement.executeBatch();

            for (int i = 0; i < numRecords; i++) {
                doctorStatement.setString(1, "Doctor " + i); // Example name
                doctorStatement.setString(2, "doctor" + i + "@example.com"); // Example email
                doctorStatement.setString(3, "password" + i); // Example password
                doctorStatement.setInt(4, i + 1); // Assuming health_care_provider_id starts from 1
                doctorStatement.addBatch();
            }
            doctorStatement.executeBatch();

            System.out.println("Records inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
