package org.nrg.qc.http;


import groovy.util.GroovyTestCase;

class HttpResourceTest extends GroovyTestCase {
	private static final String SAMPLE_PATH = "/REST/assessment"
	private static final String SAMPLE_DOCUMENT = "<doc>hello</doc>"
	
	def http
	def resource
	
	void setUp(){
		http = [:]
		resource = new HttpResource(httpClient: http)
	}
	
	void testPost(){
		http.post = { x, y ->
			assertEquals(SAMPLE_PATH, x.path)
			assertEquals(SAMPLE_DOCUMENT, x.body)
		}
		
		resource.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
	}	
	
	void testPostSuccess(){
		http.post = { x, y ->
			y(['status': 200])
		}
		
		resource.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
	}
	
	void testPostFailure(){
		http.post = { x, y ->
			shouldFail({ y(['status': 404]) })
		}
		
		resource.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
	}
}