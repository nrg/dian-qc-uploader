package org.nrg.dian.qc.csv;

import java.io.File;

import org.nrg.dian.qc.csv.Parser;

import groovy.util.GroovyTestCase;
import au.com.bytecode.opencsv.CSVReader;

class ParserTest extends GroovyTestCase {
	Parser parser
	def file
	
	void setUp() throws Exception {
		parser = new Parser();
	}
	
	void testEmptyFile(){
		def reader = makeReader("")
		
		assertEmpty(parser.parseCSV(reader))
	}
	
	void testNullReader(){
		shouldFail(NullPointerException.class, { parser.parseCSV(null) })
	}	
	
	void testNoHeader(){
		def reader = makeReader("1,2,3,4")
		
		assertEmpty(parser.parseCSV(reader))
	}
	
	void testSingleDataRow(){
		def reader = makeReader("a,b,c,d\n11,2,3,4")
		
		def result = parser.parseCSV(reader)
		assertSize(1, result)
		assertEquals(["a":"11", "b":"2", "c":"3", "d":"4"], result[0])
	}
	
	void testMultipleDataRows(){
		def reader = makeReader('a,b,c,d\n11,2,3,4\nr,r,"r",r')
		
		def result = parser.parseCSV(reader)
		assertSize(2, result)		
		assertEquals(["a":"11", "b":"2", "c":"3", "d":"4"], result[0])
		assertEquals(["a":"r", "b":"r", "c":"r", "d":"r"], result[1])
	}
	
	void testFileName(){
		def file = File.createTempFile("qcupload_parsing_test", ".csv") << 'y,z\n"test",2'
		
		def result = parser.parse(file.path)
		assertSize(1, result)
		assertEquals(["y":"test", "z":"2"], result[0])
	}
	
	private void assertEmpty(list){
		assertSize(0, list)
	}
	
	private void assertSize(int expected, list){
		assertNotNull(list)
		assertEquals(expected, list.size())
	}
	
	private CSVReader makeReader(data){
		return new CSVReader(new StringReader(data))
	}
	
}