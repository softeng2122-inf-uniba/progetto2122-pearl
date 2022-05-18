package it.uniba.app.sistema;

import java.util.StringTokenizer;

import it.uniba.app.Gioco;

/**
 * Parser è una classe <<Control>>.
 * Offre strumenti di controllo dei comandi e delle parole.
 *
 * @author Vito Verna - 746463
 * @author Sergio Mari - 741336
 */
public class Parser {
    /**
     * IDsComandi è un enumeratore con identificatori finali per i comandi.
     * Aiuta nella flessibilità del codice.
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
         * Si riferisce a tutti i comandi non esistenti.
         */
        NONVALIDO(0),
        /**
         * 1.
         * @see Comando#nuova(String, it.uniba.app.Gioco)
         */
        NUOVA(1),
        /**
         * 2.
         * Riferimento a comando /mostra da completare
         */
        MOSTRA(2),
        /**
         * 3.
         * Riferimento a comando /gioca da completare
         */
        GIOCA(3),
        /**
         * 4.
         * Riferimento a comando /help da completare
         */
        HELP(4),
        /**
         * 5.
         * Riferimento a comando /abbandona da completare
         */
        ABBANDONA(5),
        /**
         * 6.
         * Riferimento a comando /esci da completare
         */
        ESCI(6);

        /**
         * Elemento identificatore dei comandi.
         */
        private final int id;

        /**
         * Preparatore enumerazione.
         * @param nid - L'id da assegnare agli elementi dell'enumeratore.
         */
        IDsComandi(final int nid) {
            this.id = nid;
        }

        /**
         * Rilascio dell'identificatore privato del comando.
         * @return L'identificatore del comando.
         */
        public int getId() {
            return id;
        }
    }

    /**
     * IDsParole è un enumeratore con identificatori per lo stato delle parole.
     * Aiuta nella flessibilità del codice.
     *
     * id (int) - Valore costante che fa da identificatore dello stato.
     * Ogni id corrisponde ad un diverso stato, mostrati di seguito.
     * Possibili valori di id...
     * NONVALIDO: 0 - Input contenente caratteri non validi
     * LUNGA: 1 - L'input è trollo lungo
     * CORTA: 2 - L'input è troppo corto
     * ACCETTABILE: 3 - L'input segue i criteri di accettazione
     *
     */
    public enum IDsParole {
        /**
         * 0.
         * Si riferisce a tutte le parole che contengono caratteri negati.
         */
        NONVALIDO(0),
        /**
         * 1.
         * Si riferisce a tutte le parole più lunghe di una certa lunghezza.
         * @see it.uniba.app.Gioco#getLunghezza()
         */
        LUNGA(1),
        /**
         * 2.
         * Si riferisce a tutte le parole più corte di una certa lunghezza.
         * @see it.uniba.app.Gioco#getLunghezza()
         */
        CORTA(2),
        /**
         * Si riferisce a tutte le parole che seguono i requisiti imposti.
         * @see #parseParola(String)
         */
        ACCETTABILE(3);

        /**
         * Elemento identificatore degli stati.
         */
        private final int id;

        IDsParole(final int nid) {
            this.id = nid;
        }

        /**
         * Rilascio dell'identificatore privato dello stato.
         * @return L'identificatore dello stato.
         */
        public int getId() {
            return id;
        }
    }

    /**
     * parseComando identifica un comando richiesto dall'utente.
     * Il metodo ritorna un numero intero (int) che ne identifica il comando.
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
     *  - Lunghezza che l'input dovrà necessariamente seguire.
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
        } else {
            risultato = parseParola(input, gioco.getLunghezza());
        }

        return risultato;
    }
}
