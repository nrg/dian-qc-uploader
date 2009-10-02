package org.nrg.dian.qc.http;

import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.XML

class HttpClient {
	def httpFactory
	
	def delete(path) {
		return connect().delete(path: path)
	}
	
	def get(path) {
		return connect().get(path: path, contentType : XML)
	}
	
	def post(path, content) {
		return connect().post(path: path, requestContentType: TEXT, body: content)
	}
	
	def put(path, content) {
		return connect().put(path: path, requestContentType: TEXT, body: content)
	}
	
	private def connect() {
		return httpFactory.connect()
	}
}
