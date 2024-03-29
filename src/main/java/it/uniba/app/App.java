package it.uniba.app;

import java.util.Scanner;
import java.util.StringTokenizer;

import it.uniba.app.sistema.Comando;
import it.uniba.app.sistema.Parser;
import it.uniba.app.enumerativi.IDsColori;
import it.uniba.app.enumerativi.IDsComandi;
import it.uniba.app.enumerativi.IDsParole;

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
     * @param flag - il flag passato come parametro alla prima
     * esecuzione del programma
     */
    public void start(final Parser pars, final Comando cmd,
            final Gioco gioco, final String flag) {

        Scanner scanner = new Scanner(System.in, "UTF-8");
        String inputSTR;
        int input;
        int statoSegreta;
        boolean risolto;
        boolean chiusura = false;

        pulisciSchermo();

        if (!"".equals(flag)) {
            input = pars.parseInput(flag, gioco);
            if (input == IDsComandi.HELP.getId()) {
                cmd.help();
            } else {
                System.out.println(IDsColori.CARATTEREROSSO.getUTFString()
                + "Comando " + flag + "inesistente!"
                + IDsColori.VUOTO.getUTFString());
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

            if (input == IDsComandi.NONVALIDO.getId()) {
                pulisciSchermo();
                System.out.println(IDsColori.CARATTEREROSSO.getUTFString()
                + "Comando " + inputSTR + " inesistente!"
                + IDsColori.VUOTO.getUTFString());

            } else if (input == IDsComandi.NUOVA.getId()) {
                if (!gioco.getEsecuzione()) {
                    StringTokenizer strtok = new StringTokenizer(inputSTR, " ");
                    strtok.nextToken();

                    if (strtok.hasMoreTokens()) {
                        pulisciSchermo();
                        statoSegreta = cmd.nuova(strtok.nextToken(), gioco);

                        if (statoSegreta == IDsParole.NONVALIDO.getId()) {
                            pulisciSchermo();
                            System.out.println(
                            IDsColori.CARATTEREROSSO.getUTFString()
                            + "Parola non valida!"
                            + IDsColori.VUOTO.getUTFString());

                        } else if (statoSegreta == IDsParole.LUNGA.getId()) {
                            pulisciSchermo();
                            System.out.println(
                                IDsColori.CARATTEREROSSO.getUTFString()
                            + "Parola troppo lunga!"
                            + IDsColori.VUOTO.getUTFString());

                        } else if (statoSegreta == IDsParole.CORTA.getId()) {
                            pulisciSchermo();
                            System.out.println(
                            IDsColori.CARATTEREROSSO.getUTFString()
                            + "Parola troppo corta!"
                            + IDsColori.VUOTO.getUTFString());
                        }
                    } else {
                        pulisciSchermo();
                        System.out.println(
                        IDsColori.CARATTEREROSSO.getUTFString()
                        + "Parola segreta non inserita!"
                        + IDsColori.VUOTO.getUTFString());
                    }
                } else {
                    pulisciSchermo();
                    System.out.println(IDsColori.CARATTEREROSSO.getUTFString()
                    + "Per impostare una nuova parola"
                    + "segreta devi abbandonare la partita!"
                    + IDsColori.VUOTO.getUTFString());
                }
            } else if (input == IDsComandi.MOSTRA.getId()) {
                String secret = cmd.mostra(gioco);
                if (!"".equals(secret)) {
                    final String output = "La parola segreta e' ";
                    System.out.println(output + secret);
                } else {
                    pulisciSchermo();
                    System.out.println(
                    IDsColori.CARATTEREROSSO.getUTFString()
                    + "Nessuna parola segreta e' impostata."
                    + IDsColori.VUOTO.getUTFString());
                }
            } else if (input == IDsComandi.GIOCA.getId()) {
                pulisciSchermo();
                cmd.gioca(gioco, gioco.getMatrice());
            } else if (input == IDsComandi.ESCI.getId()) {
                System.out.println("Sei Sicuro di voler uscire? "
                        + "Premi S per confermare, N per non uscire");
                inputSTR = scanner.next().toLowerCase();
                scanner.nextLine();
                chiusura = cmd.esci(inputSTR);
            } else if (input == IDsComandi.ABBANDONA.getId()) {
                if (gioco.getEsecuzione()) {
                    boolean risultato;
                    do {
                        System.out.println("Sei sicuro di voler abbandonare? "
                                + "Premi S per confermare, N per annullare.");
                        inputSTR = scanner.next().toLowerCase();
                        risultato = cmd.abbandona(
                            gioco, gioco.getMatrice(), inputSTR);
                        scanner.nextLine();
                    } while (risultato);
                } else {
                    System.out.println(
                    IDsColori.CARATTEREROSSO.getUTFString()
                    + "Non puoi abbandonare una "
                    + "partita inesistente!"
                    + IDsColori.VUOTO.getUTFString());
                }
            } else if (input == IDsComandi.HELP.getId()) {
                pulisciSchermo();
                cmd.help();
            } else if (input == IDsParole.ACCETTABILE.getId()) {
                pulisciSchermo();
              if (gioco.getEsecuzione()) {
                risolto =  gioco.setRigaMatrice(gioco.controlloTentativi(
                    gioco.getTentativo(), gioco, inputSTR),
                    gioco.getTentativo() - 1);
                gioco.getMatrice().stampaMatrice();

                if (risolto) {
                    System.out.println("Parola trovata in "
                    + gioco.getTentativo() + " tentativi");
                    cmd.abbandona(gioco, gioco.getMatrice(), "s");
                } else {
                    gioco.setTentativo(gioco.getTentativo() + 1);
                }
                if (gioco.getTentativiMassimi() < gioco.getTentativo()) {
                    pulisciSchermo();
                    System.out.println("Tentativi terminati."
                    + " La parola segreta e': " + gioco.getParolaSegreta());
                    cmd.abbandona(gioco, gioco.getMatrice(), "s");
                }
              } else {
                System.out.println(IDsColori.CARATTEREROSSO.getUTFString()
                + "Gioco non eseguito" + IDsColori.VUOTO.getUTFString());
                }
            } else if (gioco.getEsecuzione()
            && input == IDsParole.CORTA.getId()) {
                System.out.println(IDsColori.CARATTEREROSSO.getUTFString()
                + "Parola troppo corta\n" + IDsColori.VUOTO.getUTFString());
            } else if (gioco.getEsecuzione()
            && input == IDsParole.LUNGA.getId()) {
                System.out.println(IDsColori.CARATTEREROSSO.getUTFString()
                + "Parola troppo lunga\n" + IDsColori.VUOTO.getUTFString());
            } else if (gioco.getEsecuzione()
            && input != IDsParole.ACCETTABILE.getId()) {
                System.out.println(IDsColori.CARATTEREROSSO.getUTFString()
                + "Parola non valida\n" + IDsColori.VUOTO.getUTFString());
            }
        } while (!chiusura);
        pulisciSchermo();

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
        Gioco gioco = new Gioco();

        if (args.length > 0) {
            new App().start(parser, comando, gioco, args[0]);
        } else {
            new App().start(parser, comando, gioco, "");
        }
    }
}
