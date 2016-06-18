package br.com.rp.domain;

public enum TipoConta {
	CC("CONTA_CORRENTE"),CP("CONTA_POUPANCA");
	
	private final String tipoConta;
	
	private TipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public String getTipoConta() {
		return tipoConta;
	}
	
	public String toString() {
		return this.tipoConta;
	}
}