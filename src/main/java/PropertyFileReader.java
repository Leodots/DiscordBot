import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

    InputStream inputStream;

    protected String getPropValues() {
        String value = "";

        try {

            Properties prop = new Properties();

            inputStream = getClass().getResourceAsStream("config.properties");

            if(inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Config file not found.");
            }

            value = prop.getProperty("TOKEN");

        } catch(Exception e) {
            System.out.println("Exception" + e);
        }
        return value;
    }
}