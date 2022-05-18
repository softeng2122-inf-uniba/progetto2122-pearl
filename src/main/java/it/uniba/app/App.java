package it.uniba.app;

import it.uniba.app.matrice.Matrice;
import it.uniba.app.sistema.Comando;
import it.uniba.app.sistema.Parser;
import java.util.Scanner;

/**
 * App e' una classe <<Boundary>>.
 * Rappresenta la classe principale.
 */
public final class App {

    /**
     * Inizio dell'applicazione.
     *
     * @param args - argomenti da linea di comando
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        Parser parser = new Parser();
        Comando comando = new Comando();
        Gioco gioco = new Gioco(Matrice.COLONNE, Matrice.RIGHE);
        Matrice mat = new Matrice();

        String inputSTR;
        int input;
        int statoSegreta;
    }
}
