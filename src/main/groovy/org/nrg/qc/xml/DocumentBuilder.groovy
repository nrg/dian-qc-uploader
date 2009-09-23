package org.nrg.qc.xml;

import org.nrg.qc.model.Scan;
import org.nrg.qc.model.Session;

class DocumentBuilder {
	
	String build(Session session){
		def writer = new StringWriter()
		def xml = new groovy.xml.MarkupBuilder(writer)
		xml."xnat:QCManualAssessment" (ID:"assessor_jp_1", project:"Demo", "xmlns:prov":"http://www.nbirn.net/prov", 
				"xmlns:xnat":"http://nrg.wustl.edu/xnat",
				"xmlns:xsi":"http://www.w3.org/2001/XMLSchema-instance", 
				"xsi:schemaLocation":"http://nrg.wustl.edu/xnat plugin-resources/project-skeletons/xnat/src/schemas/xnat/xnat.xsd") { 
					"xnat:scans"{
						for (scan in session.scans){
							return "xnat:scan"("xsi:type":"xnat:mrQcScanData") { "xnat:imageScan_ID"(scan.imageScan_ID) }
						}
					}
				}
		
		return writer.toString()
	}
	
	//	def buildScan(Scan scan){
	//		return "xnat:scan"("xsi:type":"xnat:mrQcScanData") { "xnat:imageScan_ID"(scan) }
	//	}
}

