package br.com.rp.repository;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Banco;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BancoRepositoryTest extends AbstractTest {
	
	private final Long BANCO_TESTE_ID = 1000l;
	
	@EJB
	private BancoRepository dao;
	
	@Test
	public void testeA_consegueInserirNoBanco(){
		Banco banco = new Banco();	
		banco.setNome("TESTE");
		dao.save(banco);	
		Assert.assertNotNull(banco.getId());
		
	}
	
	@Test
	@UsingDataSet("db/banco.xml")
	public void testeB_consegueAtualizarRegistro(){
		Banco banco = dao.findById(BANCO_TESTE_ID);
		Assert.assertNotNull(banco);	
		banco.setNome("TESTE EDITAR");
		dao.save(banco);
		
		Banco result = dao.findById(BANCO_TESTE_ID);
		
		Assert.assertEquals(banco.getNome(), result.getNome());
	}
	
	@Test
	@UsingDataSet("db/banco.xml")
	public void testeC_consegueDeletarRegistro(){
		dao.remove(BANCO_TESTE_ID);
		Assert.assertEquals(0, dao.getAll().size());
	}
	
	@Test
	@UsingDataSet("db/banco.xml")
	public void testeD_consegueRecuperarRegistro(){
		Banco banco = dao.findById(BANCO_TESTE_ID);
		Assert.assertNotNull(banco);
	}
}