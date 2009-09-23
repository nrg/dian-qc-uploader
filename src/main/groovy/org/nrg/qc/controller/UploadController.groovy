package org.nrg.qc.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UploadController {
	private static ApplicationContext SPRING_CONTEXT
	
	def run(){
	}
	
	def getBean(String bean){
		return getContext().getBean(bean)
	}
	
	def getContext(){
		if (SPRING_CONTEXT == null){
			SPRING_CONTEXT = new ClassPathXmlApplicationContext("applicationContext.xml")
		}
		return SPRING_CONTEXT
	}
}