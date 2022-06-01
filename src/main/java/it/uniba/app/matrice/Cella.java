package it.uniba.app.matrice;

import it.uniba.app.enumerativi.IDsColori;

/**
 * Cella e' una classe <<Entity>>.
 * Rappresenta la singola cella della matrice di gioco.
 *
 * @author Alessandro Mazzotta - 766414
 * @author Vito Verna - 746463
 * @author Sergio Mari - 741336
 */
public class Cella {

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
        colore = IDsColori.VUOTO.getId();
        lettera = ' ';
        coloreUTF = IDsColori.VUOTO.getUTFString();
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
        if (color == IDsColori.VUOTO.getId()) {
            colore = color;
            coloreUTF = IDsColori.VUOTO.getUTFString();
        } else if (color == IDsColori.GRIGIO.getId()) {
            colore = color;
            coloreUTF = IDsColori.GRIGIO.getUTFString();
        } else if (color == IDsColori.GIALLO.getId()) {
            colore = color;
            coloreUTF = IDsColori.GIALLO.getUTFString();
        } else if (color == IDsColori.VERDE.getId()) {
            colore = color;
            coloreUTF = IDsColori.VERDE.getUTFString();
        } else {
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

    /**
     * Metodo che riceve una lettera e la inserisce nella cella.
     *
     * @param carattere - carattere da inserire
     */
    public void setLettera(final char carattere) {
        lettera = carattere;
    }

    /**
     * Metodo che converte la cella in una stringa (String), formato da:
     * il colore di sfondo (anche vuoto);
     * se lo sfondo è colorato, cambia il colore anche al carattere;
     * lettera contenuta nella cella;
     * reset dei colori naturali del terminale.
     *
     * @return la stringa (String) che rappresenta la cella
     */
    @Override
    public String toString() {
        if (colore == 0) {
            return coloreUTF + '[' + lettera + ']'
                + IDsColori.VUOTO.getUTFString();
        } else {
            return coloreUTF + IDsColori.CARATTERENERO.getUTFString()
                + '[' + lettera + ']'
                + IDsColori.VUOTO.getUTFString();
        }
    }
}
