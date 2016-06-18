package br.com.rp.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Agencia;
import br.com.rp.domain.Banco;
import br.com.rp.domain.Cartao;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Despesa;
import br.com.rp.domain.Funcionario;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.SituacaoProposta;
import br.com.rp.domain.TipoConta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value={"db/despesa_delete.sql"})
public class DespesaRepositoryTest extends AbstractTest {



	@EJB
	private CartaoRepository cartaoRepository;
	
	@EJB
	private DespesaRepository despesaRepository;
	

	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml"}) 
	public void deveInserirUmaNovaDespesa(){
		Despesa despesa = new Despesa();
		Cartao cartao = cartaoRepository.findById(1000l);
		despesa.setCartao(cartao);
		despesa.setDataLancamento(new Timestamp(System.currentTimeMillis()));
		despesa.setDescricao("Descricao da despesa");
		despesa.setValor(new BigDecimal("1000.00"));
		
		despesaRepository.save(despesa);
		Assert.assertNotNull(despesa.getId());
	}
	
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml" })
	public void deveAtualizarProposta(){
		Despesa despesa = this.despesaRepository.findById(1000l);
		despesa.setDescricao("teste");
		this.despesaRepository.save(despesa);
		Assert.assertEquals("teste", despesa.getDescricao());

	}
	
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml"})
	public void deveRemoverUmaDespesa(){
		this.despesaRepository.remove(1000l);
		Despesa despesa = this.despesaRepository.findById(1000l);
		Assert.assertNull(despesa);
		
	}
	
	@Test
	@UsingDataSet({"db/banco.xml", "db/agencia.xml", "db/conta.xml", "db/cartao.xml" , "db/despesa.xml"})
	public void testeD_consegueRecuperarRegistro(){
		Despesa despesa = this.despesaRepository.findById(1000l);
		Assert.assertNotNull(despesa);
	}
	


	

}
