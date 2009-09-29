package jp.co.drecom.mfps.model.dto.request;

/**
 * HCalendar 検索 Model
 *
 * @author Akiko Asami
 */
public class HCalendarSearchDto {

	private String type = null;

	private String fromurl = null;

	private String url = null;

	private String category = null;

	private String dtstart = null;

	private String dtend = null;

	private Integer offset = new Integer(0); // TODO デフォルト値の外だし

	private Integer limit = new Integer(5); // TODO デフォルト値の外だし

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getDtend() {
		return dtend;
	}

	public void setDtend(String dtend) {
		this.dtend = dtend;
	}

	public String getDtstart() {
		return dtstart;
	}

	public void setDtstart(String dtstart) {
		this.dtstart = dtstart;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getFromurl() {
		return fromurl;
	}

	public void setFromurl(String fromUrl) {
		this.fromurl = fromUrl;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	    retValue = "HCalendarSearchDto ( "
	        + super.toString() + TAB
	        + "type = " + this.type + TAB
	        + "fromurl = " + this.fromurl + TAB
	        + "url = " + this.url + TAB
	        + "dtstart = " + this.dtstart + TAB
	        + "dtend = " + this.dtend + TAB
	        + "offset = " + this.offset + TAB
	        + "limit = " + this.limit + TAB
	        + " )";

	    return retValue;
	}

}
