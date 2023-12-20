package log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFileTxt implements ReadFromLog{

    private final String fileName = "C:\\Users\\Саша\\IdeaProjects\\ChatJava\\src\\log\\logs.txt";

    @Override
    public String readFromFileTxt() {
        StringBuilder sb = new StringBuilder();
        try(FileReader fileReader = new FileReader(fileName)) {
            int c;
            while((c = fileReader.read()) != -1) {
                sb.append((char) c);
            }
            return sb.toString();

        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
