package ${claseTemplate.nombrePaquete};

import org.testng.Assert;
import org.testng.annotations.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

<#assign hasMock = casoPrueba.mock />
<#if hasMock>
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
</#if>

<#list casoPrueba.escenariosPrueba as esc>
<#list esc.metodos as metodo>
    <#list metodo.argumentos as arg>
    <#assign esComplejo = arg.complejo />
    <#assign esArreglo = arg.arreglo />
    <#assign esMapa = arg.mapa />
    <#if esComplejo && !esArreglo && !esMapa>
import ${arg.tipo};
    </#if>
    </#list>
</#list>
</#list>

/**
 * Test Class ${claseTemplate.nombreClase}.java
 *
 * version 1.0
 */
public class ${claseTemplate.nombreClase} {

<#list clasesNoRepetidas as clase>
    private ${clase.nombre} ${clase.simpleNombre?uncap_first};
</#list>
<#list casoPrueba.escenariosPrueba as esc>
<#list esc.metodos as metodo>
    <#list metodo.argumentos as arg>
    <#assign esComplejo = arg.complejo />
    <#if esComplejo>
    private ${arg.tipo} ${arg.valor};
    </#if>
    </#list>
</#list>
</#list>
<#if hasMock>
    private Mockery jmockContext;
<#list casoPrueba.mockObjects as mockObject>
    private ${mockObject.metodoSet.clase.nombre} ${mockObject.nombreVar};
</#list>
</#if>

    public ${claseTemplate.nombreClase}() {
    }

    @BeforeClass
    public void setUp(){
    <#list clasesNoRepetidas as clase>
        ${clase.simpleNombre?uncap_first} = new ${clase.nombre}();
    </#list>
<#assign miCount = 0 />
<#assign imprimir = false />
<#list casoPrueba.escenariosPrueba as esc>
<#list esc.metodos as metodo>
    <#list metodo.argumentos as arg>
    <#assign generarXstream = arg.generarXstream />
    <#assign esComplejo = arg.complejo />
    <#assign nombreClase = arg.tipo />
        <#if esComplejo && generarXstream>
        <#assign miCount = miCount + 1 />
        <#if miCount==1 ><#assign imprimir = true />
        try {
            XStream xstream = new XStream(new DomDriver());
        </#if>
        <#assign ruta = codeManager.getRuta(casoPrueba, arg.valor) />
            InputStream is${miCount} = new FileInputStream("${ruta}");
            ${arg.valor} = (${arg.tipo}) xstream.fromXML(is${miCount});

        </#if>
    </#list>
</#list>
</#list>
    <#if imprimir>
        } catch (FileNotFoundException ex) {
            Logger.getLogger(${claseTemplate.nombreClase}.class.getName()).log(Level.SEVERE, null, ex);
        }
    </#if>
    <#if hasMock>
        jmockContext = new JUnit4Mockery();
    <#list casoPrueba.mockObjects as mockObject>
        ${mockObject.nombreVar} = jmockContext.mock(${mockObject.metodoSet.clase.nombre}.class);
    </#list>
    </#if>
    <#list casoPrueba.mockObjects as mockObject>
    <#assign imprimirMock = mockObject.imprimirMock />
    <#if !imprimirMock>
        ${mockObject.codigo}
    </#if>
    </#list>

    }

    @AfterClass
    public void tearDown() {
    }

    <#list casoPrueba.escenariosPrueba as escenario>
    /**
     * Test of ${escenario.nombre}.
     */
    @Test
    public void ${escenario.nombre}(){
    <#list casoPrueba.mockObjects as mockObject>
    <#assign imprimirMock = mockObject.imprimirMock />
    <#if imprimirMock>
        <#if mockObject.escenario == escenario.nombre>
        ${mockObject.codigo}
        </#if>
    </#if>

    </#list>
    <#assign isEmpty = codeManager.escenarioVacio(escenario) />
    <#if isEmpty><#else>
    try {<#assign excepciones = codeManager.generarExcepciones(escenario) />
    </#if>

    <#assign ordenMetodos = codeManager.generarPrueba(casoPrueba, escenario) />
    <#list ordenMetodos as metodo>
        <#if metodo.assertLinea??>${metodo.retorno.retornoNombreSimple} ${metodo.retorno.nombreVariable} = </#if>${metodo.clase.simpleNombre?uncap_first}.${metodo.getNombre()}(<#list metodo.argumentos as arg>${arg.valor}<#if arg_has_next>, </#if></#list>);
        <#if metodo.assertLinea??>Assert.${metodo.assertLinea.condicion}(${metodo.assertLinea.variable},<#if metodo.assertLinea.valorAssert??> <#assign esEnvolvente = codeManager.esClaseEnvolvente(metodo.retorno.retorno) /> <#if esEnvolvente>new ${metodo.retorno.retorno}(${metodo.assertLinea.valorAssert}),<#else>${metodo.assertLinea.valorAssert},</#if></#if> "${metodo.assertLinea.mensaje}");</#if>

    </#list>
    <#if isEmpty><#else>
    <#list excepciones as exception>
    } catch (${exception.nombre} ex) {
        Assert.fail(ex.getMessage());
    <#if !exception_has_next>}</#if>
    </#list>
    </#if>
    }
	
    </#list>
}