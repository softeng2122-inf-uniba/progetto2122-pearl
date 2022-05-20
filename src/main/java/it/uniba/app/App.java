package it.uniba.app;

import java.util.Scanner;

import it.uniba.app.matrice.Matrice;
import it.uniba.app.sistema.Comando;
import it.uniba.app.sistema.Parser;

/**
 * App e' una classe <<Boundary>>. Rappresenta la classe principale.
 */
public final class App {

    /**
     * Metodo principale dell'applicazione che viene chiamato nel metodo main().
     *
     * @param pars - il parser che si occupa di riconoscere la stringa
     * @param cmd - il comando da eseguire
     * @param gioco - la partita da far iniziare
     * @param mat - la matrice di gioco
     */

    public void start(final Parser pars,
            final Comando cmd, final Gioco gioco, final Matrice mat) {

        Scanner scanner = new Scanner(System.in, "UTF-8");

        String inputSTR;
        int input;
        int statoSegreta;
        boolean risolto;

        do {
            if (!gioco.getEsecuzione()) {
                System.out.println("Scrivi /help per vedere la lista dei "
                        + "comandi o /gioca per iniziare a giocare");
            } else {
                System.out.println("Inserisci un comando o fai un tentativo!");
            }

            inputSTR = scanner.next();
            input = pars.parseInput(inputSTR, gioco);

            if (input == Parser.IDsComandi.NONVALIDO.getId()) {
                System.out.println("Comando inesistente!");

            } else if (input == Parser.IDsComandi.NUOVA.getId()) {
                if (!gioco.getEsecuzione()) {
                    inputSTR = scanner.next();
                    statoSegreta = cmd.nuova(inputSTR, gioco);

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

                cmd.gioca(gioco, mat);
            } else if (input == Parser.IDsParole.ACCETTABILE.getId()) {
              if (gioco.getEsecuzione()) {
                risolto =  mat.setRiga(pars.parseTentativi(
                    gioco.getTentativo(), gioco, inputSTR),
                    gioco.getTentativo() - 1);
                mat.stampaMatrice();

                if (risolto) {
                    System.out.println("Parola trovata in "
                    + gioco.getTentativo() + " tentativi");
                    gioco.setEsecuzione(false);
                }
                gioco.setTentativo(gioco.getTentativo() + 1);
                if (gioco.getTentativiMassimi() < gioco.getTentativo()) {
                    gioco.setEsecuzione(false);
                    System.out.println("Tentativi terminati. La parola segreta e':" + gioco.getParolaSegreta());
                }
              } else {
                System.out.println("Gioco non eseguito");
                }
            }
        } while (input != Parser.IDsComandi.ESCI.getId());
        scanner.close();
    }

    /**
     * Inizio dell'applicazione.
     *
     * @param args - argomenti da linea di comando
     */
    public static void main(final String[] args) {

        Parser parser = new Parser();
        Comando comando = new Comando();
        Gioco gioco = new Gioco(Matrice.COLONNE, Matrice.RIGHE);
        Matrice matrice = new Matrice();

        new App().start(parser, comando, gioco, matrice);
    }
}
