package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

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
}
