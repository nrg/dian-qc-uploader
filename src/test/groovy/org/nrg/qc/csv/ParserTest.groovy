package org.nrg.qc.csv;

import groovy.util.GroovyTestCase;
import au.com.bytecode.opencsv.CSVReader;

//import static org.mockito.Mockito.*


class ParserTest extends GroovyTestCase {
	
	Parser parser
	def file
	
	void setUp() throws Exception {
		parser = new Parser();
		//file = mock(FileReader.class)
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
		def reader = makeReader("a,b,c,d\n1,2,3,4")
		
		assertSize(1, parser.parseCSV(reader))
	}
	void testMultipleDataRows(){
		def reader = makeReader('a,b,c,d\n1,2,3,4\nr,r,"r",r')
		
		assertSize(2, parser.parseCSV(reader))
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