package br.com.lucaswagner.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.lucaswagner.domain.util.JavaUtil;

/**
 * Classe que representa o JSON de resposta da API do IBGE
 * @author Lucas Manhães
 *
 */
public class ProjecaoAtual implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String localidade;

	private String horario;

	private List<Projecao> projecao;

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public List<Projecao> getProjecao() {
		return projecao;
	}

	public void setProjecao(List<Projecao> projecao) {
		this.projecao = projecao;
	}
	
	public BigDecimal CalcularProjecaoPopulacional(LocalDateTime dataFutura) {

		LocalDateTime dataHoraAtual = JavaUtil.ConverterParaData(horario);
		int incremento = 0;
		BigDecimal populacaoAtual = new BigDecimal(0);
		BigDecimal projecaoPopulacional = new BigDecimal(0);

		try {
			
			for (Projecao p : projecao) {
				String str = p.getPeriodoMedio().get(0).getIncrementoPopulacional();
				incremento = Integer.valueOf(str);
				populacaoAtual = p.getPopulacao();
			}

			int incrementoEmSegundos = incremento / 1000;

			Long diferenca = dataHoraAtual.until(dataFutura, ChronoUnit.SECONDS);
			int difInt = Integer.valueOf(diferenca.toString());

			int qntdPessoasAMais = difInt / incrementoEmSegundos;

			projecaoPopulacional = populacaoAtual.add(new BigDecimal(qntdPessoasAMais));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return projecaoPopulacional;

	}
}
