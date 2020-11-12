package br.com.lucaswagner.domain.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucaswagner.domain.exception.EntidadeNaoEncontradaException;
import br.com.lucaswagner.domain.model.ProjecaoAtual;
import br.com.lucaswagner.domain.model.ProjecaoLog;
import br.com.lucaswagner.domain.util.ArquivoUtil;

@Service
public class ProjecaoService {
	
	public ProjecaoAtual BuscarParametrosIBGE() throws JsonMappingException, JsonProcessingException {
		
		RestTemplate template = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		ProjecaoAtual pa = new ProjecaoAtual();
		
		try {
			
			ResponseEntity<String> response = template.getForEntity("https://servicodados.ibge.gov.br/api/v1/projecoes/populacao", String.class);
			String json = response.getBody();
			pa = objectMapper.readValue(json, ProjecaoAtual.class);
			
			if(response.getStatusCodeValue() != 200) {
				throw new EntidadeNaoEncontradaException("Dados da API do IBGE não retornou resultado!");
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Erro ao Trazer os dados da API do IBGE");
		}
		
		return pa;
	}
	
	public void GerarLog(ProjecaoLog pl) {
		
		ArquivoUtil.SalvarArquivoNaPasta(pl);
	}
	
	public List<ProjecaoLog> BuscarLogsParaRelatorio() throws JsonParseException, JsonMappingException, IOException {
		
		return ArquivoUtil.LerLogs();
	}
}
