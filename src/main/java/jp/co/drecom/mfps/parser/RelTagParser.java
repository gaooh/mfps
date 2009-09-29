package jp.co.drecom.mfps.parser;

import java.net.URLConnection;
import java.util.Iterator;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.RelTag;
import jp.co.drecom.mfps.microformats.RelTag.RelTagElement;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class RelTagParser extends MicroformatsParser  {

	private Logger logger = new Logger(getClass());

	public RelTagParser(String markClass, String text) {
		super(markClass, text);
	}

	public RelTagParser(URLConnection connection) {
		super(connection);
	}

	@Override
	protected void parser() throws ParserException {

		try {

			HasAttributeFilter attrFilter = new HasAttributeFilter(RelTag.RELNAME);
			NodeFilter filter = new AndFilter(new TagNameFilter(HtmlConst.A), attrFilter);
			NodeList list = getParser().parse(filter);

			SimpleNodeIterator it = list.elements();

			logger.info("Find rel-tag size = " + list.size());
			while (it.hasMoreNodes()) { // rel-tag ÇÃâÚÇÃÉäÉXÉg
				Node node = it.nextNode();
				RelTag relTag = new RelTag();
				analize(relTag, node);
				resultList.add(relTag);
			}

		} catch (Exception e) {
			logger.warn("can't parse. : " + e);
			throw new ParserException(e.getMessage());
		}
	}

	/**
	 * nodeListÇâêÕÇµÅAåãâ Ç RelTagExtraction Ç…äiî[ÇµÇƒÇ¢Ç≠
	 *
	 * @param relTag
	 * @param node
	 * @throws ParserException
	 */
	protected void analize(final RelTag relTag, Node node) throws ParserException {

		LinkTag tag = (LinkTag) node;

		/* -- rel-tag -- */
		for (Iterator itr = RelTag.elementsDefinition().iterator(); itr.hasNext();) {
			RelTagElement element = (RelTagElement) itr.next();

			logger.debug(" element, value : " + element + "," + tag.toPlainTextString());
			relTag.setElement(element, tag);
		}
	}

}
