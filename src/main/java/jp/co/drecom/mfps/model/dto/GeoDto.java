package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.Geo;
import jp.co.drecom.mfps.util.ElementUtil;

public class GeoDto {

	private Long microformatsId = null;

	private String latitude = "";

	private String longitude = "";

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Long getMicroformatsId() {
		return microformatsId;
	}

	public void setMicroformatsId(Long microformatsId) {
		this.microformatsId = microformatsId;
	}

	/**
	 * Geo 要素を自分自身にコピーする
	 * @param hCard
	 */
	public void copyElements(Geo geo) {
		ElementUtil.copyElements(geo.getElements(), this);
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

	    retValue = "GeoDto ( "
	        + super.toString() + TAB
	        + "microformatsId = " + this.microformatsId + TAB
	        + "latitude = " + this.latitude + TAB
	        + "longitude = " + this.longitude + TAB
	        + " )";

	    return retValue;
	}

}
