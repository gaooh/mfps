package jp.co.drecom.mfps.util;

import jp.co.drecom.mfps.common.TestBase;

import org.junit.Test;

public class TimestampUtilTest extends TestBase {

	@Test
	public void formt() {
		System.out.println(TimestampUtil.formatDt("20070207T0000+0900"));
	}

}
