/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheLostHotel.GUI;

import TheLostHotel.Other.GameManager;
import TheLostHotel.Other.MenuManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 *
 * @author gaetano
 */
public class MenuGUI extends javax.swing.JFrame {

    private MenuManager menu;
    /*Statement stm;
    Connection con;

    static final String QUERY_CREATE = "CREATE TABLE IF NOT EXISTS za (ID INTEGER, NAME VARCHAR(20))";
    static final String QUERY_INSERT = "INSERT INTO za VALUES (4, 'PROVA 4')";
    static final String QUERY_SELECT = "SELECT * FROM za";
    static final String QUERY_DROP = "DROP TABLE IF EXISTS za";

    ResultSet resultSet;*/

    private Font font;
    private Font fontMenuButton, fontMenuBar;
    /**
     * Creates new form Menu
     */
    public MenuGUI(GameManager gameManager) {
        initComponents();
        initFont();
        menu = new MenuManager(gameManager);
        //initDb();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpMenu = new javax.swing.JPanel();
        jbNewGame = new javax.swing.JButton();
        jbLoadGame = new javax.swing.JButton();
        jbQuitGame = new javax.swing.JButton();
        jlBackgroundMenu = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmOptions = new javax.swing.JMenu();
        jmMusic = new javax.swing.JMenu();
        jmiNewGame = new javax.swing.JMenuItem();
        jmiLoadGame = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Lost Hotel");
        setPreferredSize(new java.awt.Dimension(900, 600));
        //setIconImage(new ImageIcon("resources//img//other//logo.jpeg").getImage());
        setResizable(false);

        jpMenu.setLayout(null);

        jbNewGame.setText("Avvia una nuova partita");
        jbNewGame.setMnemonic(KeyEvent.VK_A);
        jbNewGame.setToolTipText("Inizia una nuova partita");
        jbNewGame.setBorder(new javax.swing.border.MatteBorder(null));
        jbNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbNewGameActionPerformed(e);
            }
        });
        jpMenu.add(jbNewGame);
        jbNewGame.setBounds(550, 170, 248, 35);

        jbLoadGame.setText("Carica una partita esistente");
        jbLoadGame.setToolTipText("Riprendi una partita salvata");
        jbLoadGame.setBorderPainted(true);
        jbLoadGame.setMnemonic(KeyEvent.VK_C);
        jbLoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load_game();
            }
        });
        jpMenu.add(jbLoadGame);
        jbLoadGame.setBounds(550, 270, 248, 35);

        jbQuitGame.setText("Esci");
        jbQuitGame.setToolTipText("Chiudi il gioco");
        jbQuitGame.setBorderPainted(true);
        jbQuitGame.setMnemonic(KeyEvent.VK_E);
        jbQuitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbEsciActionPerformed(e);
            }
        });
        jpMenu.add(jbQuitGame);
        jbQuitGame.setBounds(550, 370, 248, 35);

        jlBackgroundMenu.setIcon(new ImageIcon("resources//img//background//menuBackground.jpg"));
        jlBackgroundMenu.setBounds(-150, 0, 1050, 560);
        jpMenu.add(jlBackgroundMenu);

        jmOptions.setText("Opzioni");
        jmOptions.setMnemonic(KeyEvent.VK_O);

        jmiNewGame.setText("Nuova Partita");
        KeyStroke keyStrokeNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        jmiNewGame.setAccelerator(keyStrokeNew);
        jmiNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbNewGameActionPerformed(e);
            }
        });

        jmiLoadGame.setText("Carica partita");
        KeyStroke keyStrokeLoad = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
        jmiLoadGame.setAccelerator(keyStrokeLoad);
        jmiLoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load_game();
            }
        });

        jmOptions.add(jmiNewGame);
        jmOptions.addSeparator();
        jmOptions.add(jmiLoadGame);

        jmMusic.setText("Musica");
        jmMusic.setMnemonic(KeyEvent.VK_M);

        jMenuBar1.add(jmOptions);
        jMenuBar1.add(jmMusic);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Viene inizializzato il gioco con un determinato font per i caratteri,
     * caricato da file.
     */
    private void initFont() {

        // La risorsa del try with resource si chiuderà da sola poiché implementa l'interfaccia AutoCloseable
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("resources//font//melted.ttf"));

            font = Font.createFont(Font.TRUETYPE_FONT, is);
            fontMenuButton = font.deriveFont(Font.PLAIN, 16);
            fontMenuBar = font.deriveFont(Font.PLAIN, 13);

            //this.setFont(fontMenuButton);
            jbNewGame.setFont(fontMenuButton);
            jbLoadGame.setFont(fontMenuButton);
            jbQuitGame.setFont(fontMenuButton);

            jMenuBar1.setFont(fontMenuBar);
            jmOptions.setFont(fontMenuBar);
            jmMusic.setFont(fontMenuBar);
            jmiNewGame.setFont(fontMenuBar);
            jmiLoadGame.setFont(fontMenuBar);
        } catch (FontFormatException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Font non caricato correttamente; e'stato impostato un font di default.", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jbNewGameActionPerformed(java.awt.event.ActionEvent evt)
    {
        try {
            menu.newGame();

            GameGUI gameGUI = new GameGUI(menu.getgInteraction());
            gameGUI.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Chiude l'applicazione
     *
     * @param evt ActionPerformed
     */
    private void jbEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEsciActionPerformed
        int option;
        option = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler chiudere il gioco?", "Sei sicuro di voler chiudere il gioco?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            menu.quitGame();
        }
    }

    public void load_game()
    {
        // Creazione del JFileChooser per selezionare il file
        JFileChooser fChooser = new JFileChooser();
        fChooser.setMultiSelectionEnabled(false);
        fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fChooser.setCurrentDirectory(new File(".")); // Parte dalla cartella del progetto

        try {

            if (fChooser.showOpenDialog(this) == (JFileChooser.APPROVE_OPTION)) {

                // Carica il gioco con il file di partita selezionato
                menu.loadGame(fChooser.getSelectedFile().getAbsolutePath());

                //Per iniziare il gioco si passa al GameGUI
                GameGUI g = new GameGUI(menu.getgInteraction());
                g.setVisible(true);
                this.dispose();

                g.appendToPane(g.jtpReadingArea,"\nCaricamento partita completato.\n\n", Color.cyan);
                g.jtpReadingArea.setCaretPosition(g.jtpReadingArea.getDocument().getLength());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore: File non valido\n " + e.getMessage(), "File non valido", JOptionPane.ERROR_MESSAGE);
        } //catch (IOException e) {
        //JOptionPane.showMessageDialog(this, "Errore: " + e.getMessage(), "Errore nell'apertura del file", JOptionPane.ERROR_MESSAGE);
        //}
    }

    /*public void initDb() throws SQLException, ClassNotFoundException {

        try {
            con = DriverManager.getConnection("jdbc:h2:./resources/database/store");
            System.out.println("Connessione al database riuscita con successo");
            stm = con.createStatement();
            stm.executeUpdate(QUERY_CREATE);
            stm.close();
            //esegui_query();
        }catch (Exception exception)
        {
            System.out.println(exception);
        }

    }*/

    /*public void esegui_query() throws SQLException {
        stm = con.createStatement();
        //stm.executeUpdate(QUERY_INSERT);
        //Per le query di SELECT si usa executeQuery
        resultSet = stm.executeQuery(QUERY_SELECT);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ": " + resultSet.getString(2));
        }

        resultSet.close();
        stm.close();
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton jbLoadGame;
    private javax.swing.JButton jbNewGame;
    private javax.swing.JButton jbQuitGame;
    private javax.swing.JLabel jlBackgroundMenu;
    private javax.swing.JMenu jmOptions;
    private javax.swing.JMenu jmMusic;
    private javax.swing.JMenuItem jmiNewGame;
    private javax.swing.JMenuItem jmiLoadGame;
    private javax.swing.JPanel jpMenu;
    // End of variables declaration//GEN-END:variables
}
