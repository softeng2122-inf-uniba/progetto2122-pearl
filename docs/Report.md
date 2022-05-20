# Report
# 1. Introduzione
In questo gioco per poter vincere dovrai indovinare la parola segreta. Il gioco ti aiutera' ad indovinare la parola grazie all'uso dei colori, dove
- Grigio --> la lettera non fa parte della parola;
- Giallo --> la lettera fa parte della parola ma è in una posizione errata;
- Verde --> la lettera fa parte della parola ed è nella poszione giusta
---
Di seguito la lista dei comandi:
- /nuova < parola segreta > 
    - Per impostare una parola segreta
- /mostra 
    - Per mostrare la parola segreta
- /gioca 
    - Per iniziare una nuova partita
- /help o --help o -h 
    - Per vedere la lista dei comandi
- /abbandona 
    - Per abbandonare la partita in corso
- /esci 
    - Per uscire dall'applicazione.

# 2. Modello di dominio
```mermaid
classDiagram 
class IDsComandi {
    <<enum>>
    NONVALIDO = 0
    NUOVA = 1
    MOSTRA = 2
    GIOCA = 3
    HELP = 4
    ABBANDONA = 5
    ESCI = 6
    +getId() int
}

class IDsParole {
    <<enum>>
    NONVALIDO = 0
    LUNGA = -1
    CORTA = -2
    ACCETTABILE = -3
    +getId() int
}

class Comando {
    +nuova(String, Gioco) void
    +mostra(Gioco) void
    +gioca(Gioco) 
    +help() void
    +abbandona(Gioco) int
    +esci(String) boolean 
}

class Parser {
    +parseInput(String) int
    +parseComando(String) int
    +parseParola(String) int 
    +parseTentatvi(int, Gioco, String) Cella[]
}

class Cella {
    -String coloreUTF
    -int colore
    -char lettera
    +Cella()
    +getColore() int
    +setColore(int) void
    +getLettera() char
    +setLettera(char) void
}

class Matrice {
    +int RIGHE
    +int COLONNE
    -Cella[][] mat
    +Matrice()
    +stampaMatrice() void
    +setCella(int, int, int, char) void
    +setRiga(Cella[], int) boolean
    +resetMatrice() void
}

class Gioco {
    -String partolaSegreta
    -int lunghezza
    -int tentativiMassimi
    -int tentativoAttuale
    -boolean esecuzione
    +Gioco(int, int)
    +getLunghezza() int
    +getTentativiMassimi() int
    +setTentaiviMassimi(int) void
    +getTenativo() int
    +setTentativo(int) void
    +getParolaSegreta() String
    +setParolaSegreta(String) void
    +getEzecuzione() boolean
    +setEsecuzione(boolean) void
}

class App  {
    +Parser parser
    +Gioco gioco
    +Comando comando
    +start() void
}

IDsComandi --* Parser : Senza enum comandi non esiste parser
IDsParole --* Parser : Senza enum parole non esiste parser
Cella ..> Matrice
Parser -- App : Comunica con
Comando -- App : Chiama
Matrice --o Parser
App *-- Gioco : Senza gioco non esiste app
Gioco ..> Parser
Gioco ..> Comando


direction RL
```
# 3. Requisiti specifici
## 3.1 Requisiti funzionali
Attori: Giocatore e "Paroliere"

Le seguenti user story inizierebbero con **Come paroliere** voglio ...
- impostare una parola segreta manualmente
- mostrare la parola segreta

Le seguenti story inizierebbero con **Come giocatore** voglio ...
- mostrare l'help con elenco comandi e regole del gioco
- iniziare una nuova partita
- abbandonare una partita
- effettuare un tentativo per indovinare la parola segreta
- chiudere il gioco