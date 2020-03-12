package ForConnection;

import ForConnection.Configs;

import java.sql.*;

public class Connector extends Configs {
    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        String connectString = "jdbc:mysql://" + host + ":" + port + "/" + name;
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectString, user, pass);
        return connection;
    }
}
