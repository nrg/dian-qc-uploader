package org.nrg.dian.qc.http;

import org.apache.log4j.Logger;
import org.nrg.dian.qc.model.SessionAssessment;

class DocumentUploader {
	def static LOGGER = Logger.getLogger(DocumentUploader.class)
	def http
	
	def upload(SessionAssessment session){
		def url = "REST/projects/${session.project}/subjects/${session.subject_id}/experiments/${session.system_session_id}/assessors/${session.makeId()}"
		LOGGER.debug("Uploading XML document: " + session.toXml())
		LOGGER.debug("HTTP PUT to " + url)
		def response = http.put(url, session.toXml())
		LOGGER.debug("Reponse to PUT: " + response.status)
		return response
	}
}