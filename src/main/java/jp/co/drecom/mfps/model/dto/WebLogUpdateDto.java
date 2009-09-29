package jp.co.drecom.mfps.model.dto;

import java.sql.Timestamp;

/**
 * weblogupdate ŽÀ‘ÌƒNƒ‰ƒX
 *
 * @author Akiko Asami
 */
public class WebLogUpdateDto {

	/** column: url */
	private Long id = null;

	/** column: url */
	private String url = "";

	/** column: name */
	private String name = "";

	/** column: regist_date */
	private Timestamp registDate = null;

	/** column: ip */
	private String ip = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Timestamp registDate) {
		this.registDate = registDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
}
