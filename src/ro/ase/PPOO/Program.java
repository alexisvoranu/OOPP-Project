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

    public static List<Comanda> citireComenzi() {
        List<Comanda> comenzi = new ArrayList<>();

        try (BufferedReader fisier = new BufferedReader(new FileReader(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\comenzi.txt"))) {
            String linie;
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

            while ((linie = fisier.readLine()) != null) {
                String[] valori = linie.split("\t");

                int id = Integer.parseInt(valori[0]);
                int numar = Integer.parseInt(valori[1]);
                double valoareTotala = Double.parseDouble(valori[2]);
                Date dataPlasare = dateFormat.parse(valori[3]);
                String adresaLivrare = valori[4];
                String tipPlata = valori[5];
                int idClient = Integer.parseInt(valori[6]);
                int nrProduse = Integer.parseInt(valori[7]);

                int[] idProduse = new int[nrProduse];
                for (int i = 0; i < nrProduse; i++) {
                    idProduse[i] = Integer.parseInt(valori[8 + i]);
                }

                Comanda comanda = new Comanda(id, numar, valoareTotala, dataPlasare,
                        adresaLivrare, tipPlata, idClient, nrProduse, idProduse);
                comenzi.add(comanda);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return comenzi;
    }

    public static void main(String[] args) {
        List<Produs> produse = citireProduse();
        List<Client> clienti = citireClienti();
        List<Comanda> comenzi = citireComenzi();

        produse.forEach(System.out::println);
        System.out.println();
        clienti.forEach(System.out::println);
        System.out.println();
        comenzi.forEach(System.out::println);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.OCTOBER, 24, 15, 35);
        Date dataPlasare = calendar.getTime();

        Comanda comanda1 = new Comanda(1, 10001, 297.5,
                dataPlasare,
                "Bucuresti, Str. Frumoasa, nr.7", "ramburs", 1, 3, new int[]{1, 2, 3}
        );


    }
}
