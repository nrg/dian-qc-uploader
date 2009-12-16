package org.nrg.dian.qc.csv;

import org.nrg.dian.qc.model.MrScanAssessment;
import org.nrg.dian.qc.util.DateUtil;
import org.nrg.dian.qc.model.SessionAssessment;

import antlr.collections.List;


class DataMapper {
	private static final String SESSION_ID = "patientid"

	def map(qualityRecords, inclusionRecords){
		def retVal = []
		
		inclusionRecords.each { inclusionRecord ->
			
			SessionAssessment session = new SessionAssessment()
			session.session_id = inclusionRecord[SESSION_ID]
			session.rater = inclusionRecord["initials"]
			session.pass = score(inclusionRecord["pass"])
			session.payable = score(inclusionRecord["pay_site"])
			session.rescan = score(inclusionRecord["rescan"])
			session.quarantineRelease = score(inclusionRecord["quarantineRelease"])
			session.comments = clearEmpty(inclusionRecord["comments"])
			
			// Map all the inclusion criteria into the incidental findings
			StringBuilder sb = new StringBuilder();
			addToBuffer(sb, inclusionRecord["surgery"], "Previous Surgery")
			addToBuffer(sb, inclusionRecord["hemmorhage"], "Hemmorhage")
			addToBuffer(sb, inclusionRecord["dev_anomaly"], "Developmental Anomaly")
			addToBuffer(sb, inclusionRecord["lesion"], "Space Occupying Lesion")
			addToBuffer(sb, inclusionRecord["atrophy"], "Atypical Atrophy")
			addToBuffer(sb, inclusionRecord["infarction"], "Infarction")
			addToBuffer(sb, inclusionRecord["trauma"], "Prior Head Trauma")
			addToBuffer(sb, inclusionRecord["infarction"], "Infarction")
			addToBuffer(sb, inclusionRecord["metallic"], "Metallic Artifact")
			addToBuffer(sb, inclusionRecord["nph"], "NPH")
			addToBuffer(sb, inclusionRecord["edema"], "Cerebral Edema")
			addToBuffer(sb, inclusionRecord["other"], "Other")
			session.incidentalFindings = clearEmpty(sb.toString())
			
			session.stereotacticMarker = null // default to not evaluated
			
			filter(session.session_id, qualityRecords).each { record ->
				MrScanAssessment scan = new MrScanAssessment()
				session.scans.add scan
				scan.imageScan_ID = record["seriesnumber"]
				scan.blurring = score(record["in_bgr"])
				scan.flow = score(record["in_flow"])
				scan.otherImageArtifacts = score(record["in_other"])
				scan.wrap = score(record["wrap"])
				scan.coverage = score(record["headcoverage"])
				scan.susceptibility = score(record["susceptibility"])
				scan.motion = score(record["head_motion"])
				scan.interpacMotion = score(record["ip_motion"])
				scan.comments = clearEmpty(record["comments"])
				
				scan.pass = score(record["pass"])
				
				// set the time to the last scan's time
				session.date = Date.parse(DateUtil.YYYY_MM_DD, record['sdate'])
				
				// set the sterotactic marker value on the session based upon the
				// last non-null marker value in the list of scans.
				def marker = score(record["marker"])
				if (marker != null) {
					session.stereotacticMarker = marker
				}
			}
			retVal.add(session)
		}
		return retVal
	}
	
	/** 
	 * Add to a StringBuffer, inoutBuffer, only if element is not empty or null
	 */
	void addToBuffer(inoutBuffer, element, label){
		if (clearEmpty(element)){
			inoutBuffer.append(label).append(": ").append(element).append(", ")
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
		if (item == null || item.trim().size() == 0){
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
			it[SESSION_ID] == id 
		}
	}
	
}