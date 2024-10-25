package ro.ase.PPOO;

public class Produs {
    private int id;
    private String nume;
    private double pret;
    private int garantie;

    public Produs() {
    }

    public Produs(int id, String nume, double pret, int garantie) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.garantie = garantie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getGarantie() {
        return garantie;
    }

    public void setGarantie(int garantie) {
        this.garantie = garantie;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                ", garantie=" + garantie +
                '}';
    }
}
