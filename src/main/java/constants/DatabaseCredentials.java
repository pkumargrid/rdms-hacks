package constants;

public enum DatabaseCredentials {
    DB_URL("jdbc:postgresql://localhost:5432/test");

    public final String value;

    DatabaseCredentials(String value) {
        this.value = value;
    }
}
