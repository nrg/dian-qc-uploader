package org.nrg.dian.qc.model;

import java.text.SimpleDateFormat;

import org.nrg.dian.qc.util.DateUtil;

import groovy.xml.StreamingMarkupBuilder;

/** 
 * Simple POGO for holding information about the session and set of scans.
 */
class SessionAssessment {
	static final String PASS = "1"
	static final String FAIL = "0"
	
	def project
	def subject_id
	def system_session_id
	def session_id
	Date date
	def rater
	def stereotacticMarker
	def incidentalFindings
	def comments
	def pass
	def payable
	
	List<MrScanAssessment> scans = [];

	def makeId(){
		if (session_id) {
			def timestamp = ""
    		if (date){
    			timestamp = DateUtil.dateFormat(date)
    		}
			return "${session_id}_mQC_${timestamp}"
		}
		return null
	}
	
	String toXml() {
		def xml = new StreamingMarkupBuilder().bind{
			mkp.xmlDeclaration()
			"xnat:QCManualAssessment" ("ID":makeId(), "project":project, 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				if (date){
					"xnat:date" DateUtil.dateFormat(date)
					"xnat:time" DateUtil.timeFormat(date)
				}
				"xnat:imageSession_ID" system_session_id
				if (rater){
					"xnat:rater" rater
				}
				if (stereotacticMarker){
					"xnat:stereotacticMarker" stereotacticMarker
				}
				if (incidentalFindings){
					"xnat:incidentalFindings" incidentalFindings
				}
				"xnat:scans" {
					for (scan in scans) {
						unescaped << scan.toXml()
					}
				}
				if (comments){
					"xnat:comments" comments
				}
				"xnat:pass" pass
				if (payable){
					"xnat:payable" payable
				}
			}
		}
		return xml.toString()
	}
}