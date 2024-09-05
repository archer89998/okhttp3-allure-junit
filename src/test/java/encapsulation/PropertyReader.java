package encapsulation;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader() {
    }

    public static void getProperties() {
        Properties properties = new Properties();
        String filePath = Paths.get("", "src", "test", "resources", "junit-platform.properties").toAbsolutePath().toString();

        try (InputStream inStream = new FileInputStream(filePath)) {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.stringPropertyNames();
        int a = 1;
    }
}
