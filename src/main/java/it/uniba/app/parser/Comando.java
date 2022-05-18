package it.uniba.app.parser;

import it.uniba.app.Gioco;

/**
 * Comando è una classe Control.
 * Offre definizioni interne dei comandi accessibili dall'applicazione.
 * 
 * @author Vito Verna - 746463
 * @author Sergio Mari - 741336
 */
public class Comando {
    /**
     * 
     * Esecuzione effettiva del comando "/nuova [PAROLA]".
     * 
     * @param input String da utilizzare come parola segreta
     * @param gioco La partita attuale (Gioco) che contiene la parola segreta che si vuole modficare
     */
    //TODO Decidere se eseguire i controlli qui o all'esterno
    public void nuova(String input, Gioco gioco){
        gioco.setParolaSegreta(input);
    }

}
