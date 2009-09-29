package jp.co.drecom.mfps.microformats;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Item extends Microformats {

	public enum ItemElement { TYPE, URL, PHOTE };

	public Item() {
		elements.put(ItemElement.TYPE.toString(), Element.getTagElementInstance());
		elements.put(ItemElement.URL.toString(), Element.getTagElementInstance());
		elements.put(ItemElement.PHOTE.toString(), Element.getTagElementInstance());
	}

	private HCard hCard = new HCard();

	private HCalendar hCalendar = new HCalendar();

	public HCalendar getHCalendar() {
		return hCalendar;
	}

	public void setHCalendar(HCalendar calendar) {
		hCalendar = calendar;
	}

	public HCard getHCard() {
		return hCard;
	}

	public void setHCard(HCard card) {
		hCard = card;
	}

	public String getElementValue(ItemElement element) {
		return super.getElementValue(element.toString());
	}

	public static List elementsDefinition() {
		return Arrays.asList(ItemElement.values());
	}

	/**
	 * 要素があるか。
	 * @return
	 */
	public boolean isEmpty() {
		List definitionElements = Item.elementsDefinition();
		for(Iterator itrElements = definitionElements.iterator() ; itrElements.hasNext(); ) {
			String value = getElementValue((ItemElement) itrElements.next());
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
