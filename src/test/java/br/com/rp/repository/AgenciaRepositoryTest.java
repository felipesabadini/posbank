package br.com.rp.repository;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Agencia;
import br.com.rp.domain.Banco;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgenciaRepositoryTest extends AbstractTest {
	
	private final Long AGENCIA_TESTE_ID = 1000l;
	private final Long BANCO_TESTE_ID = 1000l;
	
	
	@EJB
	private AgenciaRepository dao;
	@EJB
	private BancoRepository daoBanco;
	
	@Test
	@UsingDataSet("db/banco.xml")
	public void testeA_consegueInserirNoBanco(){
		Banco banco = daoBanco.findById(BANCO_TESTE_ID);
		Assert.assertNotNull(banco);
		
		Agencia agencia = new Agencia();
		agencia.setNome("856-7");
		agencia.setBanco(banco);
		dao.save(agencia);	
		Assert.assertNotNull(agencia.getId());
	}
	
	@Test
	@UsingDataSet("db/agencia.xml")
	public void testeB_consegueAtualizarRegistro(){
		Agencia agencia = dao.findById(AGENCIA_TESTE_ID);
		Assert.assertNotNull(agencia);	
		agencia.setNome("12345");
		dao.save(agencia);
		
		Agencia result = dao.findById(AGENCIA_TESTE_ID);
		
		Assert.assertEquals(agencia.getNome(), result.getNome());
	}
	
	@Test
	@UsingDataSet("db/agencia.xml")
	public void testeC_consegueDeletarRegistro(){
		dao.remove(AGENCIA_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet("db/agencia.xml")
	public void testeD_consegueRecuperarRegistro(){
		Agencia agencia = dao.findById(AGENCIA_TESTE_ID);
		Assert.assertNotNull(agencia);
	}
}