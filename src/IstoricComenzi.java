import ro.ase.PPOO.Client;
import ro.ase.PPOO.Comanda;
import ro.ase.PPOO.ServiciiClient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ro.ase.PPOO.Program.citireClienti;
import static ro.ase.PPOO.Program.citireComenzi;

public class IstoricComenzi {
    private JComboBox<String> comboBoxClient;
    private JTextArea textAreaComenzi;
    private List<Client> clienti;
    private Set<Comanda> comenzi;
    private JButton buttonBackToMenu;


    public IstoricComenzi() {
        clienti = citireClienti();
        comenzi = citireComenzi();

        List<String> numeClienti = new ArrayList<>();
        for (Client client : clienti) {
            numeClienti.add(client.getNume() + " " + client.getPrenume());
        }

        comboBoxClient = new JComboBox<>(numeClienti.toArray(new String[0]));

        textAreaComenzi = new JTextArea(15, 40);
        textAreaComenzi.setEditable(false);

        comboBoxClient.addActionListener(e -> afiseazaComenziClient());

        JFrame frame = new JFrame("Vizualizare Comenzi Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel panelSelectie = new JPanel(new FlowLayout());
        panelSelectie.add(new JLabel("Selectează clientul:"));
        panelSelectie.add(comboBoxClient);

        frame.add(panelSelectie, BorderLayout.NORTH);
        frame.add(new JScrollPane(textAreaComenzi), BorderLayout.CENTER);

        afiseazaComenziClient();
        buttonBackToMenu = new JButton("Înapoi la Meniu");
        buttonBackToMenu.setPreferredSize(new Dimension(150, 30));
        buttonBackToMenu.addActionListener(e -> {
            MeniuPrincipal meniuPrincipal = new MeniuPrincipal();
            meniuPrincipal.setVisible(true);

            ((JFrame) SwingUtilities.getWindowAncestor(buttonBackToMenu)).dispose();
        });

        JPanel panelButon = new JPanel();
        panelButon.add(buttonBackToMenu);
        frame.add(panelButon, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void afiseazaComenziClient() {
        textAreaComenzi.setText("");

        int selectedClientIndex = comboBoxClient.getSelectedIndex();
        if (selectedClientIndex >= 0 && selectedClientIndex < clienti.size()) {
            int idClient = clienti.get(selectedClientIndex).getId();

            ServiciiClient serviciiClient = new Comanda();
            Set<Comanda> comenziClient = serviciiClient.vizualizeazaIstoricComenzi(idClient, comenzi);

            if (comenziClient.isEmpty()) {
                textAreaComenzi.append("Clientul selectat nu are comenzi.\n");
            } else {
                for (Comanda comanda : comenziClient) {
                    textAreaComenzi.append(comanda.toString() + "\n");
                }
            }
        } else {
            textAreaComenzi.append("Nu a fost selectat niciun client valid.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IstoricComenzi::new);
    }
}
