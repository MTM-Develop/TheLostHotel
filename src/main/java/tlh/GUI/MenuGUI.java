package tlh.GUI;

import tlh.Other.Description;
import tlh.Other.GameManager;
import tlh.Other.MenuManager;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

/**
 * Classe che gestisce l'interfaccia
 * grafica del menu principale.
 *
 * @author MTM-Develop.
 */
public class MenuGUI extends javax.swing.JFrame {

    /**
     * Gestore del menu.
     */
    private MenuManager menu;

    /**
     * Font di gioco.
     */
    private Font font;

    /**
     * Font per i jButton.
     */
    private Font fontMenuButton;

    /**
     * Font per i jMenu.
     */
    private Font fontMenuBar;

    /**
     * Creates new form Menu.
     *
     * @param gameManager gestore di gioco.
     */
    public MenuGUI(final GameManager gameManager) {
        initComponents();
        initFont();
        menu = new MenuManager(gameManager);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpMenu = new javax.swing.JPanel();
        jbNewGame = new javax.swing.JButton();
        jbLoadGame = new javax.swing.JButton();
        jbQuitGame = new javax.swing.JButton();
        jlBackgroundMenu = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmOptions = new javax.swing.JMenu();
        jmiNewGame = new javax.swing.JMenuItem();
        jmiLoadGame = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Lost Hotel");
        setPreferredSize(new java.awt.Dimension(Description.MENU_GUI_WIDTH,
                Description.MENU_GUI_HEIGHT));
        setIconImage(new ImageIcon("resources//img//other//logo.jpeg")
                .getImage());
        setResizable(false);

        jpMenu.setLayout(null);

        jbNewGame.setText("Avvia una nuova partita");
        jbNewGame.setMnemonic(KeyEvent.VK_A);
        jbNewGame.setToolTipText("Inizia una nuova partita");
        jbNewGame.setBorder(new javax.swing.border.MatteBorder(null));
        jbNewGame.addActionListener(e -> jbNewGameActionPerformed(e));
        jpMenu.add(jbNewGame);
        jbNewGame.setBounds(Description.MENU_GUI_X_JB_NEW,
                Description.MENU_GUI_Y_JB_NEW,
                Description.MENU_GUI_WIDTH_JB_NEW,
                Description.MENU_GUI_HEIGHT_JB_NEW);

        jbLoadGame.setText("Carica una partita esistente");
        jbLoadGame.setToolTipText("Riprendi una partita salvata");
        jbLoadGame.setBorderPainted(true);
        jbLoadGame.setMnemonic(KeyEvent.VK_C);
        jbLoadGame.addActionListener(e -> loadGame());
        jpMenu.add(jbLoadGame);
        jbLoadGame.setBounds(Description.MENU_GUI_X_JB_LOAD,
                Description.MENU_GUI_Y_JB_LOAD,
                Description.MENU_GUI_WIDTH_JB_LOAD,
                Description.MENU_GUI_HEIGHT_JB_LOAD);

        jbQuitGame.setText("Esci");
        jbQuitGame.setToolTipText("Chiudi il gioco");
        jbQuitGame.setBorderPainted(true);
        jbQuitGame.setMnemonic(KeyEvent.VK_E);
        jbQuitGame.addActionListener(e -> jbEsciActionPerformed(e));
        jpMenu.add(jbQuitGame);
        jbQuitGame.setBounds(Description.MENU_GUI_X_JB_QUIT,
                Description.MENU_GUI_Y_JB_QUIT,
                Description.MENU_GUI_WIDTH_JB_QUIT,
                Description.MENU_GUI_HEIGHT_JB_QUIT);

        jlBackgroundMenu.setIcon(new ImageIcon(
                "resources//img//background//menuBackground.jpg"));
        jlBackgroundMenu.setBounds(Description.MENU_GUI_X_JL_BG,
                Description.MENU_GUI_Y_JL_BG,
                Description.MENU_GUI_WIDTH_JL_BG,
                Description.MENU_GUI_HEIGHT_JL_BG);
        jpMenu.add(jlBackgroundMenu);

        jmOptions.setText("Opzioni");
        jmOptions.setMnemonic(KeyEvent.VK_O);

        jmiNewGame.setText("Nuova Partita");
        KeyStroke keyStrokeNew = KeyStroke.getKeyStroke(KeyEvent.VK_N,
                KeyEvent.CTRL_DOWN_MASK);
        jmiNewGame.setAccelerator(keyStrokeNew);
        jmiNewGame.addActionListener(e -> jbNewGameActionPerformed(e));

        jmiLoadGame.setText("Carica partita");
        KeyStroke keyStrokeLoad = KeyStroke.getKeyStroke(KeyEvent.VK_C,
                KeyEvent.CTRL_DOWN_MASK);
        jmiLoadGame.setAccelerator(keyStrokeLoad);
        jmiLoadGame.addActionListener(e -> loadGame());

        jmOptions.add(jmiNewGame);
        jmOptions.addSeparator();
        jmOptions.add(jmiLoadGame);


        jMenuBar1.add(jmOptions);


        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpMenu,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpMenu,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Description.MENU_GUI_SIZE_LAYOUT,
                                Short.MAX_VALUE)
        );

        pack();
    } // </editor-fold>//GEN-END:initComponents


    /**
     * Viene inizializzato il gioco con un determinato font per i caratteri,
     * caricato da file.
     */
    private void initFont() {

        try {
            InputStream is = new BufferedInputStream(
                    new FileInputStream("resources//font//melted.ttf"));

            font = Font.createFont(Font.TRUETYPE_FONT, is);
            fontMenuButton = font.deriveFont(Font.PLAIN,
                    Description.MENU_GUI_SIZE_FONT_JB);
            fontMenuBar = font.deriveFont(Font.PLAIN,
                    Description.MENU_GUI_SIZE_FONT_JM);

            jbNewGame.setFont(fontMenuButton);
            jbLoadGame.setFont(fontMenuButton);
            jbQuitGame.setFont(fontMenuButton);

            jMenuBar1.setFont(fontMenuBar);
            jmOptions.setFont(fontMenuBar);
            jmiNewGame.setFont(fontMenuBar);
            jmiLoadGame.setFont(fontMenuBar);
        } catch (FontFormatException | IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Errore: " + ex.getMessage(),
                    "Font non caricato correttamente; "
                            + "e'stato impostato un font di default.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jbNewGameActionPerformed(final java.awt.event.ActionEvent e) {
        try {
            menu.newGame();

            GameGUI gameGUI = new GameGUI(menu.getgInteraction());
            gameGUI.setVisible(true);
            this.dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this,
                    "Errore: " + exception.getMessage(),
                    exception.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Chiude l'applicazione.
     *
     * @param evt ActionPerformed.
     */
    private void jbEsciActionPerformed(final java.awt.event.ActionEvent evt) {
        int option;
        option = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler chiudere il gioco?",
                "Sei sicuro di voler chiudere il gioco?",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            menu.quitGame();
        }
    }

    /**
     * Carica una partita esistente da file.
     */
    public void loadGame() {
        // Creazione del JFileChooser per selezionare il file.
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setCurrentDirectory(new File("."));
        // Parte dalla cartella del progetto.

        try {

            if (chooser.showOpenDialog(this) == (JFileChooser.APPROVE_OPTION)) {

                // Carica il gioco con il file di partita selezionato.
                menu.loadGame(chooser.getSelectedFile().getAbsolutePath());

                //Per iniziare il gioco si passa al GameGUI.
                GameGUI g = new GameGUI(menu.getgInteraction());
                g.setVisible(true);
                this.dispose();

                g.appendToPane(g.getJtpReadingArea(),
                        "\nCaricamento partita completato.\n\n", Color.cyan);
                g.getJtpReadingArea().setCaretPosition(g.getJtpReadingArea().
                        getDocument().getLength());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Errore: File non valido\n " + e.getMessage(),
                    "File non valido", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * JMenuBar con le varie opzioni.
     */
    private javax.swing.JMenuBar jMenuBar1;

    /**
     * Bottone per caricare una partita esistente.
     */
    private javax.swing.JButton jbLoadGame;

    /**
     * Bottone per avviare una nuova partita.
     */
    private javax.swing.JButton jbNewGame;

    /**
     * Bottone per uscire dal gioco.
     */
    private javax.swing.JButton jbQuitGame;

    /**
     * Immagine di sfondo del Menu.
     */
    private javax.swing.JLabel jlBackgroundMenu;

    /**
     * Sezione "opzioni" del JMenuBar.
     */
    private javax.swing.JMenu jmOptions;

    /**
     * Sotto-sezione di "opzioni" per avviare una nuova partita.
     */
    private javax.swing.JMenuItem jmiNewGame;

    /**
     * Sotto-sezione di "opzioni" per caricare una partita esistente.
     */
    private javax.swing.JMenuItem jmiLoadGame;

    /**
     * Pannello contenente tutti i componenti grafici.
     */
    private javax.swing.JPanel jpMenu;
    // End of variables declaration//GEN-END:variables
}
