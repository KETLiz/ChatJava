package server;

import client.Client;
import log.ReadFromLog;
import log.WriteToLog;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private ViewServer view;
    WriteToLog writeLog;
    ReadFromLog readLog;
    private List<Client> clients;
    private boolean work = false; // флаг - сервер запущен или нет. Изначально сервер не работает

    public Server(ViewServer view, WriteToLog writeLog, ReadFromLog readLog) {
        this.view = view;
        this.writeLog = writeLog;
        this.readLog = readLog;
        clients = new ArrayList<>();
    }

    /**
     * Запуск сервера
     * @return work = true
     */
    public void startServer() {
        work = true;
    }

    /**
     * Получаем данные запущен сервер или отключён
     * @return work
     */
    public boolean getStatus() {
        return work;
    }

    /**
     * Остановка сервера
     */
    public void stopServer() {
        work = false;
        System.exit(0);
    }

    /**
     * Добавление клиента к списку подключённых клиентов
     * @param client
     */
    public void connectClient(Client client) {
        String login = client.getLogin();
        clients.add(client);
        view.addClient(login);
    }

    public void disconnectClient(Client client) {
        clients.remove(client);
        view.disconnectClient(client.getLogin() + " вышел из чата\n");
        sendMessageToConnectedClients(client.getLogin() + " вышел из чата\n");
    }

    public void sendMessageToAll(String message) {
        receiveMessage(message);
        sendMessageToConnectedClients(message);
        writeToLogs(message);
    }

    public void sendMessagesFromLogs(String messages) {
        receiveMessage(messages);
        sendMessageToConnectedClients(messages);
    }

    /**
     * Отправка сообщения на окно сервера
     * @param message
     */
    public void receiveMessage(String message) {
        view.receiveMessage(message);
    }

    /**
     * Отправка сообщения всем подключённым пользователям
     * @param message
     */
    public void sendMessageToConnectedClients(String message) {
        for(Client client : clients) {
            client.sendMessage(message);
        }
    }

    /**
     * Получаем список подключённых клиентов к серверу
     * @return clients
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Запись истории сообщений в файл
     * @param messages
     */
    public void writeToLogs(String messages) {
        writeLog.writeToFile(messages);
    }

    public String getLog() {
        return readLog.readFromFileTxt();
    }
}
