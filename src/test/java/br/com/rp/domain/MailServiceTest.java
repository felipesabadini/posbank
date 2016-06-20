package br.com.rp.domain;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.services.EmailService;

public class MailServiceTest {

	@Test()
	public void consegueEnviarEmailTeste() throws MessagingException{
		
		EmailService service = new EmailService();
		service.enviarEmail("williammaximo@hotmail.com.br", "Informativo", "Não havera aulas :D");
		
		Assert.assertTrue(true);
	}

}
