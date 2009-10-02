package org.nrg.dian.qc.functional;

import groovy.util.GroovyTestCase;
import groovyx.net.http.HTTPBuilder;
import groovyx.net.http.HttpResponseDecorator;

import org.nrg.dian.qc.http.HttpClient;
import org.nrg.dian.qc.http.HttpFactory;

import static groovyx.net.http.ContentType.XML
class GetProjectInformation extends GroovyTestCase {
	def http
	
	void setUp() {
		def factory = new HttpFactory(baseUrl: "http://localhost:8380/cnda_xnat/", 
		username: "admin", password: "admin") 
		http = new HttpClient(httpFactory: factory)
	}
	
	void testLookup(){
		def session_id = "0000202_MR1"
		def response = http.get("REST/experiments?format=xml&xsiType=xnat:mrSessionData&project=DIAN_*&label=${session_id}&columns=ID,subject_ID,label,project,date")
		println response.data.results
	}
	
//	void testGoogle(){
//		def http = new HTTPBuilder('http://localhost:8380/cnda_xnat/')
//		http.auth.basic ("admin", "admin")
//		
//		def html = http.get( path : 'REST/experiments?format=xml&xsiType=xnat:mrSessionData&project=DIAN_*&label=${session_id}&columns=ID,subject_ID,label,project,date', 
//				contentType : XML)
//		println html
//		
//	}
	
}