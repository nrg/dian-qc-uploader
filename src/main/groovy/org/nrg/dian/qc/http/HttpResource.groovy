package org.nrg.dian.qc.http;

import static groovyx.net.http.ContentType.TEXT

class HttpResource {
	def httpClient
	
	def post(String path, String content) {
		httpClient.post(path: path, requestContentType: TEXT, body: content) { resp -> return resp }
	}
	
	def delete(String path) {
		httpClient.delete(path: path) { resp -> return resp }
	}
}

