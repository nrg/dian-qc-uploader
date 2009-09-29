package org.nrg.dian.qc.util;

import groovy.util.GroovyTestCase;

class DateUtilTest extends GroovyTestCase {

	void testSecondsToDateZero(){
		assertEquals(new Date(0), DateUtil.secondsToDate(0))
	}

	void testSecondsToDate(){
		assertEquals(new Date(1237926736000), DateUtil.secondsToDate(1237926736))
	}
}