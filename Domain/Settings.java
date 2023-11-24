package Domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final String SETTINGS_FILE = "settings.properties";
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

    public String getPatientsFile() {
        return properties.getProperty("Patients");
    }

    public String getAppointmentsFile() {
        return properties.getProperty("Appointments");
    }
}

