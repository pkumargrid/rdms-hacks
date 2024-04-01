package consistency;

import java.sql.*;

import static constants.DatabaseCredentials.DB_URL;
import static constants.UserCredentials.USER_NAME;
import static constants.UserCredentials.USER_PASSWORD;

public class Thread7 extends Thread{
    @Override
    public void run() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL.value, USER_NAME.value, USER_PASSWORD.value);
            try(Statement statement = connection.createStatement()) {
                final String change = """
                        explain analyze select d.name from doctor as d inner join
                        health_care_provider as h on h.id = d.health_care_provider_id limit 100000;
                        """;
                ResultSet resultSet = statement.executeQuery(change);
                while (resultSet.next()) {
                    String executionPlan = resultSet.getString(1);
                    System.out.println(executionPlan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
