package org.nrg.dian.qc.http;

import groovyx.net.http.HTTPBuilder;

class HttpFactory {

	def connect(String url, String username, String password){
		HTTPBuilder http = new HTTPBuilder(url)
		http.auth.basic(username, password)
		return new HttpResource(httpClient: http)
	}
}