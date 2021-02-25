package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDriverSetUp  {
    private Connection connection;


    public void dbDriverSetUp () throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "xfqrf2424");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection(){
        return connection;
    }

}
