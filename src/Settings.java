import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private Properties properties;

    public Settings() {
        properties = new Properties();
        try {
            FileReader fr = new FileReader("C:\\Users\\rusva\\IdeaProjects\\inceram_lab3\\src\\settings.properties"); ///am  incarcat din fisier
            properties.load(fr);
            fr.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //cititm valorile din dictionar/fisier
    public String getRepoType(){
        return properties.getProperty("RepoType");
    }

    public String getCarsFileName() {
        return  properties.getProperty("CarsFileName");
    }

    public String getInchirieriFileName() {
        return  properties.getProperty("InchirieriFileName");
    }
}
