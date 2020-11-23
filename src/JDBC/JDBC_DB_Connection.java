package JDBC;

import java.sql.*;


public class JDBC_DB_Connection implements AutoCloseable {

    private String db_url;
    private String db_user;
    private String db_password;

    private PreparedStatement ps_get_all_cities_count;
    private PreparedStatement ps_get_all_cities_above_5_mil;

    private Connection connection;

    public JDBC_DB_Connection(String db_url, String db_user, String db_password) throws SQLException {
        this.db_url = db_url;
        this.db_user = db_user;
        this.db_password = db_password;
        prepareConnection();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private void prepareConnection() throws SQLException
    {
        connection = DriverManager.getConnection(db_url, db_user, db_password);
        ps_get_all_cities_count = connection.prepareStatement("SELECT COUNT(*) FROM world.city");
        ps_get_all_cities_above_5_mil = connection.prepareStatement("SELECT * FROM world.city WHERE Population > 5000000");
    }
}