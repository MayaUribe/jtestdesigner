package com.teg.logica;

import com.teg.dominio.CasoPrueba;
import com.teg.dominio.AssertTest;
import com.teg.dominio.Argumento;
import com.teg.dominio.ClaseTest;
import com.teg.dominio.Retorno;
import com.teg.dominio.Metodo;
import java.util.ArrayList;
import com.teg.dominio.EscenarioPrueba;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maya
 */
public class XmlManagerTest {
    
    private XmlManager xmlManager;

    public XmlManagerTest() {
    }

    @Before
    public void setUp() {
        xmlManager = new XmlManager();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of crearXml method, of class XmlManager.
     */
    @Test
    public void testCrearXml() {
        System.out.println("crearXml");

        EscenarioPrueba casoPrueba = new EscenarioPrueba("miCasoPrueba");
        ArrayList<Metodo> metodos = new ArrayList<Metodo>();
        Metodo miMetodo1 = new Metodo("miMetodo", new ClaseTest("java.lang.String", "String"));
        miMetodo1.setRetorno(new Retorno("java.lang.String", "String", "var1"));

        ArrayList<Argumento> argumentos = new ArrayList<Argumento>();
        argumentos.add(new Argumento("arg1", "java.lang.String", "var", false, false, false));
        miMetodo1.setArgumentos(argumentos);
        miMetodo1.setAssertLinea(new AssertTest("miMensaje", "var1.nombre", "assertEquals"));

        metodos.add(miMetodo1);

        Metodo miMetodo2 = new Metodo("miMetodo2", new ClaseTest("java.lang.String", "String"));
        miMetodo2.setRetorno(new Retorno("Object", "Object", "var2"));

        ArrayList<Argumento> argumentos2 = new ArrayList<Argumento>();
        argumentos2.add(new Argumento("arg1", "java.lang.String", "var", false, false, false));
        argumentos2.add(new Argumento("arg2", "java.lang.Integer", "var", false, false, false));
        miMetodo2.setArgumentos(argumentos2);
        miMetodo2.setAssertLinea(new AssertTest("miMensaje2", "var1", "assertEquals"));

        metodos.add(miMetodo2);

        casoPrueba.setMetodos(metodos);

        //xmlManager.crearXml(casoPrueba, "ruta");
    }

    /**
     * Test of getCasoPruebaXML method, of class XmlManager.
     */
    @Test
    public void getCasoPruebaXML(){
        CasoPrueba casoPrueba = xmlManager.getCasoPruebaXML("/home/maya/miCasoPrueba.xml");
        assertNotNull(casoPrueba);
        assertEquals("mensaje", "objeto esperado", "objeto actual");
        assertNotSame("mensaje", "objeto inesperado", "objeto actual");
        assertNull("mensaje", "objeto actual");
        assertNotNull("mensaje", "objeto actual");
        assertTrue("mensaje", true);
        assertFalse("mensaje", true);
        System.out.println(casoPrueba.getNombre());
    }

}