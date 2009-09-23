package org.nrg.qc.cli;

import groovy.util.CliBuilder;

class CommandLine {
	private CliBuilder cli
	
	CommandLine() {
		cli = new CliBuilder(usage: 'groovy uploadqc -s <server> -u <user> -p <password> <qualityfile> <inclusionfile> <protocolfile>')
		
		cli.with {
			h longOpt: 'help', 'Show usage information'
			s longOpt: 'server', args: 1, argName: 'server', required: true, 'XNAT server URL, including protocol and port (if necessary)'
			u longOpt: 'user', args: 1, argName: 'user', required: true, 'XNAT username'
			p longOpt: 'password', args: 1, argName: 'password', required: true, 'XNAT password'
		}
	}
	
	def parse(args){
		def options = cli.parse(args)
		if (!options || options.h) {
			error()
		}
		
		def extraArguments = options.arguments()
		if (!extraArguments || extraArguments.size() != 3) {
			error()
		}
		
		return ["server": options.s, "user": options.u, "password": options.p, 
		        "qualityfile":extraArguments[0], "inclusionfile":extraArguments[1],
		        "protocolfile":extraArguments[2]]
	}
	
	private def error(){
		cli.usage()
		System.exit(0)
	}
}