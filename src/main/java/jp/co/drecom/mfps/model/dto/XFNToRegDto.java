package jp.co.drecom.mfps.model.dto;

public class XFNToRegDto {

	private Long xfnId = null;

	private Long regId = null;

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public Long getXfnId() {
		return xfnId;
	}

	public void setXfnId(Long xfnId) {
		this.xfnId = xfnId;
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
	    
	    retValue = "XFNToRegDto ( "
	        + super.toString() + TAB
	        + "xfnId = " + this.xfnId + TAB
	        + "regId = " + this.regId + TAB
	        + " )";
	    
	    return retValue;
	}

	
}
