package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.uniba.app.enumerativi.IDsColori;
import it.uniba.app.matrice.Cella;
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

    /**
     * Metodo che testa l'impostazione della riga nella matrice.
     */
    @Test
    @DisplayName("Imposta la riga nella matrice")
    public void testSetRigaMatrice() {
        Cella[] array = new Cella[Matrice.COLONNE];
        Cella cella;

        for (int i = 0; i < Matrice.COLONNE; i++) {
            cella = new Cella();
            cella.setLettera(parola.charAt(i));
            array[i] = cella;
        }

        assertFalse(gioco.setRigaMatrice(array, 1));

        for (int i = 0; i < Matrice.COLONNE; i++) {
            cella = new Cella();
            cella.setColore(IDsColori.VERDE.getId());
            cella.setLettera(parola.charAt(i));
            array[i] = cella;
        }

        assertTrue(gioco.setRigaMatrice(array, 1));
    }

    /**
     * Metodo che testa la restituzione della matrice.
     */
    @Test
    @DisplayName("Restituisce la matrice")
    public void testGetMatrice() {
        Cella[][] mat = new Cella[Matrice.RIGHE][Matrice.COLONNE];
        Cella[][] tmp = gioco.getMatrice().getMat();

        for (int i = 0; i < Matrice.RIGHE; i++) {
            for (int j = 0; j < Matrice.COLONNE; j++) {
                mat[i][j] = new Cella();
            }
        }

        for (int i = 0; i < Matrice.RIGHE; i++) {

            assertArrayEquals(mat[i], tmp[i]);
        }
    }

    /**
     * Metodo che testa la restituzione della riga della matrice colorata in
     * base alla parola inserita.
     */
    @Test
    @DisplayName("Restituisce la riga colorata")
    public void testControlloTentativi() {
        Cella[] array = new Cella[Matrice.COLONNE];
        Cella cella;
        final int n = 0;
        final int k = 2;
        final int j = 4;

        parola = "barbe";

        for (int i = 0; i < Matrice.COLONNE; i++) {
            cella = new Cella();
            cella.setLettera(parola.charAt(i));
            if (i == n || i == j) {
                cella.setColore(IDsColori.VERDE.getId());
            } else if (i == k) {
                cella.setColore(IDsColori.GIALLO.getId());
            } else {
                cella.setColore(IDsColori.GRIGIO.getId());
            }
            array[i] = cella;
        }

        assertArrayEquals(array, gioco.controlloTentativi(1, gioco, parola));
    }
}
