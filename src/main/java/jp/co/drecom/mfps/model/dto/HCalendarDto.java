package jp.co.drecom.mfps.model.dto;

import java.sql.Timestamp;
import java.util.List;

import jp.co.drecom.mfps.microformats.HCalendar;
import jp.co.drecom.mfps.util.ElementUtil;
import jp.co.drecom.mfps.util.TimestampUtil;

public class HCalendarDto {

	private Long microformatsId = null;

	private String description = "";

	private String location = "";

	private String status = "";

	private String summary = "";

	private String url = "";

	private Timestamp dtstart = null;

	private Timestamp dtstamp = null;

	private Timestamp dtend = null;

	private String uid = "";

	private MicroformatsDto microformats = null;

	private List categories = null;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDtend() {
		return dtend;
	}

	public void setDtend(Timestamp dtend) {
		this.dtend = dtend;
	}

	public void setDtend(String dtend) {
		this.dtend = TimestampUtil.formatDt(dtend);
	}

	public Timestamp getDtstamp() {
		return dtstamp;
	}

	public void setDtstamp(Timestamp dtstamp) {
		this.dtstamp = dtstamp;
	}

	public void setDtstamp(String dtstamp) {
		this.dtstamp = TimestampUtil.formatDt(dtstamp);
	}

	public Timestamp getDtstart() {
		return dtstart;
	}

	public void setDtstart(Timestamp dtstart) {
		this.dtstart = dtstart;
	}

	public void setDtstart(String dtstart) {
		this.dtstart = TimestampUtil.formatDt(dtstart);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getMicroformatsId() {
		return microformatsId;
	}

	public void setMicroformatsId(Long microformatsId) {
		this.microformatsId = microformatsId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MicroformatsDto getMicroformats() {
		return microformats;
	}

	public void setMicroformats(MicroformatsDto microformats) {
		this.microformats = microformats;
	}

	public List getCategories() {
		return categories;
	}

	public void setCategories(List categories) {
		this.categories = categories;
	}

	public void copyElements(HCalendar hCalendar) {
		ElementUtil.copyElements(hCalendar.getElements(), this);
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

	    retValue = "HCalendarDto ( "
	        + super.toString() + TAB
	        + "microformatsId = " + this.microformatsId + TAB
	        + "description = " + this.description + TAB
	        + "location = " + this.location + TAB
	        + "status = " + this.status + TAB
	        + "summary = " + this.summary + TAB
	        + "url = " + this.url + TAB
	        + "dtstart = " + this.dtstart + TAB
	        + "dtstamp = " + this.dtstamp + TAB
	        + "dtend = " + this.dtend + TAB
	        + "uid = " + this.uid + TAB
	        + "microformats = " + this.microformats + TAB
	        + " )";

	    return retValue;
	}

}
