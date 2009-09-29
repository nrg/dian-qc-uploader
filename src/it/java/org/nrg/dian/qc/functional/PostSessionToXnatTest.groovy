package org.nrg.dian.qc.functional;

import org.nrg.dian.qc.model.SessionAssessment;
import groovy.util.GroovyTestCase;

import org.nrg.dian.qc.http.HttpFactory;
import org.nrg.dian.qc.http.HttpClient;
import static groovyx.net.http.ContentType.TEXT;

class PostSessionToXnatTest extends GroovyTestCase {
	SessionAssessment session
	//	private static final Logger LOGGER = Logger.getLogger(PostSessionToXnatTest.class)
	
	
	void setUp() throws Exception {
		session = new SessionAssessment()
		
		// Customize these variables
		session.project = "Demo"
		session.id = "jp_assess_2"
		session.session_id = "XNATDev_S00001"
	}
	
	void tearDown(){
		def response = http.delete("/xnat/REST/projects/${session.project}/subjects/${session.session_id}/experiments/SampleID/assessors", session.toXml())
	}
	
	void testPost(){
		def factory = new HttpFactory()
		HttpClient http = factory.connect("http://localhost:8080", "admin", "admin")
		def response = http.post("/xnat/REST/projects/${session.project}/subjects/${session.session_id}/experiments/SampleID/assessors", session.toXml())
		assertEquals(200, response.status)
	}
	
}