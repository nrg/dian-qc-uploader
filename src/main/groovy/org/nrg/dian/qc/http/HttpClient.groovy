package org.nrg.dian.qc.http;

import static groovyx.net.http.ContentType.TEXT

class HttpClient {
	def httpFactory
	
	def delete(path) {
		connect().delete(path: path) { resp -> return resp }
	}

	def get(path) {
		connect().get(path: path) { resp -> return resp }
	}

	def post(path, content) {
		connect().post(path: path, requestContentType: TEXT, body: content) { resp -> return resp }
	}
	
	def put(path, content) {
		connect().put(path: path, requestContentType: TEXT, body: content) { resp -> return resp }
	}

	private def connect() {
		return httpFactory.connect()
	}
}
