package br.com.rp.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Cpf {
	
	private String value;
	
	public Cpf() {}
	
	public Cpf(String value) {
		if(isValid(value))
			this.value = value;
		else
			throw new RuntimeException("CPF invalido!");
	}
	
	public String getValue() {
		return this.value;
	}
	
	private Boolean isValid(String value) {		
		return value.matches("^(([0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2})|([0-9]{11}))$");
	}
	
	@Override
	public String toString() {
		return value;
	}
}
