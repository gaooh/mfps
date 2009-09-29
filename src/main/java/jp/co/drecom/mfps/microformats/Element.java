package jp.co.drecom.mfps.microformats;

import org.apache.commons.lang.StringUtils;

import jp.co.drecom.mfps.microformats.extracton.Extraction;
import jp.co.drecom.mfps.microformats.extracton.ReflecExtraction;
import jp.co.drecom.mfps.microformats.extracton.RelTagExtraction;

/**
 * 要素。
 * 取得するためにタグのクラス名とメソッド名をもっている。
 *
 * @author Akiko Asami
 */
public class Element {

	private Extraction extraction = null;

	private String value = "";

	private String defalut = "";

	private Element(Extraction extraction, String defalut) {
		this.extraction = extraction;
		this.defalut = defalut;
	}

	private Element(Extraction extraction) {
		this.extraction = extraction;
		this.defalut = "";
	}

	public static Element getTagElementInstance() {
		return new Element(new ReflecExtraction("toPlainTextString"));
	}

	public static Element getTagAttributeElementInstance(String param) {
		return new Element(new ReflecExtraction("getAttribute", param));
	}

	public static Element getRatingElementInstance() {
		return new Element(new ReflecExtraction("getAttribute", "title"), "-1");
	}

	public static Element getImageTagElementInstance() {
		return new Element(new ReflecExtraction("getImageURL"));
	}

	public static Element getLinkTagElementInstance() {
		return new Element(new ReflecExtraction("getLink"));
	}

	public static Element getRelTagElementInstance() {
		return new Element(new RelTagExtraction());
	}

	public String getValue() {
		return value;
	}

	public void setValue(Object object) {
		String tmp =  extraction.extract(object);
		if(StringUtils.isBlank(tmp)) {
			this.value = defalut;
		} else {
			this.value = tmp;
		}
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefalut() {
		return defalut;
	}

	public void setDefalut(String defalut) {
		this.defalut = defalut;
	}

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 *
	 * @return a <code>String</code> representation of this object.
	 */
	public String toString() {
		String retValue = " value = " + this.value + " ";
		return retValue;
	}




}
