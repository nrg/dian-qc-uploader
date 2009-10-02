package org.nrg.dian.qc.controller;

import org.nrg.dian.qc.http.ProjectDirectory;
import org.nrg.dian.qc.http.HttpFactory;
import org.nrg.dian.qc.cli.CommandLine;
import org.nrg.dian.qc.csv.CsvParser;
import org.nrg.dian.qc.csv.DataMapper;
import org.nrg.dian.qc.http.HttpClient;

class UploadController {
	
	def run(options){
		def http = getHttpClient(options)
		println "Reading and parsing the CSV files: ${options.qualityfile} and ${options.inclusionfile}"
		def sessions = map(options.qualityfile, options.inclusionfile)
		println "Finished parsing and combining the CSV files."
		sessions.each { session ->
			println "Looking up project information for ${session.session_id}"
			getProjectDirectory(http).addProjectDetails(session)
			println "Found ${session.session_id} in ${session.project}"
			println session.toXml()
			//			http.post(path, session.toXml())
		}
	}
	
	def map(qualityFile, inclusionFile){
		def csvParser = getCsvParser()
		def qualityRecords = csvParser.parse(qualityFile)
		def inclusionRecords = csvParser.parse(inclusionFile)
		
		return getDataMapper().map(qualityRecords, inclusionRecords)
	}
	
	def getProjectDirectory(http){
		return new ProjectDirectory([http: http])
	}
	
	def getDataMapper(){
		return new DataMapper() 
	}
	
	def getHttpClient(options){
		def factory = new HttpFactory(baseUrl: options.server, username: options.user, password: options.password) 
		return new HttpClient(httpFactory: factory)
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