package it.uniba.app.sistema;

import it.uniba.app.Gioco;

/**
 * Comando è una classe <<Control>>.
 * Offre definizioni interne dei comandi accessibili dall'applicazione.
 *
 * @author Vito Verna - 746463
 * @author Sergio Mari - 741336
 */
public class Comando {
    /**
     *
     * Esecuzione effettiva del comando "/nuova [PAROLA]".
     *
     * @param input - [PAROLA] String da utilizzare come parola segreta
     * @param gioco
     *  - La partita (Gioco) che contiene la parola segreta da modificare
     *
     * @return Accettabilità della parola input.
     * @see Parser#parseParola(String)
     */
    public int nuova(final String input, final Gioco gioco) {
        int stato = new Parser().parseParola(input);

        if (stato == Parser.IDsParole.ACCETTABILE.id) {
            gioco.setParolaSegreta(input);
        }
        return stato;
    }

    /**
     * Esecuzione effettiva del comando "/mostra".
     *
     * @param gioco - La partita che contiene la parola segreta da mostrare
     * @return La parola segreta come String
     */
    public String mostra(final Gioco gioco) {
        return gioco.getParolaSegreta();
    }

}
