package br.com.rp.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class Email implements Serializable {
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
