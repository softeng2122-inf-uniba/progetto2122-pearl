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

        do {
            if (!gioco.getEsecuzione()) {
                System.out.println("Scrivi /help per vedere la lista dei "
                        + "comandi o /gioca per iniziare a giocare");
            } else {
                System.out.println("Inserisci un comando o fai un tentativo!");
            }
            inputSTR = scanner.next();
            input = parser.parseInput(inputSTR, gioco);

            if (input == Parser.IDsComandi.NONVALIDO.getId()) {
                System.out.println("Comando inesistente!");

            } else if (input == Parser.IDsComandi.NUOVA.getId()) {
                if (!gioco.getEsecuzione()) {
                    inputSTR = scanner.next();
                    statoSegreta = comando.nuova(inputSTR, gioco);

                    if (statoSegreta == Parser.IDsParole.NONVALIDO.getId()) {
                        System.out.println("Parola non valida!");

                    } else if (statoSegreta == Parser.IDsParole.LUNGA.getId()) {
                        System.out.println("Parola troppo lunga!");

                    } else if (statoSegreta == Parser.IDsParole.CORTA.getId()) {
                        System.out.println("Parola troppo corta!");

                    }
                } else {
                    System.out.println("Per impostare una nuova parola"
                            + " segreta devi abbandonare la partita!");
                }
            } else if (input == Parser.IDsComandi.GIOCA.getId()) {

                comando.gioca(gioco, mat);
            }
        } while (input != Parser.IDsComandi.ESCI.getId());
    }
}
