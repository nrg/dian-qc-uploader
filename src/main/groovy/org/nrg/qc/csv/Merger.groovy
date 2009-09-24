package org.nrg.qc.csv;

import org.nrg.qc.model.MrScanAssessment;
import org.nrg.qc.model.SessionAssessment;


class Merger {
	//	<qualityfile> <inclusionfile> <protocolfile>
	List<SessionAssessment> merge(qualityRecords){
		uniqueId(records).each { id ->
			
			SessionAssessment session = new SessionAssessment()
			//
			// get session id from REST API [patientId: id["patid"], date: id["sdate"]]
			//
			session.incidentalFindings
			session.pass = SessionAssessment.PASS // default to passing
			session.stereotacticMarker = null // default to not evaluated
			filter(id, qualityRecords).each { record ->
				MrScanAssessment scan = new MrScanAssessment()
				scan.imageScan_ID = record["seriesnumber"]
				scan.blurring = score(record["in_bgr"])
				scan.flow = score(record["in_flow"])
				scan.otherImageArtifacts = score(record["in_other"])
				scan.coverage = score(record["headcoverage"])
				scan.susceptibility = score(record["susceptibility"])
				scan.motion = score(record["head_motion"])
				scan.interpacMotion = score(record["ip_motion"])
				scan.comments = clearEmpty(record["comments"])
				if (record["pass"] != Session.PASS) {
					scan.pass = SessionAssessment.FAIL
					session.pass = Session.FAIL
				} else {
					scan.pass = Session.PASS
				}
				
				// set the sterotactic marker value on the session based upon the
				// last non-null marker value in the list of scans.
				def marker = score(record["marker"])
				if (marker != null) {
					session.stereotacticMarker = marker
				}
			}
		}
	}
	
	/*
	 * Transform the scores such that an empty (null) explicitly means that the
	 * metric was not evaluated. 
	 */
	def score(item){
		def value = item.trim()
		if (value.size() == 0){
			return "0"
		} else if (value == "-1") {
			return null
		}
		return value
	}
	
	/*
	 * Replace empty items with nulls
	 */
	def clearEmpty(item){
		if (item.trim().size() == 0){
			return null
		}
		return item
	}
	
	/*
	 * Give a list of records, return a list that is filtered to only include
	 * those records which have the same patid and sdate
	 */
	def filter(id, records){
		return records.findAll { 
			it["patid"] == id["patid"] && it["sdate"] == id["sdate"] 
		}
	}
	
	/*
	 * Generate a list of the unique set of patid and sdate values. 
	 */
	def uniqueId(records){
		return records.collect {
			["patid": it["patid"], "sdate": it["sdate"]]
		}.unique()
	}
}