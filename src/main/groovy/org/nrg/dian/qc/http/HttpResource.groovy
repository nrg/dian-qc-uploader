package org.nrg.dian.qc.http;

import groovyx.net.http.HTTPBuilder;


class HttpResource {
	def httpClient
	
	def post(String path, String content) {
		httpClient.post(path: path, body: content) { resp ->
			println "Server response status: ${resp.status}"
			assert resp.status == 200
		}
	}
}

