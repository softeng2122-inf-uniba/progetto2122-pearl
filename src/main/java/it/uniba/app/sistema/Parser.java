package it.uniba.app.sistema;

import java.util.StringTokenizer;
import it.uniba.app.matrice.Cella;
import it.uniba.app.matrice.Matrice;

import java.util.Map;
import java.util.HashMap;

import it.uniba.app.Gioco;

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
     * IDsComandi e' un enumeratore con identificatori finali per i comandi.
     * Aiuta nella flessibilita' del codice.
     *
     * id (int) - Valore costante che fa da identificatore del comando.
     * Ogni id corrisponde al suo omonimo comando, mostrati di seguito.
     * Possibili valori di id...
     * NONVALIDO: 0 - Comando non esistente
     * NUOVA: 1 - /nuova
     * MOSTRA: 2 - /mostra
     * GIOCA: 3 - /gioca
     * HELP: 4 - /help
     * ABBANDONA: 5 - /abbandona
     * ESCI: 6 - /esci
     */
    public enum IDsComandi {

        /**
         * 0.
         *
         * Si riferisce a tutti i comandi non esistenti.
         */
        NONVALIDO(0),

        /**
         * 1.
         *
         * @see Comando#nuova(String, it.uniba.app.Gioco)
         */
        NUOVA(1),

        /**
         * 2.
         *
         * @see Comando#mostra(it.uniba.app.Gioco)
         */
        MOSTRA(2),

        /**
         * 3.
         *
         * @see Comando#gioca(it.uniba.app.Gioco, it.uniba.app.matrice.Matrice)
         */
        GIOCA(3),

        /**
         * 4.
         *
         * @see Comando#help()
         */
        HELP(4),

        /**
         * 5.
         *
         * @see Comando#abbandona(it.uniba.app.Gioco,
         * it.uniba.app.matrice.Matrice, java.lang.String)
         */
        ABBANDONA(5),

        /**
         * 6.
         *
         * @see Comando#esci(java.lang.String)
         */
        ESCI(6);

        /**
         * Elemento identificatore dei comandi.
         */
        private final int id;

        /**
         * Preparatore enumerazione.
         *
         * @param nid - L'id da assegnare agli elementi dell'enumeratore.
         */
        IDsComandi(final int nid) {
            this.id = nid;
        }

        /**
         * Rilascio dell'identificatore privato del comando.
         *
         * @return L'identificatore del comando.
         */
        public int getId() {
            return id;
        }
    }

    /**
     * IDsParole e' un enumeratore con identificatori per lo stato delle parole.
     * Aiuta nella flessibilita' del codice.
     *
     * id (int) - Valore costante che fa da identificatore dello stato.
     * Ogni id corrisponde ad un diverso stato, mostrati di seguito.
     * Possibili valori di id...
     * NONVALIDO: 0 - Input contenente caratteri non validi
     * LUNGA: -1 - L'input e' troppo lungo
     * CORTA: -2 - L'input e' troppo corto
     * ACCETTABILE: -3 - L'input segue i criteri di accettazione
     *
     */
    public enum IDsParole {

        /**
         * 0.
         *
         * Si riferisce a tutte le parole che contengono caratteri negati.
         */
        NONVALIDO(0),

        /**
         * -1.
         *
         * Si riferisce a tutte le parole piu' lunghe di una certa lunghezza.
         * @see it.uniba.app.Gioco#getLunghezza()
         */
        LUNGA(-1),

        /**
         * -2.
         *
         * Si riferisce a tutte le parole piu' corte di una certa lunghezza.
         * @see it.uniba.app.Gioco#getLunghezza()
         */
        CORTA(-2),

        /**
         * -3
         *
         * Si riferisce a tutte le parole che seguono i requisiti imposti.
         * @see #parseParola(String)
         */
        ACCETTABILE(-3);

        /**
         * Elemento identificatore degli stati.
         */
        private final int id;

        IDsParole(final int nid) {
            this.id = nid;
        }

        /**
         * Rilascio dell'identificatore privato dello stato.
         *
         * @return L'identificatore dello stato.
         */
        public int getId() {
            return id;
        }
    }

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
                   arr[i] = Cella.IDsColori.VERDE.getId();
                }
            }
            for (byte i = 0; i < input.length(); i++) {
                 if (dizionario.containsKey(charInput[i])) {
                     if (arr[i] != Cella.IDsColori.VERDE.getId()) {
                        if (dizionario.get(charInput[i]) != 0) {
                            dizionario.replace(charInput[i],
                            dizionario.get(charInput[i]) - 1);
                            arr[i] = Cella.IDsColori.GIALLO.getId();
                        } else {
                            arr[i] = Cella.IDsColori.GRIGIO.getId();
                        }
                    }
                } else {
                    arr[i] = Cella.IDsColori.GRIGIO.getId();
                }
            }
        }
        Cella[] array = new Cella[Matrice.COLONNE];
        for (int i = 0; i < input.length(); i++) {
            array[i] = new Cella();
            array[i].setLettera(charInput[i]);
            if (arr[i] == Cella.IDsColori.VERDE.getId()) {
                array[i].setColore(Cella.IDsColori.VERDE.getId());
            } else if (arr[i] == Cella.IDsColori.GIALLO.getId()) {
                array[i].setColore(Cella.IDsColori.GIALLO.getId());
            } else if (arr[i] == Cella.IDsColori.GRIGIO.getId()) {
                array[i].setColore(Cella.IDsColori.GRIGIO.getId());
            }
        }
        return array;
    }
}
