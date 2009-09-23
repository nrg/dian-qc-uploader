package org.nrg.qc.xml;

import groovy.util.GroovyTestCase;

import org.custommonkey.xmlunit.XMLUnit;
import org.nrg.qc.model.Scan;
import org.nrg.qc.model.Session;

class DocumentBuilderTest extends GroovyTestCase {
	
	DocumentBuilder builder
	Session session
	
	void setUp() throws Exception {
		builder = new DocumentBuilder()
		session = new Session()
		
		//XMLUnit.ignoreWhitespace(true)
	}
	
	void testNoScans() {
		xml = builder.build(session)
	}
	
}