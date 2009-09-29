package jp.co.drecom.mfps.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.microformats.RelTag;

import org.junit.Test;

public class RelTagParserTest extends TestBase {

	@Test
	public void parseNormal() {
		RelTagParser parser = null;

		try {

			Crawler crawler = new Crawler("http://mogue.jp/tag");
			parser = new RelTagParser(crawler.accession());
			parser.parser();

			List list = parser.getResultList();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				RelTag element = (RelTag) iter.next();
				System.out.println("element" + element.toString());
			}

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
