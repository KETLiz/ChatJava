package client;
import javax.swing.*;
import server.ServerGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ViewClient {
    private Client client;
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    JTextArea idServer, portServer, userName, userPassword, messagesArea, sendMessageArea;
    JPanel northPanel, southPanel;
    JButton login, btnSend;
    JTextArea allMessages; // окно, где все сообщения

    public ClientGUI(ServerGUI serverGui){
        createWindow(serverGui);
        client = new Client(this, serverGui.getServer());

        setVisible(true);
    }

    /**
     * Создание всего окна клиента
     */
    private void createWindow(ServerGUI serverGui) {
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        setLocation(serverGui.getX() - 400, serverGui.getY());
        add(createNorthPanel(), BorderLayout.NORTH);
        add(createCentrePanel());
        add(createSourthPanel(), BorderLayout.SOUTH);
    }

    /**
     * Создание верхней панельки
     */
    private Component createNorthPanel() {
        northPanel = new JPanel(new GridLayout(2, 3));
        idServer = new JTextArea("127.0.0.1");
        portServer = new JTextArea("8189");
        userName = new JTextArea("Введите имя: ");
        userPassword = new JTextArea("Введите пароль: ");
        login = new JButton("login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedToServer();
            }
        });

        northPanel.add(idServer);
        northPanel.add(portServer);
        northPanel.add(new JPanel());
        northPanel.add(userName);
        northPanel.add(userPassword);
        northPanel.add(login);

        return northPanel;
    }

    /**
     * Создание центрального окна со всеми сообщениями
     */
    private Component createCentrePanel() {
        messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        return messagesArea;
    }

    /**
     * Создание нижней панельки
     */
    private Component createSourthPanel() {
        southPanel = new JPanel(new BorderLayout());
        sendMessageArea = new JTextArea("");
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessageToServer(sendMessageArea.getText());
                sendMessageArea.setText("");
            }
        });
        southPanel.add(sendMessageArea);
        southPanel.add(btnSend, BorderLayout.EAST);

        return southPanel;
    }

    @Override
    public void sendMessage(String message) {
        messagesArea.append(message);
    }

    @Override
    public void connectedToServer() {
        if(client.connectToServer(userName.getText())) {
            northPanel.setVisible(false);
        }
    }

    @Override
    public void disconnectedFromServer() {

    }

    @Override
    public String getMessage() {
        return sendMessageArea.getText();
    }
}
