package it.uniba.app.matrice;

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
     * IDsColori e' un enumeratore con identificatori finali sequenziali
     * per i colori disponibili come sfondo o per i caratteri.
     * Ogni colore ha anche un codice UTF per l'effettiva visualizzazione a
     * schermo.
     *
     * id (int) - Valore costante che fa da identificatore del colore.
     * uftString (String) - Codice UTF per il rispettivo colore.
     * Possibili valori di id...
     * CARATTERENERO: -1 - Colore del carattere nero
     * VUOTO: 0 - Colore di sfondo non impostato (come per inizializzazione)
     * GRIGIO: 1 - Colore di sfondo grigio
     * GIALLO: 2 - Colore di sfondo giallo
     * VERDE: 3 - Colore di sfondo verde
     */
    public enum IDsColori {
        /**
        * Costante di tipo String; codice UTF per il carattere nero
        * usato a causa del poco contrasto con sfondo colorato.
        */
        CARATTERENERO(-1, "\u001b[38;2;0;0;0m"),
        /**
        * ID e codice UTF per il reset dello sfondo.
        */
        VUOTO(0, "\u001b[0m"),
        /**
        * ID e codice UTF per lo sfondo grigio.
        */
        GRIGIO(1, "\u001b[48;2;128;128;128m"),
        /**
        * ID e codice UTF per lo sfondo giallo.
        */
        GIALLO(2, "\u001b[48;2;204;204;0m"),
        /**
        * Costante di tipo String; codice UTF per lo sfondo verde.
        */
        VERDE(3, "\u001b[48;2;0;204;0m");

        /**
        * Elemento identificatore dei colori.
        */
        private int id;
        /**
        * Elemento con stringa UTF dei colori.
        */
        private String uftString;

        /**
         * Preparatore enumerazione.
         * @param nid - L'id da assegnare agli elementi dell'enumeratore.
         * @param nutfString - La stringa UTF da assegnare agli elementi.
         */
        IDsColori(final int nid, final String nutfString) {
            id = nid;
            uftString = nutfString;
        }

        /**
         * Rilascio dell'identificatore privato del colore.
         * @return L'identificatore del colore.
         */
        public int getId() {
            return id;
        }

        /**
         * Rilascio della stringa UTF privata del colore.
         * @return La stringa UTF del colore.
         */
        public String getUTFString() {
            return uftString;
        }
    }

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
