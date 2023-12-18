package server;

import client.Client;
import java.util.List;

public class Server {
    private ViewServer view;
    private List<Client> clients;
    private boolean work = false; // флаг - сервер запущен или нет. Изначально сервер не работает

    public Server(ViewServer view) {
        this.view = view;
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

}
