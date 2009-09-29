package org.nrg.dian.qc.http;

import groovy.util.GroovyTestCase;
import groovyx.net.http.HTTPBuilder;

class HttpFactoryTest extends GroovyTestCase {
	HttpFactory factory
	
	void setUp() throws Exception {
		factory = new HttpFactory(baseUrl:"http://localhost", username:"scott", password:"tiger")
	}
	
	void testConnect(){
		def http = factory.connect()
		assertNotNull(http)
		assertTrue(http instanceof HTTPBuilder)
		assertEquals("http://localhost", http.uri.toString())
		assertNotNull(http.auth)
	}
}
