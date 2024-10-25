package ro.ase.PPOO;

import java.util.Queue;

public interface ServiciiClient {
    void aplicaDiscount(float discount) throws InvalidDiscountException;
    Queue<Comanda> vizualizeazaIstoricComenzi(int idClient);
    Object[][] obtineStatisticiClient(int idClient);
}
