package com.employe.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import com.employe.Entity.Employe;

public class Csvhelper {
	
	public static String TYPE = "text/csv";
	static String[] HEADERs = {"id","address","email","name","phonenumber","salary"};
	
	
	public static boolean hasCSVFormat(MultipartFile file) {
		if(!TYPE.equals(file.getContentType())) {
			return false;
		}
		
		return true;
	}
	
	public static List<Employe> csvToEmploye(InputStream is){
		
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				
			CSVParser csvpraser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			
			List<Employe> employes = new ArrayList<Employe>();
			
			Iterable<CSVRecord> csvRecords = csvpraser.getRecords();
			
			for (CSVRecord csvRecord: csvRecords) {
				Employe employe = new Employe(
						Integer.parseInt(csvRecord.get("id")),
						csvRecord.get("address"),
						csvRecord.get("email"),
						csvRecord.get("name"),
						Long.parseLong(csvRecord.get("phonenumber")),
						Float.parseFloat(csvRecord.get("salary")));
					
				employes.add(employe);
			}
			return employes;
			
		}catch (Exception e) {
		throw new RuntimeException("fail to prase CSV file: "+ e.getMessage());
		}
	}
	
	public static ByteArrayInputStream employeesToCSV(List<Employe> employes) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		
		
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);){
			 for(Employe employe : employes) {
				 List<String> data = Arrays.asList(
						 String.valueOf(employe.getId()),
						 employe.getName(),
						 employe.getAddress(),
						 employe.getEmail(),
						 String.valueOf(employe.getPhonenumber()),
						 String.valueOf(employe.getSalary())
						 );
				 csvPrinter.printRecord(data);
			 }
			 csvPrinter.flush();
			 return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to csv file: " +e.getMessage());
		}
	}
	
	
}
	  
	  
	