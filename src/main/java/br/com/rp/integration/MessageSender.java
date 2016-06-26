package br.com.rp.integration;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;

import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;

@Stateless
public class MessageSender {

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(name="java:/jms/queue/EnvioBacen")
	private Destination destinationBacen;
	
	@Resource(name="java:/jms/queue/EnvioEUA")
	private Destination destinationEUA;
	
	@EJB
	MovimentacaoResumoRepository MovimentacaoResumoRepository;
	
	public void realizarIntegracaoBACEN(MovimentacaoResumo movimentacaoResumo){
		ObjectMessage om = context.createObjectMessage();
		try {
			om.setObject(movimentacaoResumo);
			JMSProducer producer = context.createProducer();
			producer.send(destinationBacen, om);
			
			movimentacaoResumo.setEnviadoBacen(Boolean.TRUE);
			MovimentacaoResumoRepository.save(movimentacaoResumo);
		} catch (JMSException e) {
			movimentacaoResumo.setErroEnviarBacen(Boolean.TRUE);
			MovimentacaoResumoRepository.save(movimentacaoResumo);
			
			e.printStackTrace();
		}
	}
	
	public void realizarIntegracaoEUA(MovimentacaoResumo movimentacaoResumo){
		ObjectMessage om = context.createObjectMessage();
		try {
			om.setObject(movimentacaoResumo);
			JMSProducer producer = context.createProducer();
			producer.send(destinationBacen, om);
			
			movimentacaoResumo.setEnviadoEUA(Boolean.TRUE);
			MovimentacaoResumoRepository.save(movimentacaoResumo);
		} catch (JMSException e) {
			movimentacaoResumo.setErroEnviarEUA(Boolean.TRUE);
			MovimentacaoResumoRepository.save(movimentacaoResumo);
			
			e.printStackTrace();
		}
	}
}