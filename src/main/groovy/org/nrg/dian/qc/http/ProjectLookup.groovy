package org.nrg.dian.qc.http;

import groovy.util.XmlSlurper;

import org.nrg.dian.qc.model.SessionAssessment;

class ProjectLookup {
	def http
	
	def lookup(SessionAssessment session){
		
	}
	
	protected def query(session_id){
		def response = http.get("REST/experiments?format=xml&xsiType=xnat:mrSessionData&project=DIAN_*&label=${session_id}&columns=ID,subject_ID,label,project,date")
		assert response.status == 200
		return response.content
	}
	
	protected def parse(data){
		def xml = new XmlSlurper().parseText(data).results
		// ensure we only found a single session (can not proceed if we did not 
		// find a record, unsure how to proceed if we receive more than one)
		assert xml.rows.row.size() == 1

		def retVal = [:]
		xml.columns.column.eachWithIndex {  column, i -> 
			retVal[column.text()] = xml.rows.row.cell[i].text()
		}
		return retVal
	}
}