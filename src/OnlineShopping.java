import ro.ase.PPOO.Client;
import ro.ase.PPOO.Produs;
import ro.ase.PPOO.Comanda;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ro.ase.PPOO.Program.citireClienti;
import static ro.ase.PPOO.Program.citireProduse;
import static ro.ase.PPOO.Comanda.adaugaComandaInFisier;

public class OnlineShopping {
    private JComboBox<String> comboBoxClient;
    private JComboBox<String> comboBoxProdus;
    private JSpinner spinnerCantitate;
    private JButton adaugaProdusulButton;
    private JTextField textFieldAdresaLivrare;
    private JComboBox<String> comboBoxTipPlata;
    private JTextField textFieldValoareTotala;
    private JButton buttonPlasareComanda;
    private JFormattedTextField formattedTextField1;
    private List<int[]> matriceProduseTemp = new ArrayList<>();
    private int[][] matriceProduse;
    private List<Produs> produse;
    private List<Client> clienti;

    public OnlineShopping() {
        clienti = citireClienti();
        produse = citireProduse();

        List<String> numeClienti = new ArrayList<>();
        for (Client client : clienti) {
            numeClienti.add(client.getNume() + " " + client.getPrenume());
        }

        // Înlocuim spinnerClient cu comboBoxClient
        comboBoxClient = new JComboBox<>(numeClienti.toArray(new String[0]));

        List<String> numeProduse = new ArrayList<>();
        for (Produs produs : produse) {
            numeProduse.add(produs.getNume()+", "+produs.getPret()+ " ron, "+ produs.getGarantie() +" luni garantie");
        }

        // Înlocuim spinnerProdus cu comboBoxProdus
        comboBoxProdus = new JComboBox<>(numeProduse.toArray(new String[0]));

        spinnerCantitate = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        adaugaProdusulButton = new JButton("Adaugă produsul în coș");
        textFieldAdresaLivrare = new JTextField(20);
        textFieldValoareTotala = new JTextField(10);
        textFieldValoareTotala.setEditable(false);

        // Înlocuim spinnerTipPlata cu comboBoxTipPlata
        comboBoxTipPlata = new JComboBox<>(new String[]{"Card", "Ramburs"});

        buttonPlasareComanda = new JButton("Plasează Comanda");

        adaugaProdusulButton.addActionListener(e -> {
            int produsIndex = comboBoxProdus.getSelectedIndex();
            int cantitate = (int) spinnerCantitate.getValue();

            if (produsIndex >= 0 && produsIndex < produse.size()) {
                int produsID = produse.get(produsIndex).getId();
                matriceProduseTemp.add(new int[]{produsID, cantitate});

                double totalValoare = calculateTotal();
                textFieldValoareTotala.setText(String.valueOf(totalValoare));

                JOptionPane.showMessageDialog(null, cantitate + " produse au fost adaugate în coș!");
            }
        });

        buttonPlasareComanda.addActionListener(e -> {
            matriceProduse = new int[matriceProduseTemp.size()][2];
            for (int i = 0; i < matriceProduseTemp.size(); i++) {
                matriceProduse[i][0] = matriceProduseTemp.get(i)[0];
                matriceProduse[i][1] = matriceProduseTemp.get(i)[1];
            }

            String adresa = textFieldAdresaLivrare.getText();
            String tipPlata = (String) comboBoxTipPlata.getSelectedItem();
            double totalValoare = calculateTotal();

            if (adresa.isEmpty() || matriceProduseTemp.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Te rog completează adresa și adaugă produse în coș!");
            } else {
                int selectedClientIndex = comboBoxClient.getSelectedIndex();
                if (selectedClientIndex >= 0 && selectedClientIndex < clienti.size()) {
                    int idClient = clienti.get(selectedClientIndex).getId();
                    Date dataPlasare = new Date();
                    int idComanda = generateOrderId();

                    Comanda comanda = new Comanda(
                            idComanda,
                            totalValoare,
                            dataPlasare,
                            adresa,
                            tipPlata,
                            idClient,
                            matriceProduseTemp.size(),
                            matriceProduse
                    );

                    adaugaComandaInFisier(comanda);

                    JOptionPane.showMessageDialog(null, comanda.toString());
                }
            }
        });

        JFrame frame = new JFrame("Selectează clientul și produsul");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new java.awt.FlowLayout());

        frame.add(new JLabel("Selectează clientul:"));
        frame.add(comboBoxClient);

        frame.add(new JLabel("Selectează produsul:"));
        frame.add(comboBoxProdus);

        frame.add(new JLabel("Selectează cantitatea:"));
        frame.add(spinnerCantitate);

        frame.add(adaugaProdusulButton);

        frame.add(new JLabel("Adresa de livrare:"));
        frame.add(textFieldAdresaLivrare);

        frame.add(new JLabel("Tip de plată:"));
        frame.add(comboBoxTipPlata);

        frame.add(new JLabel("Valoare totală:"));
        frame.add(textFieldValoareTotala);

        frame.add(buttonPlasareComanda);

        frame.setVisible(true);
    }

    private double calculateTotal() {
        double total = 0;
        for (int[] produs : matriceProduseTemp) {
            double price = produse.stream()
                    .filter(p -> p.getId() == produs[0])
                    .findFirst()
                    .map(Produs::getPret)
                    .orElse(0.0);
            total += price * produs[1];
        }
        return total;
    }

    private int generateOrderId() {
        return (int) (Math.random() * 100000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OnlineShopping::new);
    }
}
