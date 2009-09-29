package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.VoteLinks;
import jp.co.drecom.mfps.util.ElementUtil;

public class VoteLinksDto {

	private Long microformatsId = null;

	private String type = "";

	private String url = "";

	private String title = "";

	public Long getMicroformatsId() {
		return microformatsId;
	}

	public void setMicroformatsId(Long microformatsId) {
		this.microformatsId = microformatsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void copyElements(VoteLinks voteLinks) {
		ElementUtil.copyElements(voteLinks.getElements(), this);
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
	    
	    retValue = "VoteLinksDto ( "
	        + super.toString() + TAB
	        + "microformatsId = " + this.microformatsId + TAB
	        + "type = " + this.type + TAB
	        + "url = " + this.url + TAB
	        + "title = " + this.title + TAB
	        + " )";
	    
	    return retValue;
	}


}
