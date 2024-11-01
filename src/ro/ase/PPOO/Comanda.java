package ro.ase.PPOO;

import java.io.*;
import java.util.*;

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
                " ron a fost plasată cu succes!" + dataPlasare;
    }

    @Override
    public void aplicaDiscount(float discount) throws InvalidDiscountException {
        if (discount < 0 || discount >= 100) {
            throw new InvalidDiscountException("Discountul trebuie sa fie o valoare cuprinsa intre 0 si 100");
        }
        else{
            this.valoareTotala -= this.valoareTotala * (discount / 100);
        }
    }

    @Override
    public Queue<Comanda> vizualizeazaIstoricComenzi(int idClient) {
        Queue<Comanda> istoricComenzi = new PriorityQueue<>(Comparator.comparing(Comanda::getDataPlasare));

        return istoricComenzi;
    }


    @Override
    public Object[][] obtineStatisticiClient(int idClient) {
        Object[][] statistici = new Object[3][2];

        statistici[0][0] = "Numărul total de comenzi";
        statistici[0][1] = 5;

        statistici[1][0] = "Valoarea totală a comenzilor";
        statistici[1][1] = 750.0;

        statistici[2][0] = "Data ultimei comenzi";
        statistici[2][1] = new Date();

        return statistici;
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
