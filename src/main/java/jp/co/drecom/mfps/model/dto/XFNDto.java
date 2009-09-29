package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.XFN;
import jp.co.drecom.mfps.util.ElementUtil;

public class XFNDto {

	private Long microformatsId = null;

	private String url = "";

	public Long getMicroformatsId() {
		return microformatsId;
	}

	public void setMicroformatsId(Long microformatsId) {
		this.microformatsId = microformatsId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void copyElements(XFN xfn) {
		ElementUtil.copyElements(xfn.getElements(), this);
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

	    retValue = "XFNDto ( "
	        + super.toString() + TAB
	        + "microformatsId = " + this.microformatsId + TAB
	        + "url = " + this.url + TAB
	        + " )";

	    return retValue;
	}



}
