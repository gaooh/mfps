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
	 * 今日の日付をかえす
	 * @return
	 */
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 明日の日付をかえす
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 日付をフォーマットした文字列でかえす
	 * @return
	 */
	public static String getFormatDate(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date.getTime());
	}

	/**
	 * 指定パターンでフォーマットされた文字列をDateでかえす
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
	 * dt値を Timstamp に変換する
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
