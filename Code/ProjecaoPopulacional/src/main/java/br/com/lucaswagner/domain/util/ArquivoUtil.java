package br.com.lucaswagner.domain.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucaswagner.domain.model.ProjecaoLog;

/**
 * Classe Contendo os métodos para manipulação dos arquivos de Log gerados.
 * Todos os Logs estão sendo gravados numa pasta local de nome "SistemaProjeçãoPopulacional", que é criada automaticamente caso não exista, na partição "C"
 * de onde a aplicação estiver rodando.
 * 
 * @author Lucas Manhães
 */
public class ArquivoUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String FOLDER = File.separator + "SistemaProjeçãoPopulacional" + File.separator;

	public static void SalvarArquivoNaPasta(ProjecaoLog pl) {

		String proximoLog = ProximoLog();
		String nome = "LOG-" + proximoLog + "-ProjeçãoPopulacional" + ".txt";

		try {
			
			pl.setId(Long.valueOf(proximoLog));
			Salvar(nome, pl);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Erro ao Salvar no Arquivo");
		}
	}
	
	public static List<ProjecaoLog> LerLogs() throws JsonParseException, JsonMappingException, IOException {
		
		List<ProjecaoLog> projecoes = new ArrayList<ProjecaoLog>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			File[] arquivos = BuscarTodosArquivos();
			
			for(File f : arquivos) {
				ProjecaoLog pl = objectMapper.readValue(new File(FOLDER + f.getName()), ProjecaoLog.class);
				projecoes.add(pl);
			
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Erro ao Ler os Logs do Diretório Local");
		}
		
		return projecoes;

	}

	private static File[] BuscarTodosArquivos() {
		
		File f = new File(FOLDER);
		if (!f.exists()) {
			f.mkdirs();
			f.setReadable(true);
			f.setWritable(true);
		}

		File arquivos[] = null;
		File diretorio = new File(FOLDER);

		try {
			arquivos = diretorio.listFiles();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Erro ao buscar os Arquivos de Log");
		}

		return arquivos;
	}

	private static void Salvar(String nome, ProjecaoLog pl) throws IOException {

		File f = new File(FOLDER);
		if (!f.exists()) {
			f.mkdirs();
			f.setReadable(true);
			f.setWritable(true);
		}

		Writer writer = null;

		try {
		
			ObjectMapper objectMapper = new ObjectMapper();

			String log = objectMapper.writeValueAsString(pl);
			
			writer = new BufferedWriter(new FileWriter(FOLDER + nome));
			writer.write(log);

		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Erro ao salvar o arquivo");
		} finally {
			writer.flush();
			writer.close();
		}
	}

	/**
	 * Método feito para buscar o número do proximo log a ser criado
	 * @return
	 */
	private static String ProximoLog() {

		File arquivos[] = BuscarTodosArquivos();
		List<Integer> numeros = new ArrayList<Integer>();
		String proximoLog = "0";
		
		if ((arquivos.length == 0) || (arquivos == null)){
			proximoLog = "1";

		} else {
			for(int i=0;i<arquivos.length;i++) {
				String separar[] = arquivos[i].getName().split("-");
				String numero = separar[1];
				int n = Integer.valueOf(numero);
				numeros.add(n);
			}
			
			Collections.sort(numeros);
			int nAtual = numeros.get(numeros.size() - 1);
			int prox = nAtual + 1;
			proximoLog = String.valueOf(prox);
		}

		return proximoLog;

	}
}
