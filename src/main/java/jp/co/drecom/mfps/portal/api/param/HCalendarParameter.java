package jp.co.drecom.mfps.portal.api.param;

import javax.servlet.http.HttpServletRequest;

import jp.co.drecom.mfps.log.Logger;

import org.apache.commons.lang.StringUtils;

public class HCalendarParameter {

	/** �P�x�Ɏ擾�ł���ő吔 */
	private static final Integer MAX_LIMIT = 50; // TODO �f�t�H���g�l�̊O����

	private Logger logger = new Logger(getClass());

	private String fromurl = null;

	private String type = null;

	private String category = null;

	private String url = null;

	private String dtstart = null;

	private String dtend = null;

	private Integer offset = new Integer(0); // TODO �f�t�H���g�l�̊O����

	private Integer limit = new Integer(5); // TODO �f�t�H���g�l�̊O����

	private Integer pageNum = new Integer(0);

	public HCalendarParameter(HttpServletRequest request) {

		// TODO �啶���ł��������ł��ӎ����Ȃ��Ƃ�����

		this.fromurl = request.getParameter("fromurl");
		this.url = request.getParameter("url");
		this.type = request.getParameter("type");
		this.dtstart = request.getParameter("dtstart");
		this.dtend = request.getParameter("dtend");
		this.category = request.getParameter("category");

		setLimit(request.getParameter("max"));
		setPageNum(request.getParameter("pagenum"));



		this.offset = this.limit * this.pageNum;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDtend() {
		return dtend;
	}

	public void setDtend(String dtend) {
		this.dtend = dtend;
	}

	public String getDtstart() {
		return dtstart;
	}

	public void setDtstart(String dtstart) {
		this.dtstart = dtstart;
	}

	public String getFromurl() {
		return fromurl;
	}

	public void setFromurl(String fromUrl) {
		this.fromurl = fromUrl;
	}

	public Integer getLimit() {
		return limit;
	}


	/**
	 * �擾�ő吔��ݒ肷��B
	 * �K��l�͈͊O�̃��N�G�X�g�������ꍇ�̓f�t�H���g�l�����̂܂܎g��
	 * @param limit
	 */
	public void setLimit(String strLimit) {

		if(StringUtils.isNotBlank(strLimit)) {
			try {
				Integer value = Integer.valueOf(strLimit);
				if(value.intValue() > 0 && value.intValue() < MAX_LIMIT.intValue()) {
					this.limit = value;
				}
			}catch (NumberFormatException e) {
				logger.warn("[max] request paramter error " + e);
				// TODO �G���[�̃��X�|���X
			}
		}
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(String strPageNum) {
		if(StringUtils.isNotBlank(strPageNum)) {
			try {
				Integer value = Integer.valueOf(strPageNum);
				this.pageNum = value;

			}catch (NumberFormatException e) {
				logger.warn("[pageNum] request paramter error " + e);
				// TODO �G���[�̃��X�|���X
			}
		}

	}

	protected Integer helperIntegerParameter(String value) throws NumberFormatException {
		if(StringUtils.isNotBlank(value)) {
			return Integer.valueOf(value);
		}
		return null;
	}


}
