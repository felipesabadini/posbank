package br.com.rp.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Email {
	private String value;
	
	public Email() {}
	
	public Email(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		return value;
	}
}
