package org.nrg.qc.csv;

import groovy.util.GroovyTestCase;

class MergerTest extends GroovyTestCase {
	Merger merger
	
	void setUp() {
		merger = new Merger()
	}
	
	void testUniqueIdSingleRecord(){
		def records = [record("56", "20091025", "1")]
		def expected = [["patid":"56", "sdate":"20091025"]] 
		assertEquals(expected, merger.uniqueId(records))
	}
	
	void testUniqueIdMutipleIdsSingleRecords(){
		def records = [record("56", "20091025", "1"),
		record("56", "20091026", "1")]
		def expected = [["patid":"56", "sdate":"20091025"], ["patid":"56", "sdate":"20091026"]] 
		assertEquals(expected, merger.uniqueId(records))
	}
	
	
	void testUniqueIdSingleIdMultipleRecords(){
		def records = [record("56", "20091025", "1"),
		record("56", "20091025", "2")]
		def expected = [["patid":"56", "sdate":"20091025"]] 
		assertEquals(expected, merger.uniqueId(records))
	}
	
	void testUniqueIdMutipleIdsMultipleRecords(){
		def records = [record("56", "20091025", "1"),
		record("56", "20091025", "2"),
		record("700", "20091025", "1"),
		record("700", "20091025", "13")]
		def expected = [["patid":"56", "sdate":"20091025"], ["patid":"700", "sdate":"20091025"]] 
		assertEquals(expected, merger.uniqueId(records))
	}
	
	void testFilter(){
		def records = [record("56", "20091025", "1")]
		def expected = records
		assertEquals(expected, merger.filter(["patid": "56", "sdate": "20091025"], records))
	}
	
	void testFilterNotInList(){
		def records = [record("56", "20091025", "1")]
		def expected = []
		assertEquals(expected, merger.filter(["patid": "700", "sdate": "20091025"], records))
	}
	
	void testFilterMultiple(){
		def records = [record("56", "20091025", "1"),
		record("700", "20091025", "2"),
		record("56", "20091025", "3"),
		record("56", "20091026", "4")]
		def expected = records[0,2]
		assertEquals(expected, merger.filter(["patid": "56", "sdate": "20091025"], records))
	}
	
	void testScoreRegularValue(){
		assertEquals("2", merger.score("2"))
	}
	
	void testScoreRegularValuePadded(){
		assertEquals("1", merger.score(" 1 "))
	}
	
	void testScoreZero(){
		assertEquals("0", merger.score("0"))
	}
	
	void testScoreEmpty(){
		assertEquals("0", merger.score(" "))
	}
	
	void testScoreNegativeOne(){
		assertNull(merger.score("-1"))
	}
	
	
	
	private def record(patid, sdate, seriesnumber){
		return ["patid": patid, "sdate": sdate, "seriesnumber": seriesnumber]
	}
}