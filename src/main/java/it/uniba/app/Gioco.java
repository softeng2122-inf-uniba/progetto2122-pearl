package it.uniba.app;

import it.uniba.app.matrice.Matrice;
import it.uniba.app.matrice.Cella;

import java.util.HashMap;
import java.util.Map;

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
    public Cella[] controlloTentativi(final int tentativi, final Gioco gioco,
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
