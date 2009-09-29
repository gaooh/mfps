package jp.co.drecom.mfps.util;

import static org.junit.Assert.assertEquals;
import jp.co.drecom.mfps.common.TestBase;

import org.junit.Assert;
import org.junit.Test;

public class TextUtilTest extends TestBase {

	@Test
	public void trueIsURL() {
		Assert.assertTrue(TextUtil.isURL("http://www.drecom.jp"));
	}

	@Test
	public void trueIsHTTPSURL() {
		Assert.assertTrue(TextUtil.isURL("https://www.drecom.jp"));
	}

	@Test
	public void falseIsURL() {
		Assert.assertFalse(TextUtil.isURL("xxxxe:y"));
	}

	@Test
	public void extractTag() {
		assertEquals(TextUtil.extractTag("http://www.drecom.co/suer"), "suer");
		assertEquals(TextUtil.extractTag("http://www.drecom.co/ebisen/"), "ebisen");
		assertEquals(TextUtil.extractTag("http://www.drecom.co/kobaken?param=date"), "kobaken");
	}

}
