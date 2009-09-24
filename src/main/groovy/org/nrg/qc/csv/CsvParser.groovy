package org.nrg.qc.csv;

import au.com.bytecode.opencsv.CSVReader;


class Parser {
	
	List<Map<String, String>> parse(String fileName){
		return parseCSV(new CSVReader(new FileReader(fileName)))
	}
	
	List<Map<String, String>> parseCSV(CSVReader csvReader){
		def records = []
		List<String[]> lines = csvReader.readAll()
		if (lines.size() >= 2){
			def header = lines[0]
			def body = lines[1..-1]
			body.each {row -> 
				def map = [:]
				row.eachWithIndex { elem, i -> map[header[i]] = elem } 
				records.add map
			}
		}
		return records
	}
}