package ro.ase.PPOO;

import java.io.*;
import java.util.*;

public class Comanda implements ServiciiClient{
    private int id;
    private int numar;
    private double valoareTotala;
    private Date dataPlasare;
    private String adresaLivrare;
    private String tipPlata;
    private int idClient;
    private int nrProduse;
    private int[] idProduse;

    public Comanda() {
    }

    public Comanda(int id, int numar, double valoareTotala, Date dataPlasare, String adresaLivrare, String tipPlata, int idClient, int nrProduse, int[] idProduse) {
        this.id = id;
        this.numar = numar;
        this.valoareTotala = valoareTotala;
        this.dataPlasare = dataPlasare;
        this.adresaLivrare = adresaLivrare;
        this.tipPlata = tipPlata;
        this.idClient = idClient;
        this.nrProduse = nrProduse;
        this.idProduse = idProduse;

        try (var fisier = new BufferedWriter(new FileWriter("C:\\Users\\Alex Isvoranu\\Desktop\\Facultate\\PPOO\\Proiect\\src\\ro\\ase\\PPOO\\Date\\comenzi.txt"))) {

            fisier.write(id+"\t"+numar+"\t"+valoareTotala+"\t"+dataPlasare+"\t"+adresaLivrare+"\t"+tipPlata+"\t"+idClient+"\t"+nrProduse+"\t");
            for(int i=0;i<nrProduse;i++)
                fisier.write(idProduse[i]+"\t");
            fisier.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
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

    public int[] getIdProduse() {
        return idProduse;
    }

    public void setIdProduse(int[] idProduse) {
        this.idProduse = idProduse;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", numar=" + numar +
                ", valoareTotala=" + valoareTotala +
                ", dataPlasare=" + dataPlasare +
                ", adresaLivrare='" + adresaLivrare + '\'' +
                ", tipPlata='" + tipPlata + '\'' +
                ", idClient=" + idClient +
                ", nrProduse=" + nrProduse +
                ", idProduse=" + Arrays.toString(idProduse) +
                '}';
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

}
