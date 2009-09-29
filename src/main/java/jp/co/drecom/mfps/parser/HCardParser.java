package jp.co.drecom.mfps.parser;

import java.net.URLConnection;
import java.util.Iterator;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Adr;
import jp.co.drecom.mfps.microformats.Geo;
import jp.co.drecom.mfps.microformats.HCard;
import jp.co.drecom.mfps.microformats.N;
import jp.co.drecom.mfps.microformats.Org;
import jp.co.drecom.mfps.microformats.Adr.AdrElement;
import jp.co.drecom.mfps.microformats.Geo.GeoElement;
import jp.co.drecom.mfps.microformats.HCard.HCardElement;
import jp.co.drecom.mfps.microformats.N.NElement;
import jp.co.drecom.mfps.microformats.Org.OrgElement;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

/**
 * HCardóp Parser
 *
 * @author Akiko Asami
 */
class HCardParser extends MicroformatsParser {

	public HCardParser(String markClass, String text) {
		super(markClass, text);
	}

	public HCardParser(URLConnection connection) {
		super(connection);
	}

	private Logger logger = new Logger(getClass());

	/* (îÒ Javadoc)
	 * @see jp.co.drecom.mfps.parser.MicroformatsParser#parser(java.lang.String)
	 */
	@Override
	protected void parser() throws ParserException {

		try {

			if(StringUtils.isBlank(markClass)) {
				markClass = HCard.CLASSNAME;
			}

			HasAttributeFilter attrFilter = new HasAttributeFilter(HtmlConst.CLASS, markClass);

			NodeList list = getParser().parse(attrFilter);
			SimpleNodeIterator it = list.elements();

			while (it.hasMoreNodes()) { // hCardÇÃâÚÇÃÉäÉXÉg
				Node node = it.nextNode();
				NodeList childList = node.getChildren();
				HCard hcard = new HCard();
				analize(hcard, childList);
				resultList.add(hcard);
			}

		} catch (Exception e) {
			logger.warn("can't parse. : " + e);
			throw new ParserException(e.getMessage());
		}
	}

	/**
	 * nodeListÇâêÕÇµÅAåãâ ÇhcardÇ…äiî[ÇµÇƒÇ¢Ç≠
	 * @param hcard
	 * @param nodeList
	 */
	protected void analize(final HCard hcard, NodeList nodeList) {

		Adr adr = hcard.getAdr();
		Geo geo = hcard.getGeo();
		Org org = hcard.getOrg();
		N n = hcard.getN();

		SimpleNodeIterator nodeIterator = nodeList.elements();
		while(nodeIterator.hasMoreNodes()) {
			Node node = nodeIterator.nextNode();

			if(node instanceof Tag) {
				Tag tag = (Tag) node;
				String attributeClass = tag.getAttribute(HtmlConst.CLASS);

				if(StringUtils.isNotBlank(attributeClass)) { // TODO Ç‡Ç¡Ç∆å¯ó¶ÇÊÇ≠Ç≈Ç´ÇÈÇÕÇ∏

					/* -- HCard -- */
					for(Iterator itr = HCard.elementsDefinition().iterator() ; itr.hasNext() ; ){
						HCardElement element = (HCardElement) itr.next();

						if(hcard.equalElements(element, attributeClass)) {
							hcard.setElement(element, tag);
							break;
						}
					}

					/* -- Adr -- */
					for(Iterator itr = Adr.elementsDefinition().iterator() ; itr.hasNext() ; ){
						AdrElement element = (AdrElement) itr.next();

						if(adr.equalElements(element, attributeClass)) {
							adr.setElement(element, tag);
							break;
						}
					}

					/* -- GEO -- */
					for(Iterator itr = Geo.elementsDefinition().iterator() ; itr.hasNext() ; ){
						GeoElement element = (GeoElement) itr.next();

						if(geo.equalElements(element, attributeClass)) {
							geo.setElement(element, tag);
							break;
						}
					}

					/* -- ORG -- */
					for(Iterator itr = Org.elementsDefinition().iterator() ; itr.hasNext() ; ){
						OrgElement element = (OrgElement) itr.next();

						if(org.equalElements(element, attributeClass)) {
							org.setElement(element, tag);
							break;
						}
					}

					/* -- N -- */
					for(Iterator itr = N.elementsDefinition().iterator() ; itr.hasNext() ; ){
						NElement element = (NElement) itr.next();

						if(n.equalElements(element, attributeClass)) {
							n.setElement(element, tag);
							break;
						}
					}
				}

			}

			if(node.getChildren() != null) { // éqóvëfÇ™Ç†Ç¡ÇΩÇÁíHÇ¡ÇƒÇ¢Ç≠
				analize(hcard, node.getChildren());
			}
		}
	}
}
