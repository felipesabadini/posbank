package br.com.rp.domain;

public enum SituacaoProposta {
	AC("aceita"),REC("recebida"),REG("rejeitada");

	private final String SituacaoProposta;
	
	private SituacaoProposta (String situacaoProposta){
		this.SituacaoProposta=situacaoProposta;
	}
	
	
	private String getSituacaoProposta(){
		return SituacaoProposta;
	};
	
	public String toString(){
		return this.SituacaoProposta;
	}
	
}
