package org.nrg.dian.qc.http;


import groovy.util.GroovyTestCase;

class HttpClientTest extends GroovyTestCase {
	private static final String SAMPLE_PATH = "/REST/assessment"
	private static final String SAMPLE_DOCUMENT = "<doc>hello</doc>"
	
	def http
	def client
	
	void setUp(){
		http = new Expando()
		
		client = new HttpClient(httpFactory: ["connect": { http }])
	}
	
	void testPost(){
		http.post = { 
			assertEquals(SAMPLE_PATH, it.path)
			assertEquals(SAMPLE_DOCUMENT, it.body)
			assertEquals("text/plain", it.requestContentType.toString())
		}
		
		client.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
	}	
	
	void testPostSuccess(){
		http.post = { return ['status': 200] }
		
		def response = client.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
		assertEquals(200, response.status)
	}
	
	void testPostFailure(){
		http.post = { return ['status': 404] }
		
		def response = client.post(SAMPLE_PATH, SAMPLE_DOCUMENT)
		assertEquals(404, response.status)
	}
	
	void testPut(){
		http.put = { 
			assertEquals(SAMPLE_PATH, it.path)
			assertEquals(SAMPLE_DOCUMENT, it.body)
			assertEquals("text/plain", it.requestContentType.toString())
		}
		
		client.put(SAMPLE_PATH, SAMPLE_DOCUMENT)
	}	
	
	void testDelete(){
		http.delete = { 
			assertEquals(SAMPLE_PATH, it.path)
			return ['status': 200]
		}
		
		def response = client.delete(SAMPLE_PATH)
		assertEquals(200, response.status)
	}
	
	
	void testGet(){
		http.get = { x ->
			assertEquals(SAMPLE_PATH, x.path)
			return ["status": 200, "data":"hello world"]
		}
		
		def response = client.get(SAMPLE_PATH)
		assertEquals("hello world", response.data)
		assertEquals(200, response.status)
	}
}
