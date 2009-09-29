package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.N;
import jp.co.drecom.mfps.util.ElementUtil;

public class NDto {

	private Long id = null;

	private String familyName = "";

	private String givenName = "";

	private String additionalName = "";

	private String honorificPrefix = "";

	private String honorificSuffix = "";

	public String getAdditionalName() {
		return additionalName;
	}

	public void setAdditionalName(String additionalName) {
		this.additionalName = additionalName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getHonorificPrefix() {
		return honorificPrefix;
	}

	public void setHonorificPrefix(String honorificPrefix) {
		this.honorificPrefix = honorificPrefix;
	}

	public String getHonorificSuffix() {
		return honorificSuffix;
	}

	public void setHonorificSuffix(String honorificSuffix) {
		this.honorificSuffix = honorificSuffix;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void copyElements(N n) {
		ElementUtil.copyElements(n.getElements(), this);
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

	    retValue = "NDto ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "familyName = " + this.familyName + TAB
	        + "givenName = " + this.givenName + TAB
	        + "additionalName = " + this.additionalName + TAB
	        + "honorificPrefix = " + this.honorificPrefix + TAB
	        + "honorificSuffix = " + this.honorificSuffix + TAB
	        + " )";

	    return retValue;
	}



}
