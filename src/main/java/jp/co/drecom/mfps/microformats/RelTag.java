package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.List;



public class RelTag extends Microformats {

	public static final String RELNAME = "rel";

	public enum RelTagElement { TAG, URL };

	public RelTag() {
		elements.put(RelTagElement.TAG.toString(), Element.getRelTagElementInstance());
		elements.put(RelTagElement.URL.toString(), Element.getLinkTagElementInstance());
	}

	public void setElement(RelTagElement element, Object object) {
		super.setElement(element.toString(), object);
	}

	public static List elementsDefinition() {
		return Arrays.asList(RelTagElement.values());
	}

}
