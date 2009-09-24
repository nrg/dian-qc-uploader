package org.nrg.qc.model;

import groovy.util.GroovyTestCase;

public class ModelXmlTestCase extends GroovyTestCase {
	def writer
	def expected
	
	protected void setUp() throws Exception {
		writer = new StringWriter()
		expected = new groovy.xml.MarkupBuilder(writer)
	}

	void assertExpectedXml(xml) {
		assertEquals(getExpectedXml(), xml)
	}
	
	def getExpectedXml(){
		return writer.toString()
	}
}