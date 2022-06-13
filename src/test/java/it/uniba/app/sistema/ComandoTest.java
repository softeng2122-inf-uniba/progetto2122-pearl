package it.uniba.app.sistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.uniba.app.Gioco;
import it.uniba.app.enumerativi.IDsParole;
import it.uniba.app.matrice.Matrice;

public class ComandoTest {

    /**
     * Attributo di tipo Comando che rappresenta il comando da eseguire.
     */
    private Comando comando = new Comando();

    /**
     * Atttributo di tipo String che rappresenta la parola.
     */
    private String parola = "breve";

    /**
     * Attributo di tipo Gioco che rappresenta la partita attualmente in
     * esecuzione.
     */
    private Gioco gioco = new Gioco();

    /**
     * Attributo di tipo Matrice che rappresenta la matrice di gioco.
     */
    private Matrice mat = new Matrice();

    /**
     * Metodo che testa il funzionamento del comando /nuova.
     */
    @Test
    @DisplayName("Testa il comando /nuova")
    public void testNuova() {

        assertEquals(IDsParole.ACCETTABILE.getId(),
            comando.nuova(parola, gioco), "Paorla accettata");

        assertEquals(IDsParole.NONVALIDO.getId(),
            comando.nuova("brev3", gioco), "Parola con numeri");

        assertEquals(IDsParole.NONVALIDO.getId(),
            comando.nuova("test ", gioco), "Parola con spazi");

        assertEquals(IDsParole.NONVALIDO.getId(),
            comando.nuova("test@", gioco), "Parola con caratteri speciali");

        assertEquals(IDsParole.CORTA.getId(),
            comando.nuova("bre", gioco), "Parola troppo corta");

        assertEquals(IDsParole.LUNGA.getId(),
            comando.nuova("brevissimo", gioco), "Parola troppo lunga");

    }
}
