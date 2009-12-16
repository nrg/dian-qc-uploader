package org.nrg.dian.qc.model;

import org.nrg.dian.qc.model.SessionAssessment;
import org.nrg.dian.qc.util.DateUtil;

class SessionAssessmentTest extends ModelXmlTestCase {
	SessionAssessment session
	
	void setUp() {
		super.setUp();
		session = new SessionAssessment()
	}
	
	void testToXmlEmpty(){
		def expected = builder.bind{
			mkp.xmlDeclaration()
			"xnat:QCManualAssessment" ("ID":"", "project":"", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				"xnat:imageSession_ID" null
				"xnat:scans" {}
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, session)
	}
	
	void testToXmlWithProperties(){
		session.project = "Demo"
		session.date = Date.parse(DateUtil.YYYY_MM_DD, "2009-03-24")
		session.session_id = "ABC1234"
		session.system_session_id = "INTERNAL_ID_1234"
		session.rater = "Jane Doe"
		session.stereotacticMarker = "0"
		session.incidentalFindings = "There were several lesions\ntest\n"
		session.comments = "hello world"
		session.pass = "1"
		session.payable = "1"
		session.rescan = "0"
		session.quarantineRelease = "1"
		
		def expected = builder.bind{
			mkp.xmlDeclaration()
			"xnat:QCManualAssessment" ("ID":"ABC1234_mQC_2009-03-24", "project":"Demo", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){
				"xnat:date" "2009-03-24"
				"xnat:imageSession_ID" "INTERNAL_ID_1234"
				"xnat:rater" "Jane Doe"
				"xnat:stereotacticMarker" "0"
				"xnat:incidentalFindings" "There were several lesions\ntest\n"
				"xnat:scans" {}
				"xnat:comments" "hello world"
				"xnat:pass" "1"
				"xnat:payable" "1"
				"xnat:rescan" "0"
				"xnat:quarantineRelease" "1"
			}
		}
		assertExpectedXml(expected, session)
	}
	
	
	void testToXmlWithScan(){
		session.scans.add(new MrScanAssessment([imageScan_ID: "ab1", pass:"1"]))
		def expected = builder.bind{
			mkp.xmlDeclaration()
			"xnat:QCManualAssessment" ("ID":"", "project":"", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				"xnat:imageSession_ID" null
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
			mkp.xmlDeclaration()
			"xnat:QCManualAssessment" ("ID":"", "project":"", 
			"xmlns:prov":"http://www.nbirn.net/prov", 
			"xmlns:xnat":"http://nrg.wustl.edu/xnat",
			"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
			"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd"){ 
				"xnat:imageSession_ID" null
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

	void testMakeId(){
		session.session_id = "ABC_1234"
		session.date = Date.parse(DateUtil.YYYY_MM_DD, "2009-02-28")
		assertEquals("ABC_1234_mQC_2009-02-28", session.makeId())
	}
}