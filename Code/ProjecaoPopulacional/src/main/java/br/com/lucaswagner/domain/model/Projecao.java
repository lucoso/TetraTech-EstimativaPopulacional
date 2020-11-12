package br.com.lucaswagner.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Classe que representa a lista de Projecao dentro da classe ProjecaoAtual
 * 
 * Geralmente não costumo fazer classes internas, porém como essa classe apenas existe para representar um JSON de resposta de uma
 * API externa, resolvi fazer.
 * @author Lucas Manhães
 *
 */
public class Projecao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal populacao;
	
	private List<PeriodoMedio> periodoMedio;
	
	public static class PeriodoMedio {
		
		private String incrementoPopulacional;
		
		private String nascimento;
		
		private String obito;

		public String getIncrementoPopulacional() {
			return incrementoPopulacional;
		}

		public void setIncrementoPopulacional(String incrementoPopulacional) {
			this.incrementoPopulacional = incrementoPopulacional;
		}

		public String getNascimento() {
			return nascimento;
		}

		public void setNascimento(String nascimento) {
			this.nascimento = nascimento;
		}

		public String getObito() {
			return obito;
		}

		public void setObito(String obito) {
			this.obito = obito;
		}
		
	}

	public BigDecimal getPopulacao() {
		return populacao;
	}

	public void setPopulacao(BigDecimal populacao) {
		this.populacao = populacao;
	}

	public List<PeriodoMedio> getPeriodoMedio() {
		return periodoMedio;
	}

	public void setPeriodoMedio(List<PeriodoMedio> periodoMedio) {
		this.periodoMedio = periodoMedio;
	}
	
	

}
