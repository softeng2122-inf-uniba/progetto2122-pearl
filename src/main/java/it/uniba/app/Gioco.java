package it.uniba.app;

/**
 * 
 * Gioco è una classe Entity.
 * Rappresenta la partita attualmente in esecuzione.
 * 
 * @author Sergio Mari - 741336
 * 
 */
public class Gioco {
    private String parolaSegreta = "";
    private int lunghezza;
    private int tentativiMassimi;

    /**
     *  
     * @param lunghezza Lunghezza della parola segreta come numero intero (int)
     * @param tentativiMassimi Tentativi massimi per trovare la parola segreta come numero intero (int)
     */
    public Gioco(int lunghezza, int tentativiMassimi){
        this.lunghezza = lunghezza;
        this.tentativiMassimi = tentativiMassimi;
    }

    /**
     * Ottieni la lunghezza che ci si aspetta la parola segreta e i tentativi abbiano la per la partita attuale.
     * 
     * @return La lunghezza annunciata come numero intero (int)
     */
    public int getLunghezza(){
        return lunghezza;
    }

    /**
     * Ottieni la quantità di tentativi massimi che l'utente può eseguire in questa partita.
     * 
     * @return La quantità di tentativi massimi come numero intero (int)
     */
    public int getTentativiMassimi(){
        return tentativiMassimi;
    }

    /**
     * Imposta una nuova parola segreta per la partita corrente.
     * 
     * @param nuova La nuova parola segreta da impostare come String
     */
    public void setParolaSegreta(String nuova){
        parolaSegreta = nuova;
    }

    /** 
     * Ottieni la parola segreta attulamente impostata nella partita.
     * 
     * @return La parola segreta come String.
     */
    public String getParolaSegreta(){
        return parolaSegreta;
    }
}
