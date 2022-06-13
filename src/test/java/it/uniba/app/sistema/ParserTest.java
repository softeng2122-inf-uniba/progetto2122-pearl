package it.uniba.app.sistema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import it.uniba.app.Gioco;
import it.uniba.app.enumerativi.IDsComandi;
import it.uniba.app.enumerativi.IDsParole;

public class ParserTest {
    private Parser parser = new Parser();
    private final int lunghezzaInputConst = 5;

    @Test
    public void testParseInput() {
        assertEquals(
            IDsParole.NONVALIDO.getId(),
            parser.parseInput("", null),
            "Controllo input vuoto");

        assertNotEquals(
            IDsParole.NONVALIDO.getId(),
            parser.parseInput("/mostra", null));

        assertNotEquals(
            IDsParole.NONVALIDO.getId(),
            parser.parseInput("input", new Gioco()));
    }

    @Test
    public void testParseComando() {
        assertEquals(
            IDsComandi.MOSTRA.getId(),
            parser.parseComando("/mostra"),
            "Test /mostra");

        assertEquals(
            IDsComandi.NUOVA.getId(),
            parser.parseComando("/nuova"),
            "Test /nuova");

        assertEquals(
            IDsComandi.GIOCA.getId(),
            parser.parseComando("/gioca"),
            "Test /gioca");

        assertEquals(
            IDsComandi.ESCI.getId(),
            parser.parseComando("/esci"),
            "Test /esci");

        assertEquals(
            IDsComandi.ABBANDONA.getId(),
            parser.parseComando("/abbandona"),
            "Test /abbandona");

        assertEquals(
            IDsComandi.HELP.getId(),
            parser.parseComando("/help"),
            "Test /help");

        assertEquals(
            IDsComandi.HELP.getId(),
            parser.parseComando("-h"),
            "Test -h");

        assertEquals(
            IDsComandi.HELP.getId(),
            parser.parseComando("--help"),
            "Test --help");

        assertEquals(
            IDsComandi.NONVALIDO.getId(),
            parser.parseComando("input"),
            "Controllo input parola");

        assertEquals(
            IDsComandi.NONVALIDO.getId(),
            parser.parseComando("."),
            "Controllo input non valido");

        assertEquals(
            IDsComandi.NONVALIDO.getId(),
            parser.parseComando(""),
            "Controllo input vuoto");

        assertEquals(
            IDsComandi.NONVALIDO.getId(),
            parser.parseComando("/nonesiste"),
            "Controllo comando non esistente");

    }

    @Test
    public void testParseParola() {
        int lunghezza = lunghezzaInputConst;

        assertEquals(
            IDsParole.ACCETTABILE.getId(),
            parser.parseParola("testa", lunghezza),
            "Input accettabile");

        assertEquals(
            IDsParole.CORTA.getId(),
            parser.parseParola("mano", lunghezza),
            "Input corto");

        assertEquals(
            IDsParole.LUNGA.getId(),
            parser.parseParola("avvoltoio", lunghezza),
            "Input lungo");

        assertEquals(
            IDsParole.NONVALIDO.getId(),
            parser.parseParola(".-/&*", lunghezza),
            "Input con simboli");

        assertEquals(
            IDsParole.CORTA.getId(),
            parser.parseParola("", lunghezza),
            "Input vuoto");

        assertEquals(
            IDsParole.NONVALIDO.getId(),
            parser.parseParola("test7", lunghezza),
            "Input con numero");

        assertEquals(
            IDsParole.NONVALIDO.getId(),
            parser.parseParola("telo ", lunghezza),
            "Input con spazio");
    }
}
