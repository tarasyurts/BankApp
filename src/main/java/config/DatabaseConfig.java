package config;

public class DatabaseConfig {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bank_db?serverTimezone=UTC";

    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "root";

    {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
