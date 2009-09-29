package jp.co.drecom.mfps.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.microformats.XFN;

import org.junit.Test;

public class XFNParserTest  extends TestBase {

	@Test
	public void parseNormal() {
		XFNParser parser = null;

		try {

			Crawler crawler = new Crawler("http://xml.blog.drecom.jp/archive/24");
			parser = new XFNParser(crawler.accession());
			parser.parser();

			List list = parser.getResultList();
			if(list.isEmpty()) {
				System.err.println("list size == 0, can't parse");
				fail();
				return;
			}
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				XFN element = (XFN) iter.next();
				System.out.println("element" + element.toString());

			}

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
