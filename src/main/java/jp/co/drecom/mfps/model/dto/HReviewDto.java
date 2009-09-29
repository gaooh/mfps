package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.microformats.HReview;
import jp.co.drecom.mfps.util.ElementUtil;

public class HReviewDto {

	private Long microformatsId = null;

	private String dtreviewed = "";

	private String description = "";

	private Long itemId = null;

	private String license = "";

	private String permalink = "";

	private String type = "";

	private Integer rating = null;

	private Long reviewerId = null;

	private String summary = "";

	private String version = "";

	private ItemDto item = null;

	private MicroformatsDto microformats = null;

	private HCardDto hcard = null;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDtreviewed() {
		return dtreviewed;
	}

	public void setDtreviewed(String dtreviewed) {
		this.dtreviewed = dtreviewed;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Long getMicroformatsId() {
		return microformatsId;
	}

	public void setMicroformatsId(Long microformatsId) {
		this.microformatsId = microformatsId;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setRating(String rating) {
		this.rating = Integer.valueOf(rating);
	}

	public Long getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String reviewType) {
		this.type = reviewType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}

	public MicroformatsDto getMicroformats() {
		return microformats;
	}

	public void setMicroformats(MicroformatsDto microformats) {
		this.microformats = microformats;
	}

	public HCardDto getHcard() {
		return hcard;
	}

	public void setHcard(HCardDto card) {
		hcard = card;
	}

	public void copyElements(HReview hReview) {
		ElementUtil.copyElements(hReview.getElements(), this);
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

	    retValue = "HReviewDto ( "
	        + super.toString() + TAB
	        + "microformatsId = " + this.microformatsId + TAB
	        + "dtreviewed = " + this.dtreviewed + TAB
	        + "description = " + this.description + TAB
	        + "itemId = " + this.itemId + TAB
	        + "license = " + this.license + TAB
	        + "permalink = " + this.permalink + TAB
	        + "type = " + this.type + TAB
	        + "rating = " + this.rating + TAB
	        + "reviewerId = " + this.reviewerId + TAB
	        + "summary = " + this.summary + TAB
	        + "version = " + this.version + TAB
	        + "item = " + this.item + TAB
	        + "microformats = " + this.microformats + TAB
	        + "hcard = " + this.hcard + TAB
	        + " )";

	    return retValue;
	}



}
