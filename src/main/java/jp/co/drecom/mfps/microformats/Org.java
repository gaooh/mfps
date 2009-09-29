package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Org extends Microformats {

	public enum OrgElement { ORGANIZATION_NAME, ORGANIZATION_UNIT };

	public Org() {
		elements.put(OrgElement.ORGANIZATION_NAME.toString(), Element.getTagElementInstance());
		elements.put(OrgElement.ORGANIZATION_UNIT.toString(), Element.getTagElementInstance());
	}

	public static List elementsDefinition() {
		return Arrays.asList(OrgElement.values());
	}

	public String getElementValue(OrgElement element) {
		return super.getElementValue(element.toString());
	}

	public boolean equalElements(OrgElement element, String attributeName) {
		return super.equalElements(element.toString(), attributeName);
	}

	public void setElement(OrgElement elements, Object object) {
		super.setElement(elements.toString(), object);
	}

	/**
	 * �v�f�����邩�B
	 * @return
	 */
	public boolean isEmpty() {
		List definitionElements = Org.elementsDefinition();
		for(Iterator itrElements = definitionElements.iterator() ; itrElements.hasNext(); ) {
			String value = getElementValue((OrgElement) itrElements.next());
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
