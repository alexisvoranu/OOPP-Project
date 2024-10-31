import ro.ase.PPOO.Client;
import ro.ase.PPOO.Produs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static ro.ase.PPOO.Program.citireClienti;
import static ro.ase.PPOO.Program.citireProduse;

public class OnlineShopping {
    private JSpinner spinnerClient;
    private JSpinner spinnerProdus;
    private JSpinner spinnerCantitate;
    private JButton adaugaProdusulButton;
    private JTextField textFieldAdresaLivrare;
    private JSpinner spinnerTipPlata;
    private JTextField textFieldValoareTotala;
    private JButton buttonPlasareComanda;
    private List<int[]> matriceProduse = new ArrayList<>(); // Stores pairs of [product ID, quantity]
    private List<Produs> produse; // To store product list for total calculation

    public OnlineShopping() {
        List<Client> clienti = citireClienti();
        produse = citireProduse(); // Store the list of products

        List<String> numeClienti = new ArrayList<>();
        for (Client client : clienti) {
            numeClienti.add(client.getNume() + " " + client.getPrenume());
        }

        SpinnerListModel clientModel = new SpinnerListModel(numeClienti);
        spinnerClient = new JSpinner(clientModel);

        List<String> numeProduse = new ArrayList<>();
        for (Produs produs : produse) {
            numeProduse.add(produs.getNume());
        }

        SpinnerListModel produsModel = new SpinnerListModel(numeProduse);
        spinnerProdus = new JSpinner(produsModel);

        spinnerCantitate = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Spinner for quantity

        adaugaProdusulButton = new JButton("Adauga Produsul");
        textFieldAdresaLivrare = new JTextField(20);
        textFieldValoareTotala = new JTextField(20);
        textFieldValoareTotala.setEditable(false); // Total value should be read-only
        spinnerTipPlata = new JSpinner(new SpinnerListModel(new String[]{"Card", "Numerar"}));
        buttonPlasareComanda = new JButton("Plaseaza Comanda");

        adaugaProdusulButton.addActionListener(e -> {
            // Get selected product and quantity
            int produsIndex = spinnerProdus.getValue() != null ? numeProduse.indexOf(spinnerProdus.getValue().toString()) : -1;
            int cantitate = (int) spinnerCantitate.getValue();

            if (produsIndex >= 0 && produsIndex < produse.size()) {
                int produsID = produse.get(produsIndex).getId(); // Assuming Produs has a getId() method
                matriceProduse.add(new int[]{produsID, cantitate});

                // Update the total value
                double totalValoare = calculateTotal();
                textFieldValoareTotala.setText(String.valueOf(totalValoare));

                JOptionPane.showMessageDialog(null, "Produsul a fost adaugat: ID=" + produsID + ", Cantitate=" + cantitate);
            }
        });

        buttonPlasareComanda.addActionListener(e -> {
            // Create order summary
            String adresa = textFieldAdresaLivrare.getText();
            String tipPlata = (String) spinnerTipPlata.getValue();
            double totalValoare = calculateTotal();

            if (adresa.isEmpty() || matriceProduse.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Te rog completeaza adresa si adauga produse in comanda!");
            } else {
                // Display order confirmation
                StringBuilder comanda = new StringBuilder("Comanda plasata cu succes!\n");
                comanda.append("Produse:\n");
                for (int[] produs : matriceProduse) {
                    comanda.append("Produs ID: ").append(produs[0]).append(", Cantitate: ").append(produs[1]).append("\n");
                }
                comanda.append("Adresa de livrare: ").append(adresa).append("\n");
                comanda.append("Tip de plata: ").append(tipPlata).append("\n");
                comanda.append("Valoare totala: ").append(totalValoare).append(" lei");

                JOptionPane.showMessageDialog(null, comanda.toString());
            }
        });

        // Configurare JFrame pentru a testa
        JFrame frame = new JFrame("Selecteaza Clientul si Produsul");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new java.awt.FlowLayout());

        frame.add(new JLabel("Selecteaza Clientul:"));
        frame.add(spinnerClient);

        frame.add(new JLabel("Selecteaza Produsul:"));
        frame.add(spinnerProdus);

        frame.add(new JLabel("Selecteaza Cantitatea:"));
        frame.add(spinnerCantitate);

        frame.add(adaugaProdusulButton);

        frame.add(new JLabel("Adresa de livrare:"));
        frame.add(textFieldAdresaLivrare);

        frame.add(new JLabel("Tip de plata:"));
        frame.add(spinnerTipPlata);

        frame.add(new JLabel("Valoare totala:"));
        frame.add(textFieldValoareTotala);

        frame.add(buttonPlasareComanda);

        frame.setVisible(true);
    }

    private double calculateTotal() {
        double total = 0;
        for (int[] produs : matriceProduse) {
            // Assuming you have a method to get the price of the product
            // double price = getPriceById(produs[0]); // Implement this method according to your product class
            double price = produse.stream()
                    .filter(p -> p.getId() == produs[0])
                    .findFirst()
                    .map(Produs::getPret) // Assuming Produs has a getPret() method
                    .orElse(0.0);
            total += price * produs[1];
        }
        return total;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OnlineShopping::new);
    }
}
