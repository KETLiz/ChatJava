import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    ServerWindow server;
    private String name;
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    JTextArea idServer = new JTextArea("127.0.0.1");
    JTextArea portServer = new JTextArea("8189");
    JTextArea userName = new JTextArea("Введите имя:");
    JTextArea userPassword = new JTextArea("Введите пароль: ");
    JButton login = new JButton("login");
    JButton btnSend = new JButton("send");
    JTextArea messageArea = new JTextArea(""); // окно, где клиент пишет сообщение
    JTextArea allMessages; // окно, где все сообщения
    SaveMessageToFile save = new SaveMessageToFile();

    public ClientGUI(ServerWindow serverWindow) {
        this.server = serverWindow;
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        setLocation(server.getX() - 450, server.getY());
        createPanels();
        setVisible(true);
    }

    private void createPanels() {
        add(createNorthPanel(), BorderLayout.NORTH);
        add(createCenterPanel());
        add(createSouthPanel(), BorderLayout.SOUTH);
    }
    private Component createNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout(2, 3));
        northPanel.add(idServer);
        northPanel.add(portServer);
        northPanel.add(new JPanel());
        northPanel.add(userName);
        northPanel.add(userPassword);
        northPanel.add(login);
        logToServer(login);
        return northPanel;
    }

    private Component createSouthPanel() {
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(messageArea);
        southPanel.add(btnSend, BorderLayout.EAST);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        return southPanel;
    }

    /**
     * Создаём окно для всех сообщений (редактирование невозможно)
     * @return
     */
    private Component createCenterPanel() {
        allMessages = new JTextArea();
        allMessages.setEditable(false);
        return allMessages;
    }

    /**
     * Метод отправки сообщения подключённым пользователям и на сервер
     */
    private void sendMessage() {
        String message = messageArea.getText();
        if(server.isServerWorking()) {
            server.jt.append(getName() + ": " + message + "\n");
            sendMessageForAllUsers(getName() + ": " + message + "\n");
            save.saveToLogs(getName() + ": " + message);
        }
    }

    /**
     * Обработчик книпки login подключения клиента к серверу
     */
    public void logToServer(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(server.isServerWorking()) {
                    if(isValidName(userName)) {
                        allMessages.append("Вы успешно подключились\n");
                        name = userName.getText();
                        createNorthPanel().setVisible(false);
                        addToServer();
                    }
                } else {
                    allMessages.append("Сервер не работает\n");
                }
            }
        });
    }

    /**
     * Метод проверки валиднсти имени. Надо доработать
     * @param nameFromTextArea имя пользователя
     * @return boolean
     */
    private boolean isValidName(JTextArea nameFromTextArea) {
        name = nameFromTextArea.getText();
        if(name != null) {
            if(name.equalsIgnoreCase("Введите имя:")) {
                allMessages.append("Введите корректное имя\n");
                return false;
            } else {
                return true;
            }
        } else  {
            allMessages.append("Не будьте пустотой, введите имя :)\n");
            return false;
        }
    }

    /**
     * Добавление текущего клиента в список клиентов сервера
     */
    private void addToServer() {
        server.addClientGUIToClients(this);
    }

    /**
     * Метод отправки сообщений всем подключённым пользователям
     * @return
     */
    private void sendMessageForAllUsers(String message) {
        for(ClientGUI client : server.getClients()) {
            client.allMessages.append(message);
        }
    }

    public String getName() {
        return name;
    }
}
