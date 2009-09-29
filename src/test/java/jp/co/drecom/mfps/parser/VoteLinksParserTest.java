package jp.co.drecom.mfps.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.microformats.VoteLinks;

import org.junit.Test;

public class VoteLinksParserTest extends TestBase {

	@Test
	public void parseNormal() {
		VoteLinksParser parser = null;

		try {

			Crawler crawler = new Crawler("http://redmonk.net/tags/microformats");
			parser = new VoteLinksParser(crawler.accession());
			parser.parser();

			List list = parser.getResultList();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				VoteLinks element = (VoteLinks) iter.next();
				System.out.println("element" + element.toString());

				//assertEquals(element.getElementValue(VoteLinksElement.URL), "20070203T1646++0900");
			}

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
