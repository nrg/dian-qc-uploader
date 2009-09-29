package org.nrg.dian.qc.http;

import groovy.util.GroovyTestCase;

class HttpFactoryTest extends GroovyTestCase {
	HttpFactory factory
	
	void setUp() throws Exception {
		factory = new HttpFactory(baseUrl:"http://localhost", username:"scott", password:"tiger")
	}
	
	void testConnect(){
		def resource = factory.connect()
		assertNotNull(resource)
		assertTrue(resource instanceof HttpResource)
		assertEquals("http://localhost", resource.httpClient.uri.toString())
		assertNotNull(resource.httpClient.auth)
	}
}
