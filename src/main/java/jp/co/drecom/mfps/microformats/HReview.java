package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.List;

public class HReview extends Microformats {

	public static final String CLASSNAME = "hreview";

	public static final String REVIWER_CLASSNAME = "reviewer vcard";

	public enum HReviewElement { TYPE, DTREVIEWED, DESCRIPTION, LICENSE, PERMALINK, RATING, SUMMARY, VERSION };

	public enum Type { product, business, event, person, place, website, url };

	public HReview() {
		elements.put(HReviewElement.TYPE.toString(), Element.getTagElementInstance());
		elements.put(HReviewElement.DTREVIEWED.toString(), Element.getTagAttributeElementInstance("title"));
		elements.put(HReviewElement.DESCRIPTION.toString(), Element.getTagElementInstance());
		elements.put(HReviewElement.LICENSE.toString(), Element.getTagElementInstance());
		elements.put(HReviewElement.PERMALINK.toString(), Element.getImageTagElementInstance());
		elements.put(HReviewElement.RATING.toString(), Element.getRatingElementInstance());
		elements.put(HReviewElement.SUMMARY.toString(), Element.getTagElementInstance());
		elements.put(HReviewElement.VERSION.toString(), Element.getTagElementInstance());
	}

	/** レビュー対象 */
	private Item item = new Item();

	/** レビューワー */
	private HCard hcard = new HCard();

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public HCard getHcard() {
		return hcard;
	}

	public void setHcard(HCard hcard) {
		this.hcard = hcard;
	}

	public static List elementsDefinition() {
		return Arrays.asList(HReviewElement.values());
	}

	public boolean equalElements(HReviewElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
	}

	public void setElement(HReviewElement element, Object object) {
		super.setElement(element.toString(), object);
	}

	public String getElementValue(HReviewElement element) {
		return super.getElementValue(element.toString());
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

	    retValue = "HReview ( "
	        + super.toString() + TAB
	        + "CLASSNAME = " + HReview.CLASSNAME + TAB
	        + "item = " + this.item.toStringElements() + TAB
	        + "hCard = " + this.hcard.toString() + TAB
	        + "elements = " + super.toStringElements() + TAB
	        + " )";

	    return retValue;
	}



}
