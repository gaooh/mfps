package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class Adr extends Microformats {

	public enum Type { work, home, pref, postal, dom, intl };

	public enum AdrElement { TYPE, POST_OFFICE_BOX, STREET_ADDRESS, EXTENDED_ADDRESS, REGION, LOCALITY, POSTAL_CODE, COUNTRY_NAME };

	public Adr() {
		elements.put(AdrElement.TYPE.toString(), Element.getTagElementInstance());
		elements.put(AdrElement.POST_OFFICE_BOX.toString(), Element.getTagElementInstance());
		elements.put(AdrElement.STREET_ADDRESS.toString(), Element.getTagElementInstance());
		elements.put(AdrElement.EXTENDED_ADDRESS.toString(), Element.getTagElementInstance());
		elements.put(AdrElement.REGION.toString(), Element.getTagElementInstance());
		elements.put(AdrElement.LOCALITY.toString(), Element.getTagElementInstance());
		elements.put(AdrElement.POSTAL_CODE.toString(), Element.getTagElementInstance());
		elements.put(AdrElement.COUNTRY_NAME.toString(), Element.getTagElementInstance());

	}

	public void setElement(AdrElement element, Object object) {
		super.setElement(element.toString(), object);
	}

	public void setElementValue(AdrElement element, String value) {
		super.setElementValue(element.toString(), value);
	}

	public String getElementValue(AdrElement element) {
		return super.getElementValue(element.toString());
	}

	public static List elementsDefinition() {
		return Arrays.asList(AdrElement.values());
	}

	public boolean equalElements(AdrElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
	}

	/**
	 * 要素があるか。
	 * @return
	 */
	public boolean isEmpty() {
		List definitionElements = Adr.elementsDefinition();
		for(Iterator itrElements = definitionElements.iterator() ; itrElements.hasNext(); ) {
			String value = getElementValue((AdrElement) itrElements.next());
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
}
