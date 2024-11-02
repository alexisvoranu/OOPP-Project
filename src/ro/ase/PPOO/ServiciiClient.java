package ro.ase.PPOO;

import java.util.Set;

public interface ServiciiClient {
    boolean aplicaDiscount(String codDiscount) throws InvalidDiscountException;
    Set<Comanda> vizualizeazaIstoricComenzi(int idClient, Set<Comanda> comenzi);
}
