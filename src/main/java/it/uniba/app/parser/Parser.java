package it.uniba.app.parser;

/**
 * Parser è una classe Control.
 * Offre strumenti di controllo dei comandi e delle parole ricevute in input dall'applicazione.
 *
 * @author Vito Verna - 746463
 */
public class Parser {
    /**
     * IDsComandi è un enumeratore che offre identificatori finali per i comandi per aiutare con la flessibilità del codice.
     * 
     * id (int) - Valore costante che ne rappresenta il suo identificatore, mostrati di seguito. Ogni id corrisponde al suo omonimo comando.
     * Possibili valori di id...
     * NONVALIDO: 0 - Comando non esistente
     * NUOVA: 1 - /nuova
     * MOSTRA: 2 - /mostra
     * GIOCA: 3 - /gioca
     * HELP: 4 - /help
     * ABBANDONA: 5 - /abbandona
     * ESCI: 6 - /esci
     * 
     * @author Sergio Mari - 741336
     * 
     */
    public enum IDsComandi{
        NONVALIDO(0), NUOVA(1), MOSTRA(2), GIOCA(3), HELP(4), ABBANDONA(5), ESCI(6);

        public final int id;

        private IDsComandi(int id){
            this.id = id;
        }
    }

    /**
     * IDsParole è un enumeratore che offre identificatori finali per i vari stati in cui una parola può trovarsi quando inserita dall'utente.
     * 
     * id (int) - Valore costante che ne rappresenta il suo identificatore, mostrati di seguito.
     * Possibili valori di id...
     * NONVALIDO: 0 - Input contenente caratteri non validi
     * LUNGA: 1 - L'input è trollo lungo
     * CORTA: 2 - L'input è troppo corto
     * ACCETTABILE: 3 - L'input segue i criteri di accettazione
     * 
     * @author Sergio Mari - 741336
     * 
     */
    public enum IDsParole{
        NONVALIDO(0), LUNGA(1), CORTA(2), ACCETTABILE(3);

        public final int id;

        private IDsParole(int id){
            this.id = id;
        }
    }

    
}
