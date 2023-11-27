import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private Properties properties;

    public Settings() {
        properties = new Properties();
        try {
            FileReader fr = new FileReader("C:\\Users\\rusva\\IdeaProjects\\model_partialMAP\\src\\settings.properties"); ///am  incarcat din fisier
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

    public String getBPFileName() {
        return  properties.getProperty("BPFileName");
    }

    public String getBMIFileName() { return  properties.getProperty("BMIFileName");}
}

