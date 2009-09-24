package org.nrg.qc.model;

/** 
 * Simple POGO for holding information about the session and set of scans.
 */
class Session {
	static final String PASS = "1"
	static final String FAIL = "0"
	
	def rater
	def stereotacticMarker
	def incidentalFindings
	def comments
	def pass
	def payable
	
	List<MrScan> scans;
}