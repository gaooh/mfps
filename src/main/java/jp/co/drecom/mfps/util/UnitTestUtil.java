package jp.co.drecom.mfps.util;

public class UnitTestUtil {

	/**
	 * 長い文字列を生成してかえす
	 * @param value
	 * @param count
	 * @return
	 */
	public static String generatLongValue(int count) {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0 ; i < count ; i++) {
			buffer.append("a");
		}
		return buffer.toString();
	}
}
