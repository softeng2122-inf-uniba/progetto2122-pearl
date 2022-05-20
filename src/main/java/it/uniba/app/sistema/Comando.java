package it.uniba.app.sistema;

import it.uniba.app.Gioco;
import it.uniba.app.matrice.Matrice;

/**
 * Comando e' una classe <<Control>>.
 * Offre definizioni interne dei comandi accessibili dall'applicazione.
 *
 * @author Vito Verna - 746463
 * @author Sergio Mari - 741336
 * @author Fabio Spiriticchio - 736518
 * @author Alessia Marsico - 738959
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
     * @return Accettabilita' della parola input.
     * @see Parser#parseParola(String)
     */
    public int nuova(final String input, final Gioco gioco) {
        int stato = new Parser().parseParola(input, gioco.getLunghezza());

        if (stato == Parser.IDsParole.ACCETTABILE.getId()) {
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

    /** Esecuzione effettiva del comando "/gioca".
     *
     * @param gioco - La partita (gioco) che sta per iniziare
     * @param mat - La prima stampa della matrice di gioco
     */
    public void gioca(final Gioco gioco, final Matrice mat) {
        if (!gioco.getEsecuzione()) {
            if (!"".equals(gioco.getParolaSegreta())) {
                gioco.setEsecuzione(true);
                mat.stampaMatrice();
            } else {
                System.out.println("Non e' stata impostata la parola segreta!");
            }
        } else {
            System.out.println("Il gioco e' gia' in esecuzione!");
        }
    }

    /**
     * Raccolta stringa per uscita.
     *
     * @param input
     * @return true se la stringa corrisponde.
     */
    public boolean esci(final String input) {
        boolean risultato = false;
        if (input.charAt(0) == 's') {
            risultato = true;
        }
        return risultato;
    }

    /**
     * Esecuzione effettiva del comando /abbandona.
     *
     * @param gioco - per il reset delle informazioni del gioco
     * @param mat - per il reset della matrice
     * @param input - per la conferma dell'utente
     * @return true se l'input e' stato riconosciuto
     */
    public boolean abbandona(final Gioco gioco, final Matrice mat,
            final String input) {
        boolean risultato;
        switch (input.charAt(0)) {
            case 's':
                risultato = false;
                gioco.setEsecuzione(false);
                gioco.setTentativo(1);
                mat.resetMatrice();
                break;
            case 'n':
                risultato = false;
                break;
            default:
                System.out.println("Non capisco cosa vuoi dirmi");
                risultato = true;
                break;
        }
        return risultato;
    }
}
