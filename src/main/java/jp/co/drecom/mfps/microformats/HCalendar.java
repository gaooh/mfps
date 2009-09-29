package jp.co.drecom.mfps.microformats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HCalendar extends Microformats {

	public static final String CLASSNAME = "vevent";

	public enum HCalendarElement { DESCRIPTION, LOCATION, STATUS, SUMMARY, URL, DTSTART, DTSTAMP, DTEND, UID };

	private List<HCalendarCategory> category = new ArrayList<HCalendarCategory>();

	public HCalendar() {
		elements.put(HCalendarElement.DESCRIPTION.toString(), Element.getTagElementInstance());
		elements.put(HCalendarElement.LOCATION.toString(), Element.getTagElementInstance());
		elements.put(HCalendarElement.STATUS.toString(), Element.getTagElementInstance());
		elements.put(HCalendarElement.SUMMARY.toString(), Element.getTagElementInstance());
		elements.put(HCalendarElement.URL.toString(), Element.getTagAttributeElementInstance("href"));
		elements.put(HCalendarElement.DTSTART.toString(), Element.getTagAttributeElementInstance("title"));
		elements.put(HCalendarElement.DTSTAMP.toString(), Element.getTagAttributeElementInstance("title"));
		elements.put(HCalendarElement.DTEND.toString(), Element.getTagAttributeElementInstance("title"));
		elements.put(HCalendarElement.UID.toString(), Element.getTagElementInstance());
	}

	public static List elementsDefinition() {
		return Arrays.asList(HCalendarElement.values());
	}

	public boolean equalElements(HCalendarElement element, String attributeClass) {
		return super.equalElements(element.toString(), attributeClass);
	}

	public void setElement(HCalendarElement element, Object object) {
		super.setElement(element.toString(), object);
	}

	public HCalendarCategory createCategory() {
		return new HCalendarCategory();
	}

	public void addCategory(HCalendarCategory category) {
		this.category.add(category);
	}

	public List<HCalendarCategory> getCategory() {
		return category;
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

	    retValue = "HCalendar ( "
	        + super.toString() + TAB
	        + "CLASSNAME = " + HCalendar.CLASSNAME + TAB
	        + "elements = " + super.toStringElements() + TAB
	        + "category = " + this.category.toString() + TAB
	        + " )";

	    return retValue;
	}

}