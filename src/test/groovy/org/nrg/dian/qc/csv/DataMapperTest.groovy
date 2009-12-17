package org.nrg.dian.qc.csv;

import org.nrg.dian.qc.csv.DataMapper;

import groovy.util.GroovyTestCase;

class DataMapperTest extends GroovyTestCase {
	DataMapper mapper
	
	void setUp() {
		mapper = new DataMapper()
	}
	
	void testFilter(){
		def records = [record("56", "1")]
		def expected = records
		assertEquals(expected, mapper.filter("56", records))
	}
	
	void testFilterNotInList(){
		def records = [record("56", "1")]
		def expected = []
		assertEquals(expected, mapper.filter("700", records))
	}
	
	void testFilterMultiple(){
		def records = [record("56", "1"),
                	   record("700", "2"),
                	   record("56", "3")]
		def expected = records[0,2]
		assertEquals(expected, mapper.filter("56", records))
	}
	
	void testScoreRegularValue(){
		assertEquals("2", mapper.score("2"))
	}
	
	void testScoreRegularValuePadded(){
		assertEquals("1", mapper.score(" 1 "))
	}
	
	void testScoreZero(){
		assertEquals("0", mapper.score("0"))
	}
	
	void testScoreEmpty(){
		assertEquals("0", mapper.score(" "))
	}
	
	void testScoreNegativeOne(){
		assertNull(mapper.score("-1"))
	}
	
	private def record(patientid, seriesnumber){
		return ["patid": patientid, "seriesnumber": seriesnumber]
	}
}