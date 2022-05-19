package it.uniba.app.matrice;

/**
 * Matrice e' una classe <<Entity>>.
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
     * Metodo che si occupa della stampa della matrice
     * usando il toString() di Cella.
     */
    public void stampaMatrice() {
        int i;
        int j;
        System.out.println();
        for (i = 0; i < RIGHE; i++) {
            for (j = 0; j < COLONNE; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
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
                setCella(i, j, 0, ' ');
            }
        }
    }
}
