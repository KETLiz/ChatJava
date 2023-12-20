package client;

import server.Server;

public class Client {
    private String login;
    private ViewClient view;
    private Server server;
    private boolean connected = false; // флаг - подключён клиент или нет. изначально клиент отключён

    public  Client(ViewClient view, Server server) {
        this.view = view;
        this.server = server;
    }

    /**
     * Подключение клиента к серверу
     * @param login
     * @return подключился/не подключился
     */
    public boolean connectToServer(String login) {
        this.login = login;
        if(server.getStatus()) {
            server.connectClient(this);
            connected = true;
            view.sendMessage("Вы успешно подключились!\n");
            return true;
        } else {
            view.sendMessage("Сервер не запущен\n");
        }
        return false;
    }

    /**
     * Метод отправки сообщения на сервер
     * @param message
     */
    public void sendMessageToServer(String message) {
        if(connected) {
            server.sendMessageToAll(login + ": " + message + "\n");
        } else {
            view.sendMessage("Для отправки сообщений подключитесь к серверу\n");
        }
    }

    public void sendMessage(String message) {
        view.sendMessage(message);
    }

    /**
     * Отправка сохранённой переписки с сервера на клиент
     */
    public void answerFromServer() {
        String message = server.getLog();
        if(message != null) {
            view.answerFromServer(message);
        }
    }
    public String reseiveMessagesFromLog(String messages) {
        return view.getMessage();
    }

    public String getLogin() {
        return login;
    }

    public void disconnectFromServer() {
        if(connected) {
            connected = false;
            server.disconnectClient(this);
        }
    }

}
