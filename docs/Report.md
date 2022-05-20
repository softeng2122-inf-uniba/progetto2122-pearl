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

## 3.2 Requisiti non funzionali
1. il container docker dell’app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16.

Elenco di terminali supportati
Linux:
- terminal
Mac OS
- terminal
Windows
- Powershell
- Git Bash (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ....)
---
### Comando per l’esecuzione del container
Dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, il comando Docker da usare per eseguire il container contenente l’applicazione è:
```
docker run --rm -it ghcr.io/softeng2122-inf-uniba/wordle-pearl:latest
```

# 5 OO Design
## 5.1 Come Paroliere voglio impostare una parola segreta
Per semplicità assumiamo che il Giocatore e il Paroliere siano lo stesso attore.
```mermaid
    sequenceDiagram
    autonumber
        actor Giocatore
        participant App
        participant Parser
        participant Comando
        participant Gioco

        Giocatore->>+App: /nuova
        App-)+Giocatore: Richiesta input
        deactivate App
        Giocatore-)+App: input
        deactivate Giocatore
        App->>+Parser: parseInput(input, gioco)
        deactivate App
        Parser-->>+App: IDsComandi.NUOVA.id
        deactivate Parser
        App->>+Comando: nuova(input, gioco)
        deactivate App
        Comando-->>+App: stato
        deactivate App
        opt stato == IDsParole.ACCETTABILE.id 
            Comando->>+Gioco: setParolaSegreta(input)
        end
        deactivate Comando
```

## 5.2 Come Paroliere voglio mostrare la parola segreta 
Per semplicità assumiamo che il Giocatore e il Paroliere siano lo stesso attore.
```mermaid
    sequenceDiagram
        autonumber
        actor Giocatore
        participant App
        participant Parser
        participant Comando
        participant Gioco

        Giocatore->>+App: /mostra
        App->>+Parser: parseInput(/mostra, gioco)
        deactivate App
        Parser-->>+App: IDsComandi.MOSTRA.id
        deactivate Parser
        App->>+Comando: mostra(gioco)
        deactivate App
        Comando->>+Gioco: getParolaSegreta()
        deactivate Comando
        Gioco-->>+Comando: secret
        deactivate Gioco
        alt secret != ""
            Comando-->>+App: secret
            App--)Giocatore: Stampa secret
            deactivate App
        else secret == ""
            Comando--)Giocatore: Stampa errore
        end
        deactivate Comando
```

## 5.3 Come Giocatore voglio iniziare una nuova partita 
```mermaid
    sequenceDiagram
        autonumber
        actor Giocatore
        participant App
        participant Parser
        participant Comando
        participant Gioco

        Giocatore->>+App: /gioca
        App->>+Parser: parseInput(/gioca, gioco)
        deactivate App
        Parser-->>+App: IDsComandi.GIOCA.id
        deactivate Parser
        App->>+Comando: gioca(gioco, matrice)
        deactivate App
        Comando->>+Gioco: getEsecuzione()
        deactivate Comando
        Gioco-->>+Comando: esecuzione
        deactivate Gioco
        alt esecuzione == false
            Comando->>+Gioco: getParolaSegreta()
            deactivate Comando
            Gioco-->>+Comando: parolaSegreta
            deactivate Gioco
            alt parolaSegreta != ""
                Comando->>+Gioco: setEsecuzione(true)
                Comando-)Giocatore: stampaMatrice()
            else parolaSegreta == ""
                Comando-)Giocatore: Stampa errore parola
            end
        else esecuzione == true
            Comando-)Giocatore: Stampa errore esecuzione
        end
        deactivate Comando
```

## 5.4 Come Giocatore voglio mostrare l'help con l'elenco comandi 
```mermaid
    sequenceDiagram
        autonumber
        actor Giocatore
        participant App
        participant Parser
        participant Comando

        Giocatore->>+App: /help, -h, --help
        App->>+Parser: parseInput(/help, gioco)
        deactivate App
        Parser-->>+App: IDsComandi.HELP.id
        deactivate Parser
        App->>+Comando: help()
        deactivate App
        Comando->>+Giocatore: Stampa help
        deactivate Comando
```

## 5.5 Come Giocatore voglio effettuare un nuovo tentativo 
```mermaid
    sequenceDiagram
    autonumber
    actor Giocatore
    participant App
    participant Parser
    participant Gioco
    participant Matrice
    participant Cella

    loop input != IDsComandi.ESCI.id
        Giocatore->>+App: input
        App->>+Parser: parseInput(input, gioco)
        deactivate App
        Parser->>+Gioco: getEsecuzione()
        deactivate Parser
        Gioco-->>+App: esecuzione
        deactivate Gioco
        alt esecuzione == true
            App->>Matrice: stampaMatrice()
            App ->>Gioco: setEsecuzione(false)
            App->>Gioco: setTentativo(tentativo+1)
            App->>+Parser: parseTentativi(tentativo, gioco, input)
        else esecuzione == false
            App->>Giocatore: Stampa errore
            deactivate App
        end
        Parser->>+Gioco: getTentativiMassimi()
        deactivate Parser
        Gioco-->>+Parser: tentativiMassimi
        deactivate Gioco
        opt tentativiMassimi >= tentativi
            Parser->>+Gioco: getParolaSegreta()
            deactivate Parser
            Gioco-->>+Parser: parolaSegreta
            deactivate Gioco
            Parser->>Cella: setColore(int)
            Parser->>Cella: setLettera(char_input[i])
        end
        Parser-->>+App: array //riga
        deactivate Parser
        App->>+Matrice: setRiga(array, tentativo-1)
        deactivate App
        Matrice->>+Matrice: setCella(tentativo, i, colore, lettera)
        deactivate Matrice
        Matrice-->>+App: corretto
        deactivate Matrice
        opt corretto == true
            App->>Giocatore: Stampa successo
            App->>Gioco: setEsecuzione(false)
        end
        deactivate App
    end
```

## 5.6 Come Giocatore voglio abbandonare la partita
```mermaid
    sequenceDiagram
        autonumber
        actor Giocatore
        participant App
        participant Parser
        participant Comando
        participant Gioco
        participant Matrice

        Giocatore->>+App: /abbandona
        App->>+Parser: parseInput(/abbandona, gioco)
        deactivate App
        Parser-->>+App: IDsComandi.ABBANDONA.id
        deactivate Parser
        App->>+Gioco: getEsecuzione()
        deactivate App
        Gioco-->>+App: esecuzione
        alt esecuzione == true
            loop risultato = true
                App->>+Giocatore: Richiesta conferma
                deactivate App
                Giocatore->>+App: Input
                deactivate Giocatore
                App->>+Comando: abbandona(gioco, matrice, input)
                deactivate App
                alt input == 'S'
                    Comando->>Gioco: setEsecuzione(false)
                    Comando->>Gioco: setTentativo(1)
                    Comando->>Matrice: resetMatrice()
                    Comando-->>+App: risultato = false
                else input == 'N'
                    Comando-->>+App: risultato = false
                else
                    Comando-->>Giocatore: Stampa errore
                    Comando-->>+App: risultato = true
                end
                deactivate Comando
            end
        else esecuzione == false
            App-->>Giocatore: Stampa errore
        end
```

## 5.7 Come Giocatore voglio uscire dal gioco
```mermaid
    sequenceDiagram
        autonumber
        actor Giocatore
        participant App
        participant Parser
        participant Comando

        loop chiusura == false
            Giocatore->>+App: /esci
            App->>+Parser: parseInput(/esci, gioco)
            deactivate App
            Parser-->>+App: IDsComandi.ESCI.id
            deactivate Parser
            App-->>+Giocatore: Richiede conferma
            deactivate App
            Giocatore->>+App: input
            deactivate Giocatore
            App->>+Comando: esci(input)
            deactivate App
            Comando->>Giocatore: chiusura
            deactivate Comando
        end
```