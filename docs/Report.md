# Report
# 1. Introduzione
Questo programma è una simulazione del gioco **Wordle**, gioco basato sull'indovinare una parola segreta in tentativi limitati, posizionando le parole inserite in una matrice. Il programma si occuperà di:
- Riconoscere comandi:
    - Nuova (parola): comando per l'inserimento di una nuova parola segreta;
    - Mostra: comando che recupera la parola segreta impostata e la mostra all'utente;
    - Gioca: comando che permette all'utente di iniziare una nuova partita;
    - Help: comando che mostra un introduzione del programma e l'elenco dei comandi disponibili;
    - Abbandona: comando che permette all'utente di abbandonare una partita in corso;
    - Esci: comando che permette all'utente di uscire dal programma.
- Riconoscere i tentativi: guida l'utente con dei messaggi nell'inserimento di tentativi compatibili con la parola segreta:
    - Parola troppo lunga: se la parola inserita come tentativo non rispetta la lunghezza della parola segreta;
    - Parola troppo corta: analogo al messaggio precedente, ma se la parola è più corta della lunghezza della parola segreta;
    - Parola non valida: se la parola inserita come tentativo contiene caratteri speciali o numeri.

Inoltre, il programma aiuterà l'utente colorando le lettere della parola inserita nella matrice, in base alla posizione/presenza delle lettere confrontandole con la parola segreta. I colori saranno:
- Grigio: se la lettera della parola inserita, non è presenta nella parola segreta;
- Giallo: se la lettera della parola inserita, è presente nella parola segreta, ma nella posizione errata;
- Verde: se la lettera della parola inserita, è presenta nella parola segreta ed è nella posizione corretta.

![Esempio gioco](./img/wordle_end.png)

# 2. Modello di dominio
```mermaid
classDiagram 
class IDsComandi {
    <<enum>>
    NONVALIDO = 1
    NUOVA = 2
    MOSTRA = 3
    GIOCA = 4
    HELP = 5
    ABBANDONA = 6
    ESCI = 7
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

class IDsColori {
    <<enum>>
    CARATTERENERO = -1, "\u001b[38;2;0;0;0m"
    VUOTO = 0, "\u001b[0m"
    GRIGIO = 1, "\u001b[48;2;128;128;128m"
    GIALLO = 2, "\u001b[48;2;204;204;0m"
    VERDE = 3, "\u001b[48;2;0;204;0m"
    CARATTEREROSSO = 4, "\u001b[38;2;255;0;0m"
    CARATTEREVERDE = 5, "\u001b[38;2;0;204;0m"
    +getId() int
    +getUTFString() String
}

class Comando {
    <<Control>>
    +nuova(String, Gioco) int
    +mostra(Gioco) String
    +gioca(Gioco, Matrice) void 
    +help() void
    +abbandona(Gioco, Matrice, String) boolean
    +esci(String) boolean
}

class Parser {
    <<Control>>
    +parseInput(String, Gioco) int
    +parseComando(String) int
    +parseParola(String, int) int 
}

class Cella {
    <<Entity>>
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
    <<Entity>>
    +int RIGHE
    +int COLONNE
    -Cella[][] mat
    +Matrice()
    +getMat() Cella[][]
    +stampaMatrice() void
    +setCella(int, int, int, char) void
    +resetMatrice() void
}

class Gioco {
    <<Control>>
    -String parolaSegreta
    -int lunghezza
    -int tentativiMassimi
    -int tentativoAttuale
    -boolean esecuzione
    -Matrice matrice
    +Gioco()
    +Gioco(int, int)
    +getLunghezza() int
    +getTentativiMassimi() int
    +getTenativo() int
    +setTentativo(int) void
    +getParolaSegreta() String
    +setParolaSegreta(String) void
    +getEsecuzione() boolean
    +setEsecuzione(boolean) void
    +getMatrice() Matrice
    +setRigaMatrice(Cella[], int) boolean
    +controlloTentativi(int, Gioco, String) Cella[]
}

class App {
    <<Boundary>>
    +Parser parser
    +Gioco gioco
    +Comando comando
    +start(Parser, Comando, Gioco, String) void
}

IDsComandi --* Parser : Senza enum comandi non esiste parser
IDsParole --* Parser : Senza enum parole non esiste parser
IDsParole --* Comando
IDsColori --* Gioco
IDsColori --* App
IDsColori --* Cella : Senza enum colori non esiste Cella colorata
Cella ..> Matrice
Cella ..> Gioco
Parser -- App : Comunica con
Comando -- App : Chiama
Comando ..> Gioco
App -- Gioco : Comunica con
Matrice ..> Gioco
Matrice -- Comando


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

        Giocatore->>+App: /nuova input
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
        Gioco-->>+Comando: parolaSegreta
        deactivate Gioco
        alt parolaSegreta != ""
            Comando-->>+App: parolaSegreta
            App--)Giocatore: Stampa parolaSegreta
            deactivate App
        else parolaSegreta == ""
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

    loop input != IDsComandi.ESCI.id or input != IDsComandi.ABBANDONA.id
        Giocatore->>+App: input
        App->>+Parser: parseInput(input, gioco)
        deactivate App
        Parser -->>+App: IDsParole.ACCETTABILE.id
        deactivate Parser
        App->>+Gioco: getEsecuzione()
        deactivate App
        Gioco-->>+App: esecuzione
        deactivate Gioco
        alt esecuzione == true
            App->>+Gioco: controlloTentativi(tentativo, gioco, input)
            deactivate App
            Gioco-->>+App: Array di celle (riga)
            deactivate Gioco
            App->>+Gioco: setRigaMatrice(riga, tentativo)
            deactivate App
            Gioco-->>+App: corretto
            deactivate Gioco
            App->>+Matrice: stampaMatrice()
            Matrice-->>Giocatore: Stampa matrice
            deactivate Matrice
            alt risolto == true
                App->>Giocatore: Stampa successo
            else risolto == false
                App->>Gioco: setTentativo(tentativo+1)
            end
            opt tentativiMassimi < tentativo
                App->>Giocatore: Stampa sconfitta
                App->>Giocatore: Stampa parola segreta
            end
        else esecuzione == false
            App->>Giocatore: Stampa errore
            deactivate App
        end
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

# 6 Riepilogo del test
![CheckStyle Test](./img/CheckStyleTest.png)
![SpotBugs Test](./img/SpotBugsTest.png)
![Jacoco Test](./img/JacocoTest.png)
Per scrivere i casi di test sono stati adottati i criteri della suddivisione in classi di equivalenza.

# 7 Manuale utente
In questo gioco per poter vincere è necessario indovinare la parola segreta. Il programma aiutera' ad indovinare la parola con l'uso dei colori:
- Grigio --> la lettera non fa parte della parola;
- Giallo --> la lettera fa parte della parola ma è in una posizione errata;
- Verde --> la lettera fa parte della parola ed è nella posizione giusta.
## Di seguito la lista dei comandi:
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

```
Per visualizzare la scheda di help a inizio programma, è necessario avviarlo con la flag -h o --help.
```

## Per iniziare una partita:
- La parola segreta deve essere stata impostata correttamente;
    - `/nuova pizza`
- Deve essere chiamato il comando /gioca.

## Per abbandonare una partita:
- La partita deve essere in esecuzione;
- Deve essere chiamato il comando /abbandona:
    - Il comando chiederà una conferma.
- L'utente deve confermare o negare.

# 8 Processo di sviluppo e organizzazione del lavoro
L'organizzazione del lavoro è stata decisa tramite meeting organizzati da tutti i componenti del team.

In questi meeting abbiamo deciso:
- Quando effettuare i meeting di pianificazione;
- Ogni quanto effettuare un meeting di aggiornamento;

I meeting di pianificazione sono stati effettuati ogni inizio sprint, per la durata minima di 30 minuti.

I meeting di aggiornamento sono stati effettuati ogni settimana, a parte dei casi di estrema urgenza, per la durata media di 30/40 minuti.

Nei meeting di pianificazione:
- Abbiamo ragionato in gruppo su come approcciarci alle varie issue, per avere una visione generale del lavoro da svolgere;
- Ci siamo suddivisi le issue in base a due principali criteri: difficoltà e tempo stimato di sviluppo;

Nei meeting di aggiornamento:
- Abbiamo esposto uno alla volta le modifiche apportate la settimana precedente;
- Abbiamo esposto uno alla volta le modifiche ancora non apportate;
- Abbiamo esposto le nostre opinioni su relative modifiche da effettuare/effettuate;
- Abbiamo realizzato le bozze dei diagrammi UML.

# 9 Analisi retrospettiva
## 9.1 Sprint 1
![retrospettiva](./img/retrospettiva.png)