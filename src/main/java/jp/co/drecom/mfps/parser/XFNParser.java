package jp.co.drecom.mfps.parser;

import java.net.URLConnection;
import java.util.Iterator;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.XFN;
import jp.co.drecom.mfps.microformats.XFN.XFNElement;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class XFNParser extends MicroformatsParser {

	private Logger logger = new Logger(getClass());

	public XFNParser(URLConnection connection) {
		super(connection);
	}

	public XFNParser(String markClass, String text) {
		super(markClass, text);
	}

	@Override
	protected void parser() throws ParserException {

		try {
			HasAttributeFilter attrFilter = new HasAttributeFilter(XFN.RELNAME);
			NodeFilter filter = new AndFilter(new TagNameFilter(HtmlConst.A), attrFilter);
			NodeList list = getParser().parse(filter);

			SimpleNodeIterator it = list.elements();

			logger.info("Find XFN size = " + list.size());
			while (it.hasMoreNodes()) { // VoteLinks ÇÃâÚÇÃÉäÉXÉg
				Node node = it.nextNode();
				XFN xfn = new XFN();
				analize(xfn, node);
				resultList.add(xfn);
			}

		} catch (Exception e) {
			logger.warn("can't parse. : " + e);
			throw new ParserException(e.getMessage());
		}

	}

	/**
	 * nodeListÇâêÕÇµÅAåãâ Ç XFN Ç…äiî[ÇµÇƒÇ¢Ç≠
	 *
	 * @param voteLinks
	 * @param nodeList
	 * @throws ParserException
	 */
	protected void analize(final XFN xfn, Node node) throws ParserException {

		LinkTag tag = (LinkTag) node;

		/* -- XFN -- */
		for (Iterator itr = XFN.elementsDefinition().iterator(); itr.hasNext();) {
			XFNElement element = (XFNElement) itr.next();

			logger.debug(" element, value : " + element + "," + tag.toPlainTextString());
			xfn.setElement(element, tag);
		}
	}

}
