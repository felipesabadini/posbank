package br.com.rp.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Email {
	private String value;
	
	public Email(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
}
