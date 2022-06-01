package it.uniba.app.enumerativi;

/**
     * IDsComandi e' un enumeratore con identificatori finali per i comandi.
     * Aiuta nella flessibilita' del codice.
     *
     * id (int) - Valore costante che fa da identificatore del comando.
     * Ogni id corrisponde al suo omonimo comando, mostrati di seguito.
     * Possibili valori di id...
     * NONVALIDO: 0 - Comando non esistente
     * NUOVA: 1 - /nuova
     * MOSTRA: 2 - /mostra
     * GIOCA: 3 - /gioca
     * HELP: 4 - /help
     * ABBANDONA: 5 - /abbandona
     * ESCI: 6 - /esci
     */
    public enum IDsComandi {

        /**
         * 0.
         *
         * Si riferisce a tutti i comandi non esistenti.
         */
        NONVALIDO(0),

        /**
         * 1.
         *
         * @see Comando#nuova(String, it.uniba.app.Gioco)
         */
        NUOVA(1),

        /**
         * 2.
         *
         * @see Comando#mostra(it.uniba.app.Gioco)
         */
        MOSTRA(2),

        /**
         * 3.
         *
         * @see Comando#gioca(it.uniba.app.Gioco, it.uniba.app.matrice.Matrice)
         */
        GIOCA(3),

        /**
         * 4.
         *
         * @see Comando#help()
         */
        HELP(4),

        /**
         * 5.
         *
         * @see Comando#abbandona(it.uniba.app.Gioco,
         * it.uniba.app.matrice.Matrice, java.lang.String)
         */
        ABBANDONA(5),

        /**
         * 6.
         *
         * @see Comando#esci(java.lang.String)
         */
        ESCI(6);

        /**
         * Elemento identificatore dei comandi.
         */
        private final int id;

        /**
         * Preparatore enumerazione.
         *
         * @param nid - L'id da assegnare agli elementi dell'enumeratore.
         */
        IDsComandi(final int nid) {
            this.id = nid;
        }

        /**
         * Rilascio dell'identificatore privato del comando.
         *
         * @return L'identificatore del comando.
         */
        public int getId() {
            return id;
        }
    }