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

        if (!"".equals(flag)) {
            input = pars.parseInput(flag, gioco);
            if (input == Parser.IDsComandi.HELP.getId()){
                cmd.help();
            }
        } else {
            System.out.println("Comando " + flag + " insistente!");
        }

        do {
            if (!gioco.getEsecuzione()) {
                System.out.println("Scrivi /help per vedere la lista dei "
                        + "comandi o /gioca per iniziare a giocare");
            } else {
                System.out.println("Inserisci un comando o fai un tentativo!");
            }

            inputSTR = scanner.next().toLowerCase();
            scanner.nextLine();
            input = pars.parseInput(inputSTR, gioco);

            if (input == Parser.IDsComandi.NONVALIDO.getId()) {
                System.out.println("Comando inesistente!");

            } else if (input == Parser.IDsComandi.NUOVA.getId()) {
                if (!gioco.getEsecuzione()) {
                    System.out.println("Inserisci la nuova parola segreta");
                    inputSTR = scanner.next().toLowerCase();
                    scanner.nextLine();
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
            } else if (input == Parser.IDsComandi.MOSTRA.getId()) {
                String secret = cmd.mostra(gioco);
                if (!"".equals(secret)) {
                    final String output = "La parola segreta e' ";
                    System.out.println(output + secret);
                } else {
                    System.out.println("Nessuna parola segreta e' impostata.");
                }
            } else if (input == Parser.IDsComandi.GIOCA.getId()) {

                cmd.gioca(gioco, mat);
            } else if (input == Parser.IDsComandi.ESCI.getId()) {
                System.out.print("Sei Sicuro di voler uscire? "
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
                        scanner.nextLine();
                        risultato = cmd.abbandona(gioco, mat, inputSTR);
                    } while (risultato);
                } else {
                    System.out.println("Non puoi abbandonare una "
                            + "partita inesistente!");
                }
            } else if (input == Parser.IDsComandi.HELP.getId()) {
                cmd.help();
            } else if (input == Parser.IDsParole.ACCETTABILE.getId()) {
              if (gioco.getEsecuzione()) {
                risolto =  mat.setRiga(pars.parseTentativi(
                    gioco.getTentativo(), gioco, inputSTR),
                    gioco.getTentativo() - 1);
                mat.stampaMatrice();

                if (risolto) {
                    System.out.println("Parola trovata in "
                    + gioco.getTentativo() + " tentativi");
                    cmd.abbandona(gioco, mat, "s");
                }
                gioco.setTentativo(gioco.getTentativo() + 1);
                if (gioco.getTentativiMassimi() < gioco.getTentativo()) {
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
     * Inizio dell'applicazione.
     *
     * @param args - argomenti da linea di comando
     */
    public static void main(final String[] args) {

        Parser parser = new Parser();
        Comando comando = new Comando();
        Gioco gioco = new Gioco(Matrice.COLONNE, Matrice.RIGHE);
        Matrice matrice = new Matrice();

        if (args.length != 0)
            new App().start(parser, comando, gioco, matrice, args[0]);
        else
            new App().start(parser, comando, gioco, matrice, "");
    }
}
