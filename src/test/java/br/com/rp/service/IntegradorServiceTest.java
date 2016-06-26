package br.com.rp.service;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;
import br.com.rp.services.IntegradorService;

@CleanupUsingScript(phase = TestExecutionPhase.AFTER, value = { "db/integradorService_delete.sql" })
public class IntegradorServiceTest extends AbstractTest {

	private final Long MOVIMENTACAO_RESUMO_ID = 1000L;

	@EJB
	private IntegradorService integradorService ;

	@EJB
	private MovimentacaoResumoRepository MovimentacaoResumoRepository;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(name="java:/jms/queue/EnvioBacen")
	private Destination destinationBacen;

	@SuppressWarnings("static-access")
	@Test
	@UsingDataSet({"db/cliente.xml", "db/conta.xml", "db/movimentacao.xml", "db/movimentacao_resumo.xml"})
	public void testeA_consegueEnviarMensagemParaFilaBACEN() throws InterruptedException, JMSException {
		//integradorService.enviarMovimentacaoBancoCentral();

		MovimentacaoResumo movimentacaoResumo = MovimentacaoResumoRepository.findById(MOVIMENTACAO_RESUMO_ID);
		
		ObjectMessage om = context.createObjectMessage();
		om.setObject(movimentacaoResumo);
		JMSProducer producer = context.createProducer();
		producer.send(destinationBacen, om);

		JMSConsumer createConsumer = context.createConsumer(destinationBacen);

		Message m = createConsumer.receive();
		Assert.assertNotNull(m);

		ObjectMessage o = (ObjectMessage) m;
		MovimentacaoResumo objResult = (MovimentacaoResumo) o.getObject();

		Assert.assertNotNull(objResult);
	}
}