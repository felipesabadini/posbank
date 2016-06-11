package br.com.rp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Proposta extends BaseEntity {
	
	private Timestamp dataCadastro;
	
	private Cliente cliente;
	
	private BigDecimal rendimento;
	
	private Funcionario funcionario;
	
	private String mensagem;
	
	private Timestamp dataAvaliacao;
	
	private SituacaoProposta situacao;

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getRendimento() {
		return rendimento;
	}

	public void setRendimento(BigDecimal rendimento) {
		this.rendimento = rendimento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Timestamp getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Timestamp dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public SituacaoProposta getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoProposta situacao) {
		this.situacao = situacao;
	}
}