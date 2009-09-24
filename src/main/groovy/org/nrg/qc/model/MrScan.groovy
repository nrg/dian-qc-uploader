package org.nrg.qc.model;

import groovy.util.NodeBuilder;

class MrScan {
	def imageScan_ID
	def coverage
	def motion
	def otherImageArtifacts
	def comments
	def pass
	def blurring
	def flow
	def imageContrast
	def inhomogeneity
	def wrap
	def susceptibility
	def interpacMotion
	
	String toXml(){
		def writer = new StringWriter()
		def xml = new groovy.xml.MarkupBuilder(writer)
		xml.omitNullAttributes = true
		xml.omitEmptyAttributes = true
		xml."xnat:scan" ("xsi:type": "xnat:mrQcScanData") {
			if (imageScan_ID){ 
				"xnat:imageScan_ID" imageScan_ID
			}
			if (coverage){
				"xnat:coverage" coverage
			}
			if (motion) {
				"xnat:motion" motion
			}
			if (otherImageArtifacts){
				"xnat:otherImageArtifacts" otherImageArtifacts
			}
			if (comments) {
				"xnat:comments" comments
			}
			if (pass) {
				"xnat:pass" pass
			}
			if (blurring){
				"xnat:blurring" blurring
			}
			if (flow){
				"xnat:flow" flow
			}
			if (imageContrast){
				"xnat:imageContrast" imageContrast
			}
			if (inhomogeneity) {
				"xnat:inhomogeneity" inhomogeneity
			}
			if (wrap) {
				"xnat:wrap" wrap
			}
			if (susceptibility){
				"xnat:susceptibility" susceptibility
			}
			if (interpacMotion){
				"xnat:interpacMotion" interpacMotion
			}
		}
		
		return writer.toString()
	}
	
}