package org.nrg.dian.qc.model;

import groovy.xml.StreamingMarkupBuilder;

class MrScanAssessment {
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
		def xml = new StreamingMarkupBuilder().bind{
			"xnat:scan" ("xsi:type": "xnat:mrQcScanData") {
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
				"xnat:pass" pass
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
		}
		return xml.toString()
	}
}