package org.nrg.qc.model;

import groovy.util.GroovyTestCase;

public class MrScanAssessmentTest extends ModelXmlTestCase {
	MrScanAssessment scan
	
	void setUp() throws Exception {
		super.setUp()
		scan = new MrScanAssessment()
	}
	
	void testToXmlEmpty(){
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") { "xnat:pass" {
			} }
		assertExpectedXml(scan.toXml())
	}	
	
	void testToXmlOnlyId(){
		scan.imageScan_ID = "ABC_2134"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:imageScan_ID" "ABC_2134" 
			"xnat:pass" {}
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyCoverage(){
		scan.coverage = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:coverage" "1" 
			"xnat:pass" {}
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyMotion(){
		scan.motion = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:motion" "1"
			"xnat:pass" {}
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyOtherImageArtifacts(){
		scan.otherImageArtifacts = "0"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:otherImageArtifacts" "0" 
			"xnat:pass" {}
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyComments(){
		scan.comments = "Mild ringing"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:comments" "Mild ringing" 
			"xnat:pass" {}
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyPass(){
		scan.pass = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:pass" "1" }
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyBlurring(){
		scan.blurring = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:pass" {}
			"xnat:blurring" "1"
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyFlow(){
		scan.flow = "2"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:pass" {}
			"xnat:flow" "2"
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyImageContrastn(){
		scan.imageContrast = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:pass" {}
			"xnat:imageContrast" "1"
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyWrap(){
		scan.wrap = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:pass" {}
			"xnat:wrap" "1"
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlySusceptibility(){
		scan.susceptibility = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:pass" {}
			"xnat:susceptibility" "1"
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlOnlyInterpacMotion(){
		scan.interpacMotion = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {
			"xnat:pass" {}
			"xnat:interpacMotion" "1"
		}
		assertExpectedXml(scan.toXml())
	}
	
	void testToXmlMultiple(){
		scan.imageScan_ID = "ABC_2134"
		scan.flow = "1"
		scan.motion = "0"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  
			"xnat:imageScan_ID" "ABC_2134" 
			"xnat:motion" "0"
			"xnat:pass" {}
			"xnat:flow" "1"
		}
		assertExpectedXml(scan.toXml())
	}
	
	def getExpectedXml(){
		return writer.toString()
	}
}