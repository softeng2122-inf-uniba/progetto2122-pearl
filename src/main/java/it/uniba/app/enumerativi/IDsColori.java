package it.uniba.app.enumerativi;

/**
 * IDsColori e' un enumeratore con identificatori finali sequenziali per i
 * colori disponibili come sfondo o per i caratteri. Ogni colore ha anche un
 * codice UTF per l'effettiva visualizzazione a schermo.
 *
 * Questo enumeratore e' composto da:
 * <ul>
 * <li>id (int) - Valore costante che fa da identificatore del colore. </li>
 * <li>uftString (String) - Codice UTF per il rispettivo colore. </li>
 * </ul>
 *
 * Possibili valori di id:
 * <ul>
 * <li>CARATTERENERO: -1 - Colore del carattere nero </li>
 * <li>VUOTO: 0 - Colore di sfondo non impostato 
 * (come per inizializzazione) </li>
 * <li>GRIGIO: 1 - Colore di sfondo grigio </li>
 * <li>GIALLO: 2 - Colore di sfondo giallo </li>
 * <li>VERDE: 3 - Colore di sfondo verde </li>
 * </ul>
 *
 * @author Sergio Mari - 741336
 */
public enum IDsColori {
    
    /**
     * Costante di tipo String; codice UTF per il carattere nero usato a causa
     * del poco contrasto con sfondo colorato.
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
     *
     * @param nid
     *      L'id da assegnare agli elementi dell'enumeratore.
     * @param nutfString
     *      La stringa UTF da assegnare agli elementi.
     */
    IDsColori(final int nid, final String nutfString) {
        id = nid;
        uftString = nutfString;
    }

    /**
     * Rilascio dell'identificatore privato del colore.
     *
     * @return
     *      L'identificatore del colore.
     */
    public int getId() {
        return id;
    }

    /**
     * Rilascio della stringa UTF privata del colore.
     *
     * @return
     *      La stringa UTF del colore.
     */
    public String getUTFString() {
        return uftString;
    }
}
