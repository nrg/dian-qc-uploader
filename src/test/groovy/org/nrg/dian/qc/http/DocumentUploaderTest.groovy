package org.nrg.dian.qc.http;

import org.nrg.dian.qc.model.SessionAssessment;
import org.nrg.dian.qc.util.DateUtil;

import groovy.util.GroovyTestCase;

class DocumentUploaderTest extends GroovyTestCase {
	def http
	DocumentUploader uploader
	SessionAssessment session
	
	void setUp() {
		http = new Expando()
		uploader = new DocumentUploader(http: http)
	}
	
	void testUpload(){
		http.put = { path, document ->
			assertEquals("REST/projects/DIAN_1/subjects/pat123/experiments/XNAT_001/assessors/XNAT_DIAN_001_mQC_2009-03-24", path)
			return ["status": 200, "data": "ok"]
		}
		session = new SessionAssessment(project:"DIAN_1", subject_id:"pat123",
                        				system_session_id:"XNAT_001", 
                        				session_id: "XNAT_DIAN_001", 
                        				date: Date.parse(DateUtil.YYYY_MM_DD, "2009-03-24"))
		uploader.upload(session)
	}
}