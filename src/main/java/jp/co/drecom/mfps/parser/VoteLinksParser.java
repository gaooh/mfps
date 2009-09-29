package jp.co.drecom.mfps.parser;

import java.net.URLConnection;
import java.util.Iterator;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.VoteLinks;
import jp.co.drecom.mfps.microformats.VoteLinks.VoteLinksElement;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class VoteLinksParser extends MicroformatsParser {

	private Logger logger = new Logger(getClass());

	public VoteLinksParser(URLConnection connection) {
		super(connection);
	}

	public VoteLinksParser(String markClass, String text) {
		super(markClass, text);
	}

	@Override
	protected void parser() throws ParserException {

		try {
			HasAttributeFilter attrFilter = new HasAttributeFilter(VoteLinks.REVNAME);
			NodeFilter filter = new AndFilter(new TagNameFilter(HtmlConst.A), attrFilter);
			NodeList list = getParser().parse(filter);

			SimpleNodeIterator it = list.elements();

			logger.info("Find VoteLinks size = " + list.size());
			while (it.hasMoreNodes()) { // VoteLinks ÇÃâÚÇÃÉäÉXÉg
				Node node = it.nextNode();
				VoteLinks voteLinks = new VoteLinks();
				analize(voteLinks, node);
				resultList.add(voteLinks);
			}

		} catch (Exception e) {
			logger.warn("can't parse. : " + e);
			throw new ParserException(e.getMessage());
		}
	}

	/**
	 * nodeListÇâêÕÇµÅAåãâ ÇVoteLinksÇ…äiî[ÇµÇƒÇ¢Ç≠
	 *
	 * @param voteLinks
	 * @param nodeList
	 * @throws ParserException
	 */
	protected void analize(final VoteLinks voteLinks, Node node) throws ParserException {

		LinkTag tag = (LinkTag) node;

		/* -- VoteLinks -- */
		for (Iterator itr = VoteLinks.elementsDefinition().iterator(); itr.hasNext();) {
			VoteLinksElement element = (VoteLinksElement) itr.next();

			logger.debug(" element, value : " + element + "," + tag.toPlainTextString());
			voteLinks.setElement(element, tag);
		}
	}
}
