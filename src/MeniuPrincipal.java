import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeniuPrincipal extends JFrame {

    public MeniuPrincipal() {
        setTitle("Meniu Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton openShoppingButton = new JButton("Deschide fereastra de cumpărături");
        JButton openOrdersHistory = new JButton("Vezi istoric cumpărături");
        JButton openClientStatistics = new JButton("Vezi statistici client");
        openShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OnlineShopping();
                dispose();
            }
        });

        openOrdersHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IstoricComenzi();
                dispose();
            }
        });

        openClientStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatisticiClient();
                dispose();
            }
        });

        setLayout(new FlowLayout());
        add(openShoppingButton);
        add(openOrdersHistory);
        add(openClientStatistics);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MeniuPrincipal mainMenu = new MeniuPrincipal();
            mainMenu.setVisible(true);
        });
    }
}
