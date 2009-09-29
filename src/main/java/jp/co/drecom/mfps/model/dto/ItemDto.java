package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.Item;
import jp.co.drecom.mfps.util.ElementUtil;

public class ItemDto {

	private Long id = null;

	private String type = "";

	private String url = "";

	private String photo = "";

	private Long hcardId = null;

	private Long hcalendarId = null;

	public Long getHcalendarId() {
		return hcalendarId;
	}


	public void setHcalendarId(Long calendarId) {
		hcalendarId = calendarId;
	}


	public Long getHcardId() {
		return hcardId;
	}


	public void setHcardId(Long cardId) {
		hcardId = cardId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public void copyElements(Item item) {
		ElementUtil.copyElements(item.getElements(), this);
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
	    
	    retValue = "ItemDto ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "type = " + this.type + TAB
	        + "url = " + this.url + TAB
	        + "photo = " + this.photo + TAB
	        + "hcardId = " + this.hcardId + TAB
	        + "hcalendarId = " + this.hcalendarId + TAB
	        + " )";
	    
	    return retValue;
	}

	
}
