package org.nrg.dian.qc.http;

import groovy.util.GroovyTestCase;

class ProjectLookupTest extends GroovyTestCase {
	private static final String SAMPLE_XML = """
		<ResultSet totalRecords="1" title="Matching experiments">
			<results>
				<columns>
    				<column>xnat:mrsessiondata/id</column>
    				<column>subject_ID</column>
    				<column>ID</column>
    				<column>label</column>
    				<column>project</column>
    				<column>date</column>
    				<column>URI</column>
				</columns>
    			<rows>
    				<row>
    					<cell>CNDA_E01861</cell>
    					<cell>CNDA_S00515</cell>
    					<cell>CNDA_E01861</cell>
    					<cell>0003814_MR1</cell>
    					<cell>DIAN_941</cell>
    					<cell>2009-07-29</cell>
    					<cell>/REST/experiments/CNDA_E01861</cell>
    				</row>
    			</rows>
    		</results>
		</ResultSet>
		""" 
	
	ProjectLookup lookup
	def http
	
	void setUp() throws Exception {
		http = new Expando()
		lookup = new ProjectLookup([http: http])
	}
	
	void testQuery(){
		http.get = { path ->
			assertEquals("REST/experiments?format=xml&xsiType=xnat:mrSessionData&project=DIAN_*&label=ABC&columns=ID,subject_ID,label,project,date", path)
			return ["status": 200, "content": "<ResultSet></ResultSet>"]
		}
		
		def response = lookup.query("ABC")
		assertEquals("<ResultSet></ResultSet>", response)
	}
	
	void testQueryHttpError(){
		http.get = { path ->
			return ["status": 500, "content": "<ResultSet></ResultSet>"]
		}
		
		shouldFail { lookup.query("ABC") }
	}
	
	void testParse(){
		def result = lookup.parse(SAMPLE_XML)
		
		def expected = ["xnat:mrsessiondata/id": "CNDA_E01861", "subject_ID":"CNDA_S00515", 
				"ID":"CNDA_E01861", "label":"0003814_MR1", "project":"DIAN_941", 
				"date":"2009-07-29", "URI":"/REST/experiments/CNDA_E01861"]
		assertEquals(expected, result)
	}
	
	void testParseMultipleRows(){
		def xml = """<ResultSet><results>
		<columns>
			<column>xnat:mrsessiondata/id</column>
		</columns>
		<rows>
			<row><cell>CNDA_E01861</cell></row>
			<row><cell>CNDA_E01862</cell></row>
    	</rows>
    	</results></ResultSet>
		"""
		
		shouldFail { lookup.parse(xml) }
	}
	
	
}