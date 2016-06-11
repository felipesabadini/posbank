package br.com.rp.domain;

import org.junit.Test;

public class CpfTest {

	@Test(expected=RuntimeException.class)
	public void deveLancarUmaException() {
		new Cpf("12312");
	}
		
}
