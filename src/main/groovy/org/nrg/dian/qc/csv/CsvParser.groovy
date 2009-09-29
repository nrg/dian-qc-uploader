package org.nrg.dian.qc.csv;

import au.com.bytecode.opencsv.CSVReader;


class CsvParser {
	
	List<Map<String, String>> parse(String fileName){
		return parseCSV(new CSVReader(new FileReader(fileName)))
	}
	
	/**
	 * Converts the CSV file, in which the columns represent the metrics and the rows 
	 * represent the scans, into a list of maps.  Each map in this list represents 
	 * one row from the file.  The map is keyed by the column header and holds the 
	 * value for that row at that column.
	 */
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