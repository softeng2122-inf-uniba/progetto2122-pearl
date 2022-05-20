package it.uniba.app;

import java.util.Scanner;
import java.util.StringTokenizer;

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
     * @param flag - il flag passato come parametro alla prima
     * esecuzione del programma
     */
    public void start(final Parser pars, final Comando cmd,
            final Gioco gioco, final Matrice mat, final String flag) {

        Scanner scanner = new Scanner(System.in, "UTF-8");

        String inputSTR;
        int input;
        int statoSegreta;
        boolean risolto;
        boolean chiusura = false;

        pulisciSchermo();

        if (!"".equals(flag)) {
            input = pars.parseInput(flag, gioco);
            if (input == Parser.IDsComandi.HELP.getId()) {
                cmd.help();
            } else {
                System.out.println("Comando " + flag + "inesistente!");
            }
        }

        do {
            if (!gioco.getEsecuzione()) {
                System.out.println("Scrivi /help per vedere la lista dei "
                        + "comandi o /gioca per iniziare a giocare");
            } else {
                System.out.println("Inserisci un comando o fai un tentativo!");
            }

            inputSTR = scanner.nextLine().toLowerCase();
            input = pars.parseInput(inputSTR, gioco);

            if (input == Parser.IDsComandi.NONVALIDO.getId()) {
                pulisciSchermo();
                System.out.println("Comando " + inputSTR + " inesistente!");

            } else if (input == Parser.IDsComandi.NUOVA.getId()) {
                if (!gioco.getEsecuzione()) {
                    StringTokenizer strtok = new StringTokenizer(inputSTR, " ");
                    strtok.nextToken();

                    if (strtok.hasMoreTokens()) {
                        pulisciSchermo();
                        statoSegreta = cmd.nuova(strtok.nextToken(), gioco);

                        if (statoSegreta
                            == Parser.IDsParole.NONVALIDO.getId()) {
                            pulisciSchermo();
                            System.out.println("Parola non valida!");

                        } else if (statoSegreta
                            == Parser.IDsParole.LUNGA.getId()) {
                            pulisciSchermo();
                            System.out.println("Parola troppo lunga!");

                        } else if (statoSegreta
                            == Parser.IDsParole.CORTA.getId()) {
                            pulisciSchermo();
                            System.out.println("Parola troppo corta!");

                        }
                    } else {
                        pulisciSchermo();
                        System.out.println("Parola segreta non inserita!");
                    }
                } else {
                    pulisciSchermo();
                    System.out.println("Per impostare una nuova parola"
                            + " segreta devi abbandonare la partita!");
                }
            } else if (input == Parser.IDsComandi.MOSTRA.getId()) {
                String secret = cmd.mostra(gioco);
                if (!"".equals(secret)) {
                    final String output = "La parola segreta e' ";
                    System.out.println(output + secret);
                } else {
                    pulisciSchermo();
                    System.out.println("Nessuna parola segreta e' impostata.");
                }
            } else if (input == Parser.IDsComandi.GIOCA.getId()) {
                pulisciSchermo();
                cmd.gioca(gioco, mat);
            } else if (input == Parser.IDsComandi.ESCI.getId()) {
                System.out.println("Sei Sicuro di voler uscire? "
                        + "Premi S per confermare, N per non uscire");
                inputSTR = scanner.next().toLowerCase();
                scanner.nextLine();
                chiusura = cmd.esci(inputSTR);
            } else if (input == Parser.IDsComandi.ABBANDONA.getId()) {
                if (gioco.getEsecuzione()) {
                    boolean risultato;
                    do {
                        System.out.println("Sei sicuro di voler abbandonare? "
                                + "Premi S per confermare, N per annullare.");
                        inputSTR = scanner.next().toLowerCase();
                        risultato = cmd.abbandona(gioco, mat, inputSTR);
                        scanner.nextLine();
                    } while (risultato);
                    pulisciSchermo();
                } else {
                    System.out.println("Non puoi abbandonare una "
                            + "partita inesistente!");
                }
            } else if (input == Parser.IDsComandi.HELP.getId()) {
                pulisciSchermo();
                cmd.help();
            } else if (input == Parser.IDsParole.ACCETTABILE.getId()) {
                pulisciSchermo();
              if (gioco.getEsecuzione()) {
                risolto =  mat.setRiga(pars.parseTentativi(
                    gioco.getTentativo(), gioco, inputSTR),
                    gioco.getTentativo() - 1);
                mat.stampaMatrice();

                if (risolto) {
                    pulisciSchermo();
                    System.out.println("Parola trovata in "
                    + gioco.getTentativo() + " tentativi");
                    cmd.abbandona(gioco, mat, "s");
                } else {
                    gioco.setTentativo(gioco.getTentativo() + 1);
                }
                if (gioco.getTentativiMassimi() < gioco.getTentativo()) {
                    pulisciSchermo();
                    System.out.println("Tentativi terminati."
                    + " La parola segreta e': " + gioco.getParolaSegreta());
                    cmd.abbandona(gioco, mat, "s");
                }
              } else {
                System.out.println("Gioco non eseguito");
                }
            }
        } while (!chiusura);

        scanner.close();
    }

    /**
     * Metodo che si occupa della pulizia dello schermo dopo
     * determinate stampe.
     */
    private void pulisciSchermo() {
        final String ansiCLS = "\u001b[2J";
        final String ansiHOME = "\u001b[H";
        System.out.print(ansiCLS + ansiHOME);
        System.out.flush();
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

        if (args.length > 0) {
            new App().start(parser, comando, gioco, matrice, args[0]);
        } else {
            new App().start(parser, comando, gioco, matrice, "");
        }
    }
}
