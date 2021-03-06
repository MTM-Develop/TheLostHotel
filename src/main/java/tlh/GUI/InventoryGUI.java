package tlh.GUI;

import tlh.Other.Description;
import tlh.Type.GameItem;
import tlh.Type.Inventory;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.ComponentOrientation;
import java.awt.Color;

/**
 * Classe che gestisce l'interfaccia
 * grafica dell'inventario di gioco.
 *
 * @author MTM-Develop.
 */
public class InventoryGUI extends javax.swing.JDialog {

    /**
     * Inventario del giocatore.
     */
    private Inventory inventory;

    /**
     * Costruttore.
     *
     * @param inv inventario del giocatore.
     */
    public InventoryGUI(final Inventory inv) {
        initComponents();
        this.inventory = inv;
        init();
        this.setModalityType(ModalityType.APPLICATION_MODAL);
    }

    /**
     * Per ogni elemento presente nell'inventario
     * crea un bottone mettendogli come immagine l'oggetto che rappresenta.
     * È possibile cliccare sul bottone
     * per visualizzare nome e descrizione dell'oggetto.
     */
    private void init() {

        this.setSize(Description.INV_GUI_WIDTH, Description.INV_GUI_HEIGHT);

        jScrollPane1.setSize(Description.INV_GUI_WIDTH_JSP,
                Description.INV_GUI_HEIGHT_JSP);
        jScrollPane1.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
        jScrollPane1.setAlignmentX(Description.INV_GUI_ALIGNMENT_X_JSP);
        jScrollPane1.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            for (GameItem i : inventory.getInventoryList()) {

                // Inizializza il bottone.
                JButton jbImage = new JButton();
                jbImage.setBackground(Color.black);
                jbImage.setFocusable(false);

                ImageIcon img = i.getItemImage();
                if (!i.isGIPassword() || i.isItemCorrectlyAdded()) {

                    jbImage.setIcon(img);
                    jbImage.setSize(Description.INV_GUI_WIDTH_JB_IMG,
                            Description.INV_GUI_HEIGHT_JB_IMG);
                    jbImage.setToolTipText(i.getName());

                    //Aggiunge l'ActionListener per il click sul bottone
                    //che permette di visualizzare nome e descrizione dell'Item.
                    jbImage.addActionListener(evt -> jbImageActionPerformed(evt,
                            i.getDescription(), i.getName()));

                    jPanel3.add(jbImage);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getLocalizedMessage(),
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Se si clicca su un'immagine si aprirà un JDialog
     * avente come nome la descrizione dell'oggetto.
     *
     * @param evt ActionPerformed.
     * @param itemDesc descrizione dell'oggetto.
     * @param itemName nome dell'oggetto.
     */
    private void jbImageActionPerformed(final java.awt.event.ActionEvent evt,
                                        final String itemDesc,
                                        final String itemName) {
        JOptionPane.showMessageDialog(this, itemDesc,
                itemName, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inventario");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(Description.INV_GUI_SIZE,
                Description.INV_GUI_SIZE));
        setMinimumSize(new java.awt.Dimension(Description.INV_GUI_SIZE,
                Description.INV_GUI_SIZE));
        setPreferredSize(new java.awt.Dimension(Description.INV_GUI_SIZE,
                Description.INV_GUI_SIZE));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setMaximumSize(new java.awt.Dimension(
                Description.INV_GUI_WIDTH_JP1, Description.INV_GUI_HEIGHT_JP1));
        jPanel1.setMinimumSize(new java.awt.Dimension(
                Description.INV_GUI_WIDTH_JP1, Description.INV_GUI_HEIGHT_JP1));
        jPanel1.setPreferredSize(new java.awt.Dimension(
                Description.INV_GUI_WIDTH_JP1, Description.INV_GUI_HEIGHT_JP1));
        jPanel1.setLayout(null);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, Description.INV_GUI_COLOR_JSP,
                        Description.INV_GUI_COLOR_JSP)));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(
                Description.INV_GUI_SIZE_JSP, Description.INV_GUI_SIZE_JSP));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(
                Description.INV_GUI_SIZE_JSP, Description.INV_GUI_SIZE_JSP));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(
                Description.INV_GUI_SIZE_JSP, Description.INV_GUI_SIZE_JSP));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new java.awt.GridLayout(
                Description.INV_GUI_ROWS_JP3, Description.INV_GUI_COLS_JP3));
        jScrollPane1.setViewportView(jPanel3);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(Description.INV_GUI_X_JSP,
                Description.INV_GUI_Y_JSP, Description.INV_GUI_WIDTH_JSP,
                Description.INV_GUI_HEIGHT_JSP);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(
                "resources//img//background2.jpg"));
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setMaximumSize(new java.awt.Dimension(
                Description.INV_GUI_WIDTH_JL, Description.INV_GUI_HEIGHT_JL));
        jLabel1.setMinimumSize(new java.awt.Dimension(
                Description.INV_GUI_WIDTH_JL, Description.INV_GUI_HEIGHT_JL));
        jLabel1.setPreferredSize(new java.awt.Dimension(
                Description.INV_GUI_WIDTH_JL, Description.INV_GUI_HEIGHT_JL));
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, Description.INV_GUI_WIDTH_JL,
                Description.INV_GUI_HEIGHT_JL);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, Description.INV_GUI_WIDTH_JL,
                Description.INV_GUI_HEIGHT_JL);

        pack();
    } // </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Immagine dell'oggetto.
     */
    private javax.swing.JLabel jLabel1;

    /**
     * Pannello che conterrà l'immagine del gameItem.
     */
    private javax.swing.JPanel jPanel1;

    /**
     * Pannello che conterrà tutti i gameItem.
     */
    private javax.swing.JPanel jPanel3;

    /**
     * Componente per scrollare in caso di molti oggetti.
     */
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

