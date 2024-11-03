import ro.ase.PPOO.Client;
import ro.ase.PPOO.Comanda;
import ro.ase.PPOO.Produs;
import ro.ase.PPOO.Program;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ro.ase.PPOO.Program.*;

public class StatisticiClient {
    private JComboBox<String> comboBoxClient;
    private JTextArea textAreaStatistici;
    private List<Client> clienti;
    private Set<Comanda> comenzi;
    private JButton buttonBackToMenu;

    public StatisticiClient() {
        clienti = citireClienti();
        comenzi = citireComenzi();

        List<String> numeClienti = clienti.stream()
                .map(client -> client.getNume() + " " + client.getPrenume())
                .collect(Collectors.toList());

        comboBoxClient = new JComboBox<>(numeClienti.toArray(new String[0]));

        textAreaStatistici = new JTextArea(10, 40);
        textAreaStatistici.setEditable(false);

        buttonBackToMenu = new JButton("Înapoi la Meniu");
        buttonBackToMenu.setPreferredSize(new Dimension(150, 30));
        buttonBackToMenu.addActionListener(e -> {
            MeniuPrincipal meniuPrincipal = new MeniuPrincipal();
            meniuPrincipal.setVisible(true);

            ((JFrame) SwingUtilities.getWindowAncestor(buttonBackToMenu)).dispose();
        });

        JPanel panelButon = new JPanel();
        panelButon.add(buttonBackToMenu);

        comboBoxClient.addActionListener(e -> afiseazaStatisticiClient());

        JFrame frame = new JFrame("Statistici Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel panelSelectie = new JPanel(new FlowLayout());
        panelSelectie.add(new JLabel("Selectează clientul:"));
        panelSelectie.add(comboBoxClient);

        frame.add(panelSelectie, BorderLayout.NORTH);
        frame.add(new JScrollPane(textAreaStatistici), BorderLayout.CENTER);

        afiseazaStatisticiClient();
        frame.add(panelButon, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void afiseazaStatisticiClient() {
        textAreaStatistici.setText("");

        int selectedClientIndex = comboBoxClient.getSelectedIndex();
        if (selectedClientIndex >= 0 && selectedClientIndex < clienti.size()) {
            int idClient = clienti.get(selectedClientIndex).getId();

            List<Comanda> comenziClient = comenzi.stream()
                    .filter(comanda -> comanda.getIdClient() == idClient)
                    .collect(Collectors.toList());

            int numarComenzi = comenziClient.size();
            int totalProduse = 0;
            double sumaTotala = 0;
            List<Produs> produse = citireProduse();

            for (Comanda comanda : comenziClient) {
                int[][] produseInComanda = comanda.getProduse();
                for (int[] produs : produseInComanda) {
                    int cantitate = produs[1];
                    totalProduse += cantitate;

                    Produs produsComandat = Program.gasesteProdusDupaId(produs[0], produse);
                    if (produsComandat != null) {
                        sumaTotala += produsComandat.getPret() * cantitate;
                    }
                }
            }

            textAreaStatistici.append("Număr de comenzi: " + numarComenzi + "\n");
            textAreaStatistici.append("Total produse comandate: " + totalProduse + "\n");
            textAreaStatistici.append("Suma totală plătită: " + sumaTotala + " RON\n");
        } else {
            textAreaStatistici.append("Nu a fost selectat niciun client valid.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StatisticiClient::new);
    }
}
