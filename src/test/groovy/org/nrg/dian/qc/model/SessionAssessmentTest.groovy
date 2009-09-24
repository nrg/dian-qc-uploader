package org.nrg.dian.qc.model;

import org.nrg.dian.qc.model.SessionAssessment;

class SessionAssessmentTest extends ModelXmlTestCase {
	SessionAssessment session
	
	void setUp() {
		super.setUp();
		session = new SessionAssessment()
	}
	
	void testToXmlEmpty(){
		def expected = builder.bind{
			"xnat:QCManualAssessment" ("ID":"", "project":"", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				"xnat:scans" {}
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, session)
	}
	
	void testToXmlWithProperties(){
		session.project = "Demo"
		session.id = "test_135"
		session.rater = "Jane Doe"
		session.stereotacticMarker = "0"
		session.incidentalFindings = "There were several lesions\ntest\n"
		session.comments = "hello world"
		session.pass = "1"
		session.payable = "1"
		
		def expected = builder.bind{
			"xnat:QCManualAssessment" ("ID":"test_135", "project":"Demo", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				"xnat:rater" "Jane Doe"
				"xnat:stereotacticMarker" "0"
				"xnat:incidentalFindings" "There were several lesions\ntest\n"
				"xnat:scans" {}
				"xnat:comments" "hello world"
				"xnat:pass" "1"
				"xnat:payable" "1"
			}
		}
		assertExpectedXml(expected, session)
	}
	
	
	void testToXmlWithScan(){
		session.scans.add(new MrScanAssessment([imageScan_ID: "ab1", pass:"1"]))
		def expected = builder.bind{
			"xnat:QCManualAssessment" ("ID":"", "project":"", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				"xnat:scans" {
					"xnat:scan" ("xsi:type": "xnat:mrQcScanData") {
						"xnat:imageScan_ID" "ab1"
						"xnat:pass" "1"
					}
				}
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, session)
	}	
	
	void testToXmlWithMultipleScans(){
		session.pass = "0"
		session.payable = "1"
		session.scans.add(new MrScanAssessment([imageScan_ID: "ab1", pass:"1"]))
		session.scans.add(new MrScanAssessment([imageScan_ID: "ab2", pass:"0"]))
		def expected = builder.bind{
			"xnat:QCManualAssessment" ("ID":"", "project":"", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				"xnat:scans" {
					"xnat:scan" ("xsi:type": "xnat:mrQcScanData") {
						"xnat:imageScan_ID" "ab1"
						"xnat:pass" "1"
					}	
					"xnat:scan" ("xsi:type": "xnat:mrQcScanData") {
						"xnat:imageScan_ID" "ab2"
						"xnat:pass" "0"
					}
				}
				"xnat:pass" "0"
				"xnat:payable" "1"
			}
		}
		assertExpectedXml(expected, session)
	}
}