package ro.ase.PPOO.Interfete;

import ro.ase.PPOO.Clase.Produs;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static ro.ase.PPOO.Clase.Program.*;

/**
 * Clasa GestionareProduse permite adăugarea, modificarea și ștergerea produselor printr-o interfață dedicată ce permite vizualizarea detaliilor acestora
 */
public class GestionareProduse {
    private JComboBox<String> comboBoxProdus;
    private JTextField textFieldPret;
    private JTextField textFieldGarantie;
    private JTextField textFieldNumeProdus;
    private JButton buttonSalvare;
    private JButton buttonStergere;
    private JButton buttonAdaugaProdus;
    private List<Produs> produse;
    private JButton buttonBackToMenu;

    public GestionareProduse() {
        produse = citireProduse();

        List<String> numeProduse = produse.stream()
                .map(Produs::getNume)
                .collect(Collectors.toList());
        comboBoxProdus = new JComboBox<>(numeProduse.toArray(new String[0]));

        textFieldPret = new JTextField(10);
        textFieldGarantie = new JTextField(10);
        textFieldNumeProdus = new JTextField(10);

        buttonSalvare = new JButton("Salvează Modificările");
        buttonStergere = new JButton("Șterge Produsul");
        buttonAdaugaProdus = new JButton("Adaugă Produs");

        JPanel panelSelectie = new JPanel();
        panelSelectie.add(new JLabel("Selectează Produsul:"));
        panelSelectie.add(comboBoxProdus);

        buttonBackToMenu = new JButton("Înapoi la Meniu");
        buttonBackToMenu.setPreferredSize(new Dimension(150, 30));
        buttonBackToMenu.addActionListener(e -> {
            MeniuPrincipal meniuPrincipal = new MeniuPrincipal();
            meniuPrincipal.setVisible(true);

            ((JFrame) SwingUtilities.getWindowAncestor(buttonBackToMenu)).dispose();
        });

        JPanel panelButoane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelButoane.add(buttonBackToMenu);
        panelButoane.add(buttonSalvare);
        panelButoane.add(buttonStergere);
        panelButoane.add(buttonAdaugaProdus);

        JPanel panelDetalii = new JPanel();
        panelDetalii.setLayout(new GridLayout(3, 2));
        panelDetalii.add(new JLabel("Nume Produs:"));
        panelDetalii.add(textFieldNumeProdus);
        panelDetalii.add(new JLabel("Preț:"));
        panelDetalii.add(textFieldPret);
        panelDetalii.add(new JLabel("Garanție:"));
        panelDetalii.add(textFieldGarantie);

        JFrame frame = new JFrame("Gestionare Produse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        frame.add(panelSelectie, BorderLayout.NORTH);
        frame.add(panelDetalii, BorderLayout.CENTER);
        frame.add(panelButoane, BorderLayout.SOUTH);

        comboBoxProdus.addActionListener(e -> updateDetaliiProdus());

        buttonSalvare.addActionListener(e -> salveazaModificari());

        buttonStergere.addActionListener(e -> stergeProdus());

        buttonAdaugaProdus.addActionListener(e -> adaugaProdus());

        updateDetaliiProdus();
        frame.setVisible(true);
    }

    private void updateDetaliiProdus() {
        int selectedIndex = comboBoxProdus.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < produse.size()) {
            Produs produs = produse.get(selectedIndex);
            textFieldNumeProdus.setText(produs.getNume());
            textFieldPret.setText(String.valueOf(produs.getPret()));
            textFieldGarantie.setText(String.valueOf(produs.getGarantie()));
        }
    }

    private void salveazaModificari() {
        try {
            int selectedIndex = comboBoxProdus.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < produse.size()) {
                Produs produs = produse.get(selectedIndex);
                produs.setNume(textFieldNumeProdus.getText());
                produs.setPret(Double.parseDouble(textFieldPret.getText()));
                produs.setGarantie(Integer.parseInt(textFieldGarantie.getText()));
                scrieProduseInFisier();
                JOptionPane.showMessageDialog(null, "Modificările au fost salvate cu succes.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduceți valori valide pentru preț și garanție.");
        }
    }

    private void stergeProdus() {
        int selectedIndex = comboBoxProdus.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < produse.size()) {
            produse.remove(selectedIndex);
            scrieProduseInFisier();
            comboBoxProdus.removeItemAt(selectedIndex);
            JOptionPane.showMessageDialog(null, "Produsul a fost șters cu succes.");
            if (comboBoxProdus.getItemCount() > 0) {
                comboBoxProdus.setSelectedIndex(0);
            }
            updateDetaliiProdus();
        }
    }

    private void adaugaProdus() {
        String numeProdus = textFieldNumeProdus.getText().trim();
        if (numeProdus.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Introduceți un nume valid pentru produs.");
            return;
        }

        int idUltimProdus = produse.get(produse.size() - 1).getId();
        int idNouProdus = idUltimProdus + 1;
        double pret = 0.0;
        int garantie = 0;

        try {
            pret = Double.parseDouble(textFieldPret.getText());
            garantie = Integer.parseInt(textFieldGarantie.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduceți valori valide pentru preț și garanție.");
            return;
        }

        Produs produsNou = new Produs(idNouProdus, numeProdus, pret, garantie);
        produse.add(produsNou);

        comboBoxProdus.addItem(numeProdus);
        scrieProduseInFisier();

        textFieldNumeProdus.setText("");
        textFieldPret.setText("");
        textFieldGarantie.setText("");

        JOptionPane.showMessageDialog(null, "Produsul a fost adăugat cu succes.");
    }

    private void scrieProduseInFisier() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\produse.txt"))) {
            for (Produs produs : produse) {
                writer.write(produs.getId() + "," + produs.getNume() + "," + produs.getPret() + "," + produs.getGarantie());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Eroare la actualizarea fișierului de produse.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestionareProduse::new);
    }
}
