package br.com.rp.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Funcionario;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoProposta;

public class PropostaRepositoryTest extends AbstractTest {

	@EJB
	private ClienteRepository clienteRepository;
	@EJB
	private FuncionarioRepository funcionarioRepository;
	@EJB
	private PropostaRepository dao;
	
	@Test
	@UsingDataSet({"db/cliente.xml", "db/funcionario.xml"}) 
	public void deveInserirUmaNovaProposta() {
		Proposta proposta = new Proposta();
		Cliente cliente = this.clienteRepository.findById(100L);
		Funcionario funcionario = this.funcionarioRepository.findById(1000L);
		proposta.setCliente(cliente);
		proposta.setRendimento(new BigDecimal("1000.00"));
		proposta.setFuncionario(funcionario);
		proposta.setMensagem("quero ser aprovado");
		proposta.setDataAvaliacao(new Timestamp(System.currentTimeMillis()));
		proposta.setSituacao(SituacaoProposta.REC);
		proposta.setDataCadastro(new Timestamp(System.currentTimeMillis()));
		dao.save(proposta);
		
		Assert.assertNotNull(proposta.getId());
		
	}
	
	
	
	@Test
	@UsingDataSet({"db/funcionario.xml", "db/cliente.xml", "db/proposta.xml" })
	public void deveRemoverUmaProposta() {
		this.dao.remove(1000L);
		Proposta proposta = this.dao.findById(1000l);	
		Assert.assertNull(proposta);
	}
	
	
	@Test
	@UsingDataSet({"db/funcionario.xml", "db/cliente.xml", "db/proposta.xml" })
	public void deveAtualizarProposta(){
		Proposta proposta = this.dao.findById(1000L);
		proposta.setMensagem("teste");
		this.dao.save(proposta);
		Assert.assertEquals("teste", proposta.getMensagem());
	}
	
	@Test
	@UsingDataSet({"db/funcionario.xml", "db/cliente.xml", "db/proposta.xml" })
	public void devePesquisarPorEstado(){
		List<Proposta> propostas = this.dao.procurarProspostasPorEstado("PR");
		
	Assert.assertEquals(propostas.get(0), "PR");		
		
	}

}
