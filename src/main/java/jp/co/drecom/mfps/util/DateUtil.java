package jp.co.drecom.mfps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jp.co.drecom.mfps.log.Logger;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	private static Logger logger = new Logger(DateUtil.class);

	/**
	 * �����̓��t��������
	 * @return
	 */
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * �����̓��t��������
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * ���t���t�H�[�}�b�g����������ł�����
	 * @return
	 */
	public static String getFormatDate(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date.getTime());
	}

	/**
	 * �w��p�^�[���Ńt�H�[�}�b�g���ꂽ�������Date�ł�����
	 * @return
	 */
	public static Date getParseDate(String target, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			return formatter.parse(target);
		} catch (ParseException e) {
			logger.warn(e);
			throw new IllegalStateException(e.getMessage());
		}
	}

	/**
	 * dt�l�� Timstamp �ɕϊ�����
	 * @param value
	 * @return
	 */
	public static Date formatDt(String value) {
		if(StringUtils.isBlank(value)) {
			return null;
		}
		SimpleDateFormat utils = new SimpleDateFormat("yyyyMMdd'T'hhmmZ");
		try {
			utils.parse(value);
		} catch (ParseException e) {
			logger.warn(e);
			return null;
		}
		return new Date(utils.getCalendar().getTimeInMillis());
	}

}
