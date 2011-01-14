/*
 * MenuPrincipal.java
 *
 * Created on Nov 22, 2010, 1:33:34 PM
 */
package com.teg.vista;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author maya
 */
public class MenuPrincipal extends javax.swing.JInternalFrame {

    private Inicio inicio;
    private ArrayList<File> archivosJavaDoc = new ArrayList<File>();

    /** Creates new form MenuPrincipal */
    public MenuPrincipal(Inicio inicio) {
        initComponents();
        this.inicio = inicio;

        javax.swing.plaf.InternalFrameUI ifu = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);

        int w = this.getSize().width;
        int h = this.getSize().height;
        this.inicio.setSize(new Dimension(w, h));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        casoPrueba = new javax.swing.JTextField();
        rutaJavaDoc = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        continuar = new javax.swing.JButton();

        setBorder(null);

        jPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Nombre del Caso Prueba:");

        jLabel2.setText("Ruta JavaDoc:");

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel3.setText("Introduzca los siguientes campos: ");

        rutaJavaDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rutaJavaDocActionPerformed(evt);
            }
        });

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(casoPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(rutaJavaDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscar)))))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        panelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        panelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {casoPrueba, rutaJavaDoc});

        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(casoPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rutaJavaDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscar))))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        panelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buscar, casoPrueba, jLabel1, jLabel2, rutaJavaDoc});

        continuar.setText("Continuar");
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(continuar)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(continuar)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed

        this.inicio.principalToClassManager(this, this.getArchivosJavaDoc(), this.casoPrueba.getText());
        this.crearDirectorios(this.casoPrueba.getText());

    }//GEN-LAST:event_continuarActionPerformed

    private void rutaJavaDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rutaJavaDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rutaJavaDocActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(MenuPrincipal.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File directory = fc.getSelectedFile();

            String pathDirectory = directory.getPath();

            setArchivosJavaDoc(this.leerDirectorio(pathDirectory, getArchivosJavaDoc()));

            this.rutaJavaDoc.setText(pathDirectory);
        }

    }//GEN-LAST:event_buscarActionPerformed

    /**
     * Metodo para la creacion de los directorios de la prueba
     * @param nombreCasoPrueba nombre del caso de prueba que sera creado
     */
    public void crearDirectorios(String nombreCasoPrueba) {

        File miCasoPrueba = new File(System.getProperty("user.home")
                + System.getProperty("file.separator") + nombreCasoPrueba
                + System.getProperty("file.separator"));
        miCasoPrueba.mkdir();

        File src = new File(miCasoPrueba.getPath()
                + System.getProperty("file.separator") + "src"
                + System.getProperty("file.separator"));
        src.mkdir();

        File com = new File(src.getPath() +
                System.getProperty("file.separator") + "com" +
                System.getProperty("file.separator"));
        com.mkdir();

        File paquete = new File(com.getPath() +
                System.getProperty("file.separator") + "codeGeneratorTest" +
                System.getProperty("file.separator"));
        paquete.mkdir();

        File metadata = new File(miCasoPrueba.getPath()
                + System.getProperty("file.separator") + "metadata"
                + System.getProperty("file.separator"));
        metadata.mkdir();

        File metawidget = new File(miCasoPrueba.getPath()
                + System.getProperty("file.separator") + "metawidgetData"
                + System.getProperty("file.separator"));
        metawidget.mkdir();

        File lib = new File(miCasoPrueba.getPath()
                + System.getProperty("file.separator") + "lib"
                + System.getProperty("file.separator"));
        lib.mkdir();

        File classes = new File(miCasoPrueba.getPath()
                + System.getProperty("file.separator") + "classes"
                + System.getProperty("file.separator"));
        classes.mkdir();

        File resultados = new File(miCasoPrueba.getPath()
                + System.getProperty("file.separator") + "resultados"
                + System.getProperty("file.separator"));
        resultados.mkdir();

        inicio.setDirectorioCasoPrueba(metawidget);

    }

    public ArrayList<File> leerDirectorio(final String nombreDirectorio, ArrayList<File> archivos) {

        final File directorio = new File(nombreDirectorio);

        if (directorio.exists() && directorio.isDirectory()) {

            final File[] nombreFicherosFile = directorio.listFiles();

            for (File file : nombreFicherosFile) {
                if (!file.getName().equals("class-use")) {
                    if (file.isDirectory()) {
                        archivos = leerDirectorio(file.getPath(), archivos);
                    } else {
                        archivos.add(file);
                    }
                }
            }
        } else {
            System.out.println("Error: " + nombreDirectorio + " no existe o no corresponde a un directorio");
        }
        return archivos;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JTextField casoPrueba;
    private javax.swing.JButton continuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField rutaJavaDoc;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the archivosJavaDoc
     */
    public ArrayList<File> getArchivosJavaDoc() {
        return archivosJavaDoc;
    }

    /**
     * @param archivosJavaDoc the archivosJavaDoc to set
     */
    public void setArchivosJavaDoc(ArrayList<File> archivosJavaDoc) {
        this.archivosJavaDoc = archivosJavaDoc;
    }
}
