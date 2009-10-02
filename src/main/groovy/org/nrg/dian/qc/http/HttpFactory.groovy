package org.nrg.dian.qc.http;

import groovyx.net.http.HTTPBuilder;
import groovyx.net.http.RESTClient;

class HttpFactory {
	String baseUrl
	String username
	String password
	
	def connect(){
		RESTClient http = new RESTClient(baseUrl)
		http.auth.basic(username, password)
		return http
	}
}
