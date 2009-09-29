package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.Org;
import jp.co.drecom.mfps.util.ElementUtil;

public class OrgDto {

	private Long id = null;

	private String organizationName = "";

	private String organizationUnit = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(String organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public void copyElements(Org org) {
		ElementUtil.copyElements(org.getElements(), this);
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

	    retValue = "OrgDto ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "organizationName = " + this.organizationName + TAB
	        + "organizationUnit = " + this.organizationUnit + TAB
	        + " )";

	    return retValue;
	}

}
