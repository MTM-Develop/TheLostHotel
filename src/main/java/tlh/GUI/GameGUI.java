/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlh.GUI;

import tlh.Other.GameInteraction;
import tlh.Other.MenuManager;
import tlh.Parser.Parser;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.UIDefaults;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 *
 * @author gaetano
 */
public class GameGUI extends javax.swing.JFrame {

    /**
     * Gestore di interazione col gioco e parser.
     */
    private GameInteraction gInteraction;

    /**
     * Parser del gioco.
     */
    private Parser parser = new Parser();

    /**
     * Gestore del menu.
     */
    private MenuManager menu;

    /**
     * Timer utilizzato per l'effetto del testo "typewriter".
     */
    private Timer tm;

    /**
     * Tipo di stringa per concatenare i vari messaggi
     * con effetto "typewriter".
     */
    private StringBuilder s = new StringBuilder();

    /**
     * Indica il contatore per il testo.
     */
    private int counter = 0;

    /**
     * Font di gioco.
     */
    private Font font;

    /**
     * Font di gioco.
     */
    private Font font1;

    /**
     * Font per i jButton.
     */
    private Font fontGameButton;

    /**
     * Font per jLabel.
     */
    private Font fontGameLabel;

    /**
     * Font per i jMenu.
     */
    private Font fontGameBar;

    /**
     * Font per il testo della JTextPane.
     */
    private Font fontGameText;

    /**
     * Font per jLabel (della stanza corrente).
     */
    private Font fontGameRoomName;

    /**
     * Indica se il gioco è stato salvato.
     */
    private boolean savedGame = true;

    /**
     * Rappresenta il testo veloce a schermo.
     */
    private boolean fastText = false;

    /**
     * Indica lo storico dei comandi del giocatore.
     */
    private ArrayList<String> commandHistory = new ArrayList<String>();

    /**
     * Indica il numero di volte in cui l'utente preme la freccia in giù
     * (utile per lo storico dei comandi).
     */
    private int count_key_down = 0;

    /**
     * Creates new form GameGUI.
     */
    public GameGUI(GameInteraction gInteraction) {
        initComponents();
        this.gInteraction = gInteraction;
        menu = new MenuManager(gInteraction.getGameManager());
        jtCommand.requestFocus();
        initGame();
        initFont();

        ActionListener taskPerformer = (ActionEvent evt) -> {
            enableComponents(false);
            counter++;
            if (counter >= s.length()) {
                counter = 0;
                tm.stop();
                enableComponents(true);
            } else {
                StringBuilder supp = new StringBuilder();
                supp.append(s.toString().charAt(counter));
                appendToPane(jtpReadingArea, supp.toString(), Color.white);
                supp = new StringBuilder();
            }
        };
        int delay = 10;
        tm = new Timer(delay, taskPerformer);
        tm.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbWest = new javax.swing.JButton();
        jbNorth = new javax.swing.JButton();
        jbSouth = new javax.swing.JButton();
        jbEast = new javax.swing.JButton();
        jlCommand = new javax.swing.JLabel();
        jlRoomName = new javax.swing.JLabel();
        jlCurrentRoom = new javax.swing.JLabel();
        jbQuitGame = new javax.swing.JButton();
        jbSaveGame = new javax.swing.JButton();
        jbSendCommand = new javax.swing.JButton();
        jbInventory = new javax.swing.JButton();
        jspCommand = new javax.swing.JScrollPane();
        jtCommand = new javax.swing.JTextField();
        jlRoomImage = new javax.swing.JLabel();
        jspReadingArea = new javax.swing.JScrollPane();
        jtpReadingArea = new javax.swing.JTextPane();
        jlBackground = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmOptions = new javax.swing.JMenu();
        jmiSave = new JMenuItem();
        jmiQuit = new javax.swing.JMenuItem();
        jmText = new javax.swing.JMenu();
        jmiFastText = new JCheckBoxMenuItem();
        jmCommands = new javax.swing.JMenu();
        jmiCommands = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1220, 700));
        setTitle("The Lost Hotel");
        setBackground(new java.awt.Color(0, 0, 0));
        setIconImage(new ImageIcon("resources//img//other//logo.jpeg").getImage());
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(1220, 700));
        setMinimumSize(new java.awt.Dimension(1220, 700));
        setPreferredSize(new java.awt.Dimension(1220, 700));
        setResizable(false);

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jbNorth, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jbWest, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jbSouth, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbEast, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jbNorth, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jbSouth, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jbEast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbWest, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(680, 510, 250, 160);

        jbNorth.setText("Nord");
        jbNorth.setToolTipText("Vai a Nord");
        jbNorth.setMnemonic(KeyEvent.VK_N);
        jbNorth.addActionListener(e -> sendCommandByButton("nord"));

        jbEast.setText("Est");
        jbEast.setToolTipText("Vai a Est");
        jbEast.setMnemonic(KeyEvent.VK_E);
        jbEast.addActionListener(e -> sendCommandByButton("est"));

        jbSouth.setText("Sud");
        jbSouth.setToolTipText("Vai a Sud");
        jbSouth.setMnemonic(KeyEvent.VK_S);
        jbSouth.addActionListener(e -> sendCommandByButton("sud"));

        jbWest.setText("Ovest");
        jbWest.setToolTipText("Vai a Ovest");
        jbWest.setMnemonic(KeyEvent.VK_O);
        jbWest.addActionListener(e -> sendCommandByButton("ovest"));

        jlCommand.setBackground(new java.awt.Color(43, 52, 43));
        jlCommand.setOpaque(true);
        jlCommand.setForeground(new java.awt.Color(255, 255, 255));
        jlCommand.setText("  INSERISCI UN COMANDO:   ");
        jlCommand.setCursor(new java.awt.Cursor(Cursor.TEXT_CURSOR));
        getContentPane().add(jlCommand);
        jlCommand.setBounds(40, 530, 200, 40);

        jlRoomName.setText("  NOME STANZA  ");
        jlRoomName.setBackground(new java.awt.Color(43, 52, 43));
        jlRoomName.setOpaque(true);
        jlRoomName.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jlRoomName);
        jlRoomName.setBounds(750, 60, 190, 40);

        jlCurrentRoom.setBackground(new java.awt.Color(43, 52, 43));
        jlCurrentRoom.setOpaque(true);
        jlCurrentRoom.setForeground(new java.awt.Color(255, 255, 255));
        jlCurrentRoom.setText("  STANZA CORRENTE: ");
        getContentPane().add(jlCurrentRoom);
        jlCurrentRoom.setBounds(560, 60, 160, 40);

        jbSaveGame.setText("SALVA (CTRL-S)");
        jbSaveGame.addActionListener(e -> saveGame());
        getContentPane().add(jbSaveGame);
        jbSaveGame.setBounds(1010, 520, 170, 40);

        jbQuitGame.setText("ESCI (CTRL-Q)");
        jbQuitGame.addActionListener(e -> quitGame());
        getContentPane().add(jbQuitGame);
        jbQuitGame.setBounds(1010, 580, 170, 40);

        jbSendCommand.setText("INVIA");
        jbSendCommand.setMnemonic(KeyEvent.VK_I); //Provare con INVIO
        jbSendCommand.addActionListener(evt -> sendCommandByTextField());
        getContentPane().add(jbSendCommand);
        jbSendCommand.setBounds(430, 580, 100, 40);

        jbInventory.setText("");
        jbInventory.setOpaque(true);
        jbInventory.setIcon(new ImageIcon("resources//img//other//inventory.png"));
        jbInventory.setToolTipText("Inventario");
        jbInventory.setBackground(new Color(0, 0, 0));
        jbInventory.addActionListener(e -> {
            new InventoryGUI(gInteraction.getGameManager().getGame().getInventory()).setVisible(true);
        });
        getContentPane().add(jbInventory);
        jbInventory.setBounds(1000, 45, 70, 70);

        jtCommand.setToolTipText("Inserisci un comando");
        jtCommand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldCommandKeyEvent(evt);
            }
        });
        jspCommand.setViewportView(jtCommand);
        getContentPane().add(jspCommand);
        jspCommand.setBounds(40, 580, 380, 40);

        jlRoomImage.setBackground(new java.awt.Color(0, 0, 0));
        jlRoomImage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jlRoomImage.setOpaque(true);
        getContentPane().add(jlRoomImage);
        jlRoomImage.setBounds(560, 140, 620, 350);

        jtpReadingArea.setEditable(false);
        jtpReadingArea.setBackground(new java.awt.Color(0, 0, 0));
        jtpReadingArea.setForeground(new java.awt.Color(255, 255, 255));
        jtpReadingArea.setMaximumSize(new java.awt.Dimension(900, 450));
        jtpReadingArea.setMinimumSize(new java.awt.Dimension(900, 450));
        jtpReadingArea.setPreferredSize(new java.awt.Dimension(280, 360));
        jspReadingArea.setViewportView(jtpReadingArea);

        Color bgColor = new Color(0, 0, 0);
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        jtpReadingArea.putClientProperty("Nimbus.Overrides", defaults);
        jtpReadingArea.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        jtpReadingArea.setBackground(bgColor);

        getContentPane().add(jspReadingArea);
        jspReadingArea.setBounds(40, 30, 500, 460);
        getContentPane().add(jlBackground);
        jlBackground.setIcon(new ImageIcon("resources//img//background//gameBackground.jpg"));
        jlBackground.setBounds(0, -20, 1220, 700);

        jmOptions.setText("Opzioni");

        jmiSave.setText("Salva");
        KeyStroke keyStrokeSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        jmiSave.setAccelerator(keyStrokeSave);
        jmiSave.addActionListener(e -> saveGame());

        jmiQuit.setText("Torna al menu principale");
        KeyStroke keyStrokeQuit = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
        jmiQuit.setAccelerator(keyStrokeQuit);
        jmiQuit.addActionListener(e -> quitGame());

        jmOptions.add(jmiSave);
        jmOptions.addSeparator();
        jmOptions.add(jmiQuit);
        jMenuBar1.add(jmOptions);

        jmText.setText("Text");
        jmiFastText.setText("Fast text");
        KeyStroke keyStrokeFastText = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
        jmiFastText.setAccelerator(keyStrokeFastText);
        jmiFastText.addActionListener(e -> jmiFastTextActionPerformed());
        jmText.add(jmiFastText);
        jMenuBar1.add(jmText);

        jmCommands.setText("Comandi");
        jmiCommands.setText("Lista comandi");
        KeyStroke keyStrokeCommands = KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK);
        jmiCommands.setAccelerator(keyStrokeCommands);
        jmiCommands.addActionListener(e -> jmiCommandsActionPerformed());
        jmCommands.add(jmiCommands);
        jMenuBar1.add(jmCommands);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initGame()
    {
        //Utilizzato per bloccare lo scorrimento al fine di visualizzare correttamente la trama del gioco
        DefaultCaret caret = (DefaultCaret)jtpReadingArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        appendToPane(jtpReadingArea, "7 gennaio 2000, " , new Color(127, 255, 0));
        appendToPane(jtpReadingArea, "21:32\n" , Color.red);
        appendToPane(jtpReadingArea,"\n" +
                "L'appuntamento importante è alle porte e decidi di riunirti con gli amici di sempre (Ethan e John)\n" +
                "per organizzare il tutto.\n" +
                "Ancora qualche giorno e poi finalmente passerete una settimana insieme in America.\n" +
                "Lo aspettavate dalle scuole medie!\n\n", Color.white);

        appendToPane(jtpReadingArea, "12 gennaio 2000,  ", new Color(127, 255, 0));
        appendToPane(jtpReadingArea, "22:03\n" , Color.red);
        appendToPane(jtpReadingArea,"\n" +
                "Poichè il viaggio è lungo e stancante, decidete di partire in tarda serata per non perdere il volo.\n" +
                "Si sa, l'uomo mira sempre al risparmio, e anche questa volta non vi siete lasciati sfuggire questo\n" +
                "super volo low-cost presso un'agenzia britannica privata.\n" +
                "Quindi, questo estenuante viaggio in macchina sarà il prezzo da pagare...\n\n", Color.white);

        appendToPane(jtpReadingArea, "12 gennaio 2000,  ", new Color(127, 255, 0));
        appendToPane(jtpReadingArea, "23:56\n" , Color.red);
        appendToPane(jtpReadingArea,"\n" +
                "Sono passate circa due ore ma sembra che il viaggio duri il doppio.\n" +
                "Siete in una strada di periferia, quando all'improvviso\n" +
                "Ethan impreca: \"Spia rossa del motore. Cazzo, ci voleva solo questa!\"\n" +
                "\"Speriamo che la macchina regga fino a destinazione...\", ribatte John.\n" +
                "Passati esattamente 5 minuti, ecco che si intravede del fumo bianco dal motore.\n" +
                "Presi dalla rabbia e disperazione, vi fermate e cerchi subito soccorso.\n" +
                "Il telefono, come da previsione, non dà segni di vita.\n" +
                "<<Beep - Beep - Beep>> \"Non prende, ovviamente!\", esclami.\n\n", Color.white);

        appendToPane(jtpReadingArea, "13 gennaio 2000,  ", new Color(127, 255, 0));
        appendToPane(jtpReadingArea, "00:33\n" , Color.red);
        appendToPane(jtpReadingArea, "\n" +
                "È ormai mezzanotte inoltrata. È iniziato a piovere, il freddo e la stanchezza si fanno sentire.\n" +
                "Sembra che il viaggio che aspettavate da una vita stia diventando un incubo!\n" +
                "Quando in lontananza Ethan vede una struttura mezza illuminata ed esclama: " +
                "\"È un hotel, magari potremmo passare la nottata qui e chiedere aiuto\".\n" +
                "Così, non avendo altre possibilità, vi rassegnate e decidete di entrare.\n" +
                "Prima di abbandonare l'auto, riempi il tuo zaino con un paio di oggetti che potrebbero esserti utili...\n\n", Color.white);

        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        changeJtpFont();

        appendToPane(jtpReadingArea, "\n" +
                "Puoi visualizzare in qualsiasi momento l'elenco \n" +
                "dei comandi nella sezione \"Comandi\", oppure digitando \n" +
                "\"help\", \"aiuto\", \"guida\".\n\n", Color.cyan);

        appendToPane(jtpReadingArea, "\n" + "-- " + gInteraction.getGameManager().getGame().getCurrentRoom().getName() + " --"
                + "\n\n" + gInteraction.getGameManager().getGame().getCurrentRoom().getDescription() + "\n\n", Color.white);

        appendToPane(jtpReadingArea, "\nSUGGERIMENTO: " +
                "\nDigita \"osserva\" per guardarti intorno ed esaminare la stanza.\n\n", Color.white); //Provare a rimuoverlo se si carica una partita

        //new Color(104, 140, 189))

        gInteraction.getGameManager().getGame().getCurrentRoom().setVisited(true);

        jlRoomImage.setIcon(gInteraction.getGameManager().getGame().getCurrentRoom().getRoomImage());
        jlRoomImage.setToolTipText(gInteraction.getGameManager().getGame().getCurrentRoom().getName());
        jlRoomName.setText("  " + gInteraction.getGameManager().getGame().getCurrentRoom().getName());
    }

    private void initFont() {

        // La risorsa del try with resource si chiuderà da sola poiché implementa l'interfaccia AutoCloseable
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("resources//font//regular.otf"));
            InputStream is1 = new BufferedInputStream(new FileInputStream("resources//font//melted.ttf"));

            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font1 = Font.createFont(Font.TRUETYPE_FONT, is1);

            fontGameText = font.deriveFont(Font.PLAIN, 18);
            fontGameLabel = font1.deriveFont(Font.PLAIN, 17);
            fontGameButton = font1.deriveFont(Font.PLAIN, 15);
            fontGameBar = font1.deriveFont(Font.PLAIN, 13);
            fontGameRoomName = font1.deriveFont(Font.PLAIN, 14);

            jlCommand.setFont(fontGameLabel);
            jlRoomName.setFont(fontGameRoomName);
            jlCurrentRoom.setFont(fontGameLabel);

            jbSendCommand.setFont(fontGameButton);
            jbNorth.setFont(fontGameButton);
            jbEast.setFont(fontGameButton);
            jbSouth.setFont(fontGameButton);
            jbWest.setFont(fontGameButton);
            jbSaveGame.setFont(fontGameButton);
            jbQuitGame.setFont(fontGameButton);
            jtCommand.setFont(fontGameButton);

            jtpReadingArea.setFont(fontGameText);

            jmOptions.setFont(fontGameBar);
            jmText.setFont(fontGameBar);
            jmCommands.setFont(fontGameBar);
            jmiSave.setFont(fontGameBar);
            jmiQuit.setFont(fontGameBar);
            jmiFastText.setFont(fontGameBar);
            jmiCommands.setFont(fontGameBar);

        } catch (FontFormatException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Font non caricato correttamente; e'stato impostato un font di default.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeJtpFont()
    {
        Font timesNewRoman = new Font("Times New Roman", Font.PLAIN, 18);

        //Utilizzato per cambiare dinamicamente il font nella jtp
        StyledDocument doc = jtpReadingArea.getStyledDocument();
        SimpleAttributeSet aSet = new SimpleAttributeSet();

        StyleConstants.setFontFamily(aSet, timesNewRoman.getFamily());
        StyleConstants.setFontSize(aSet, timesNewRoman.getSize());
        doc.setParagraphAttributes(1900, 0, aSet, false);
    }

    private void saveGame()
    {
        // Inizializza il jFileChooser per scegliere la cartella dove salvare
        JFileChooser fChooser = new JFileChooser();
        fChooser.setMultiSelectionEnabled(false);
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //FILES_ONLY
        fChooser.setCurrentDirectory(new File(".")); // Parte dalla cartella del progetto

        try {

            if (fChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                gInteraction.getGameManager().getGame().saveGame(fChooser.getSelectedFile().getPath());
                savedGame = true;

                appendToPane(jtpReadingArea,"\nSalvataggio partita completato.\n\n", Color.cyan);
                jtpReadingArea.setCaretPosition(jtpReadingArea.getDocument().getLength());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Errore: " + e.getMessage(), "Errore nel salvataggio del file", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitGame() {
        if(!savedGame)
        {
            int ris = JOptionPane.showConfirmDialog(this, "Ci sono progressi non salvati. Desideri salvare?", "Ci sono progressi non salvati. Desideri salvare?", JOptionPane.YES_NO_CANCEL_OPTION);

            if(ris == JOptionPane.YES_OPTION) {
                saveGame();
                new MenuGUI(this.gInteraction.getGameManager()).setVisible(true);
                this.dispose();
            }
            else if(ris == JOptionPane.NO_OPTION) {
                new MenuGUI(this.gInteraction.getGameManager()).setVisible(true);
                this.dispose();
            }
        }
        else {
            if (JOptionPane.showConfirmDialog(this, "Sei sicuro di voler tornare al menu principale?", "Sei sicuro di voler tornare al menu principale?", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                new MenuGUI(this.gInteraction.getGameManager()).setVisible(true);
                this.dispose();
            }
        }
    }

    private void jmiFastTextActionPerformed() {
        fastText = !fastText;
    }

    private void jmiCommandsActionPerformed()
    {
        JOptionPane.showMessageDialog(this, "\t\t-- Come giocare a The Lost Hotel --\n"
                + "\n"
                + "E' possibile usare questi comandi testuali anche senza premere i relativi pulsanti:\n"
                + "\n"
                + ">> nord - Spostati in direzione nord\n"
                + ">> est - Spostati in direzione est\n"
                + ">> sud - Spostati in direzione sud\n"
                + ">> ovest - Spostati in direzione ovest\n"
                + ">> inventario - Consente di visualizzare l'inventario con i relativi oggetti\n"
                + ">> salva (valido solo tramite pulsante) - Salva la partita corrente\n"
                + ">> esci (valido solo tramite pulsante) - Permette di ritornare al menù principale ed eventualmente salvare una partita\n"
                + "\n"
                + "Altri comandi:\n"
                + "\n"
                + ">> aiuto - Consente di visualizzare l'elenco dei comando riconosciuti\n"
                + ">> osserva - Permette di guardarti intorno ed esaminare l'ambiente circostante\n"
                + ">> osserva [oggetto/oggetto contenitore] - Permette di esaminare un oggetto dell'inventario o della stanza corrente\n"
                + ">> usa [oggetto/oggetto contenitore] -  Usa un oggetto dell'inventario o della stanza corrente\n"
                + ">> apri [oggetto contenitore] - Apri un oggetto contenitore\n"
                + ">> apri [oggetto contenitore] con [oggetto] - Apri un oggetto contenitore bloccato con un oggetto\n"
                + ">> sposta [oggetto/oggetto contenitore] - Sposta un oggetto della stanza\n"
                + ">> prendi [oggetto] - Prendi un oggetto a terra nella stanza o in un contenitore\n"
                + ">> lascia [oggetto] - Lascia un oggetto nella stanza corrente\n"
                + ">> inserisci [oggetto] in [oggetto contenitore] - Permette di inserire un oggetto in un oggetto contenitore (non bloccato)\n\n"
                + "Altri comandi più specifici dovranno essere trovati dal giocatore.\n"
                + "N.B: Occhio agli oggetti non presenti nelle immagini!\n\n"
                + "SUGGERIMENTO:\nÈ possibile risalire ai comandi eseguiti posizionandosi sull'area di inserimento dei comandi.\n"
                + "   - Premendo la freccia in giù, è possibile scorrere i vari comandi eseguiti.\n\n"
                + "N.B: In caso in cui si carichi una partita esistente i comandi eseguiti verranno persi!\n"
                + "\n\n"
                + "Per salvare o caricare una partita, sovrascrivere il file TheLostHotel.dat\n", "Lista comandi", JOptionPane.PLAIN_MESSAGE);
    }

    private void sendCommandByTextField()
    {
        if (!jtCommand.getText().matches("\\s+")) {

            tm.start();
            s = new StringBuilder("\n");

            // Prende il testo scritto dall'utente e lo stampa sul jTextPane
            String command = jtCommand.getText();

            appendToPane(jtpReadingArea, "\n>> " + command.toLowerCase() + "\n", Color.red);

            jtpReadingArea.setCaretPosition(0);
            jtpReadingArea.setCaretPosition(jtpReadingArea.getDocument().getLength());

            jtCommand.setText("");

            // Una volta inviato il comando al gestore di interazione, ne stampa la risposta sul jTextPane
            if(fastText) {
                if(command.length() > 0)
                    appendToPane(jtpReadingArea, "\n" + gInteraction.inputPlayer(command) + "\n", Color.white);
                else
                    appendToPane(jtpReadingArea, "\nUn comando vuoto. Interessante...\n\n", Color.white);
            }
            else {
                if(command.length() > 0)
                    s.append("\n" + gInteraction.inputPlayer(command) + "\n");
                else
                    s.append("\nUn comando vuoto. Interessante...\n\n");
            }

            savedGame = false;
            addCommand(command);
            count_key_down = 0;

            // Se il comando ha fatto terminare il gioco ( ovvero se il tempo di completamento si è bloccato )
            /*if (!gInteraction.getGameManager().getGame().getGameTime().isActive()) {
                this.insertScore();
            }*/

        }

        //Aggiorna l'immagine della Room e il suo tooltip
        jlRoomImage.setIcon(gInteraction.getGameManager().getGame().getCurrentRoom().getRoomImage());
        jlRoomImage.setToolTipText(gInteraction.getGameManager().getGame().getCurrentRoom().getName());
        jlRoomName.setText("  " + gInteraction.getGameManager().getGame().getCurrentRoom().getName() + "        ");
    }

    private void sendCommandByButton(String command)
    {

        jtCommand.setText("");

        tm.start();
        s = new StringBuilder("\n");
        appendToPane(jtpReadingArea,"\n>> " + command + "\n", Color.red);
        jtpReadingArea.setCaretPosition(0);
        jtpReadingArea.setCaretPosition(jtpReadingArea.getDocument().getLength());

        if(fastText)
            appendToPane(jtpReadingArea, "\n" + gInteraction.inputPlayer(command) + "\n", Color.white);
        else
            s.append("\n" + gInteraction.inputPlayer(command) + "\n");

        savedGame = false;
        addCommand(command);
        count_key_down = 0;

        //Aggiorna l'immagine della Room e il suo tooltip
        jlRoomImage.setIcon(gInteraction.getGameManager().getGame().getCurrentRoom().getRoomImage());
        jlRoomImage.setToolTipText(gInteraction.getGameManager().getGame().getCurrentRoom().getName());
        jlRoomName.setText("  " + gInteraction.getGameManager().getGame().getCurrentRoom().getName() + "        ");
    }

    private void enableComponents(boolean enable) {
        //if (!game.isEnd()) {
        jtCommand.setEditable(enable);
        jbEast.setEnabled(enable);
        jbNorth.setEnabled(enable);
        jbSendCommand.setEnabled(enable);
        jbSouth.setEnabled(enable);
        jbWest.setEnabled(enable);
        jbSaveGame.setEnabled(enable);
        jbQuitGame.setEnabled(enable);
        jmiSave.setEnabled(enable);
        jmiQuit.setEnabled(enable);
        jmiFastText.setEnabled(enable);
        jmiCommands.setEnabled(enable);
        //}
    }

    /**
     * Le varie descrizioni appariranno nel jTextPane e per ogni interazione
     * dell'utente verrà concatenata la risposta al testo già presente.
     *
     * @param tp jTextPane in cui verranno concatenati i messaggi
     * @param msg messaggio di risposta all'utente
     * @param c colore con cui verrà visualizzato il messaggio
     */
    public void appendToPane(JTextPane tp, String msg, Color c) {

        try {

            StyledDocument sDoc = tp.getStyledDocument();

            // Aggiungo come attributo il colore desiderato c
            SimpleAttributeSet sAttrSet = new SimpleAttributeSet();
            StyleConstants.setForeground(sAttrSet, c);

            // Inserisco la stringa in coda, con gli attributi desiderati
            sDoc.insertString(sDoc.getLength(), msg, sAttrSet);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

    }

    private void addCommand(String command)
    {
        commandHistory.add(command);
    }

    private void textFieldCommandKeyEvent(java.awt.event.KeyEvent evt)
    {
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && jbSendCommand.isEnabled()) {
            sendCommandByTextField();
        } else if ((evt.getKeyCode() == KeyEvent.VK_DOWN) && jbSendCommand.isEnabled()) {
            commandHistory();
        }
    }

    private void commandHistory()
    {
        //Verifico che sia stato inserito almeno un comando
        if(!commandHistory.isEmpty()) {
            //Verranno visualizzati, in ordine temporale, i comandi eseguiti
            count_key_down++;

            if (count_key_down <= commandHistory.size()) {
                //Serve per verificare che non sia già stato visualizzato l'ultimo comando (altrimenti verrebbe scritto a doppio)
                if((commandHistory.size() - count_key_down) >= 0)
                    jtCommand.setText(commandHistory.get(commandHistory.size() - count_key_down));
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbEast;
    private javax.swing.JButton jbQuitGame;
    private javax.swing.JButton jbNorth;
    private javax.swing.JButton jbSaveGame;
    private javax.swing.JButton jbSendCommand;
    private javax.swing.JButton jbSouth;
    private javax.swing.JButton jbWest;
    private javax.swing.JButton jbInventory;
    private javax.swing.JLabel jlBackground;
    private javax.swing.JLabel jlCommand;
    private javax.swing.JLabel jlCurrentRoom;
    private javax.swing.JLabel jlRoomImage;
    private javax.swing.JLabel jlRoomName;
    private javax.swing.JMenu jmOptions;
    private javax.swing.JMenuItem jmiSave;
    private javax.swing.JMenuItem jmiQuit;
    private javax.swing.JMenu jmText;
    private javax.swing.JCheckBoxMenuItem jmiFastText;
    private javax.swing.JMenu jmCommands;
    private javax.swing.JMenuItem jmiCommands;
    private javax.swing.JScrollPane jspCommand;
    private javax.swing.JScrollPane jspReadingArea;
    private javax.swing.JTextField jtCommand;
    public javax.swing.JTextPane jtpReadingArea;
    // End of variables declaration//GEN-END:variables

}
