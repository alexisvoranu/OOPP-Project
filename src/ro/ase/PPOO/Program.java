package ro.ase.PPOO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {

    public static List<Client> citireClienti() {
        List<Client> clienti = new ArrayList<>();

        try (var fisier = new BufferedReader(new FileReader(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\clienti.txt"))) {
            String linie;
            while ((linie = fisier.readLine()) != null) {
                Client client = new Client(
                        Integer.parseInt(linie.split(",")[0]),
                        linie.split(",")[1],
                        linie.split(",")[2],
                        linie.split(",")[3],
                        linie.split(",")[4]);
                clienti.add(client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clienti;
    }

    public static List<Produs> citireProduse() {
        List<Produs> produse = new ArrayList<>();

        try (var fisier = new BufferedReader(new FileReader(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\produse.txt"))) {
            String linie;
            while ((linie = fisier.readLine()) != null) {
                Produs produs = new Produs(
                        Integer.parseInt(linie.split(",")[0]),
                        linie.split(",")[1],
                        Double.parseDouble(linie.split(",")[2]),
                        Integer.parseInt(linie.split(",")[3]));
                produse.add(produs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return produse;
    }

    public static Set<Comanda> citireComenzi() {
        Set<Comanda> comenzi = new TreeSet<>(Comparator.comparing(Comanda::getDataPlasare));

        try (BufferedReader fisier = new BufferedReader(new FileReader(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\comenzi.txt"))) {
            String linie;
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

            while ((linie = fisier.readLine()) != null) {
                String[] valori = linie.split("\t");

                int id = Integer.parseInt(valori[0]);
                double valoareTotala = Double.parseDouble(valori[1]);
                Date dataPlasare = dateFormat.parse(valori[2]);
                String adresaLivrare = valori[3];
                String tipPlata = valori[4];
                int idClient = Integer.parseInt(valori[5]);
                int nrProduse = Integer.parseInt(valori[6]);

                int[][] produse = new int[nrProduse][2];
                int index = 7;

                for (int i = 0; i < nrProduse; i++) {
                    produse[i][0] = Integer.parseInt(valori[index++]);
                    produse[i][1] = Integer.parseInt(valori[index++]);
                }

                Comanda comanda = new Comanda(id, valoareTotala, dataPlasare,
                        adresaLivrare, tipPlata, idClient, nrProduse, produse);
                comenzi.add(comanda);
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return comenzi;
    }


    public static void main(String[] args) {

    }
}
