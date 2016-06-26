package br.com.rp.services.impl;

import java.util.List;

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

import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;
import br.com.rp.services.MovimentacaoResumoService;

@Stateless
public class MovimentacaoResumoServiceImpl implements MovimentacaoResumoService {
	
	@EJB
	private MovimentacaoResumoRepository repository;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(name="java:/jms/queue/EnvioBacen")
	private Destination destinationBacen;
	
	@Resource(name="java:/jms/queue/EnvioEUA")
	private Destination destinationEUA;
	
	public void registrarMovimentacaoResumo(Movimentacao movimentacao){
		MovimentacaoResumo movimentacaoResumo = new MovimentacaoResumo();
		movimentacaoResumo.setMovimentacao(movimentacao);
		movimentacaoResumo.setEnviadoBacen(Boolean.FALSE);
		movimentacaoResumo.setEnviadoEUA(Boolean.FALSE);
		
		repository.save(movimentacaoResumo);
	}

	public List<MovimentacaoResumo> consultarMovimentacaoResumoNaoEnviadoBacen() {
		return repository.consultarMovimentacaoResumoNaoEnviadoBacen();
	}

	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacen() {
		return repository.consultarMovimentacaoResumoEnviadoBacen();
	}

	public List<MovimentacaoResumo> consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA() {
		return repository.consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA();
	}

	public void enviarFilaBacen() {
		List<MovimentacaoResumo> lstMovimentacaoResumo = consultarMovimentacaoResumoNaoEnviadoBacen();
		for (MovimentacaoResumo movimentacaoResumo : lstMovimentacaoResumo) {
			ObjectMessage om = context.createObjectMessage();
			try {
				om.setObject(movimentacaoResumo);
				JMSProducer producer = context.createProducer();
				producer.send(destinationBacen, om);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void enviarFilaEUA() {
		List<MovimentacaoResumo> lstMovimentacaoResumo = consultarMovimentacaoResumoEnviadoBacenNaoEnviadoEUA();
		for (MovimentacaoResumo movimentacaoResumo : lstMovimentacaoResumo) {
			ObjectMessage om = context.createObjectMessage();
			try {
				om.setObject(movimentacaoResumo);
				JMSProducer producer = context.createProducer();
				producer.send(destinationEUA, om);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}