package server;

import client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private ViewServer view;
    private List<Client> clients;
    private boolean work = false; // флаг - сервер запущен или нет. Изначально сервер не работает

    public Server(ViewServer view) {
        this.view = view;
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

    public void sendMessageToAll(String message) {
        receiveMessage(message);
        sendMessageToConnectedClients(message);
    }

    /**
     * Отправка сообщения на окно сервера
     * @param message
     */
    public void receiveMessage(String message) {
        view.receiveMessage(message);
    }

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
}
