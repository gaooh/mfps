package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * hCard
 *
 * @author Akiko Asami
 */
public class HCard extends Microformats {

	public static final String CLASSNAME = "vcard";

	public enum HCardElement { TITLE, NICKNAME, EMAIL, PHOTO, BDAY, URL, LOGO, NOTE, REV, SORT_STRING };

	private Adr adr = new Adr();

	private Geo geo = new Geo();

	private Org org = new Org();

	private N n = new N();

	public HCard() {
		elements.put(HCardElement.TITLE.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.NICKNAME.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.EMAIL.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.PHOTO.toString(), Element.getImageTagElementInstance());
		elements.put(HCardElement.BDAY.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.URL.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.LOGO.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.NOTE.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.REV.toString(), Element.getTagElementInstance());
		elements.put(HCardElement.SORT_STRING.toString(), Element.getTagElementInstance());
	}

	public Adr getAdr() {
		return adr;
	}

	public void setAdr(Adr adr) {
		this.adr = adr;
	}

	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public N getN() {
		return n;
	}

	public void setN(N n) {
		this.n = n;
	}

	public void setElement(HCardElement element, Object object) {
		super.setElement(element.toString(), object);
	}

	public void setElementValue(HCardElement element, String value) {
		super.setElementValue(element.toString(), value);
	}

	public String getElementValue(HCardElement element) {
		return super.getElementValue(element.toString());
	}

	public static List elementsDefinition() {
		return Arrays.asList(HCardElement.values());
	}

	public boolean equalElements(HCardElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
	}

	/**
	 * 要素があるか。
	 * @return
	 */
	public boolean isEmpty() {
		List definitionElements = HCard.elementsDefinition();
		for(Iterator itrElements = definitionElements.iterator() ; itrElements.hasNext(); ) {
			String value = getElementValue((HCardElement) itrElements.next());
			if(StringUtils.isNotBlank(value)) { // 1要素でもブランクでなかったら空ではない
				return false;
			}
		}
		return true;
	}

	/**
	 * 要素がないか。
	 * @return
	 */
	public boolean isNotEmpty() {
		return !isEmpty();
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

	    retValue = "HCard ( "
	        + super.toString() + TAB
	        + "CLASSNAME = " + HCard.CLASSNAME + TAB
	        + "adr = " + this.adr.toStringElements() + TAB
	        + "geo = " + this.geo.toStringElements() + TAB
	        + "org = " + this.org.toStringElements() + TAB
	        + "n = " + this.n.toStringElements() + TAB
	        + "elements = " + super.toStringElements() + TAB
	        + " )";

	    return retValue;
	}

}
