package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class Geo extends Microformats {

	public enum GeoElement { LATITUDE, LONGITUDE };

	public Geo() {
		elements.put(GeoElement.LATITUDE.toString(), Element.getTagElementInstance());
		elements.put(GeoElement.LONGITUDE.toString(), Element.getTagElementInstance());
	}

	public void setElement(GeoElement elements, Object object) {
		super.setElement(elements.toString(), object);
	}

	public static List elementsDefinition() {
		return Arrays.asList(GeoElement.values());
	}

	public boolean equalElements(GeoElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
	}

	public String getElementValue(GeoElement element) {
		return super.getElementValue(element.toString());
	}

	/**
	 * �v�f�����邩�B
	 * @return
	 */
	public boolean isEmpty() {
		List definitionElements = Geo.elementsDefinition();
		for(Iterator itrElements = definitionElements.iterator() ; itrElements.hasNext(); ) {
			String value = getElementValue((GeoElement) itrElements.next());
			if(StringUtils.isNotBlank(value)) { // 1�v�f�ł��u�����N�łȂ��������ł͂Ȃ�
				return false;
			}
		}
		return true;
	}

	/**
	 * �v�f���Ȃ����B
	 * @return
	 */
	public boolean isNotEmpty() {
		return !isEmpty();
	}
}
