package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class XFN extends Microformats {

	private static final String REL_SPLIT = " ";

	public static final String RELNAME = "rel";

	public enum XFNElement { URL, REL };

	public XFN() {
		elements.put(XFNElement.URL.toString(), Element.getLinkTagElementInstance());
		elements.put(XFNElement.REL.toString(), Element.getTagAttributeElementInstance("rel"));
	}

	public String getElementValue(XFNElement element) {
		return super.getElementValue(element.toString());
	}

	public static List elementsDefinition() {
		return Arrays.asList(XFNElement.values());
	}

	public void setElement(XFNElement elements, Object object) {
		super.setElement(elements.toString(), object);
	}

	/**
	 * rel の要素を配列で返す。rel自体が設定されていなければ
	 * 空で長さ 0 の 配列を返す。
	 * @return
	 */
	public String[] getRels() {
		String rel = getElementValue(XFNElement.REL);
		if(StringUtils.isNotBlank(rel)) {
			return rel.split(REL_SPLIT);
		}
		return new String[0];
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";

	    String retValue = "";

	    retValue = "XFN ( "
	        + super.toString() + TAB
	        + "RELNAME = " + XFN.RELNAME + TAB
	        + "elements = " + super.toStringElements() + TAB
	        + " )";

	    return retValue;
	}


}
