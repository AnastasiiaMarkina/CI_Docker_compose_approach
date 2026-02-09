import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbTest {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/testdb";

    private static final String USER = "test";
    private static final String PASS = "test";

    @Test
    void shouldConnectToDbAndInsertData() throws Exception {

        try (Connection conn =
                     DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users(
                  id SERIAL PRIMARY KEY,
                  name VARCHAR(100)
                )
            """);

            stmt.execute("""
                INSERT INTO users(name)
                VALUES ('Ivan')
            """);

            ResultSet rs =
                    stmt.executeQuery("SELECT name FROM users");

            rs.next();

            assertEquals("Ivan", rs.getString("name"));
        }
    }
}
