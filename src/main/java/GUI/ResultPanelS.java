/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;

/**
 *
 * @author DiegoAlvarez
 */
public class ResultPanelS extends javax.swing.JPanel {

    /**
     * Creates new form ResultsPanel
     */
    public ResultPanelS() {
        initComponents();
    }

    public ResultPanelS(String word, String description, String lines) {
        initComponents();
        this.description.setText(description);
        this.lines.setText(lines);
        this.word.setText(word);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        word = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        lines = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        word.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        word.setText("token");

        description.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        description.setText("Descripcion");

        lines.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        lines.setText("Linea, Columna");
        lines.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(word, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lines, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(word)
                .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel description;
    public javax.swing.JLabel lines;
    public javax.swing.JLabel word;
    // End of variables declaration//GEN-END:variables
}
