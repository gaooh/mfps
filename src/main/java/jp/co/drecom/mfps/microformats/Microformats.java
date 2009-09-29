package jp.co.drecom.mfps.microformats;

import java.util.HashMap;
import java.util.Iterator;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.util.TextUtil;

public class Microformats {

	public enum Type { hCard, hCalendar, hReview, voteLinks, relTag, XFN, geo };

	protected HashMap<String, Element> elements = new HashMap<String, Element>();

	private Logger logger = new Logger(getClass());

	/**
	 * Elements を文字列化する
	 * @return
	 */
	protected String toStringElements() {
		StringBuffer buffer = new StringBuffer();
		Iterator itrKey = elements.keySet().iterator();

		while(itrKey.hasNext()){
			Object keyObj = itrKey.next();
			buffer.append("[");
			buffer.append(keyObj);
			buffer.append(":");
			buffer.append(elements.get(keyObj));
			buffer.append("],");
		}

		return buffer.toString();
	}

	public HashMap<String, Element> getElements() {
		return elements;
	}

	/**
	 * チェックサムとなる文字列を返す
	 * @return
	 */
	public String getChecksum() {
		return TextUtil.getChecksum(this.toStringElements());
	}

	public String getElementValue(String elementsName) {
		Element element = elements.get(elementsName);
		return element.getValue();
	}

	protected void setElement(String elementsName, Object object) {
		Element element =  elements.get(elementsName);
		if(element != null) {
			element.setValue(object);
			elements.put(elementsName, element);
		} else { // 未定義の要素が設定されるのはおかしい
			logger.warn("this element not initialization . : " + elementsName);
		}
	}

	protected void setElementValue(String elementsName, String value) {
		Element element =  elements.get(elementsName);
		if(element != null) {
			element.setValue(value);
		} else { // 未定義の要素が設定されるのはおかしい
			logger.warn("this element not initialization . : " + elementsName);
		}
	}

	protected boolean equalElements(String elementName, String attributeName) {
		elementName = elementName.toString().toLowerCase();
		elementName = elementName.replaceAll("_", "-");
		return elementName.toString().equals(attributeName);
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

	    retValue = "Microformats ( "
	        + super.toString() + TAB
	        + "elements = " + this.elements + TAB
	        + " )";

	    return retValue;
	}


}
