package ${claseTemplate.nombrePaquete};

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Class ${claseTemplate.nombreClase?cap_first}.java
 *
 * version 1.0
 */
public class ${claseTemplate.nombreClase?cap_first}Test {

<#list clasesNoRepetidas as clase>
	private ${clase} ${clase?uncap_first};
</#list>

	public ${claseTemplate.nombreClase}Test() {
	}

	@Before
	public void setUp(){
	<#list clasesNoRepetidas as clase>
		${clase?uncap_first} = new ${clase}();
	</#list>
	}

	@After
	public void tearDown() {
	}

	<#list casoPrueba.metodos as method>
	/**
	 * Test of ${method.nombre} method, of class ${method.claseSimpleName}.
	 */
	@Test
	public void ${method.nombre}Test(){
		<#assign ordenMetodos = codeManager.generarPrueba(casoPrueba, method) />

		<#list ordenMetodos as metodo>
		${metodo.retorno.retornoSimpleName} ${metodo.retorno.nombreVariable} = ${metodo.claseSimpleName?uncap_first}.${metodo.getNombre()}(<#list metodo.argumentos as arg>${arg.valor}<#if arg_has_next>, </#if></#list>);
		</#list>
	}
	</#list>
}