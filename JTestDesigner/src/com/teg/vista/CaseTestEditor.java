/*
 * CaseTestEditor.java
 *
 * Created on Oct 28, 2010, 3:24:27 PM
 */
package com.teg.vista;

import com.teg.dominio.Argumento;
import com.teg.dominio.AssertTest;
import com.teg.dominio.Metodo;
//import com.teg.logica.GenericObjectEditor;
//import com.teg.logica.GetWidgetValues;
import com.teg.logica.XmlManager;
import com.teg.util.SwingUtils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author maya
 */
public class CaseTestEditor extends javax.swing.JInternalFrame {

    private ArrayList<Method> metodos = new ArrayList<Method>();
    private ArrayList<DefaultCellEditor> editores = new ArrayList<DefaultCellEditor>();
    private ArrayList<Metodo> metodosGuardados = new ArrayList<Metodo>();
    private ArrayList<File> archivosJavaDoc = new ArrayList<File>();
    private static int varId = 0;
    private Class tipoVarRetorno;
    private String valorFila;
    private String actualNameMethod;
    private JTable tablaArgumentos;
    private Inicio inicio;

    /** Creates new form CaseTestEditor */
    @SuppressWarnings("LeakingThisInConstructor")
    public CaseTestEditor(ArrayList<Method> metodos, Inicio inicio) {
        initComponents();
        this.metodos = metodos;
        this.inicio = inicio;
        this.inicio.getSeleccionarJar().setEnabled(false);
        archivosJavaDoc = inicio.getArchivosJavaDoc();
        javax.swing.plaf.InternalFrameUI ifu = this.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
    }

    public void cargarMetodos() {
       // int panelHeight = this.panel.getHeight();
        ArrayList<Object> metodosoLista = new ArrayList<Object>();

        for (Method method : metodos) {
           // panelHeight = panelHeight + 25;
            metodosoLista.add(method.getName());

            
        }
        addMethodList(metodosoLista);
    }

    public Method getActualMethod() {
        Method metodo = null;
        for (Method method : metodos) {
            if (method.getName().equals(actualNameMethod)) {
                metodo = method;
            }
        }
        return metodo;
    }

    public void addMethodList(ArrayList<Object> metodosLista) {


        listaMetodos.setListData(metodosLista.toArray());

        /*

        List<JTextField> components = SwingUtils.getDescendantsOfType(JTextField.class, this.panel, true);
        JTextField lastTextField = null;
        final JTextField metodoTest;

        if (!components.isEmpty()) {
            lastTextField = components.get(components.size() - 1);
        }

        if (lastTextField != null) {

            Point point = lastTextField.getLocation();
            int pointY = point.y + 40;

            metodoTest = new JTextField();

            metodoTest.setName("metodo:" + methodName);
            metodoTest.setLocation(point.x, pointY);
            metodoTest.setSize(192, 27);
            metodoTest.setText(methodName);
            metodoTest.setVisible(Boolean.TRUE);


            this.panel.add(metodoTest);

            if ((pointY > scroll.getHeight()) || (pointY == scroll.getHeight())) {

                Dimension dmnsn = new Dimension(panel.getWidth(), panelHeight);
                panel.setPreferredSize(dmnsn);
                scroll.setViewportView(panel);
                panel.repaint();
            }
        } else {

            Point point = new Point(12, 12);

            metodoTest = new JTextField();
            metodoTest.setName("metodo:" + methodName);
            metodoTest.setLocation(point);
            metodoTest.setSize(192, 27);
            metodoTest.setText(methodName);
            metodoTest.setVisible(Boolean.TRUE);
            panel.add(metodoTest);
            panel.repaint();
        }

        metodoTest.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!metodoTest.isEnabled() == false) {
                    cargarTablaArgumentos(metodoTest.getText());
                    actualNameMethod = metodoTest.getText();
                    Method metodoActual = getMethodSelected(actualNameMethod);
                    javaDocPanel(metodoActual.getDeclaringClass().getSimpleName(), metodoActual.getName(), archivosJavaDoc);
                }
            }
        });*/
    }

    public Method getMethodSelected(String text) {
        Method method = null;

        for (Method method1 : metodos) {
            if (text.equals(method1.getName())) {
                method = method1;
            }
        }
        return method;
    }

    public void cargarAssert(Class metodoRetorno) {
        assertVariables.removeAllItems();
        int countVar = varId;
        countVar++;
        if (metodoRetorno.isPrimitive()) {

            assertVariables.setSelectedItem("var" + countVar);
            System.out.println("Retorno primitivo");

        } else {
            assertVariables.addItem("var" + countVar);
            Field[] fields = metodoRetorno.getDeclaredFields();
            for (Field field : fields) {


                assertVariables.addItem("var" + countVar
                        + "." + field.getName());
            }
            System.out.println("Retorna algo complejo");
            assertVariables.setSelectedItem("Seleccione el campo a evaluar");

        }
        assertVariables.repaint();
    }

    public Method getMetodoGuardado(String nombre) {

        Method metodo = null;
        for (Method method : metodos) {
            if (method.getName().equals(nombre)) {
                metodo = method;
            }
        }

        return metodo;
    }

    public void cargarComboItemsComplex(JComboBox combo, Class argument) {

        Method method;

        for (Metodo metodo : metodosGuardados) {

            method = getMetodoGuardado(metodo.getNombre());

            Class retorno = method.getReturnType();

            if (retorno.getName().equals(argument.getName())) {
                combo.addItem(metodo.getRetorno().getNombreVariable());
            } else {
                if (retorno.getDeclaredFields().length != 0) {
                    for (Field field : retorno.getDeclaredFields()) {
                        if (field.getType().getName().equals(argument.getName())) {
                            combo.addItem(metodo.getRetorno().getNombreVariable()
                                    + "." + field.getName());
                        }
                    }

                }

            }
        }
    }

    public void cargarComboItemsPrimitive(JComboBox combo, Class parameter) {
        Method method;

        for (Metodo metodo : metodosGuardados) {

            method = getMetodoGuardado(metodo.getNombre());
            Class retorno = method.getReturnType();

            if (retorno.getDeclaredFields().length != 0) {

                for (Field field : retorno.getDeclaredFields()) {


                    if (field.getType().getName().equals(parameter.getName())) {
                        combo.addItem(metodo.getRetorno().getNombreVariable() + "." + field.getName());
                    }
                }




            } else {
                if (retorno.getName().equals(parameter.getName())) {
                    combo.addItem(metodo.getRetorno().getNombreVariable());
                }
            }

        }
    }

    /*if (!metodosGuardados.isEmpty())
    {
    for (Metodo metodo : metodosGuardados)
    {
    Method metod = null;
    String metodoRetorno = metodo.getNombre();
    for (Method method : metodos)
    {
    if (metodoRetorno.equals(method.getName()))
    metod = method;
    Class a = metod.getReturnType();


    }

    }
    }*/
    @SuppressWarnings("empty-statement")
    public void cargarTablaArgumentos(String text) {

        for (Component component : panelTablaArgumentos.getComponents()) {
            if (!component.getClass().getName().equals("javax.swing.JLabel")) {
                panelTablaArgumentos.remove(component);
            }
        }

        editores.clear();
        Method metodo;

        metodo = getMethodSelected(text);
        final Class[] parameterTypes = metodo.getParameterTypes();

        for (int i = 0; i < parameterTypes.length; i++) {

            final Class argument = parameterTypes[i];
            //int lastPoint = parameterTypes[i].getName().lastIndexOf(".");
            if (!argument.isPrimitive()) {
                JComboBox combo = new JComboBox();
                cargarComboItemsComplex(combo, argument);
                combo.addPopupMenuListener(new PopupMenuListener() {

                    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                    }

                    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                    }

                    public void popupMenuCanceled(PopupMenuEvent e) {
                    }
                });

                combo.setEditable(true);
                combo.addItem("Crear: " + parameterTypes[i].getName());
                DefaultCellEditor editor = new DefaultCellEditor(combo);
                editores.add(editor);
            } else {
                JComboBox combo = new JComboBox();
                cargarComboItemsPrimitive(combo, argument);
                combo.setEditable(true);

                DefaultCellEditor editor = new DefaultCellEditor(combo);
                editores.add(editor);
            }

        }

        tipoVarRetorno = metodo.getReturnType();
        cargarAssert(tipoVarRetorno);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Argumento", "Valor"}, parameterTypes.length);
        tablaArgumentos = new JTable(model) {

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = super.convertColumnIndexToModel(column);
                if (modelColumn == 1 && row < parameterTypes.length) {
                    return editores.get(row);
                } else {
                    return super.getCellEditor(row, column);
                }
            }
        };

        panelTablaArgumentos.add(tablaArgumentos);
        Border line = BorderFactory.createLineBorder(Color.black);
        tablaArgumentos.setBorder(line);
        tablaArgumentos.setSelectionMode(0);
        tablaArgumentos.setGridColor(Color.black);
        tablaArgumentos.setSize(new Dimension(450, 150));
        tablaArgumentos.setRowHeight(25);
        tablaArgumentos.setLocation(20, 40);

        for (int i = 0; i < parameterTypes.length; i++) {
            tablaArgumentos.setValueAt(parameterTypes[i].getName(), i, 0);
        }

        panelTablaArgumentos.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInicial = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVariables = new javax.swing.JTable();
        panelMetodoInfo = new javax.swing.JPanel();
        panelTablaArgumentos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelAssert = new javax.swing.JPanel();
        lbAssertVariables = new javax.swing.JLabel();
        assertVariables = new javax.swing.JComboBox();
        lbAssertCondiciones = new javax.swing.JLabel();
        assertCondiciones = new javax.swing.JComboBox();
        lbResultadoAssert = new javax.swing.JLabel();
        resultadoAssert = new javax.swing.JTextField();
        assertMensaje = new javax.swing.JTextField();
        lbAssertMensaje = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaMetodos = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        editorPane = new javax.swing.JEditorPane();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        generar = new javax.swing.JButton();

        setTitle("Case Test Editor");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Historial de Variables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        tablaVariables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Variable", "Metodo", "Tipo Retorno"
            }
        ));
        jScrollPane2.setViewportView(tablaVariables);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelMetodoInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        panelTablaArgumentos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Argumentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        jLabel1.setText("Argumento");

        jLabel2.setText("Valor");

        javax.swing.GroupLayout panelTablaArgumentosLayout = new javax.swing.GroupLayout(panelTablaArgumentos);
        panelTablaArgumentos.setLayout(panelTablaArgumentosLayout);
        panelTablaArgumentosLayout.setHorizontalGroup(
            panelTablaArgumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaArgumentosLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1)
                .addGap(146, 146, 146)
                .addComponent(jLabel2)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        panelTablaArgumentosLayout.setVerticalGroup(
            panelTablaArgumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaArgumentosLayout.createSequentialGroup()
                .addGroup(panelTablaArgumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        panelAssert.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Assert", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        lbAssertVariables.setText("Variable: ");

        assertVariables.setEditable(true);
        assertVariables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assertVariablesActionPerformed(evt);
            }
        });

        lbAssertCondiciones.setText("Condicion: ");

        assertCondiciones.setFont(new java.awt.Font("Calibri", 1, 12));
        assertCondiciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elige una opcion..", "Igual", "No Igual", "Nulo", "No Nulo", "Verdadero", "Falso", " " }));
        assertCondiciones.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                assertCondicionesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        assertCondiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assertCondicionesActionPerformed(evt);
            }
        });

        lbResultadoAssert.setText("Resultado: ");
        lbResultadoAssert.setEnabled(false);

        resultadoAssert.setEnabled(false);

        assertMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assertMensajeActionPerformed(evt);
            }
        });

        lbAssertMensaje.setText("Mensaje :");

        javax.swing.GroupLayout panelAssertLayout = new javax.swing.GroupLayout(panelAssert);
        panelAssert.setLayout(panelAssertLayout);
        panelAssertLayout.setHorizontalGroup(
            panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAssertLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAssertLayout.createSequentialGroup()
                        .addComponent(lbAssertVariables, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(assertVariables, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAssertLayout.createSequentialGroup()
                        .addGroup(panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbAssertCondiciones, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbResultadoAssert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAssertMensaje))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(assertMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(resultadoAssert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(assertCondiciones, 0, 241, Short.MAX_VALUE))))
                .addGap(99, 99, 99))
        );

        panelAssertLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbAssertCondiciones, lbAssertMensaje, lbAssertVariables, lbResultadoAssert});

        panelAssertLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {assertCondiciones, assertMensaje, assertVariables, resultadoAssert});

        panelAssertLayout.setVerticalGroup(
            panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAssertLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAssertVariables, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assertVariables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAssertCondiciones, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assertCondiciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbResultadoAssert, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultadoAssert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAssertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAssertMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assertMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        panelAssertLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {assertCondiciones, assertMensaje, assertVariables, lbAssertCondiciones, lbAssertMensaje, lbAssertVariables, lbResultadoAssert, resultadoAssert});

        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMetodoInfoLayout = new javax.swing.GroupLayout(panelMetodoInfo);
        panelMetodoInfo.setLayout(panelMetodoInfoLayout);
        panelMetodoInfoLayout.setHorizontalGroup(
            panelMetodoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMetodoInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMetodoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelAssert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTablaArgumentos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelMetodoInfoLayout.setVerticalGroup(
            panelMetodoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMetodoInfoLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(panelTablaArgumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAssert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(6, 6, 6))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Metodos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        listaMetodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMetodosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaMetodos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Documentacion (HTML)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        editorPane.setContentType("text/html");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(editorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setText("Dependencias");

        jButton2.setText("Ver Javadoc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        generar.setText("Generar");
        generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInicialLayout = new javax.swing.GroupLayout(panelInicial);
        panelInicial.setLayout(panelInicialLayout);
        panelInicialLayout.setHorizontalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMetodoInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicialLayout.createSequentialGroup()
                .addContainerGap(893, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelInicialLayout.setVerticalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInicialLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelInicialLayout.createSequentialGroup()
                        .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                            .addGroup(panelInicialLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelMetodoInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(23, 23, 23))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void assertCondicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assertCondicionesActionPerformed
}//GEN-LAST:event_assertCondicionesActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Method method = this.getActualMethod();
        varId++;

        Metodo metodoActual = this.agregarMetodo(method, varId, this.getActualAssert(), this.getArgumentos(method));

        tablaVariables.removeAll();

        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tablaVariables.getModel();


        Vector objects = new Vector();
        objects.add(metodoActual.getRetorno().getNombreVariable());
        objects.add(metodoActual.getNombre());
        objects.add(metodoActual.getRetorno().getRetorno());
        model.addRow(objects);


        for (Component component : listaMetodos.getComponents()) {
            if (component.getClass().getName().equals("javax.swing.JTextField")) {
                JTextField textField = (JTextField) component;
                if (textField.getText().equals(metodoActual.getNombre())) {
                    textField.setEnabled(false);
                }
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void assertVariablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assertVariablesActionPerformed
    }//GEN-LAST:event_assertVariablesActionPerformed

    private void assertCondicionesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_assertCondicionesPopupMenuWillBecomeInvisible
        if (assertCondiciones.getSelectedItem().equals("Igual")
                || assertCondiciones.getSelectedItem().equals("No Igual")) {
            lbResultadoAssert.setEnabled(true);
            resultadoAssert.setEnabled(true);
        } else {
            lbResultadoAssert.setEnabled(false);
            resultadoAssert.setEnabled(false);
        }
    }//GEN-LAST:event_assertCondicionesPopupMenuWillBecomeInvisible

    private void generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarActionPerformed

        XmlManager xmlManager = new XmlManager();

        xmlManager.crearCasoPrueba(this.inicio.getNombreCasoPrueba(), metodosGuardados);

    }//GEN-LAST:event_generarActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

        for (int i = 0; i < listaMetodos.getComponents().length; i++) {
            JTextField text = (JTextField) listaMetodos.getComponent(i);
            if (text.getText().equals(actualNameMethod)) {
                if (i != 0) {
                    int pos = i;
                    pos--;
                    JTextField textAntes = new JTextField();
                    textAntes = (JTextField) listaMetodos.getComponent(pos);
                    String textAntesValue = textAntes.getText();
                    textAntes.setText(actualNameMethod);
                    text.setText(textAntesValue);
                }
            }
        }

    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        for (int i = 0; i < listaMetodos.getComponents().length; i++) {

            JTextField text = (JTextField) listaMetodos.getComponent(i);
            if (text.getText().equals(actualNameMethod)) {
                if (i < listaMetodos.getComponents().length) {
                    //int pos = i;
                    //pos++;
                    JTextField textDespues = (JTextField) listaMetodos.getComponent(i + 1);
                    //JTextField temp = new JTextField();
                    //temp = text;
                    text.setText(textDespues.getText());
                    textDespues.setText(actualNameMethod);

                    break;
                }
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void assertMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assertMensajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assertMensajeActionPerformed

    private void listaMetodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMetodosMouseClicked
        // TODO add your handling code here:
        String metodoNombre = (String) listaMetodos.getSelectedValue();
        cargarTablaArgumentos(metodoNombre);
        actualNameMethod = metodoNombre;
       


    }//GEN-LAST:event_listaMetodosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (!actualNameMethod.equals(""))
        {
        Method metodoActual = getMethodSelected(actualNameMethod);

        javaDocPanel(metodoActual.getDeclaringClass().getSimpleName(), metodoActual.getName(), archivosJavaDoc);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox assertCondiciones;
    private javax.swing.JTextField assertMensaje;
    private javax.swing.JComboBox assertVariables;
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JButton generar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAssertCondiciones;
    private javax.swing.JLabel lbAssertMensaje;
    private javax.swing.JLabel lbAssertVariables;
    private javax.swing.JLabel lbResultadoAssert;
    private javax.swing.JList listaMetodos;
    private javax.swing.JPanel panelAssert;
    private javax.swing.JPanel panelInicial;
    private javax.swing.JPanel panelMetodoInfo;
    private javax.swing.JPanel panelTablaArgumentos;
    private javax.swing.JTextField resultadoAssert;
    private javax.swing.JTable tablaVariables;
    // End of variables declaration//GEN-END:variables

    public String setAssertCondition(String assertCondition){
        String condicion = "";

        if(assertCondition.equals("Igual")){
            condicion = "assertEquals";
        }

        if(assertCondition.equals("No Igual")){
            condicion = "assertNotSame";
        }

        if(assertCondition.equals("Nulo")){
            condicion = "assertNull";
        }
        
        if(assertCondition.equals("No Nulo")){
            condicion = "assertNotNull";
        }

        if(assertCondition.equals("Verdadero")){
            condicion = "assertTrue";
        }

        if(assertCondition.equals("Falso")){
            condicion = "assertFalse";
        }

        return condicion;
    }

    public AssertTest getActualAssert() {
        AssertTest assertion = new AssertTest(assertMensaje.getText(),
                assertVariables.getSelectedItem().toString(),
                setAssertCondition(assertCondiciones.getSelectedItem().toString()));

        if (assertCondiciones.getSelectedItem().toString().equals("Igual")
                || assertCondiciones.getSelectedItem().equals("No Igual")) {
            assertion.setValorAssert(resultadoAssert.getText());
        }
        return assertion;
    }

    public Metodo agregarMetodo(Method method, Integer cont, AssertTest condicionAssert, ArrayList<Argumento> argumentos) {

        Metodo metodo = null;

        XmlManager xmlManager = new XmlManager();

        metodo = xmlManager.agregarMetodoALista(metodosGuardados, method, cont, argumentos, condicionAssert);

        return metodo;
    }

    public ArrayList<Argumento> getArgumentos(Method method) {

        Integer cont = 1;

        ArrayList<Argumento> argumentos = new ArrayList<Argumento>();

        Class[] parametros = method.getParameterTypes();

        for (Class clazz : parametros) {
            Argumento argumento = new Argumento();
            argumento.setNombre("arg" + cont);
            argumento.setTipo(clazz.getName());
            argumento.setValor(tablaArgumentos.getValueAt(cont - 1, 1).toString());
            argumentos.add(argumento);
            cont++;
        }
        return argumentos;
    }

    public File getMethodHTML(ArrayList<File> archivos, String className) {

        File archivo = null;

        for (File file : archivos) {
            if (file.getName().equals(className + ".html")) {
                archivo = file;
                return archivo;
            }
        }
        return archivo;
    }

    public void javaDocPanel(String className, String methodName, ArrayList<File> archivos) {

        File file = this.getMethodHTML(archivos, className);

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file.getPath()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            Pattern pattern = Pattern.compile("<H3>" + methodName + "</H3>.*<!-- ========= END OF CLASS DATA ========= --><HR>");
            Matcher matcherExterno = pattern.matcher(sb.toString());

            while (matcherExterno.find()) {
                String javadocMetodo = matcherExterno.group().substring(0, matcherExterno.group().indexOf("<HR>"));
                editorPane.setText(javadocMetodo);
                editorPane.setContentType("text/html");
            }
        } catch (IOException ex) {
            Logger.getLogger(CaseTestEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
