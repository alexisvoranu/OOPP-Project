package ro.ase.PPOO.Clase;

import java.util.Set;
/**
 * Interfața ServiciiClient definește serviciile legate de comenzile unui client
 * Include aplicarea unui discount și vizualizarea istoricului comenzilor
 */
public interface ServiciiClient {
    /**
     * Aplica un discount pe baza unui cod de reducere
     *
     * @param codDiscount Codul de discount care se aplică
     * @return true dacă discount-ul a fost aplicat cu succes, altfel false
     */
    boolean aplicaDiscount(String codDiscount);

    /**
     * Vizualizează istoricul comenzilor unui client specific
     *
     * @param idClient ID-ul clientului pentru care se dorește vizualizarea istoricului comenzilor
     * @param comenzi Set-ul de comenzi din care se filtrează comenzile clientului specificat
     * @return Un Set de comenzi care conține comenzile asociate clientului cu ID-ul specificat
     */
    Set<Comanda> vizualizeazaIstoricComenzi(int idClient, Set<Comanda> comenzi);
}
