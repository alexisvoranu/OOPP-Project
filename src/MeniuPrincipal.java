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

        JButton openShoppingButton = new JButton("Deschide Fereastra de Cumpărături");
        JButton openOrdersHistory = new JButton("Vezi istoric cumpărături");
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

        setLayout(new FlowLayout());
        add(openShoppingButton);
        add(openOrdersHistory);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MeniuPrincipal mainMenu = new MeniuPrincipal();
            mainMenu.setVisible(true);
        });
    }
}
