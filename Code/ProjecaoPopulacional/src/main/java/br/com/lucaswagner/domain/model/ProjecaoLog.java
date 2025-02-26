package br.com.lucaswagner.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProjecaoLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String dataChamada;
	
	private String dataSolicitacao;
	
	private BigDecimal populacaoEstimadaAtual;
	
	private BigDecimal populacaoProjetada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataChamada() {
		return dataChamada;
	}

	public void setDataChamada(String dataChamada) {
		this.dataChamada = dataChamada;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public BigDecimal getPopulacaoEstimadaAtual() {
		return populacaoEstimadaAtual;
	}

	public void setPopulacaoEstimadaAtual(BigDecimal populacaoEstimadaAtual) {
		this.populacaoEstimadaAtual = populacaoEstimadaAtual;
	}

	public BigDecimal getPopulacaoProjetada() {
		return populacaoProjetada;
	}

	public void setPopulacaoProjetada(BigDecimal populacaoProjetada) {
		this.populacaoProjetada = populacaoProjetada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjecaoLog other = (ProjecaoLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
