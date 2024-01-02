package Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final String SETTINGS_FILE = "src/settings.properties";
    private Properties properties;

    public Settings() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public String getRepositoryType() {
        return properties.getProperty("Repository");
    }

    //Trebuie schimbate ultimele 2 get-ere !!!!
    public String getTorturiFile() {
        return properties.getProperty("Tort");
    }

    public String getComandaFile() {
        return properties.getProperty("Comanda");
    }
}
