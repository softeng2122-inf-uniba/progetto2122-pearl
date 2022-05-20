package it.uniba.app.matrice;

/**
 * Matrice e' una classe <<Controllo>>.
 * Rappresenta la matrice di gioco.
 *
 * @author Alessandro Mazzotta - 766414
 * @author Vito Verna - 746463
 */
public class Matrice {

    /**
     * Costante di tipo int; identifica il numero di righe della matrice.
     */
    public static final int RIGHE = 6;

    /**
     * Costante di tipo int; identifica il numero di colonne della matrice.
     */
    public static final int COLONNE = 5;

    /**
     * Attributo di tipo Cella per la creazione della matrice.
     */
    private final Cella[][] mat;

    /**
     * La matrice viene inizializzata da un insieme di Celle vuote,
     * con una dimensione: RIGHExCOLONNE.
     */
    public Matrice() {
        mat = new Cella[RIGHE][COLONNE];
        int i;
        int j;

        for (i = 0; i < RIGHE; i++) {
            for (j = 0; j < COLONNE; j++) {
                mat[i][j] = new Cella();
            }
        }
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
    public boolean setRiga(final Cella[] array, final int tentativo) {
        boolean corretto = true;
        for (int i = 0; i < COLONNE; i++) {
            if (array[i].getColore() != Cella.IDsColori.VERDE.getId()) {
                corretto = false;
            }
            this.setCella(tentativo, i, array[i].getColore(),
            array[i].getLettera());
        }
        return corretto;
    }

    /**
     * Metodo che si occupa della stampa della matrice
     * usando il toString() di Cella.
     */
    public void stampaMatrice() {
        int i;
        int j;
        for (i = 0; i < RIGHE; i++) {
            for (j = 0; j < COLONNE; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Metodo che si occupa di impostare il colore e la lettera
     * della cella in posizione ixj.
     *
     * @param i - indice di riga
     * @param j - indice della colonna
     * @param colore - colore da impostare
     * @param lettera - lettera da impostare
     */
    public void setCella(final int i, final int j, final int colore,
            final char lettera) {
        mat[i][j].setColore(colore);
        mat[i][j].setLettera(lettera);
    }

    /**
     * Metodo che si occupa di reimpostare la matrice a valori vuoti.
     */
    public void resetMatrice() {
        int i;
        int j;

        for (i = 0; i < RIGHE; i++) {
            for (j = 0; j < COLONNE; j++) {
                setCella(i, j, Cella.IDsColori.VUOTO.getId(), ' ');
            }
        }
    }
}
