package com.teg.vista;

import java.beans.PropertyVetoException;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

/**
 *
 * @author maya
 */
public class Inicio extends javax.swing.JFrame {

    private ArrayList<String> nombresJar = new ArrayList<String>();

    private ArrayList<File> jarsRuta = new ArrayList<File>();

    private ClassManager classManager = new ClassManager(this, jarsRuta, nombresJar);

    private ArrayList<File> archivosJavaDoc = new ArrayList<File>();

    /** Creates new form Inicio */
    public Inicio() {
        try {
            initComponents();

            this.classManager.setVisible(Boolean.TRUE);
            this.jDesktopPane.add(this.classManager);
            this.classManager.setMaximum(true);

        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane = new javax.swing.JDesktopPane();
        tapa = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        seleccionarJar = new javax.swing.JMenuItem();
        menuJavaDoc = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDesktopPane.setNextFocusableComponent(jMenuBar1);
        jDesktopPane.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout tapaLayout = new javax.swing.GroupLayout(tapa);
        tapa.setLayout(tapaLayout);
        tapaLayout.setHorizontalGroup(
            tapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        tapaLayout.setVerticalGroup(
            tapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tapa.setBounds(0, 620, 850, 50);
        jDesktopPane.add(tapa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu1.setText("Archivo");

        seleccionarJar.setText("Abrir");
        seleccionarJar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarJarActionPerformed(evt);
            }
        });
        jMenu1.add(seleccionarJar);

        menuJavaDoc.setText("Cargar Javadoc");
        menuJavaDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuJavaDocActionPerformed(evt);
            }
        });
        jMenu1.add(menuJavaDoc);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionarJarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarJarActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new Extension());
        int returnVal = fc.showOpenDialog(Inicio.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File jarFile = fc.getSelectedFile();
            String jarString = jarFile.getPath();
            File jar = new File(jarString);

            this.jarsRuta.add(jar);
            this.setJarsRuta(jarsRuta);
            this.nombresJar.add(jar.getName());
            this.classManager.getJarLista().setListData(nombresJar.toArray());
        }
    }//GEN-LAST:event_seleccionarJarActionPerformed

    private void menuJavaDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuJavaDocActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(Inicio.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File directory = fc.getSelectedFile();

            String pathDirectory = directory.getPath();

            setArchivosJavaDoc(new ArrayList<File>());

            setArchivosJavaDoc(this.leerDirectorio(pathDirectory, getArchivosJavaDoc()));

        }

    }//GEN-LAST:event_menuJavaDocActionPerformed

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

    public void cambioClassMethods(JInternalFrame classManager, ArrayList<Class> clases) {
        try {
            classManager.setVisible(false);

            MethodsManager methodsManager = new MethodsManager(this, clases);
            methodsManager.setVisible(Boolean.TRUE);
            this.getjDesktopPane().add(methodsManager);
            methodsManager.setMaximum(Boolean.TRUE);

        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambioMethodsCaseTest(JInternalFrame methodsManager, ArrayList<Method> metodos) {
        try {
            methodsManager.setVisible(false);

            CaseTestEditor caseTest = new CaseTestEditor(metodos, this);
            caseTest.setVisible(Boolean.TRUE);
            this.getjDesktopPane().add(caseTest);
            caseTest.setMaximum(Boolean.TRUE);
            caseTest.cargarMetodos();

        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Estilo estilo = new Estilo();
                estilo.setLookAndFeel();
                new Inicio().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuJavaDoc;
    private javax.swing.JMenuItem seleccionarJar;
    private javax.swing.JPanel tapa;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jarsRuta
     */
    public ArrayList<File> getJarsRuta() {
        return jarsRuta;
    }

    /**
     * @param jarsRuta the jarsRuta to set
     */
    public void setJarsRuta(ArrayList<File> jarsRuta) {
        this.jarsRuta = jarsRuta;
    }

    /**
     * @return the jDesktopPane
     */
    public javax.swing.JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    /**
     * @param jDesktopPane the jDesktopPane to set
     */
    public void setjDesktopPane(javax.swing.JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

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