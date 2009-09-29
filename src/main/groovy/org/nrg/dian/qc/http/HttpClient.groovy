package org.nrg.dian.qc.http;

import static groovyx.net.http.ContentType.TEXT

class HttpClient {
	def httpFactory
	
	def delete(String path) {
		connect().delete(path: path) { resp -> return resp }
	}

	def get(String path) {
		connect().get(path: path) { resp -> return resp }
	}

	def post(String path, String content) {
		connect().post(path: path, requestContentType: TEXT, body: content) { resp -> return resp }
	}
	
	def put(String path, String content) {
		connect().put(path: path, requestContentType: TEXT, body: content) { resp -> return resp }
	}

	private def connect() {
		return httpFactory.connect()
	}
}
