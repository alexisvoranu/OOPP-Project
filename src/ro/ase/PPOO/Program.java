package ro.ase.PPOO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {

    public static List<Client> citireClienti() {
        final int NUMAR_VIRGULE_ASTEPTAT = 4;
        List<Client> clienti = new ArrayList<>();

        try (var fisier = new BufferedReader(new FileReader(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\clienti.txt"))) {
            String linie;
            while ((linie = fisier.readLine()) != null) {
                int numarVirgule = linie.length() - linie.replace(",", "").length();
                if (numarVirgule != NUMAR_VIRGULE_ASTEPTAT) {
                    throw new InvalidNumberOfDataInFile("Fisierul clienti.txt nu contine toate informațiile necesare unui client!");
                }

                String[] valori = linie.split(",");
                for (String valoare : valori) {
                    if (valoare == null || valoare.trim().isEmpty()) {
                        throw new NullPointerException("Exista un element gol în fișierul produse.txt!");
                    }
                }

                Client client = new Client(
                        Integer.parseInt(valori[0]),
                        valori[1],
                        valori[2],
                        valori[3],
                        valori[4]);
                clienti.add(client);
            }
        } catch (IOException | InvalidNumberOfDataInFile e) {
            throw new RuntimeException(e);
        }
        return clienti;
    }


    public static List<Produs> citireProduse() {
        final int NUMAR_VIRGULE_ASTEPTAT = 3;
        List<Produs> produse = new ArrayList<>();

        try (var fisier = new BufferedReader(new FileReader(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\produse.txt"))) {
            String linie;
            while ((linie = fisier.readLine()) != null) {
                int numarVirgule = linie.length() - linie.replace(",", "").length();
                if (numarVirgule != NUMAR_VIRGULE_ASTEPTAT) {
                    throw new InvalidNumberOfDataInFile("Fisierul produse.txt nu contine toate informațiile necesare unui produs!");
                }

                String[] valori = linie.split(",");
                for (String valoare : valori) {
                    if (valoare == null || valoare.trim().isEmpty()) {
                        throw new NullPointerException("Exista un element gol în fișierul produse.txt!");
                    }
                }

                Produs produs = new Produs(
                        Integer.parseInt(valori[0]),
                        valori[1],
                        Double.parseDouble(valori[2]),
                        Integer.parseInt(valori[3]));
                produse.add(produs);
            }
        } catch (IOException | InvalidNumberOfDataInFile e) {
            throw new RuntimeException(e);
        }
        return produse;
    }


    public static Set<Comanda> citireComenzi() {
        final int NUMAR_TABS_ASTEPTAT = 6;
        Set<Comanda> comenzi = new TreeSet<>(Comparator.comparing(Comanda::getDataPlasare));

        try (BufferedReader fisier = new BufferedReader(new FileReader(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\comenzi.txt"))) {
            String linie;
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

            while ((linie = fisier.readLine()) != null) {

                String[] valori = linie.split("\t");

                for (String valoare : valori) {
                    if (valoare == null || valoare.trim().isEmpty()) {
                        throw new NullPointerException("Exista un element gol în fișierul comenzi.txt!");
                    }
                }

                int id = Integer.parseInt(valori[0]);
                double valoareTotala = Double.parseDouble(valori[1]);
                Date dataPlasare = dateFormat.parse(valori[2]);
                String adresaLivrare = valori[3];
                String tipPlata = valori[4];
                int idClient = Integer.parseInt(valori[5]);
                int nrProduse = Integer.parseInt(valori[6]);

                int numarTabs = linie.length() - linie.replace("\t", "").length();
                if (numarTabs != NUMAR_TABS_ASTEPTAT + nrProduse * 2) {
                    throw new InvalidNumberOfDataInFile("Fisierul comenzi.txt nu contine toate informațiile necesare unei comenzi!");
                }

                int[][] produse = new int[nrProduse][2];
                int index = 7;

                for (int i = 0; i < nrProduse; i++) {
                    if (index >= valori.length) {
                        throw new ArrayIndexOutOfBoundsException("Sunt mai multe produse decat numărul specificat în comandă!");
                    }
                    produse[i][0] = Integer.parseInt(valori[index++]);

                    if (index >= valori.length) {
                        throw new ArrayIndexOutOfBoundsException("Sunt mai multe produse decat numărul specificat în comandă!");
                    }
                    int cantitate = Integer.parseInt(valori[index++]);

                    if (cantitate < 0) {
                        throw new InvalidQunatityException("Cantitatea unor produse este negativă!");
                    } else {
                        produse[i][1] = cantitate;
                    }
                }

                Comanda comanda = new Comanda(id, valoareTotala, dataPlasare,
                        adresaLivrare, tipPlata, idClient, nrProduse, produse);
                comenzi.add(comanda);
            }

        } catch (IOException | ParseException | InvalidQunatityException | InvalidNumberOfDataInFile e) {
            throw new RuntimeException(e);
        }
        return comenzi;
    }

    public static Produs gasesteProdusDupaId(int id, List<Produs> produse) {
        for (Produs produs : produse) {
            if (produs.getId() == id) {
                return produs;
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
