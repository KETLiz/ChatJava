public class Main {
    public static void main(String[] args) {
        ServerWindow server = new ServerWindow();
        ClientGUI client1 = new ClientGUI(server);
        ClientGUI client2 = new ClientGUI(server);
    }
}