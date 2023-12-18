package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ViewServer {
    private Server server;
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WEIGHT = 300;
    JTextArea jt; // поле со всеми сообщениями
    JPanel panel; // нижняя панелька с кнопками
    JButton btnStart, btnStop; // кнопки старта и стоп

    public ServerGUI() {
        setting();
        setVisible(true);
        server = new Server(this);
    }

    /**
     * Создание окошка
     */
    private void setting() {
        add(createTextArea());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_HEIGHT, WINDOW_WEIGHT);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        add(createPanel(), BorderLayout.SOUTH);
    }

    /**
     * Создание нижней панельки
     */
    private Component createPanel() {
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        panel = new JPanel(new GridLayout(1, 2));
        panel.add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showStatus()) {
                    jt.append("Сервер уже работает\n");
                } else {
                    startServer();
                    jt.append("Сервер запущен!\n");
                }
            }
        });
        panel.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showStatus()) {
                    stopServer();
                }
            }
        });
        return panel;
    }

    /**
     * Создание окна с сообщениями
     */
    private Component createTextArea() {
        jt = new JTextArea();
        jt.setEditable(false);

        return jt;
    }

    @Override
    public boolean showStatus() {
        return server.getStatus();
    }

    @Override
    public void stopServer() {
        server.stopServer();
    }

    @Override
    public void startServer() {
        server.startServer();
    }
}
