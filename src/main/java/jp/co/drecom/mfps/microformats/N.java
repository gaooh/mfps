package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class N  extends Microformats {

	public enum NElement { FAMILY_NAME, GIVEN_NAME, ADDITIONAL_NAME, HONORIFIC_PREFIX, HONORIFIC_SUFFIX };

	public N() {
		elements.put(NElement.FAMILY_NAME.toString(), Element.getTagElementInstance());
		elements.put(NElement.GIVEN_NAME.toString(), Element.getTagElementInstance());
		elements.put(NElement.ADDITIONAL_NAME.toString(), Element.getTagElementInstance());
		elements.put(NElement.HONORIFIC_PREFIX.toString(), Element.getTagElementInstance());
		elements.put(NElement.HONORIFIC_SUFFIX.toString(), Element.getTagElementInstance());
	}

	public static List elementsDefinition() {
		return Arrays.asList(NElement.values());
	}

	public boolean equalElements(NElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
	}

	public String getElementValue(NElement element) {
		return super.getElementValue(element.toString());
	}

	public void setElement(NElement elements, Object object) {
		super.setElement(elements.toString(), object);
	}

	/**
	 * 要素があるか。
	 * @return
	 */
	public boolean isEmpty() {
		List definitionElements = N.elementsDefinition();
		for(Iterator itrElements = definitionElements.iterator() ; itrElements.hasNext(); ) {
			String value = getElementValue((NElement) itrElements.next());
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
