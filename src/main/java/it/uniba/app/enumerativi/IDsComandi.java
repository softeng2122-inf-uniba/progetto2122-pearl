package it.uniba.app.enumerativi;

/**
 * IDsComandi e' un enumeratore con identificatori finali per i comandi. Aiuta
 * nella flessibilita' del codice.
 *
 * Questo enumeratore e' composto da:
 * <ul>
 * <li>id (int) - Valore costante che fa da identificatore del comando. </li>
 * </ul>
 *
 * Ogni id corrisponde al suo omonimo comando, mostrati di seguito.
 * Possibili valori di id:
 * <ul>
 * <li>NONVALIDO: 0 - Comando non esistente </li>
 * <li>NUOVA: 1 - /nuova &lt;parola&gt; </li>
 * <li>MOSTRA: 2 - /mostra </li>
 * <li>GIOCA: 3 - /gioca </li>
 * <li>HELP: 4 - /help </li>
 * <li>ABBANDONA: 5 - /abbandona </li>
 * <li>ESCI: 6 - /esci </li>
 * </ul>
 *
 * @author Sergio Mari - 741336
 */
public enum IDsComandi {

    /**
     * 0.
     *
     * Si riferisce a tutti i comandi non esistenti.
     */
    NONVALIDO(1),
    /**
     * 1.
     *
     * @see it.uniba.app.sistema.Comando#nuova(String, it.uniba.app.Gioco)
     */
    NUOVA(2),
    /**
     * 2.
     *
     * @see it.uniba.app.sistema.Comando#mostra(it.uniba.app.Gioco)
     */
    MOSTRA(3),
    /**
     * 3.
     *
     * @see it.uniba.app.sistema.Comando#gioca(it.uniba.app.Gioco,
     * it.uniba.app.matrice.Matrice)
     */
    GIOCA(4),
    /**
     * 4.
     *
     * @see it.uniba.app.sistema.Comando#help()
     */
    HELP(5),
    /**
     * 5.
     *
     * @see it.uniba.app.sistema.Comando#abbandona(it.uniba.app.Gioco,
     * it.uniba.app.matrice.Matrice, java.lang.String)
     */
    ABBANDONA(6),
    /**
     * 6.
     *
     * @see it.uniba.app.sistema.Comando#esci(java.lang.String)
     */
    ESCI(7);

    /**
     * Elemento identificatore dei comandi.
     */
    private final int id;

    /**
     * Preparatore enumerazione.
     *
     * @param nid
     *      L'id da assegnare agli elementi dell'enumeratore.
     */
    IDsComandi(final int nid) {
        this.id = nid;
    }

    /**
     * Rilascio dell'identificatore privato del comando.
     *
     * @return
     *      L'identificatore del comando.
     */
    public int getId() {
        return id;
    }
}
