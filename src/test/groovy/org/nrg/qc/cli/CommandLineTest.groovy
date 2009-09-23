package org.nrg.qc.cli;
import org.codehaus.groovy.runtime.StringBufferWriter;
import groovy.util.GroovyTestCase;

class CommandLineTest extends GroovyTestCase {
	CommandLine cli
	
	void setUp() {
		cli = new CommandLine()
	}
	
	void testParse(){
		def options = cli.parse(["-s", "http://cnda.wustl.edu:8000", 
		                         "-u", "scott", "-p", "tiger",
		                         "quality.csv", "inclusion.csv", "protocol.csv"])

		assertEquals("http://cnda.wustl.edu:8000", options.server)
		assertEquals("scott", options.user)
		assertEquals("tiger", options.password)
		assertEquals("quality.csv", options.qualityfile)
		assertEquals("inclusion.csv", options.inclusionfile)
		assertEquals("protocol.csv", options.protocolfile)
	}
}