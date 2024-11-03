package ro.ase.PPOO;

import java.util.Set;

public interface ServiciiClient {
    boolean aplicaDiscount(String codDiscount);

    Set<Comanda> vizualizeazaIstoricComenzi(int idClient, Set<Comanda> comenzi);
}
