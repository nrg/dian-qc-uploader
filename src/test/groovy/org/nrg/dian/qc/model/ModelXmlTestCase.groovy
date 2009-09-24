package org.nrg.dian.qc.model;

import groovy.xml.StreamingMarkupBuilder;

public abstract class ModelXmlTestCase extends GroovyTestCase {
	def expected
	def builder
	
	void setUp() throws Exception {
		builder = new StreamingMarkupBuilder()
	}
	
	void assertExpectedXml(expected, obj) {
		assertEquals(expected.toString(), obj.toXml())
	}
}