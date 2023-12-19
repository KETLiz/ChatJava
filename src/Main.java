import server.ServerGUI;
import client.ClientGUI;

public class Main {
    public static void main(String[] args) {

        ServerGUI s = new ServerGUI();
        new ClientGUI(s);
        new ClientGUI(s);
//        ServerWindow server = new ServerWindow();
//        ClientGUI client1 = new ClientGUI(server);
//        ClientGUI client2 = new ClientGUI(server);
    }
}