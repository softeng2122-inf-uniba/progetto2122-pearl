package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.uniba.app.matrice.Matrice;

/**
 * Classe che contiene i casi di test per la classe Gioco.
 */
public class TestGioco {
    /**
     * Attributo di tipo Gioco che rappresenta il gioco attualmente in
     * esecuzione.
     */
    private Gioco gioco = new Gioco();

    /**
     * Attributo di tipo String che rappresenta la parola segreta.
     */
    private String parola = "breve";

    /**
     * Metodo che testa l'impostazione della parola segreta.
     */
    @BeforeEach
    @DisplayName("Imposta la parola segreta")
    public void testSetParolaSegreta() {
        gioco.setParolaSegreta(parola);

        assertEquals(parola, gioco.getParolaSegreta());
    }

    /**
     * Metodo che testa il costruttore con parametri di Gioco.
     */
    @Test
    @DisplayName("Costruttore variabile di Gioco")
    public void testGioco() {
        Gioco giocoCostr = new Gioco(Matrice.COLONNE, Matrice.RIGHE);

        assertEquals(Matrice.COLONNE, giocoCostr.getLunghezza(),
            "Controllo lunghezza della parola");
        assertEquals(Matrice.RIGHE, giocoCostr.getTentativiMassimi(),
            "Controllo tentativi massimi");
    }

    /**
     * Metodo che testa la restituizione della lunghezza.
     */
    @Test
    @DisplayName("Restituisce lunghezza")
    public void testGetLunghezza() {
        assertEquals(Matrice.COLONNE, gioco.getLunghezza());
    }

    /**
     * Metodo che testa la restituzione del tentativo attuale.
     */
    @Test
    @DisplayName("Restituisce tentativo attuale")
    public void testGetTentativo() {
        gioco.setTentativo(2);

        assertEquals(2, gioco.getTentativo());
    }

    /**
     * Metodo che testa la restituzione dell'esecuzione del gioco.
     */
    @Test
    @DisplayName("Restituisce l'esecuzione del gioco")
    public void testGetEsecuzione() {
        assertFalse(gioco.getEsecuzione(),
            "Controllo gioco non in esecuzione");

        gioco.setEsecuzione(true);
        assertTrue(gioco.getEsecuzione(),
            "Controllo gioco in esecuzione");
    }
}
