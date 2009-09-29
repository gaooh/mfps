package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.HCalendarCategory;
import jp.co.drecom.mfps.util.ElementUtil;

public class HCalendarCategoryDto {

	private Long hcalendarId = null;

	private String category = "";

	private String url = "";

	public Long getHcalendarId() {
		return hcalendarId;
	}

	public void setHcalendarId(Long calendarId) {
		hcalendarId = calendarId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * HCalendarCategory 要素を自分自身にコピーする
	 * @param hCard
	 */
	public void copyElements(HCalendarCategory hCalendar) {
		ElementUtil.copyElements(hCalendar.getElements(), this);
	}




}
