package jp.co.drecom.mfps.model.dto;

import java.sql.Timestamp;

public class MicroformatsDto {

	private Long id = null;

	private Integer type = null;

	private String url = "";

	private String checksum = "";

	private Timestamp registDate = null;

	private Timestamp updateDate = null;

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String hashCode) {
		this.checksum = hashCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Timestamp registDate) {
		this.registDate = registDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp udpateDate) {
		this.updateDate = udpateDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEmpty() {
		if(id == null) {
			return true;
		}
		return false;
	}

	public boolean isNotEmpty() {
		return !isEmpty();
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
	    
	    retValue = "MicroformatsDto ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "type = " + this.type + TAB
	        + "url = " + this.url + TAB
	        + "checksum = " + this.checksum + TAB
	        + "registDate = " + this.registDate + TAB
	        + "updateDate = " + this.updateDate + TAB
	        + " )";
	    
	    return retValue;
	}
	
	
}
