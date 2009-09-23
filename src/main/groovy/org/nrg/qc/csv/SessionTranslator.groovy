package org.nrg.qc.csv;

import org.nrg.qc.model.Session;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

class SessionTranslator {
	def static final TRANSLATION_TABLE = ["csv1":"xnat:prop1", 
	                                      "cvs2":"xnat:prop2"]
	
	SessionTranslator(header){
		def lookup = [:]
		header.eachWithIndex { elem, i ->  lookup[elem] = i}
	}
	
	Session translate(row){
		return null;
	}
}