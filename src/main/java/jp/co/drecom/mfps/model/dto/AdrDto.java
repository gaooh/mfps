package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.Adr;
import jp.co.drecom.mfps.util.ElementUtil;

public class AdrDto {

	private Long id = null;

	private String type = "";

	private String postOfficeBox = "";

	private String streetAddress = "";

	private String extendedAddress = "";

	private String region = "";

	private String locality = "";

	private String postalCode = "";

	private String countryName = "";

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getExtendedAddress() {
		return extendedAddress;
	}

	public void setExtendedAddress(String extendedAddress) {
		this.extendedAddress = extendedAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostOfficeBox() {
		return postOfficeBox;
	}

	public void setPostOfficeBox(String postOfficeBox) {
		this.postOfficeBox = postOfficeBox;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Adr 要素を自分自身にコピーする
	 * @param hCard
	 */
	public void copyElements(Adr adr) {
		ElementUtil.copyElements(adr.getElements(), this);
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

	    retValue = "AdrDto ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "type = " + this.type + TAB
	        + "postOfficeBox = " + this.postOfficeBox + TAB
	        + "streetAddress = " + this.streetAddress + TAB
	        + "extendedAddress = " + this.extendedAddress + TAB
	        + "region = " + this.region + TAB
	        + "locality = " + this.locality + TAB
	        + "postalCode = " + this.postalCode + TAB
	        + "countryName = " + this.countryName + TAB
	        + " )";

	    return retValue;
	}

}
