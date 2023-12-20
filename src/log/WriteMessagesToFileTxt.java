package log;

import java.io.FileWriter;
import java.io.IOException;

public class WriteMessagesToFileTxt implements WriteToLog {

    private final String fileName = "C:\\Users\\Саша\\IdeaProjects\\ChatJava\\src\\log\\logs.txt";

    @Override
    public void writeToFile(String messages) {
        try(FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(messages);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
