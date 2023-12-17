import java.io.FileWriter;
import java.io.IOException;

public class SaveMessageToFile {
    //ServerWindow server;

    ClientGUI client;

    public void saveToLogs(String message) {
        try(FileWriter file = new FileWriter("log.txt", true)) {
            if(message != null) {
                file.write(message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
