package br.com.rp.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Cpf {
	
	private String value;
	
	public Cpf(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
