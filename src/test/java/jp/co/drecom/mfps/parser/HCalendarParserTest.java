package jp.co.drecom.mfps.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.microformats.HCalendar;

import org.junit.Test;

public class HCalendarParserTest extends TestBase {

	@Test
	public void hCalendar() {
		HCalendarParser parser = null;

		try {

			//Crawler crawler = new Crawler("http://microformats.blog.drecom.jp/archive/2");
			Crawler crawler = new Crawler("http://bokukoyo.drecom.jp/for_neko/QVuJzDhNSJxD9fnHPFplHqOA6km6KZJq20070304201206.html");
			parser = new HCalendarParser(crawler.accession());
			parser.parser();

			List list = parser.getResultList();

			for (Iterator iter = list.iterator(); iter.hasNext();) {
				HCalendar element = (HCalendar) iter.next();
				System.out.println("element" + element.toString());

			}

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
