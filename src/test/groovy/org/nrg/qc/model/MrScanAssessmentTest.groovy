package org.nrg.qc.model;

import org.custommonkey.xmlunit.Diff;

public class MrScanAssessmentTest extends ModelXmlTestCase {
	MrScanAssessment scan
	
	void setUp() throws Exception {
		super.setUp()
		scan = new MrScanAssessment()
	}
	
	void testToXmlEmpty(){
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") { "xnat:pass" null }
		}
		assertExpectedXml(expected, scan)
	}	
	
	void testToXmlOnlyId(){
		scan.imageScan_ID = "ABC_2134"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:imageScan_ID" "ABC_2134" 
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyCoverage(){
		scan.coverage = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:coverage" "1" 
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyMotion(){
		scan.motion = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:motion" "1"
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyOtherImageArtifacts(){
		scan.otherImageArtifacts = "0"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:otherImageArtifacts" "0" 
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyComments(){
		scan.comments = "Mild ringing"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:comments" "Mild ringing" 
				"xnat:pass" null
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyPass(){
		scan.pass = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:pass" "1" }
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyBlurring(){
		scan.blurring = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:pass" null
				"xnat:blurring" "1"
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyFlow(){
		scan.flow = "2"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:pass" null
				"xnat:flow" "2"
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyImageContrastn(){
		scan.imageContrast = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:pass" null
				"xnat:imageContrast" "1"
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyWrap(){
		scan.wrap = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:pass" null
				"xnat:wrap" "1"
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlySusceptibility(){
		scan.susceptibility = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:pass" null
				"xnat:susceptibility" "1"
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlOnlyInterpacMotion(){
		scan.interpacMotion = "1"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
				"xnat:pass" null
				"xnat:interpacMotion" "1"
			}
		}
		assertExpectedXml(expected, scan)
	}
	
	void testToXmlMultiple(){
		scan.imageScan_ID = "ABC_2134"
		scan.flow = "1"
		scan.motion = "0"
		def expected = builder.bind{
			"xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  
				"xnat:imageScan_ID" "ABC_2134" 
				"xnat:motion" "0"
				"xnat:pass" null
				"xnat:flow" "1"
			}
		}
		assertExpectedXml(expected, scan)
	}
	
}