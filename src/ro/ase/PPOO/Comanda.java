package ro.ase.PPOO;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Comanda implements ServiciiClient{
    private int id;
    private double valoareTotala;
    private Date dataPlasare;
    private String adresaLivrare;
    private String tipPlata;
    private int idClient;
    private int nrProduse;
    private int[][] produse;

    public Comanda() {
    }

    public Comanda(int id, double valoareTotala, Date dataPlasare, String adresaLivrare, String tipPlata, int idClient, int nrProduse, int[][] produse) {
        this.id = id;
        this.valoareTotala = valoareTotala;
        this.dataPlasare = dataPlasare;
        this.adresaLivrare = adresaLivrare;
        this.tipPlata = tipPlata;
        this.idClient = idClient;
        this.nrProduse = nrProduse;
        this.produse = produse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValoareTotala() {
        return valoareTotala;
    }

    public void setValoareTotala(double valoareTotala) {
        this.valoareTotala = valoareTotala;
    }

    public Date getDataPlasare() {
        return dataPlasare;
    }

    public void setDataPlasare(Date dataPlasare) {
        this.dataPlasare = dataPlasare;
    }

    public String getAdresaLivrare() {
        return adresaLivrare;
    }

    public void setAdresaLivrare(String adresaLivrare) {
        this.adresaLivrare = adresaLivrare;
    }

    public String getTipPlata() {
        return tipPlata;
    }

    public void setTipPlata(String tipPlata) {
        this.tipPlata = tipPlata;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNrProduse() {
        return nrProduse;
    }

    public void setNrProduse(int nrProduse) {
        this.nrProduse = nrProduse;
    }

    public int[][] getProduse() {
        return produse;
    }

    public void setProduse(int[][] produse) {
        this.produse = produse;
    }

    @Override
    public String toString() {
        return "Comanda cu " +
                "numărul " + id +
                ", având valoarea totală de " + valoareTotala +
                " ron a fost plasată cu succes!";
    }

    @Override
    public boolean aplicaDiscount(String codDiscount) throws InvalidDiscountException {
        if (!(codDiscount.equals("EXTRA20") || codDiscount.equals("FALL20"))) {
            throw new InvalidDiscountException("Codul de discount introdus nu este valid!");
        }
        else{
            return true;
        }
    }

    @Override
    public Set<Comanda> vizualizeazaIstoricComenzi(int idClient, Set<Comanda> comenzi) {
        Set<Comanda> comenziClient = comenzi.stream()
                .filter(comanda -> comanda.getIdClient() == idClient)
                .sorted((c1, c2) -> Integer.compare(c1.getId(), c2.getId()))
                .collect(Collectors.toSet());
        return comenziClient;
    }

    public static void adaugaComandaInFisier(Comanda comanda){
        try (var fisier = new BufferedWriter(new FileWriter(
                "C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\comenzi.txt", true))) {
            fisier.write(comanda.id+"\t"+comanda.valoareTotala+"\t"+
                    comanda.dataPlasare+"\t"+comanda.adresaLivrare+"\t"+
                    comanda.tipPlata+"\t"+comanda.idClient+"\t"+comanda.nrProduse+"\t");
            for(int i=0;i<comanda.nrProduse;i++)
                fisier.write(comanda.produse[i][0]+"\t"+comanda.produse[i][1]+"\t");
            fisier.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
