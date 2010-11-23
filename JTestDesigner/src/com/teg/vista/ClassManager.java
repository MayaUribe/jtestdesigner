/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClassManager.java
 *
 * Created on Oct 26, 2010, 1:24:50 PM
 */
package com.teg.vista;

import com.teg.logica.ClassLoading;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;

/**
 *
 * @author maya
 */
public class ClassManager extends javax.swing.JInternalFrame {

    private Inicio inicio;
    private ArrayList<String> nombresJar = new ArrayList<String>();
    private ArrayList<Class> clasesJar = new ArrayList<Class>();
    private ArrayList<File> jarsRuta = new ArrayList<File>();
    private ClassLoading classLoader = new ClassLoading();
    private ArrayList<String> nombresClases = new ArrayList<String>();

    /** Creates new form ClassManager */
    public ClassManager(Inicio inicio, ArrayList<File> jarsRuta, ArrayList<String> nombresJar) {
        initComponents();

        this.inicio = inicio;
        this.jarsRuta = jarsRuta;
        this.nombresJar = nombresJar;
        this.inicio.getSeleccionarJar().setEnabled(true);

        javax.swing.plaf.InternalFrameUI ifu = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jarLista = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clasesLista = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        clasesSeleccionadasLista = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        seleccionarTodas = new javax.swing.JLabel();
        removerTodas = new javax.swing.JLabel();
        removerClases = new javax.swing.JLabel();
        seleccionarClases = new javax.swing.JLabel();
        continuar = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setText("Jar Examinados:");

        jarLista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jarLista.setDragEnabled(true);
        jarLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jarListaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jarLista);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        jScrollPane1.setViewportView(clasesLista);

        jScrollPane3.setViewportView(clasesSeleccionadasLista);

        seleccionarTodas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teg/recursos/imagenes/rightAll.png"))); // NOI18N
        seleccionarTodas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seleccionarTodas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarTodasMouseClicked(evt);
            }
        });

        removerTodas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teg/recursos/imagenes/leftAll.png"))); // NOI18N
        removerTodas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removerTodas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removerTodasMouseClicked(evt);
            }
        });

        removerClases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teg/recursos/imagenes/left.png"))); // NOI18N
        removerClases.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removerClases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removerClasesMouseClicked(evt);
            }
        });

        seleccionarClases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/teg/recursos/imagenes/right.png"))); // NOI18N
        seleccionarClases.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seleccionarClases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarClasesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seleccionarTodas)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(removerClases)
                        .addComponent(removerTodas))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(seleccionarClases, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(64, 64, 64))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(seleccionarClases)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seleccionarTodas)
                .addGap(18, 18, 18)
                .addComponent(removerTodas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removerClases)
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                .addContainerGap())
        );

        continuar.setText("Continuar");
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(continuar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(continuar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jarListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jarListaMouseClicked
        if (this.jarLista.getModel().getSize() > 0) {

            ArrayList<String> clasesDerecha = new ArrayList<String>();

            if (MouseEvent.BUTTON1 == 1) {
                try {
                    String jarSeleccionado = this.jarLista.getSelectedValue().toString();

                    for (File jar : jarsRuta) {
                        if (jarSeleccionado.equals(jar.getName())) {
                            clasesJar = classLoader.getClassDetails(jar.getPath());
                            nombresClases = new ArrayList<String>();
                            for (Class clazz : clasesJar) {
                                nombresClases.add(clazz.getName());
                            }
                            this.jarLista.setListData(nombresJar.toArray());

                            if (clasesSeleccionadasLista.getModel().getSize() > 0) {
                                clasesDerecha = this.getClasesDerecha();
                                nombresClases.removeAll(clasesDerecha);
                            }
                            this.clasesLista.setListData(nombresClases.toArray());
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_jarListaMouseClicked

    private void seleccionarTodasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarTodasMouseClicked
        ArrayList<String> clasesIzquierda = new ArrayList<String>();
        ArrayList<String> clasesDerecha = new ArrayList<String>();

        clasesIzquierda = this.getClasesIzquierda();
        clasesDerecha = this.getClasesDerecha();

        clasesDerecha.addAll(clasesIzquierda);
        clasesIzquierda.removeAll(clasesIzquierda);

        clasesLista.setListData(clasesIzquierda.toArray());
        clasesSeleccionadasLista.setListData(clasesDerecha.toArray());
}//GEN-LAST:event_seleccionarTodasMouseClicked

    private void removerTodasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removerTodasMouseClicked
        ArrayList<String> clasesIzquierda = new ArrayList<String>();
        ArrayList<String> clasesDerecha = new ArrayList<String>();

        clasesIzquierda = this.getClasesIzquierda();
        clasesDerecha = this.getClasesDerecha();

        clasesIzquierda.addAll(clasesDerecha);
        clasesDerecha.removeAll(clasesDerecha);

        clasesLista.setListData(clasesIzquierda.toArray());
        clasesSeleccionadasLista.setListData(clasesDerecha.toArray());
}//GEN-LAST:event_removerTodasMouseClicked

    private void removerClasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removerClasesMouseClicked
        ArrayList<String> clasesIzquierda = new ArrayList<String>();
        ArrayList<String> clasesDerecha = new ArrayList<String>();

        Object[] clasesSeleccionadas = clasesSeleccionadasLista.getSelectedValues();

        clasesIzquierda = this.getClasesIzquierda();
        clasesDerecha = this.getClasesDerecha();

        for (Object object : clasesSeleccionadas) {
            String clase = object.toString();
            clasesIzquierda.add(clase);
            clasesDerecha.remove(clase);
        }

        clasesLista.setListData(clasesIzquierda.toArray());
        clasesSeleccionadasLista.setListData(clasesDerecha.toArray());
    }//GEN-LAST:event_removerClasesMouseClicked

    private void seleccionarClasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarClasesMouseClicked
        ArrayList<String> clasesIzquierda = new ArrayList<String>();
        ArrayList<String> clasesDerecha = new ArrayList<String>();

        Object[] clasesSeleccionadas = clasesLista.getSelectedValues();

        clasesIzquierda = this.getClasesIzquierda();
        clasesDerecha = this.getClasesDerecha();

        for (Object object : clasesSeleccionadas) {
            String clase = object.toString();
            clasesIzquierda.remove(clase);
            clasesDerecha.add(clase);
        }

        clasesLista.setListData(clasesIzquierda.toArray());
        clasesSeleccionadasLista.setListData(clasesDerecha.toArray());
    }//GEN-LAST:event_seleccionarClasesMouseClicked

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed

        ArrayList<Class> clases = new ArrayList<Class>();
        clases = this.getClases();

        this.inicio.classToMethods(this, clases);

    }//GEN-LAST:event_continuarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList clasesLista;
    private javax.swing.JList clasesSeleccionadasLista;
    private javax.swing.JButton continuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList jarLista;
    private javax.swing.JLabel removerClases;
    private javax.swing.JLabel removerTodas;
    private javax.swing.JLabel seleccionarClases;
    private javax.swing.JLabel seleccionarTodas;
    // End of variables declaration//GEN-END:variables

    public ArrayList<Class> getClases() {

        ArrayList<Class> clases = new ArrayList<Class>();
        ArrayList<String> clasesDerecha = new ArrayList<String>();
        ArrayList<Class> clasesJarActual = new ArrayList<Class>();

        for (File jar : jarsRuta) {
            try {
                clasesJarActual = classLoader.getClassDetails(jar.getPath());

                clasesDerecha = this.getClasesDerecha();

                for (Class clazzJar : clasesJarActual) {
                    for (String clazzDerecha : clasesDerecha) {
                        if (clazzJar.getName().equals(clazzDerecha)) {
                            clases.add(clazzJar);
                        }
                    }
                }

            } catch (MalformedURLException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClassManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return clases;
    }

    /**
     * Metodo para agregar en un arreglo las clases de la izquierda
     * @return Lista de las clases del panel izquierdo
     */
    public ArrayList<String> getClasesIzquierda() {
        ArrayList<String> clasesIzquierda = new ArrayList<String>();

        for (int i = 0; i < clasesLista.getModel().getSize(); i++) {
            clasesIzquierda.add(clasesLista.getModel().getElementAt(i).toString());
        }
        return clasesIzquierda;
    }

    /**
     * Metodo para agregar en un arreglo las clases de la derecha
     * @return Lista de las clases del panel derecho
     */
    public ArrayList<String> getClasesDerecha() {
        ArrayList<String> clasesDerecha = new ArrayList<String>();

        for (int i = 0; i < clasesSeleccionadasLista.getModel().getSize(); i++) {
            clasesDerecha.add(clasesSeleccionadasLista.getModel().getElementAt(i).toString());
        }
        return clasesDerecha;
    }

    public JList getJarLista() {
        return jarLista;
    }

    public void setJarLista(JList jarLista) {
        this.jarLista = jarLista;
    }
}
