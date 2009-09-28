package org.nrg.dian.qc.model;

import groovy.xml.StreamingMarkupBuilder;

/** 
 * Simple POGO for holding information about the session and set of scans.
 */
class SessionAssessment {
	static final String PASS = "1"
	static final String FAIL = "0"
	
	def id
	def project
	def session_id
	def rater
	def stereotacticMarker
	def incidentalFindings
	def comments
	def pass
	def payable
	
	List<MrScanAssessment> scans = [];

	String toString(){
		return toXml()
	}
	
	String toXml() {
		def xml = new StreamingMarkupBuilder().bind{
			mkp.xmlDeclaration()
			"xnat:QCManualAssessment" ("ID":id, "project":project, 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
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