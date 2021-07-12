[![Java CI with Maven](https://github.com/MTM-Develop/TheLostHotel/actions/workflows/maven.yml/badge.svg)](https://github.com/MTM-Develop/TheLostHotel/actions/workflows/maven.yml)

# THE LOST HOTEL

## Indice

1. [Introduzione](#Introduzione)
    - [Trama del gioco](#Trama-del-gioco) <br>
    - [Mappa di gioco](#Mappa-di-gioco) <br>
    
2. [Modello di dominio (da valutare)](#Modello-di-dominio)

3. [Requisiti specifici](#Requisiti-specifici)
    - [Requisiti funzionali](#Requisiti-funzionali) <br>
    - [Requisiti non funzionali](#Requisiti-non-funzionali)

4. [O.O. Design](#OO-Design)
    - [Diagrammi delle classi](#Diagrammi-delle-classi)

5. [Contenuti rilevanti](#Contenuti-rilevanti)
    - [Lettura/scrittura su File](#Lettura-scrittura-su-File) <br>
    - [Connessione a database](#Connessione-a-database) <br>
    - [GUI mediante SWING](#GUI-mediante-SWING) <br>
    - [Thread](#Thread) <br>
    - [Lambda expression](#Lambda-expression) <br>
    - [Javadoc](#Javadoc) 

6. [Riepilogo dei test](#Riepilogo-dei-test)
    - [Analisi statica del codice](#Analisi-statica-del-codice)
        - [Checkstyle](#Checkstyle) <br>
        - [Spotbugs](#Spotbugs)
    
7. [Processo di sviluppo e organizzazione del lavoro](#Processo-di-sviluppo-e-organizzazione-del-lavoro)
    - [Product backlog](#Product-backlog) <br>
    - [Strumenti di lavoro](#Strumenti-di-lavoro)

8. [Analisi retrospettiva](#Analisi-retrospettiva)
    - [Soddisfazioni](#Soddisfazioni) <br>
    - [Insoddisfazioni](#Insoddisfazioni) <br>
    - [Cosa ci ha fatti impazzire](#Cosa-ci-ha-fatto-impazzire)

<br>

# Introduzione

La seguente relazione è stata sviluppata per il progetto che implementa il gioco <b>[The Lost Hotel](https://github.com/MTM-Develop/TheLostHotel)</b> del gruppo <b><i>MTM-Develop</b></i>, i cui membri sono:
- [Gaetano Malerba](https://github.com/GaeMale)
- [Enrico Mangia](https://github.com/EnMa4) 
- [Giovanni Todisco](https://github.com/GioTod) <br> 
  
<p>L'interfaccia dell'applicazione consente all'utente sia di digitare testualmente i comandi di gioco, sia di poter interagire tramite i bottoni presenti nel programma.</p>   
<p>Lo scopo di questo progetto è quello di creare un'avventura grafico/testuale che possa essere utilizzata da utenti che abbiano 
una minima conoscenza di questa tipologia di applicazione.<br> Inoltre, si tende a precisare che l'obiettivo di questo progetto è quello di dimostrare le competenze acquisite durante il corso di <i>Metodi Avanzati di Programmazione</i>.</p>  //aggiungere

---

## Trama del gioco

L'avventura ha inizio nel gennaio del 2000: il protagonista, insieme ai suoi due amici inseparabili (<b>John</b> e <b>Ethan</b>), decidono (o decide) di passare una settimana assieme in America.<br>
Vista la stanchezza del viaggio che li aspetta, i tre optano per partire in tarda serata per arrivare alle prime ore dell'alba in un aeroporto distante centinaia di chilometri (parlare del volo low-cost).<br>
Dopo circa due ore dalla partenza, arrivati in una strada di periferia, iniziano le prime difficoltà: dal motore esce del fumo bianco, il telefono (come da previsione) assente, e la pioggia inizia ad essere incessante.<br>
Ethan, notando in lontananza un hotel mezzo illuminato, (suggerisce) di passare la nottata lì per poi chiedere aiuto.<br>
Ma non sa cosa li aspetta lì dentro...
<br>

---

## Mappa di gioco  
//inserire immagine mappa di gioco
<p>Il gioco inizia con il protagonista che si sveglia all'interno della <b><i>Stanza 79</b></i>.<br>
 Dirigendosi verso <b>Nord</b> si avrà modo di entrare nel primo <b><i>Corridoio</i></b> dell'hotel. Per accedere ad esso, l'utente dovrà essere in grado di trovare la chiave per sbloccare l'accesso a questo. <br>
Nel <b><i>Corridoio</i></b> vi saranno altre stanze in cui sarà possibile scovare segreti e oggetti al fine di completare il gioco.
<p>Ad <b>Ovest</b> vi è il <b><i>Corridoio Columbus</i></b>, il cui accesso è libero. <br>
Qui l'utente potrà accedere a varie stanze per prendere gli oggetti necessari per il proseguio dell'avventura.
Andando a <b>Sud</b> entrerà nella <b><i>Stanza 53</b></i> dove sarà possibile raccogliere il gancio per aprire la ventola della <b><i>Lavanderia</b></i>.<br>
Dalla <b><i>Stanza 53</b></i> attraverso il comando <code>apri finestra</code> sarà possibile avere accesso al <b><i>Giardino</i></b> e successivamente alla <b><i>Stanza 63</b></i> dove si potrà raccogliere il fucile che sarà indispensabile per la vittoria del gioco. </p>
<p>Spostandosi verso <b>Nord</b> del <b><i>Corridoio Columbus</b></i> sarà possibile accedere al <b><i>Corridoio Clippings</i></b>, l'ultimo prima delle stanze finali.<br>
Qui sono presenti la <b><i>Cucina</i></b>, la <b><i>CCTV</i></b> e la <b><i>Lavanderia</i></b>. <br>
Dalla <b><i>Lavanderia</i></b> sarà possibile aver accesso alla <b><i>Hall</i></b> aprendo la ventola con il comando <code>apri ventola con gancio</code>. 
Successivamente, tramite il comando <code>usa ventola</code>, si potrà entrare nella <b><i>Hall</b></i> solamente nel caso in cui l'utente abbia nell'inventario 2 oggetti fondamentali (<u><i>Mappa della cassaforte della hall</u></i> e <u><i>Foto rappresentate una delle due combinazioni per aprire questa</i></u>) per il continuo del gioco.</p> 
Qui è presente una cassaforte. <br> 
Se l'utente è stato in grado di raccogliere tutti gli elementi fondamentali per arrivare sin qui, sarà in grado di sbloccare la cassaforte usando il comando <code>inserisci [combinazione] in cassaforte</code>, la quale conterrà la chiave per sbloccare la <b><i>Stanza 13</b></i>, dove si concluderà l'avventura.

<p>Una volta aperta la cassaforte potrà raccogliere la chiave e dirigersi ad <b>Ovest</b> per completare il gioco. <br>
Gli verrà assegnato un punteggio in base al tempo impiegato per completare il tutto e, soprattuto, potrebbe essere soggetto ad una penalità nel caso in cui non abbia raccolto l'elemento principale per la vittoria, il <b>fucile</b>.</p>

[Torna all'indice](#Indice) <br><br>

# Requisiti specifici
Di seguito sono riportati tutti i requisiti per l'applicazione <b>The Lost Hotel</b>, suddivisi in requisiti funzionali e non funzionali.

---

## Requisiti funzionali
I FUR <i>(Functional User Requirement)</i> descrivono le funzionalità del software in termini di:
- elenchi di funzionalità o servizi che il sistema deve fornire;
- risposte che l’utente si aspetta dal software in determinate situazioni;
- risultati che il software deve produrre di fronte a specifici input.<br>

Riportiamo i requisiti funzionali dell'applicazione:

| Requisito | Descrizione |
|--|--|
| Avviare una nuova partita | Nel menu principale, cliccando su <code>Avvia una nuova partita</code> (oppure, premendo <code>CTRL-N</code>) l'applicazione permette all'utente di inserire il suo nome di avventura e iniziare una nuova partita. |
| Caricare una partita esistente | Nel menu principale, cliccando su <code>Carica una partita esistente</code> (oppure, premendo <code>CTRL-C</code>) l'applicazione carica la partita salvata e permette all'utente di continuare la sua avventura dal punto in cui si era fermato. |
| Uscire dall'applicazione | Nel menu principale, cliccando su <code>Esci</code> l'applicazione permette all'utente di chiudere correttamente il programma. |
| Mostrare l'elenco dei comandi | Al comando <code>help</code> o <code>aiuto</code> o <code>guida</code> (oppure, premendo <code>CTRL-L</code>) l'applicazione mostra la lista dei comandi riconosciuti, uno per riga. |
| Muovere a Nord | Al comando <code>nord</code> (oppure, premendo <code>ALT-N</code>) l'applicazione permette al giocatore di muoversi verso Nord.<br> Se la stanza è chiusa a chiave viene mostrato il messaggio <code>Questa stanza è chiusa!</code>. Se, invece, non è presente nessun collegamento tra la stanza corrente e la stanza a Nord, viene mostrato il messaggio <code>Non puoi andare lì!</code> e l'applicazione rimane in attesa di un comando valido. |
| Muovere a Sud | Al comando <code>sud</code> (oppure, premendo <code>ALT-S</code>) l'applicazione permette al giocatore di muoversi verso Sud.<br> Se la stanza è chiusa a chiave viene mostrato il messaggio <code>Questa stanza è chiusa!</code>. Se, invece, non è presente nessun collegamento tra la stanza corrente e la stanza a Sud, viene mostrato il messaggio <code>Non puoi andare lì!</code> e l'applicazione rimane in attesa di un comando valido. |
| Muovere ad Est | Al comando <code>est</code> (oppure, premendo <code>ALT-E</code>) l'applicazione permette al giocatore di muoversi verso Est.<br> Se la stanza è chiusa a chiave viene mostrato il messaggio <code>Questa stanza è chiusa!</code>. Se, invece, non è presente nessun collegamento tra la stanza corrente e la stanza ad Est, viene mostrato il messaggio <code>Non puoi andare lì!</code> e l'applicazione rimane in attesa di un comando valido. |
| Muovere ad Ovest | Al comando <code>ovest</code> (oppure, premendo <code>ALT-O</code>) l'applicazione permette al giocatore di muoversi verso Ovest.<br> Se la stanza è chiusa a chiave viene mostrato il messaggio <code>Questa stanza è chiusa!</code>. Se, invece, non è presente nessun collegamento tra la stanza corrente e la stanza ad Ovest, viene mostrato il messaggio <code>Non puoi andare lì!</code> e l'applicazione rimane in attesa di un comando valido. |
| Mostrare l'inventario | Al comando <code>inventario</code> o <code>inv</code> o <code>zaino</code> o <code>sacca</code> (oppure, premendo l'icona dello zaino situata in alto a destra) l'applicazione mostra, sottoforma di finestra grafica, la lista degli oggetti raccolti dal giocatore. |
| Osservare la stanza corrente | Al comando <code>guarda</code> o <code>osserva</code> o <code>vedi</code> o <code>descrivi</code> o <code>controlla</code> o <code>esamina</code> l'applicazione mostra all'utente una descrizione più dettagliata della stanza dove si trova momentaneamente. |
| Osservare un oggetto | Al comando <code>guarda [oggetto]</code> o <code>osserva [oggetto]</code> o <code>vedi [oggetto]</code> o <code>descrivi [oggetto]</code> o <code>controlla [oggetto]</code> o <code>esamina [oggetto]</code> l'applicazione mostra all'utente la descrizione dell'oggetto richiesto, se presente nell'inventario o nella stanza corrente (non per forza visibile nell'immagine di questa). |
| Usare un oggetto | Al comando <code>usa [oggetto]</code> o <code>utilizza [oggetto]</code> il giocatore può utilizzare l'oggetto specificato, se presente nell'inventario o nella stanza corrente. |
| Aprire un oggetto | Al comando <code>apri [oggetto contenitore]</code> l'applicazione permette al giocatore di aprire l'oggetto contenitore specificato, se presente nella stanza corrente.<br>N.B: Tale comando sarà accettato nel caso in cui il contenitore non sia bloccato da un oggetto, altrimenti sarà visualizzato il messaggio <code>Non puoi aprire quest'oggetto così!</code>.<br> Per aprire contenitori bloccati sarà necessario inserire il comando <code>apri [oggetto contenitore] con [oggetto]</code>, dove <code>[oggetto]</code> sarà naturalmente ciò che blocca il contenitore. |
| Spostare un oggetto | Al comando <code>sposta [oggetto]</code> o <code>muovi [oggetto]</code> o <code>trascina [oggetto]</code> il giocatore può spostare l'oggetto specificato, a patto che sia della stanza corrente. |
| Prendere un oggetto | Al comando <code>prendi [oggetto]</code> o <code>raccogli [oggetto]</code> o <code>afferra [oggetto]</code> o <code>agguanta [oggetto]</code> o <code>piglia [oggetto]</code> l'applicazione permette all'utente di raccogliere l'oggetto specificato, se presente nella stanza corrente (a terra o in un contenitore sbloccato).<br>N.B: Per prendere un oggetto, il giocatore non deve aver riempito completamente l'inventario (massimo 10 oggetti), altrimenti il comando non sarà valido e dovrà lasciare un oggetto tra quelli in suo possesso. |

[Torna all'indice](#Indice) <br><br>

# Riepilogo dei test
Questa sezione evidenzia i risultati e le modalità con cui è stato testato il software. <br>

---

## Analisi statica del codice
Si è deciso di utilizzare l’Analisi statica del codice poichè permette di analizzare il codice sorgente e di mettere in evidenza le problematiche riscontrate.<br>

Tra i principali strumenti per svolgere il testing del codice è stato scelto di utilizzare [Checkstyle](https://checkstyle.org) e [Spotbugs](https://spotbugs.github.io).<br>

- ## Checkstyle
    - <b>Checkstyle</b> è uno strumento di analisi del codice statico, utilizzato per scoprire eventuali violazioni dello stile di programmazione e verificare se il codice sorgente è conforme alle regole di codifica specificate.<br>
    Di seguito ne riportiamo brevemente il risultato:<br><br>
    >![checkstyle](./imgRelazione/checkstyle.PNG)

- ## Spotbugs
    - Relativamente invece all'altro analizzatore statico del codice, <b>Spotbugs</b> si occupa di inviduare potenziali difetti che possono comportare malfunzionamenti nel codice.<br>
    Ne riportiamo brevemente l'output:<br><br>
    >![spotbugs](./imgRelazione/spotbugs.PNG)

[Torna all'indice](#Indice) <br><br>