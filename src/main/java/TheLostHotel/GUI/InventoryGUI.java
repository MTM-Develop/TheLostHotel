/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheLostHotel.GUI;

import TheLostHotel.Type.GameItem;
import TheLostHotel.Type.Inventory;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author gaetano
 */
public class InventoryGUI extends javax.swing.JDialog {

    private Inventory inventory;

    public InventoryGUI(Inventory inventory) {
        initComponents();
        this.inventory = inventory;
        init();
        this.setModalityType(ModalityType.APPLICATION_MODAL);
    }

    /**
     * Per ogni elemento presente nell'inventario crea un bottone mettendogli come immagine
     * l'oggetto che rappresenta.
     * E' possibile cliccare sul bottone per visualizzare nome e descrizione dell'oggetto.
     */
    private void init() {

        this.setSize(712, 737);

        jScrollPane1.setSize(660, 660);
        jScrollPane1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jScrollPane1.setAlignmentX(50);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            for (GameItem i : inventory.getInventoryList()) {

                // Inizializza il bottone
                JButton jbImage = new JButton();
                jbImage.setBackground(Color.black);
                jbImage.setFocusable(false);

                ImageIcon img = i.getItemImage();
                jbImage.setIcon(img);
                jbImage.setSize(75, 75);
                jbImage.setToolTipText(i.getName());

                /* Aggiunge l'ActionListener per il click sul bottone
                 * che permette di visualizzare nome e descrizione dell'Item.
                 */
                jbImage.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jbImageActionPerformed(evt, i.getDescription(), i.getName());
                    }
                });

                jPanel3.add(jbImage);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Se si clicca su un'immagine si aprirà un JDialog con nome de descrizione dell'oggetto
     * @param evt ActionPerformed
     * @param ItemDesc descrizione dell'oggetto
     * @param ItemName nome dell'oggetto
     */
    private void jbImageActionPerformed(java.awt.event.ActionEvent evt, String ItemDesc, String ItemName) {
        JOptionPane.showMessageDialog(this, ItemDesc, ItemName, JOptionPane.PLAIN_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inventario");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(700, 700));
        setMinimumSize(new java.awt.Dimension(700, 700));
        setPreferredSize(new java.awt.Dimension(700, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setMaximumSize(new java.awt.Dimension(700, 700));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 700));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 700));
        jPanel1.setLayout(null);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(650, 650));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(650, 650));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(650, 650));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new java.awt.GridLayout(6, 6));
        jScrollPane1.setViewportView(jPanel3);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 660, 660);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon("resources//img//background2.jpg"));
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setMaximumSize(new java.awt.Dimension(700, 700));
        jLabel1.setMinimumSize(new java.awt.Dimension(700, 700));
        jLabel1.setPreferredSize(new java.awt.Dimension(700, 700));
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 700, 700);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 700, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

