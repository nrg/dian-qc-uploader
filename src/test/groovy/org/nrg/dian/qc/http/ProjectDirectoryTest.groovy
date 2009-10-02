package org.nrg.dian.qc.http;

import org.nrg.dian.qc.model.SessionAssessment;

import groovy.util.GroovyTestCase;

class ProjectDirectoryTest extends GroovyTestCase {
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
	
	ProjectDirectory directory
	def http
	
	void setUp() throws Exception {
		http = new Expando()
		directory = new ProjectDirectory([http: http])
	}
	
	void testAddProjectDetails(){
		http.get = { 
			return ["status": 200, "data": toXml(SAMPLE_XML)]
		}
		
		SessionAssessment session = new SessionAssessment(["session_id": "0003814_MR1"]);
		directory.addProjectDetails(session)
		
		assertEquals("DIAN_941", session.project)
		assertEquals("CNDA_E01861", session.system_session_id)
		assertEquals("CNDA_S00515", session.subject_id)		
	}
	
	void testQuery(){
		http.get = { path ->
			assertEquals("REST/experiments?format=xml&xsiType=xnat:mrSessionData&project=DIAN_*&label=ABC&columns=ID,subject_ID,label,project,date", path)
			return ["status": 200, "data": toXml(SAMPLE_XML)]
		}
		
		def response = directory.query("ABC")
		assertEquals(toXml(SAMPLE_XML), response)
	}
	
	
	void testQueryError(){
		http.get = { path ->
			assertEquals("REST/experiments?format=xml&xsiType=xnat:mrSessionData&project=DIAN_*&label=ABC&columns=ID,subject_ID,label,project,date", path)
			return ["status": 404, "data": toXml("<ResultSet></ResultSet>")]
		}
		
		shouldFail { directory.query("ABC") }
	}
	
	void testTranslate(){
		def result = directory.translate(toXml(SAMPLE_XML))
		
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
		
		shouldFail { directory.translate(toXml(xml)) }
	}
	
	def toXml(data){
		return new XmlSlurper().parseText(data)
	}
}