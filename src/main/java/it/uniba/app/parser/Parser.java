package it.uniba.app.parser;

import java.util.StringTokenizer;

/**
 * Parser è una classe Control.
 * Offre strumenti di controllo dei comandi e delle parole ricevute in input dall'applicazione.
 *
 * @author Vito Verna - 746463
 * @author Sergio Mari - 741336
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
     * Sergio Mari - 741336
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
     * Sergio Mari - 741336
     * 
     */
    public enum IDsParole{
        NONVALIDO(0), LUNGA(1), CORTA(2), ACCETTABILE(3);

        public final int id;

        private IDsParole(int id){
            this.id = id;
        }
    }

    /**
     * parseComando è un metodo di Parser per identificare un comando richiesto dall'utente. Il metodo ritorna un numero intero (int) che ne indentifica il comando.
     * @see #Parser.IDsComandi
     * 
     * @param input String input inserita dall'utente in cui cercare il comando
     * 
     * @return L'identificatore del comando ricevuto. 
     * 
     * Sergio Mari - 741336
     */
    public int parseComando(String input){
        StringTokenizer tok = new StringTokenizer(input, " ");
        String primaSubstr = tok.nextToken();
        int risultato = 0;
        switch(primaSubstr){
            case "/nuova":{
                risultato = IDsComandi.NUOVA.id;
                break;
            }
            case "/mostra":{
                risultato = IDsComandi.MOSTRA.id;
                break;
            }

            default:{
                risultato = IDsComandi.NONVALIDO.id;
            }
        }
        return risultato;
    }

    /**
     * parseParola è un metodo di Parser per controllare la correttezza della parola secondo dei criteri preimpostati e ritorna l'identificatore dello stato della parola.
     * I criteri sono:
     * Input di esattamente 5 lettere.
     * Input con solo caratteri latini.
     * 
     * @see #Parser.IDsParole
     * 
     * @param input String input inserita dall'utente su cui controllare la correttezza
     * 
     * @return L'identificatore dello stato della correttezza della parola
     * 
     * Sergio Mari - 741336
     */
    public int parseParola(String input){
        int risultato = IDsParole.ACCETTABILE.id; //Si presuppone sia accettabile per poi contraddire

        if(input.length() < 5){
            risultato = IDsParole.CORTA.id;
        }else if(input.length() > 5){
            risultato = IDsParole.LUNGA.id;
        }

        if(risultato == IDsParole.ACCETTABILE.id){
            for(int i = 0; i < input.length(); i++){
                if(!Character.isLetter(input.charAt(i))){
                    risultato = IDsParole.NONVALIDO.id;
                }
            }
        }

        return risultato;
    }
}
