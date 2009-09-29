package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.List;

public class HCalendarCategory extends Microformats {

	public enum CategoryElement { CATEGORY };

	public HCalendarCategory() {
		elements.put(CategoryElement.CATEGORY.toString(), Element.getTagElementInstance());
	}

	public boolean equalElements(CategoryElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
	}

	public void setElement(CategoryElement element, Object object) {
		super.setElement(element.toString(), object);
	}

	public static List elementsDefinition() {
		return Arrays.asList(CategoryElement.values());
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

	    retValue = "HCalendarCategory ( "
	    	+ "elements = " + this.elements + TAB
	        + " )";

	    return retValue;
	}
}
