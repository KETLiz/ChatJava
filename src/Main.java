import server.ServerGUI;
import client.ClientGUI;

public class Main {
    public static void main(String[] args) {

        ServerGUI s = new ServerGUI();
        new ClientGUI(s);
        new ClientGUI(s);
    }
}