package client;

public interface ViewClient {
    void sendMessage(String message);

    void connectedToServer();

    void disconnectedFromServer();

    String getMessage();

    void answerFromServer(String messages);

}
