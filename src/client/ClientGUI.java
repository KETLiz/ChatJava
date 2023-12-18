package client;
import javax.swing.*;
import server.ServerGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements View {
    ServerGUI server;
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    JTextArea idServer, portServer, userName, userPassword, messagesArea, messageArea;
    JPanel northPanel, southPanel;
    JButton login, btnSend;
    JTextArea allMessages; // окно, где все сообщения

    public ClientGUI(ServerGUI server){
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        setLocation(server.getX() - 400, server.getY());
        createWindow();

        setVisible(true);
    }

    private void createWindow() {
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
        messageArea = new JTextArea("");
        btnSend = new JButton("send");
        southPanel.add(messageArea);
        southPanel.add(btnSend, BorderLayout.EAST);

        return southPanel;
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void connectedToServer() {

    }

    @Override
    public void disconnectedFromServer() {

    }
}
