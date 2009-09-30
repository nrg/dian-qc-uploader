package org.nrg.dian.qc.http;


import groovy.util.GroovyTestCase;

class HttpClientTest extends GroovyTestCase {
	private static final String SAMPLE_PATH = "/REST/assessment"
	private static final String SAMPLE_DOCUMENT = "<doc>hello</doc>"
	
	def http
	def resource
	
	void setUp(){
		http = new Expando()
		
		resource = new HttpClient(httpFactory: ["connect": { http }])
	}
	
	void testPost(){
		http.post = { x, y ->
			assertEquals(SAMPLE_PATH, x.path)
			assertEquals(SAMPLE_DOCUMENT, x.body)
			assertEquals("text/plain", x.requestContentType.toString())
		}
		
		def response = resource.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
	}	
	
	void testPostSuccess(){
		http.post = { x, y ->
			y(['status': 200])
		}
		
		def response = resource.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
		assertEquals(200, response.status)
	}
	
	void testPostFailure(){
		http.post = { x, y ->
			y(['status': 404])
		}
		
		def response = resource.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
		assertEquals(404, response.status)
	}
	
	void testPut(){
		http.put = { x, y ->
			assertEquals(SAMPLE_PATH, x.path)
			assertEquals(SAMPLE_DOCUMENT, x.body)
			assertEquals("text/plain", x.requestContentType.toString())
		}
		
		def response = resource.put(SAMPLE_PATH, SAMPLE_DOCUMENT)
	}	
	
	void testDelete(){
		http.delete = { x, y ->
			assertEquals(SAMPLE_PATH, x.path)
			y(['status': 200])
		}
		
		def response = resource.delete(SAMPLE_PATH)
		assertEquals(200, response.status)
	}
	
	
	void testGet(){
		http.get = { x, y ->
			assertEquals(SAMPLE_PATH, x.path)
			y(['status': 200])
		}
		
		def response = resource.get(SAMPLE_PATH)
		assertEquals(200, response.status)
	}
}
