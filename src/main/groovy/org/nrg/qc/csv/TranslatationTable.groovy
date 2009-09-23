package org.nrg.qc.csv;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.BiMap;

class Csv2XmlNameTranslator {
	
	static {
		table["a"] = "AA"
		def a = ["a":"AA"]
	}
	
	def getXmlElement(csvHeader){
		return table[csvHeader]
	}
}