import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception{
        Connection connection = Connector.getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from Facultet");
        while (rs.next()){
            System.out.println(rs.getInt(1));
        }
        connection.close();
    }
}
