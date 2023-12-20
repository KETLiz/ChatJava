package server;

public interface ViewServer {
    boolean showStatus();

    void stopServer();

    void startServer();

    void addClient(String name);

    void receiveMessage(String message);

    void disconnectClient(String message);

}
