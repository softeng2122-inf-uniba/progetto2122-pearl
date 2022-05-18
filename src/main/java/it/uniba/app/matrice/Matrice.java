package it.uniba.app.matrice;

/**
 * Matrice è una classe <<Entity>>.
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
}
