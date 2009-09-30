package org.nrg.dian.qc.controller;

import org.springframework.context.ApplicationContext;
import org.nrg.dian.qc.cli.CommandLine;
import org.nrg.dian.qc.csv.CsvParser;
import org.nrg.dian.qc.csv.DataMapper;
import org.nrg.dian.qc.http.HttpClient;

class UploadController {
	private static ApplicationContext SPRING_CONTEXT
	
	def run(options){
		HttpClient http = getHttpClient(options)
		def assessments = map(options.qualityfile, options.inclusionfile)
		assessments.each { assessment ->
			//get project id
			http.get()
			//assessment.project = options.projectid
			println assessment.toXml()
			//			http.post(path, assessment.toXml())
		}
	}
	
	def map(qualityFile, inclusionFile){
		def csvParser = getCsvParser()
		def qualityRecords = csvParser.parse(qualityFile)
		def inclusionRecords = csvParser.parse(inclusionFile)
		
		return getDataMapper().map(qualityRecords, inclusionRecords)
	}
	
	def getDataMapper(){
		return new DataMapper() 
	}
	
	def getHttpClient(options){
		return HttpFactory(baseUrl: options.server, username: options.user, password: options.password)
	}
	
	def getCsvParser(){
		return new CsvParser()
	}
	
	static void main(String[] args) {
		// get command line args (server (port), username/password, file names, project name)
		def options = new CommandLine().parse(args)
		
		new UploadController().run(options)
	}
}