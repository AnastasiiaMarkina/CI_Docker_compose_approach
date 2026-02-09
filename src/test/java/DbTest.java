import config.TestConfig;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbTest {
    private static final String URL = TestConfig.get("db.url");
    private static final String USER = TestConfig.get("db.user");
    private static final String PASS = TestConfig.get("db.password");

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
