package jp.co.drecom.mfps.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.microformats.HCard;
import jp.co.drecom.mfps.microformats.Adr.AdrElement;
import jp.co.drecom.mfps.microformats.Geo.GeoElement;
import jp.co.drecom.mfps.microformats.HCard.HCardElement;
import jp.co.drecom.mfps.microformats.N.NElement;
import jp.co.drecom.mfps.microformats.Org.OrgElement;

import org.junit.Test;

public class HCardParserTest extends TestBase {

	@Test
	public void sucessHCard() {
		HCardParser parser = null;

		try {

			Crawler crawler = new Crawler("http://onk.blog.drecom.jp/");
			parser = new HCardParser(crawler.accession());
			parser.parser();

			List list = parser.getResultList();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				HCard element = (HCard) iter.next();
				System.out.println("element" + element.toString());
				System.out.println("title:" + element.getElementValue(HCardElement.TITLE));

				assertEquals(element.getElementValue(HCardElement.TITLE), "Takafumi Onaka's Profile");
				assertEquals(element.getElementValue(HCardElement.NICKNAME), "‚ ‚Ó‚ë");
				assertEquals(element.getElementValue(HCardElement.PHOTO), "http://blog.drecom.jp/onk/img/1/onk.jpg");
				assertEquals(element.getElementValue(HCardElement.SORT_STRING), "‚¨‚¨‚È‚© ‚½‚©‚Ó‚Ý");

				assertEquals(element.getAdr().getElementValue(AdrElement.REGION), "“Œ‹ž“s");

				assertEquals(element.getGeo().getElementValue(GeoElement.LATITUDE), "35.702732");

				assertEquals(element.getOrg().getElementValue(OrgElement.ORGANIZATION_NAME), "Š”Ž®‰ïŽÐƒhƒŠƒRƒ€");

				assertEquals(element.getN().getElementValue(NElement.FAMILY_NAME), "‘å’‡");

			}

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
