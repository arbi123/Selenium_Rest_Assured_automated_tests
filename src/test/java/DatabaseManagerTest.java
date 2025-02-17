import db.H2Database;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Test
public class DatabaseManagerTest {
    H2Database dbTest = new H2Database();



@Test
    public void test() {

        try

    {
        dbTest.setUp();
        dbTest.insertSampleData();
        System.out.println("Database setup completed successfully!");

        List<Map<String, Object>> allUsers = dbTest.readUsers();
        System.out.println("\nAll Users:");
        allUsers.forEach(System.out::println);

        List<Map<String, Object>> johnUsers = dbTest.readUsersByName("John Doe");
        System.out.println("\nUsers named 'John Doe':");
        johnUsers.forEach(System.out::println);

        dbTest.cleanUp();
    } catch(
    SQLException e)

    {
        e.printStackTrace();
    }
}
}


