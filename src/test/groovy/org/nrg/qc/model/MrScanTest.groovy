package org.nrg.qc.model;

import groovy.util.GroovyTestCase;

public class MrScanTest extends GroovyTestCase {
	MrScan scan
	def writer
	def expected
	
	void setUp() throws Exception {
		scan = new MrScan()
		writer = new StringWriter()
		expected = new groovy.xml.MarkupBuilder(writer)
	}
	
	void testToXmlEmpty(){
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData")
		assertEquals(getExpectedXml(), scan.toXml())
	}	
	
	void testToXmlOnlyId(){
		scan.imageScan_ID = "ABC_2134"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:imageScan_ID" "ABC_2134" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyCoverage(){
		scan.coverage = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:coverage" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyMotion(){
		scan.motion = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:motion" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyOtherImageArtifacts(){
		scan.otherImageArtifacts = "0"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:otherImageArtifacts" "0" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyComments(){
		scan.comments = "Mild ringing"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:comments" "Mild ringing" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyPass(){
		scan.pass = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:pass" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyBlurring(){
		scan.blurring = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:blurring" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyFlow(){
		scan.flow = "2"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:flow" "2" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyImageContrastn(){
		scan.imageContrast = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:imageContrast" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyWrap(){
		scan.wrap = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:wrap" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlySusceptibility(){
		scan.susceptibility = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:susceptibility" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlOnlyInterpacMotion(){
		scan.interpacMotion = "1"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  "xnat:interpacMotion" "1" }
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	void testToXmlMultiple(){
		scan.imageScan_ID = "ABC_2134"
		scan.flow = "1"
		scan.motion = "0"
		expected."xnat:scan" ("xsi:type":"xnat:mrQcScanData") {  
			"xnat:imageScan_ID" "ABC_2134" 
			"xnat:motion" "0"
			"xnat:flow" "1"
		}
		assertEquals(getExpectedXml(), scan.toXml())
	}
	
	def getExpectedXml(){
		return writer.toString()
	}
}