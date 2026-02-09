package config;

import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream is =
                     TestConfig.class
                             .getClassLoader()
                             .getResourceAsStream("test.properties")) {

            props.load(is);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
