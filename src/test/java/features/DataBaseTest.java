package features;

import driver.JdbcDriverSetUp;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DataBaseTest {

    public static JdbcDriverSetUp jdbcDriverSetUp = new JdbcDriverSetUp();
    private static Statement stm;


    @Before
    public void setUpDriver() throws SQLException, ClassNotFoundException {
        jdbcDriverSetUp.dbDriverSetUp();
        Connection connectionDb = jdbcDriverSetUp.getConnection();
        assertNotNull(connectionDb);
        stm = connectionDb.createStatement();
    }

    @Test
    public void dataBaseTest() throws SQLException {
        int i = 1;
//        ResultSet table1ResultSet = stm.executeQuery("select * from table1 where id='1';");
        Map<Integer, String> expectedResult = new HashMap<>();
        expectedResult.put(1, "Kolya");
        expectedResult.put(2, "Vasya");
        expectedResult.put(3, "Oleg");
        expectedResult.put(4, null);
        ResultSet tableResultSet = stm.executeQuery("SELECT table1.* FROM table1 RIGHT JOIN table2 ON table1.id=table2.id;");

        while (tableResultSet.next()) {
        assertThat(tableResultSet.getString("Name"), equalTo(expectedResult.get(i)));
            LOGGER.info(String.format("Test complete. Result: '%s'", tableResultSet.getString("Name")));
            i++;
        }
    }

}
