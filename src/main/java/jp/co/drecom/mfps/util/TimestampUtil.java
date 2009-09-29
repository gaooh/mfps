package jp.co.drecom.mfps.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import jp.co.drecom.mfps.log.Logger;

public class TimestampUtil {

	private static Logger logger = new Logger(TimestampUtil.class);

	/**
	 * Timestamp �^�Ō��ݓ��t��Ԃ��B
	 * @return
	 */
	public static Timestamp getNowForSQL() {
		Calendar calendar = Calendar.getInstance();
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * Timestamp �^�� x ���O��Ԃ��B
	 * @return
	 */
	public static Timestamp beforeMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * dt�l�� Timstamp �ɕϊ�����
	 * @param value
	 * @return
	 */
	public static Timestamp formatDt(String value) {
		if(StringUtils.isBlank(value)) {
			return null;
		}
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyyMMdd'T'hhmmZ");
		SimpleDateFormat timstampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Timestamp result = null;
		try {
			if(value.indexOf("-") != -1) {
				timstampFormat.parse(value);
				result = new Timestamp(timstampFormat.getCalendar().getTimeInMillis());
			} else {
				isoFormat.parse(value);
				result = new Timestamp(isoFormat.getCalendar().getTimeInMillis());
			}

		} catch (ParseException e) {
			logger.warn(e);
			return null;
		}
		return result;
	}

}
