package org.nrg.qc.http;


import groovy.util.GroovyTestCase;

class HttpResourceTest extends GroovyTestCase {
	
	void setUp(){
	}
	
	void testExecute(){
		def post = new HttpResource()
		assert post.execute() == "hello"
	}
}