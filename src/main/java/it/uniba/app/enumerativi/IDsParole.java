package it.uniba.app.enumerativi;

/**
 * IDsParole e' un enumeratore con identificatori per lo stato delle parole.
 * Aiuta nella flessibilita' del codice.
 *
 * Questo enumeratore e' composto da:
 * <ul>
 * <li>id (int) - Valore costante che fa da identificatore dello stato. </li>
 * </ul>
 * 
 * Ogni id corrisponde ad un diverso stato, mostrati di seguito.
 * Possibili valori di id:
 * <ul>
 * <li>NONVALIDO: 0 - Input contenente caratteri non validi </li>
 * <li>LUNGA: -1 - L'input e' troppo lungo </li>
 * <li>CORTA: -2 - L'input e' troppo corto </li>
 * <li>ACCETTABILE: -3 - L'input segue i criteri di accettazione </li>
 * </ul>
 *
 * @author Sergio Mari - 741336
 */
public enum IDsParole {

    /**
     * 0.
     *
     * Si riferisce a tutte le parole che contengono caratteri negati.
     */
    NONVALIDO(0),
    /**
     * -1.
     *
     * Si riferisce a tutte le parole piu' lunghe di una certa lunghezza.
     *
     * @see it.uniba.app.Gioco#getLunghezza()
     */
    LUNGA(-1),
    /**
     * -2.
     *
     * Si riferisce a tutte le parole piu' corte di una certa lunghezza.
     *
     * @see it.uniba.app.Gioco#getLunghezza()
     */
    CORTA(-2),
    /**
     * -3
     *
     * Si riferisce a tutte le parole che seguono i requisiti imposti.
     *
     * @see it.uniba.app.sistema.Parser#parseParola(java.lang.String, int)
     */
    ACCETTABILE(-3);

    /**
     * Elemento identificatore degli stati.
     */
    private final int id;

    IDsParole(final int nid) {
        this.id = nid;
    }

    /**
     * Rilascio dell'identificatore privato dello stato.
     *
     * @return
     *      L'identificatore dello stato.
     */
    public int getId() {
        return id;
    }
}
