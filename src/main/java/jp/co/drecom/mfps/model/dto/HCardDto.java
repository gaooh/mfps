package jp.co.drecom.mfps.model.dto;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HCard;
import jp.co.drecom.mfps.util.ElementUtil;

public class HCardDto {

	private Logger logger = new Logger(getClass());

	private Long microformatsId = null;

	private String title = "";

	private String nickname = "";

	private String email = "";

	private String photo = "";

	private String bday = "";

	private String url = "";

	private String logo = "";

	private String note = "";

	private String rev = "";

	private String sortString = "";

	private Long geoId = new Long(-1);

	private Long adrId = new Long(-1);

	private Long nId = new Long(-1);

	private Long orgId = new Long(-1);

	private GeoDto geo = null;

	private OrgDto org = null;

	private NDto n = null;

	private AdrDto adr = null;

	public String getBday() {
		return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getGeoId() {
		return geoId;
	}

	public void setGeoId(Long geoId) {
		this.geoId = geoId;
	}

	public Long getMicroformatsId() {
		return microformatsId;
	}

	public void setMicroformatsId(Long mincroformatsId) {
		this.microformatsId = mincroformatsId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickName) {
		this.nickname = nickName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public String getSortString() {
		return sortString;
	}

	public void setSortString(String sortString) {
		this.sortString = sortString;
	}

	public Long getAdrId() {
		return adrId;
	}

	public void setAdrId(Long adrId) {
		this.adrId = adrId;
	}

	public Long getNId() {
		return nId;
	}

	public void setNId(Long id) {
		nId = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public AdrDto getAdr() {
		return adr;
	}

	public void setAdr(AdrDto adr) {
		this.adr = adr;
	}

	public GeoDto getGeo() {
		return geo;
	}

	public void setGeo(GeoDto geo) {
		this.geo = geo;
	}

	public NDto getN() {
		return n;
	}

	public void setN(NDto n) {
		this.n = n;
	}

	public OrgDto getOrg() {
		return org;
	}

	public void setOrg(OrgDto org) {
		this.org = org;
	}

	/**
	 * HCard要素を自分自身にコピーする
	 * @param hCard
	 */
	public void copyElements(HCard hCard) {
		ElementUtil.copyElements(hCard.getElements(), this);
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
	    
	    retValue = "HCardDto ( "
	        + super.toString() + TAB
	        + "logger = " + this.logger + TAB
	        + "microformatsId = " + this.microformatsId + TAB
	        + "title = " + this.title + TAB
	        + "nickname = " + this.nickname + TAB
	        + "email = " + this.email + TAB
	        + "photo = " + this.photo + TAB
	        + "bday = " + this.bday + TAB
	        + "url = " + this.url + TAB
	        + "logo = " + this.logo + TAB
	        + "note = " + this.note + TAB
	        + "rev = " + this.rev + TAB
	        + "sortString = " + this.sortString + TAB
	        + "geoId = " + this.geoId + TAB
	        + "adrId = " + this.adrId + TAB
	        + "nId = " + this.nId + TAB
	        + "orgId = " + this.orgId + TAB
	        + "geo = " + this.geo + TAB
	        + "org = " + this.org + TAB
	        + "n = " + this.n + TAB
	        + "adr = " + this.adr + TAB
	        + " )";
	    
	    return retValue;
	}

	
}
