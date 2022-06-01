package it.uniba.app.sistema;

import java.util.StringTokenizer;
import it.uniba.app.matrice.Cella;
import it.uniba.app.matrice.Matrice;

import java.util.Map;
import java.util.HashMap;

import it.uniba.app.Gioco;
import it.uniba.app.enumerativi.IDsComandi;
import it.uniba.app.enumerativi.IDsParole;
import it.uniba.app.enumerativi.IDsColori;

/**
 * Parser e' una classe <<Control>>.
 * Offre strumenti di controllo dei comandi e delle parole.
 *
 * @author Vito Verna - 746463
 * @author Sergio Mari - 741336
 * @author Fabio Spiriticchio - 736518
 * @author Alessia Marsico - 738959
 */
public class Parser {

    /**
     * parseComando identifica un comando richiesto dall'utente.
     * Il metodo ritorna un numero intero (int) che ne identifica il comando.
     *
     * @see Parser.IDsComandi
     *
     * @param input
     *  - String input inserita dall'utente in cui cercare il comando.
     *
     * @return L'identificatore del comando ricevuto.
     */
    public int parseComando(final String input) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        String primaSubstr = tok.nextToken();
        int risultato = 0;
        switch (primaSubstr) {
            case "/nuova":
                risultato = IDsComandi.NUOVA.getId();
                break;
            case "/mostra":
                risultato = IDsComandi.MOSTRA.getId();
                break;
            case "/gioca":
                risultato = IDsComandi.GIOCA.getId();
                break;
            case "/esci":
                risultato = IDsComandi.ESCI.getId();
                break;
            case "/abbandona":
                risultato = IDsComandi.ABBANDONA.getId();
                break;
            case "/help":
                risultato = IDsComandi.HELP.getId();
                break;
            case "--help":
                risultato = IDsComandi.HELP.getId();
                break;
            case "-h":
                risultato = IDsComandi.HELP.getId();
                break;
            default:
                risultato = IDsComandi.NONVALIDO.getId();
        }
        return risultato;
    }

    /**
     * parseParola controlla la correttezza input secondo criteri prestabiliti.
     * Ritorna l'identificatore dello stato della parola.
     * I criteri sono:
     * Input di esattamente N lettere;
     * Input con solo caratteri latini.
     *
     * @see Parser.IDsParole
     *
     * @param input
     *  - String input inserita dall'utente su cui controllare la correttezza
     * @param lunghezzafrz
     *  - Lunghezza che l'input dovra' necessariamente seguire.
     *
     * @return L'identificatore dello stato della correttezza della parola
     */
    public int parseParola(final String input, final int lunghezzafrz) {
        //Si presuppone sia accettabile per poi contraddire
        int risultato = IDsParole.ACCETTABILE.getId();
        if (input.length() < lunghezzafrz) {
            risultato = IDsParole.CORTA.getId();
        } else if (input.length() > lunghezzafrz) {
            risultato = IDsParole.LUNGA.getId();
        }

        if (risultato == IDsParole.ACCETTABILE.getId()) {
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isLetter(input.charAt(i))) {
                    risultato = IDsParole.NONVALIDO.getId();
                }
            }
        }

        return risultato;
    }

    /**
     * parseInput richiama la funzione corretta in base al contenuto dell'input.
     *
     * @see #parseParola(String)
     * @see #parseComando(String)
     *
     * @param input
     *  - String input inserita dall'utente su cui effettuare la selezione.
     * @param gioco
     *  - La partita attuale.
     *
     * @return
     *  L'identificatore del risultato della operazione selezionata.
     */
    public int parseInput(final String input, final Gioco gioco) {
        int risultato = 0;

        if (input.charAt(0) == '/') {
            risultato = parseComando(input);
        } else if (input.startsWith("-")) {
            risultato = parseComando(input);
        } else if (input.startsWith("--")) {
            risultato = parseComando(input);
        } else {
            risultato = parseParola(input, gioco.getLunghezza());
        }

        return risultato;
    }

    /**
     * Effettua un controllo sulla parola inserita ad ogni tentativo
     * e assegna ad ogni carattere il rispettivo colore.
     *
     * @param tentativi effettuati dall'utente a ogni inserimento
     * @param gioco usato per accedere al valore dei tentativi massimi.
     * @param input Stringa inserita dall'utente per indovinare la parola
     *
     * @return numero tentativi effettuati
     */

    public Cella[] parseTentativi(final int tentativi, final Gioco gioco,
    final String input) {
        Map<Character, Integer> dizionario = new HashMap<>();
        char[] charInput = new char[Matrice.COLONNE];
        char[] charParola = new char[Matrice.COLONNE];
        int[] arr = new int[input.length()];

        if ((gioco.getTentativiMassimi()) >= tentativi) {
            for (byte i = 0; i < input.length(); i++) {
                charInput[i] = input.charAt(i);
                charParola[i] = gioco.getParolaSegreta().charAt(i);
                if (dizionario.containsKey(
                        gioco.getParolaSegreta().charAt(i))) {
                    dizionario.replace(
                        gioco.getParolaSegreta().charAt(i),
                        dizionario.get(gioco.getParolaSegreta().charAt(i)) + 1);
                } else {
                    dizionario.put(charParola[i], 1);
                }
            }

            for (byte i = 0; i < input.length(); i++) {
                if (charInput[i] == charParola[i]) {
                    dizionario.replace(charParola[i],
                    dizionario.get(charParola[i]) - 1);
                   arr[i] = IDsColori.VERDE.getId();
                }
            }
            for (byte i = 0; i < input.length(); i++) {
                 if (dizionario.containsKey(charInput[i])) {
                     if (arr[i] != IDsColori.VERDE.getId()) {
                        if (dizionario.get(charInput[i]) != 0) {
                            dizionario.replace(charInput[i],
                            dizionario.get(charInput[i]) - 1);
                            arr[i] = IDsColori.GIALLO.getId();
                        } else {
                            arr[i] = IDsColori.GRIGIO.getId();
                        }
                    }
                } else {
                    arr[i] = IDsColori.GRIGIO.getId();
                }
            }
        }
        Cella[] array = new Cella[Matrice.COLONNE];
        for (int i = 0; i < input.length(); i++) {
            array[i] = new Cella();
            array[i].setLettera(charInput[i]);
            if (arr[i] == IDsColori.VERDE.getId()) {
                array[i].setColore(IDsColori.VERDE.getId());
            } else if (arr[i] == IDsColori.GIALLO.getId()) {
                array[i].setColore(IDsColori.GIALLO.getId());
            } else if (arr[i] == IDsColori.GRIGIO.getId()) {
                array[i].setColore(IDsColori.GRIGIO.getId());
            }
        }
        return array;
    }
}
