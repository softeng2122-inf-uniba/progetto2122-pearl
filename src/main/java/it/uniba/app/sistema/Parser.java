package it.uniba.app.sistema;

import java.util.Objects;
import java.util.StringTokenizer;

import it.uniba.app.Gioco;
import it.uniba.app.enumerativi.IDsComandi;
import it.uniba.app.enumerativi.IDsParole;

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
     *  0 se nessun input è stato inserito.
     */
    public int parseInput(final String input, final Gioco gioco) {
        int risultato;
        if (!Objects.isNull(input) && input.length() > 0) {
            if (input.charAt(0) == '/') {
                risultato = parseComando(input);
            } else if (input.startsWith("-")) {
                risultato = parseComando(input);
            } else if (input.startsWith("--")) {
                risultato = parseComando(input);
            } else {
                risultato = parseParola(input, gioco.getLunghezza());
            }
        } else {
            risultato = IDsParole.NONVALIDO.getId();
        }

        return risultato;
    }
}
