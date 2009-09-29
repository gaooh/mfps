package jp.co.drecom.mfps.parser;

import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HCard;
import jp.co.drecom.mfps.microformats.HReview;
import jp.co.drecom.mfps.microformats.HReview.HReviewElement;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class HReviewParser extends MicroformatsParser {

	private Logger logger = new Logger(getClass());

	public HReviewParser(String markCalss, String text) {
		super(markCalss, text);
	}

	public HReviewParser(URLConnection connection) {
		super(connection);
	}

	@Override
	protected void parser() throws ParserException {
		try {

			HasAttributeFilter attrFilter = new HasAttributeFilter(HtmlConst.CLASS, HReview.CLASSNAME);

			NodeList list = getParser().parse(attrFilter);
			SimpleNodeIterator it = list.elements();

			logger.info("Find HReview size = " + list.size());
			while (it.hasMoreNodes()) { // hReviewの塊のリスト
				Node node = it.nextNode();
				NodeList childList = node.getChildren();
				HReview hreview = new HReview();
				analize(hreview, childList);
				resultList.add(hreview);
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
	protected void analize(final HReview hreview, NodeList nodeList) throws ParserException {

		SimpleNodeIterator nodeIterator = nodeList.elements();
		while(nodeIterator.hasMoreNodes()) {
			Node node = nodeIterator.nextNode();

			if(node instanceof Tag) {
				Tag tag = (Tag) node;
				String attributeClass = tag.getAttribute(HtmlConst.CLASS);

				if(StringUtils.isNotBlank(attributeClass)) {

					/* -- HRreview -- */
					for(Iterator itr = HReview.elementsDefinition().iterator() ; itr.hasNext() ; ){
						HReviewElement element = (HReviewElement) itr.next();

						if(hreview.equalElements(element, attributeClass)) {
							//logger.debug(" element, value : " + element + "," + tag.toPlainTextString());
							hreview.setElement(element, tag);
							break;
						}
					}

				}

			}

			if(node.getChildren() != null) { // 子要素があったら辿っていく
				analize(hreview, node.getChildren());
			}
		}

		if(hreview.getHcard().isEmpty()) {
			HasAttributeFilter attrFilter = new HasAttributeFilter(HtmlConst.CLASS, HReview.REVIWER_CLASSNAME);
			Parser parser = new Parser(new Lexer(nodeList.toHtml()));
			NodeList list = parser.parse(attrFilter);
			SimpleNodeIterator it = list.elements();

			if (it.hasMoreNodes()) { // reviewer のリスト -> 一人のみ
				Node reviewerNode = it.nextNode();

				HCardParser hCardParser = new HCardParser(HReview.REVIWER_CLASSNAME, reviewerNode.toHtml());
				hCardParser.parser();
				List result = hCardParser.getResultList();


				if(!result.isEmpty()) {
					hreview.setHcard((HCard) result.get(0));
				}
			}
		}
	}
}
