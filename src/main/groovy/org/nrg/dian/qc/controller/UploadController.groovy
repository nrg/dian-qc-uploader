package org.nrg.dian.qc.controller;

import org.springframework.context.ApplicationContext;
import org.nrg.dian.qc.cli.CommandLine;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UploadController {
	private static ApplicationContext SPRING_CONTEXT
	
	def run(options){
		// parse the files
		def csvParser = getCsvParser()
		csvParser.parse(options.qualityfile)
		csvParser.parse(options.inclusionfile)
		csvParser.parse(options.protocolfile)
		// group into sessions
		// for each session
		//		find session ID from REST
		//		build XML for session & scans
		// 		submit XML to REST API
	}

	def get
	
	def getCsvParser(){
		return getBean("csvParser")
	}
	
	def getBean(String bean){
		return getContext().getBean(bean)
	}
	
	def getContext(){
		if (SPRING_CONTEXT == null){
			SPRING_CONTEXT = new ClassPathXmlApplicationContext("applicationContext.xml")
		}
		return SPRING_CONTEXT
	}
	
	static void main(String[] args) {
		// get command line args (server (port), username/password, file names, project name)
		def options = new CommandLine().parse(args)
		new UploadController().run(options)
	}
}