package org.nrg.dian.qc.http;

import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.XML

/**
 * Simple wrapper around HTTPBuilder's RESTClient.  This wrapper allows for easy
 * mocking of HTTP requests for testing.  The wrapper also presents the content 
 * types.
 */
class HttpClient {
	def httpFactory
	
	def delete(path) {
		return connect().delete(path: path)
	}
	
	def get(path, query=null) {
		return connect().get(path: path, contentType : XML, query: query)
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
