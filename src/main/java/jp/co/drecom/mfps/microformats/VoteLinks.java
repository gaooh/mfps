package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.List;


public class VoteLinks extends Microformats {

	public static final String REVNAME = "rev";

	public enum Type { vote_for, vote_abstain, vote_against };

	public enum VoteLinksElement { TYPE, URL, TITLE };

	public VoteLinks() {
		elements.put(VoteLinksElement.TYPE.toString(), Element.getTagAttributeElementInstance("rev"));
		elements.put(VoteLinksElement.URL.toString(), Element.getLinkTagElementInstance());
		elements.put(VoteLinksElement.TITLE.toString(), Element.getTagElementInstance());
	}

	public String getElementValue(VoteLinksElement element) {
		return super.getElementValue(element.toString());
	}

	public void setElement(VoteLinksElement elements, Object object) {
		super.setElement(elements.toString(), object);
	}

	public static List elementsDefinition() {
		return Arrays.asList(VoteLinksElement.values());
	}

	public boolean equalElements(VoteLinksElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
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

	    retValue = "VoteLinks ( "
	        + super.toString() + TAB
	        + "REVNAME = " + VoteLinks.REVNAME + TAB
	        + "elements = " + super.toStringElements() + TAB
	        + " )";

	    return retValue;
	}


}
