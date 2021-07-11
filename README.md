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
        - [Spotbugs](#Spotbugs) <br>
        - [Checkstyle](#Checkstyle)
    
7. [Processo di sviluppo e organizzazione del lavoro](#Processo-di-sviluppo-e-organizzazione-del-lavoro)
    - [Product backlog](#Product-backlog) <br>
    - [Strumenti di lavoro](#Strumenti-di-lavoro)

8. [Analisi retrospettiva](#Analisi-retrospettiva)
    - [Soddisfazioni](#Soddisfazioni) <br>
    - [Insoddisfazioni](#Insoddisfazioni) <br>
    - [Cosa ci ha fatti impazzire](#Cosa-ci-ha-fatto-impazzire)

<br>

# Introduzione

La seguente relazione è stata sviluppata per il progetto che implementa il gioco <code>The Lost Hotel</code> del gruppo MTM-Develop, i cui membri sono:
- [Gaetano Malerba](https://github.com/GaeMale)
- [Enrico Mangia](https://github.com/EnMa4) 
- [Giovanni Todisco](https://github.com/GioTod) <br> 
  
<p>L'interfaccia dell'applicazione consente all'utente sia di digitare testualmente i comandi di gioco, sia di poter interagire tramite i bottoni presenti nel programma.</p>   
<p>Lo scopo di questo progetto è quello di creare un'avventura grafico/testuale che possa essere utilizzata da utenti che abbiano 
una minima conoscenza di questa tipologia di applicazione.<br> Inoltre, si tende a precisare che l'obiettivo di questo progetto è quello di dimostrare le competenze acquisite durante il corso di <i>Metodi Avanzati di Programmazione</i>.</p>  //aggiungere

## Trama del gioco
Il gioco si presenta con una descrizione della trama, che mostra all'utente una panoramica su ciò che è successo prima del punto di partenza:

    7 gennaio 2000, 21:32

                L'appuntamento importante è alle porte e decidi di riunirti 
                con gli amici di sempre (Ethan e John)
                per organizzare il tutto.
                Ancora qualche giorno e poi finalmente passerete 
                una settimana insieme in America.
                Lo aspettavate dalle scuole medie!

    12 gennaio 2000, 22:03
        
                Poichè il viaggio è lungo e stancante, decidete di partire
                in tarda serata per non perdere il volo.
                Si sa, l'uomo mira sempre al risparmio,
                e anche questa volta non vi siete lasciati sfuggire questo
                super volo low-cost presso un'agenzia britannica privata.
                Quindi, questo estenuante viaggio in macchina 
                sarà il prezzo da pagare...

    12 gennaio 2000, 23:56
        
                Sono passate circa due ore ma sembra 
                che il viaggio duri il doppio.
                Siete in una strada di periferia, quando all'improvviso
                Ethan impreca: "Spia rossa del motore.
                Cazzo, ci voleva solo questa!"
                "Speriamo che la macchina regga fino a destinazione",
                ribatte John.
                Passati esattamente 5 minuti, ecco che si intravede
                del fumo bianco dal motore.
                Presi dalla rabbia e disperazione,
                vi fermate e cerchi subito soccorso.
                Il telefono, come da previsione, non dà segni di vita.
                <<Beep - Beep - Beep>> "Non prende, ovviamente!",
                esclami.

    "13 gennaio 2000, 00:33

                È ormai mezzanotte inoltrata. È iniziato a piovere,
                il freddo e la stanchezza si fanno sentire.
                Sembra che il viaggio che aspettavate da una vita 
                stia diventando un incubo!
                Quando in lontananza Ethan vede una struttura 
                mezza illuminata ed esclama:
                "È un hotel, magari potremmo passare 
                la nottata qui e chiedere aiuto".
                Così, non avendo altre possibilità,
                vi rassegnate e decidete di entrare.
                Prima di abbandonare l'auto, riempi il tuo zaino 
                con un paio di oggetti che
                potrebbero esserti utili...


## Mappa del gioco  
//inserire immagine mappa di gioco
<p>Il gioco inizia con il protagonista che si sveglia all'interno di una stanza d, dirigendosi verso <b>Sud</b> si avrà modo di entrare nell'
<em>Ala d'ingresso</em> in cui possiamo osservare un <b>gomitolo</b>. Raccogliere ed usare il gomitolo permetterà di 
uscire automaticamente dal labirinto una volta sconfitto il Minotauro inserendo il comando <code>home</code>.</p>
<p>Dirigendosi verso <b>Est</b> si entra nell'<em>Ala del bivio</em>.</p>
<p>Andando verso <b>Sud</b> si entra nell'<em>Ala della Spada</em> dove è appunto possibile raccogliere la <b>spada</b> 
che servirà in seguito quando si incontrerà il Minotauro per sconfiggerlo. Da questa stanza è possibile soltanto tornare 
alla precedente a <b>Nord</b> da cui poi si può proseguire.</p>
<p>Proseguendo quindi verso <b>Est</b> si entra nell'<em>Ala del grande passo</em>.</p>
<p>Verso <b>Nord</b> si entra nella <em>Stanza della Battaglia</em> dove si dovrà affrontare il <b>Minotauro</b> se si è 
in possesso della spada e la si sta usando, altrimenti incontrarlo sarà letale e il gioco finirà.</p>
<p>A <b>Sud</b> dell'<em>Ala del grande passo</em> c'è l'<em>Ala finale</em>.

[Torna all'indice](#Indice)