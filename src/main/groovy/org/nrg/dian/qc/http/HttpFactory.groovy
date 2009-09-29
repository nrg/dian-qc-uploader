package org.nrg.dian.qc.http;

import groovyx.net.http.HTTPBuilder;

class HttpFactory {
	String baseUrl
	String username
	String password
	
	def connect(){
		HTTPBuilder http = new HTTPBuilder(baseUrl)
		http.auth.basic(username, password)
		return http
	}
}
