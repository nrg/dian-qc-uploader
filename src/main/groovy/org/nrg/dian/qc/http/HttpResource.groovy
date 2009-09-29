package org.nrg.dian.qc.http;

import static groovyx.net.http.ContentType.TEXT

class HttpClient {
	def httpFactory
	
	def post(String path, String content) {
		httpFactory.connect().post(path: path, requestContentType: TEXT, body: content) { resp -> return resp }
	}
	
	def delete(String path) {
		httpFactory.connect().delete(path: path) { resp -> return resp }
	}
}
