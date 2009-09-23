package org.nrg.qc.http;

import groovyx.net.http.HTTPBuilder;


class HttpResource {
	def httpClient
	
	def post(String content) {
		httpClient.post(path: 'update.xml', body: content) { resp ->
			println "Server response status: ${resp.statusLine}"
			assert resp.statusLine.statusCode == 200
		}
	}
}

