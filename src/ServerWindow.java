import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");
    JTextArea jt; // поле с сообщениями
    private boolean work;
    private List<ClientGUI> clients;

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        setTitle("Chat server");

        createPanel();

        setVisible(true);

        clients = new ArrayList<ClientGUI>();
    }

    /**
     * Метод отрисовки стартового окна сервера
     */
    private void createPanel() {
        jt = new JTextArea();
        add(jt);
        add(createButtons(), BorderLayout.SOUTH);
    }

    /**
     * Метод создания панельки с кнопками старт, стоп
     * @return панелька
     */
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(btnStart);
        panel.add(btnStop);

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(work == true) {
                    jt.append("Сервер уже работает!\n");
                } else if(work == false) {
                    work = true;
                    jt.append("Сервер успешно запущен!\n");
                }
            }
        });
        return panel;
    }

    /**
     * Сервер запущен или нет?
     * @return boolean
     */
    public boolean isServerWorking() {
        if(work == true) {
            return true;
        }
        return false;
    }

    /**
     * Добавление клиента в список подключённых клиентов
     * @param client
     */
    public void addClientGUIToClients(ClientGUI client) {
        if(isServerWorking()) {
            clients.add(client);
            jt.append("Клиент " + client.getName() + " подключился к беседе\n");
        } else {
            jt.append("Сервер не запущен\n");
        }
    }

}
