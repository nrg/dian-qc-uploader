package org.nrg.qc.http;

import groovy.util.GroovyTestCase;

class HttpFactoryTest extends GroovyTestCase {
	HttpFactory factory
	
	void setUp() throws Exception {
		factory = new HttpFactory()
	}
	
	void testConnect(){
		def resource = factory.connect("http://localhost", "scott", "tiger")
		assertNotNull(resource)
		assertTrue(resource instanceof HttpResource)
		assertEquals("http://localhost", resource.httpClient.uri.toString())
		assertNotNull("scott", resource.httpClient.auth)
	}
}