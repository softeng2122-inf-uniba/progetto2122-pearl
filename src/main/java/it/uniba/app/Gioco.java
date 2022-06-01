package it.uniba.app;

import it.uniba.app.matrice.Matrice;
import it.uniba.app.matrice.Cella;
import it.uniba.app.enumerativi.IDsColori;

/**
 *
 * Gioco e' una classe <<Control>>.
 * Rappresenta la partita attualmente in esecuzione.
 *
 * @author Sergio Mari - 741336
 * @author Alessandro Mazzotta - 766414
 *
 */
public class Gioco {

    /**
     * Parola segreta della partita corrente.
     * Inizializzata sempre a vuoto per richiesta.
     * @see it.uniba.app.sistema.Comando#nuova(String, Gioco)
     */
    private String parolaSegreta = "";

    /**
     * Quanita' di caratteri della parola segreta.
     * Sara' anche la lunghezza dei tentativi per trovare la stessa.
     */
    private int lunghezza;

    /**
     * Quanita' massima di tentativi per trovare la parola segreta.
     */
    private int tentativiMassimi;

    /**
     * Numero attuale del tentativo.
     */
    private int tentativoAttuale;

    /**
     * Attributo di tipo boolean; segnala se il gioco e' in esecuzione o meno.
     */
    private boolean esecuzione;

    /**
     * Componente di Gioco che rappresenta il contenitore dei tentativi.
     */
    private Matrice matrice;

    /**
     * Costruttore di Gioco che prende le sue impostazioni dall'esterno.
     * @param lung
     * - Lunghezza della parola segreta come numero intero (int)
     * @param tentativiMax
     * - Tentativi massimi per trovare la parola segreta (int)
     */
    public Gioco(final int lung, final int tentativiMax) {
        this.lunghezza = lung;
        this.tentativiMassimi = tentativiMax;
        tentativoAttuale = 1;

        matrice = new Matrice();
    }

    /**
     * Costruttore di Gioco che prende delle impostazioni preimpostate.
     */
    public Gioco() {
        this.lunghezza = Matrice.COLONNE;
        this.tentativiMassimi = Matrice.RIGHE;
        tentativoAttuale = 1;

        matrice = new Matrice();
    }

    /**
     * Ottieni la lunghezza che ci si aspetta abbiano la parola segreta
     *  e i tentativi per la partita attuale.
     *
     * @return La lunghezza annunciata come numero intero (int)
     */
    public int getLunghezza() {
        return lunghezza;
    }

    /**
     * Ottieni la quantita' di tentativi massimi che l'utente puo' eseguire
     *  in questa partita.
     *
     * @return La quantita' di tentativi massimi come numero intero (int)
     */
    public int getTentativiMassimi() {
        return tentativiMassimi;
    }

    /**
     * Ottieni il numero dei tentativi attuali durante l'esecuzione.
     * @return il numero di tentativi.
     */
    public int getTentativo() {
        return tentativoAttuale;
    }

    /**
     * Salva il valore del tentativo attuale.
     * @param tentativo salvato da getTenativo().
     */

    public void setTentativo(final int tentativo) {
        tentativoAttuale = tentativo;
    }

    /**
     * Imposta una nuova parola segreta per la partita corrente.
     *
     * @param nuova - La nuova parola segreta da impostare come String
     */
    public void setParolaSegreta(final String nuova) {
        parolaSegreta = nuova;
    }

    /**
     * Ottieni la parola segreta attualmente impostata nella partita.
     *
     * @return La parola segreta come String.
     */
    public String getParolaSegreta() {
        return parolaSegreta;
    }

    /**
     * Metodo che permette di ottenere lo stato di esecuzione del gioco.
     *
     * @return true se in esecuzione, false altrimenti.
     */
    public boolean getEsecuzione() {
        return esecuzione;
    }

    /**
     * Metodo che si occupa di impostare lo stato di esecuzione del gioco, se in
     * running o meno.
     *
     * @param stato true se il gioco e' in stato di running, false altrimenti.
     */
    public void setEsecuzione(final boolean stato) {
        esecuzione = stato;
    }

    /**
     * Metodo che permette di ottenere il contenitore dei tentativi.
     *
     * @return Il contenitore dei tentativi (Matrice)
     */
    public Matrice getMatrice() {
        return matrice;
    }

    
    /**
     * Imposta il colore verde se il carattere è corretto,
     * giallo o grigio altrimenti.
     *
     * @param array set di caratteri scansionati per impostare il colore
     * @param tentativo usato come posizione all'interno dell'array
     *
     * @return valore booleano che indica se la parola è corretta o meno.
     */
    public boolean setRigaMatrice(final Cella[] array, final int tentativo) {
        boolean corretto = true;
        for (int i = 0; i < Matrice.COLONNE; i++) {
            if (array[i].getColore() != IDsColori.VERDE.getId()) {
                corretto = false;
            }
            matrice.setCella(tentativo, i, array[i].getColore(),
            array[i].getLettera());
        }
        return corretto;
    }
}
