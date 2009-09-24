package org.nrg.dian.qc.cli;

import groovy.util.GroovyTestCase;

class CommandLineTest extends GroovyTestCase {
	CommandLine cli
	
	void setUp() {
		cli = new CommandLine()
	}
	
	void testParse(){
		def options = cli.parse(["-s", "http://cnda.wustl.edu:8000", 
		                         "-u", "scott", "-p", "tiger", "project1",
		                         "quality.csv", "inclusion.csv", "protocol.csv"])

		assertEquals("http://cnda.wustl.edu:8000", options.server)
		assertEquals("scott", options.user)
		assertEquals("tiger", options.password)
		assertEquals("project1", options.projectid)
		assertEquals("quality.csv", options.qualityfile)
		assertEquals("inclusion.csv", options.inclusionfile)
		assertEquals("protocol.csv", options.protocolfile)
	}
}