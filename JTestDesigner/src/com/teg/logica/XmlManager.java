package com.teg.logica;

import com.teg.dominio.Argumento;
import com.teg.dominio.AssertTest;
import com.teg.dominio.EscenarioPrueba;
import com.teg.dominio.CasoPrueba;
import com.teg.dominio.ClaseTest;
import com.teg.dominio.Metodo;
import com.teg.dominio.MockObject;
import com.teg.dominio.Retorno;
import com.teg.vista.Inicio;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para el manejo del xml de los casos de pruebas 
 * @author maya
 */
public class XmlManager {

    private Inicio inicio;

    public XmlManager() {
    }

    /**
     * Metodo para crear el XML que contendra la informacion del caso de prueba
     * @param casoPrueba el casoPrueba a agregar
     * @param rutaCasoPrueba la ruta donde se creara la metadata del caso de
     * prueba
     */
    public void crearXml(CasoPrueba casoPrueba, String rutaCasoPrueba) {

        XStream xstream = new XStream(new DomDriver());
        xstream.alias("casoPrueba", CasoPrueba.class);
        xstream.alias("mockObject", MockObject.class);
        xstream.alias("escenarioPrueba", EscenarioPrueba.class);
        xstream.alias("claseTest", ClaseTest.class);
        xstream.alias("metodo", Metodo.class);
        xstream.alias("retorno", Retorno.class);
        xstream.alias("argumento", Argumento.class);
        xstream.alias("assert", AssertTest.class);

        String xml = xstream.toXML(casoPrueba);

        try {
            FileOutputStream fos = new FileOutputStream(rutaCasoPrueba);
            xstream.toXML(casoPrueba, fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XmlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para obtener un casoPrueba a partir de un XML
     * @param rutaCasoPrueba la ruta donde se encuentra el xml
     * @return
     */
    public CasoPrueba getCasoPruebaXML(String rutaCasoPrueba) {

        XStream xstream = new XStream(new DomDriver());
        xstream.alias("casoPrueba", CasoPrueba.class);
        xstream.alias("mockObject", MockObject.class);
        xstream.alias("escenarioPrueba", EscenarioPrueba.class);
        xstream.alias("claseTest", ClaseTest.class);
        xstream.alias("metodo", Metodo.class);
        xstream.alias("retorno", Retorno.class);
        xstream.alias("argumento", Argumento.class);
        xstream.alias("assert", AssertTest.class);

        CasoPrueba casoPrueba = null;

        try {
            InputStream is = new FileInputStream(rutaCasoPrueba);
            casoPrueba = (CasoPrueba) xstream.fromXML(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XmlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return casoPrueba;
    }

    /**
     * Metodo para crear un Caso de Prueba
     * @param nombreCasoPrueba el nombre del caso de prueba a crear
     * @param metodos los metodos a setear al caso de prueba
     */
    public CasoPrueba crearCasoPrueba(String nombreCasoPrueba, ArrayList<EscenarioPrueba> escenarios) {

        CasoPrueba casoPrueba = new CasoPrueba(nombreCasoPrueba);
        CodeGenerator cg = new CodeGenerator();
        cg.setInicio(this.getInicio());

        File casoPruebaFile = new File(System.getProperty("user.home") +
                System.getProperty("file.separator") + nombreCasoPrueba +
                System.getProperty("file.separator"));

        File metadata = new File(casoPruebaFile.getPath() +
                System.getProperty("file.separator") + "metadata" +
                System.getProperty("file.separator"));

        casoPrueba.setEscenariosPrueba(escenarios);
        casoPrueba.setMock(false);
        
        this.crearXml(casoPrueba, metadata.getPath() +
                System.getProperty("file.separator") + nombreCasoPrueba + ".xml");

        cg.generateTest(metadata.getPath() + System.getProperty("file.separator")
                + nombreCasoPrueba + ".xml");
        
        return casoPrueba;

    }

    /**
     * Metodo para crear un Caso de Prueba
     * @param nombreCasoPrueba el nombre del caso de prueba a crear
     * @param metodos los metodos a setear al caso de prueba
     */
    public CasoPrueba crearCasoPrueba(String nombreCasoPrueba, ArrayList<EscenarioPrueba> escenarios, ArrayList<MockObject> mockObjects) {

        CasoPrueba casoPrueba = new CasoPrueba(nombreCasoPrueba);
        CodeGenerator cg = new CodeGenerator();
        cg.setInicio(this.getInicio());

        File casoPruebaFile = new File(System.getProperty("user.home") +
                System.getProperty("file.separator") + nombreCasoPrueba +
                System.getProperty("file.separator"));

        File metadata = new File(casoPruebaFile.getPath() +
                System.getProperty("file.separator") + "metadata" +
                System.getProperty("file.separator"));

        //casoPrueba.setNombrePaquete("com.test.prueba");
        casoPrueba.setEscenariosPrueba(escenarios);
        casoPrueba.setMock(true);

        if(!mockObjects.isEmpty()){
            casoPrueba.setMockObjects(mockObjects);
        }

        this.crearXml(casoPrueba, metadata.getPath() +
                System.getProperty("file.separator") + nombreCasoPrueba + ".xml");

        cg.generateTest(metadata.getPath() + System.getProperty("file.separator")
                + nombreCasoPrueba + ".xml");

        return casoPrueba;

    }

    /**
     * Metodo para agregar un metodo a la lista de metodos
     * @param metodos lista de metodos donde se agregara el metodo
     * @param method el metodo a setear
     * @param numVariable numero de la variable a crear
     * @param argumentos lista de argumentos del metodo a setear 
     * @param condAssert condicion de Assert para crear la variable
     * @return ArrayList<Metodo> la nueva lista de metodos con el metodo agregado 
     */
    public Metodo agregarMetodoALista(ArrayList<Metodo> metodos, Method method,
           int numVariable, ArrayList<Argumento> argumentos, AssertTest condAssert) {
           //int numVariable, ArrayList<Argumento> argumentos, AssertTest condAssert, MockObject mockObject) {

        Metodo miMetodo = new Metodo(method.getName(), new ClaseTest(method.getDeclaringClass().getName(),
                method.getDeclaringClass().getSimpleName()));
        miMetodo.setRetorno(new Retorno(method.getReturnType().getName(), 
                method.getReturnType().getSimpleName(), "resultado" + numVariable));
        miMetodo.setArgumentos(argumentos);
        miMetodo.setAssertLinea(condAssert);

        ArrayList<ClaseTest> excepciones = new ArrayList<ClaseTest>();

        Class[] methodExcepcions = method.getExceptionTypes();
        
        for (Class clazz : methodExcepcions) {
            excepciones.add(new ClaseTest(clazz.getName(), clazz.getSimpleName()));
        }

        miMetodo.setExcepciones(excepciones);

//        if(mockObject != null){
//            miMetodo.setMockObject(mockObject);
//        }

        metodos.add(miMetodo);

        return miMetodo;
    }

    /**
     * Metodo para agregar un caso de prueba a un grupo de prueba
     * @param casoPrueba el grupo al cual se agregara el caso de prueba
     * @param escenarioPrueba el caso de prueba a agregar
     * @return el nuevo Grupo con el caso de prueba seteado
     */
    public CasoPrueba agregarEscenarioACaso(CasoPrueba casoPrueba, EscenarioPrueba escenarioPrueba) {

        ArrayList<EscenarioPrueba> casosPrueba = casoPrueba.getEscenariosPrueba();

        casosPrueba.add(escenarioPrueba);

        casoPrueba.setEscenariosPrueba(casosPrueba);

        return casoPrueba;

    }

    /**
     * @return the inicio
     */
    public Inicio getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Inicio inicio) {
        this.inicio = inicio;
    }
}
