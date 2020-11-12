package br.com.lucaswagner.api.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.lucaswagner.domain.exception.EntidadeNaoEncontradaException;
import br.com.lucaswagner.domain.exception.ErroDoUsuarioException;
import br.com.lucaswagner.domain.model.ProjecaoAtual;
import br.com.lucaswagner.domain.model.ProjecaoLog;
import br.com.lucaswagner.domain.service.ProjecaoService;
import br.com.lucaswagner.domain.util.JavaUtil;

@RestController
@RequestMapping("/projecoes")
public class ProjecaoController {

	@Autowired
	private ProjecaoService projecaoService;
	
	/**
	 * Métodos
	 * @author Lucas Manhães
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	
	@GetMapping("/{dataFutura}")
	public ResponseEntity<?> EstimativaPopulacional(@PathVariable String dataFutura) throws JsonMappingException, JsonProcessingException{
		
		boolean b = JavaUtil.ValidarData(dataFutura);
		
		if(b == false) {
			throw new ErroDoUsuarioException("Data no formato Errado");
		}
		
		LocalDateTime futuro = JavaUtil.ConverterParaData(dataFutura);
		ProjecaoAtual pa = projecaoService.BuscarParametrosIBGE();
		BigDecimal projecaoEstimada = pa.CalcularProjecaoPopulacional(futuro);
		
		String dataatual = String.valueOf(LocalDateTime.now());
		
		ProjecaoLog plog = new ProjecaoLog();
		plog.setDataChamada(dataatual);
		plog.setDataSolicitacao(dataFutura);
		plog.setPopulacaoEstimadaAtual(pa.getProjecao().get(0).getPopulacao());
		plog.setPopulacaoProjetada(projecaoEstimada);
		
		projecaoService.GerarLog(plog);
		
		return ResponseEntity.status(200).body(plog);
		
	}
	
	@GetMapping
	public ResponseEntity<?> RelatorioChamadasAPI() throws JsonParseException, JsonMappingException, IOException{
		
		List<ProjecaoLog> relatorio = new ArrayList<ProjecaoLog>();
		List<ProjecaoLog> projecoes = projecaoService.BuscarLogsParaRelatorio();
		
		if(projecoes.isEmpty()){
			throw new EntidadeNaoEncontradaException("Ainda Não Existe nenhum Log Gravado");
		}
		
		if(projecoes.size() < 10) {
			relatorio = projecoes;
		
		}else {
			
			int init = projecoes.size() - 10;
			
			for(ProjecaoLog pl : projecoes) {
				if((pl.getId() > init) && (pl.getId() <= projecoes.size())){
					relatorio.add(pl);
				}
			}
		}
		
		return ResponseEntity.status(200).body(relatorio);
		
	}
}
