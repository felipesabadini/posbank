package br.com.rp.repository;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Configuracao;
import br.com.rp.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfiguracaoRepositoryTest extends AbstractTest {
	
	private final Long CONFIGURACAO_ID = 1L;
	
	@EJB
	private ConfiguracaoRepository dao;
	
	@Test
	@UsingDataSet({"db/banco.xml"})
	public void testeA_consegueSalvarERecuperarConfiguracaoBancoDados(){
		Configuracao config = new Configuracao();
		config.setId(CONFIGURACAO_ID);
		config.setHoraInicialTransacao(Util.getDataAtual());
		config.setHoraInicialTransacao(Util.getDataAtual());
		
		dao.save(config);
		
		Configuracao result = dao.findById(CONFIGURACAO_ID);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getId());
	}
	

}