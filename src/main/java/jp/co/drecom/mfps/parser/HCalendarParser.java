package jp.co.drecom.mfps.parser;

import java.net.URLConnection;
import java.util.Iterator;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HCalendarCategory;
import jp.co.drecom.mfps.microformats.HCalendar;
import jp.co.drecom.mfps.microformats.HCalendarCategory.CategoryElement;
import jp.co.drecom.mfps.microformats.HCalendar.HCalendarElement;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

/**
 * hCalendar 用パーサー
 *
 * @author Akiko Asami
 *
 */
public class HCalendarParser extends MicroformatsParser {

	private Logger logger = new Logger(getClass());

	public HCalendarParser(URLConnection connection) {
		super(connection);
	}

	public HCalendarParser(String markClass, String text) {
		super(markClass, text);
	}

	@Override
	protected void parser() throws ParserException {
		try {

			HasAttributeFilter attrFilter = new HasAttributeFilter(HtmlConst.CLASS, HCalendar.CLASSNAME);

			NodeList list = getParser().parse(attrFilter);
			SimpleNodeIterator it = list.elements();

			logger.info("Find HCalendar size = " + list.size());
			while (it.hasMoreNodes()) { // HCalendarの塊のリスト
				Node node = it.nextNode();
				NodeList childList = node.getChildren();
				HCalendar hcalendar = new HCalendar();
				analize(hcalendar, childList);
				resultList.add(hcalendar);
			}

		} catch (Exception e) {
			logger.warn("can't parse. : " + e);
			throw new ParserException(e.getMessage());
		}

	}

	/**
	 * nodeListを解析し、結果をhReviewに格納していく
	 * @param hcard
	 * @param nodeList
	 * @throws ParserException
	 */
	protected void analize(final HCalendar hcalendar, NodeList nodeList) throws ParserException {

		HCalendarCategory category = hcalendar.createCategory();

		SimpleNodeIterator nodeIterator = nodeList.elements();
		while(nodeIterator.hasMoreNodes()) {
			Node node = nodeIterator.nextNode();

			if(node instanceof Tag) {
				Tag tag = (Tag) node;
				String attributeClass = tag.getAttribute(HtmlConst.CLASS);

				if(StringUtils.isNotBlank(attributeClass)) {

					/* -- HCalendar -- */
					for(Iterator itr = HCalendar.elementsDefinition().iterator() ; itr.hasNext() ; ){
						HCalendarElement element = (HCalendarElement) itr.next();

						if(hcalendar.equalElements(element, attributeClass)) {
							logger.debug(" element, value : " + element + "," + tag.getAttribute("title"));
							hcalendar.setElement(element, tag);
							break;
						}
					}

					/* -- HCalendarCategory -- */
					for(Iterator itr = HCalendarCategory.elementsDefinition().iterator() ; itr.hasNext() ; ){
						CategoryElement element = (CategoryElement) itr.next();

						if(category.equalElements(element, attributeClass)) {
							category.setElement(element, tag);
							hcalendar.addCategory(category);
							category = hcalendar.createCategory();
							break;
						}
					}
				}

			}

			if(node.getChildren() != null) { // 子要素があったら辿っていく
				analize(hcalendar, node.getChildren());
			}
		}
	}

}
