package org.nrg.qc.csv;
import java.io.FileReader;
import org.nrg.qc.model.Session;

import au.com.bytecode.opencsv.CSVReader;


class Parser {
	
	List<Session> parse(String fileName){
		return parseCVS(new CSVReader(new FileReader(fileName)))
	}
	
	List<Session> parseCSV(CSVReader csvReader){
		def sessions = []
		List<String[]> lines = csvReader.readAll()
		if (lines.size() >= 2){
			def header = lines[0]
			def body = lines[1..-1]
			body.each { sessions.add(it) }
		}
		return sessions
	}
	
	def translateHeader(csvHeader){
		
	}
}