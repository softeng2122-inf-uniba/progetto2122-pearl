package it.uniba.app.matrice;

/**
 * Cella è una classe <<Entity>>.
 * Rappresenta la singola cella della matrice di gioco.
 *
 * @author Alessandro Mazzotta - 766414
 * @author Vito Verna - 746463
 */
public class Cella {

    /**
     * Costante di tipo String; codice UTF per il reset dello sfondo.
     */
    private static final String VUOTO = "\u001b[0m";

    /**
     * Costante di tipo String; codice UTF per lo sfondo grigio.
     */
    private static final String GRIGIO = "\u001b[48;2;128;128;128m";

    /**
     * Costante di tipo String; codice UTF per lo sfondo giallo.
     */
    private static final String GIALLO = "\u001b[48;2;204;204;0m";

    /**
     * Costante di tipo String; codice UTF per lo sfondo verde.
     */
    private static final String VERDE = "\u001b[48;2;0;204;0m";

    /**
     * Costante di tipo String; codice UTF per il carattere nero
     * usato a causa del poco contrasto con sfondo colorato.
     */
    private static final String CARATTERE = "\u001b[38;2;0;0;0m";

    /**
     * Attributo di tipo int che segna il colore che deve assumere la cella.
     * colore vuoto = 0
     * colore grigio = 1
     * colore giallo = 2
     * colore verde = 3
     */
    private int colore;

    /**
     * Attributo di tipo char che contiene la lettera della cella.
     */
    private char lettera;

    /**
     * Attributo di tipo String che prende in carico il codice UTF.
     */
    private String coloreUTF;

    /**
     * Ogni cella viene inzializzata con i tre attributi vuoti.
     */
    public Cella() {
        colore = 0;
        lettera = ' ';
        coloreUTF = VUOTO;
    }

    /**
     * Metodo che permette di ottenere il colore della cella.
     *
     * @return colore - valore numerico del colore della cella
     */
    public int getColore() {
        return colore;
    }

    /**
     * Metodo che in base al valore ricevuto,
     * setta il codice UTF corrispondente.
     *
     * @param color - valore numerico del colore da impostare alla cella
     */
    public void setColore(final int color) {
        final int vuoto = 0;
        final int grigio = 1;
        final int giallo = 2;
        final int verde = 3;

        switch (color) {
            case vuoto:
                colore = color;
                coloreUTF = VUOTO;
                break;
            case grigio:
                colore = color;
                coloreUTF = GRIGIO;
                break;
            case giallo:
                colore = color;
                coloreUTF = GIALLO;
                break;
            case verde:
                colore = color;
                coloreUTF = VERDE;
                break;
            default:
                System.out.println("setColore(int colore) error:\n"
                    + "colore non valido, i colori possibili sono:\n"
                    + "0 = null\n1 = grigio\n2 = giallo\n3 = verde");
        }
    }

    /**
     * Metodo che permette di ottenere la lettera contenuta nella cella.
     *
     * @return lettera - carattere contenuto nella cella
     */
    public char getLettera() {
        return lettera;
    }
}
