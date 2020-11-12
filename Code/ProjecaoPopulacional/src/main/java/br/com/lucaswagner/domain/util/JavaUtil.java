package br.com.lucaswagner.domain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaUtil {
	
	public static boolean ValidarData(String data) {
		
		 try {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	            sdf.setLenient(false);
	            sdf.parse(data);
	            return true;
	            
	        } catch (ParseException ex) {
	            return false;
	        }
	}
	
	public static LocalDateTime ConverterParaData(String data) {
		
		String str = data.replaceAll("-", "/");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime dateTime = null;
		try {
			dateTime =  LocalDateTime.parse(str, dtf);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Erro ao Converter a Data");
		}
		
		return dateTime;
	}

}
