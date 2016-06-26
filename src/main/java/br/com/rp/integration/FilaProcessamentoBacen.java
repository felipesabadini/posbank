package br.com.rp.integration;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.com.rp.domain.MovimentacaoResumo;
import br.com.rp.repository.MovimentacaoResumoRepository;
/*
@MessageDriven(activationConfig = {
@ActivationConfigProperty(propertyName = "destinationType",
		propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "destinationLookup",
propertyValue = "jms/EnvioBacen")
})*/
public class FilaProcessamentoBacen /*implements MessageListener*/ {

	@EJB
	private MovimentacaoResumoRepository movimentacaoResumoRepository;
	
	//private static Logger logger = Logger.getLogger(FilaProcessamentoBacen.class.getName());
	
	/*public void onMessage(Message message) {
		try {
			ObjectMessage om = (ObjectMessage) message;
			Object o = om.getObject();
			MovimentacaoResumo movimentacaoResumo = (MovimentacaoResumo) o;
			
			enviaInformacaoBacen(movimentacaoResumo);
			movimentacaoResumo.setEnviadoBacen(Boolean.TRUE);
			movimentacaoResumoRepository.save(movimentacaoResumo);
		} catch (JMSException ex) {
			//logger.log(Level.SEVERE, null, ex);
		}		
	}

	private void enviaInformacaoBacen(MovimentacaoResumo movimentacaoResumo){
		//Realiza o envio das informações necessárias, caso der erro, levanta exceção
		System.out.println("PASSOU POR AQUI:" + movimentacaoResumo);
	}*/
}
