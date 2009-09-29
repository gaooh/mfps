package jp.co.drecom.mfps.model.module;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HCalendar;
import jp.co.drecom.mfps.microformats.HCalendarCategory;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.model.dao.HCalendarCategoryDao;
import jp.co.drecom.mfps.model.dao.HCalendarDao;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dto.HCalendarCategoryDto;
import jp.co.drecom.mfps.model.dto.HCalendarDto;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;
import jp.co.drecom.mfps.model.dto.request.HCalendarSearchDto;
import jp.co.drecom.mfps.portal.api.param.HCalendarParameter;
import jp.co.drecom.mfps.util.DateUtil;
import jp.co.drecom.mfps.util.TimestampUtil;

public class HCalendarModule extends MicroformatsModule {

	private Logger logger = new Logger(getClass());

	@Override
	public void save(String location, Microformats microformats) {
		HCalendar hCalendar = (HCalendar)microformats;

		MicroformatsDao microformatsDao = DaoFactory.getMicroformatsDao();
		final MicroformatsDto microformatsDto = microformatsDao.findByChecksum(location, hCalendar.getChecksum());

		/* -- Table:Microformats -- */
		microformatsDto.setUrl(location);
		microformatsDto.setRegistDate(TimestampUtil.getNowForSQL());
		microformatsDto.setUpdateDate(TimestampUtil.getNowForSQL());
		microformatsDto.setType(Microformats.Type.hCalendar.ordinal());
		microformatsDto.setChecksum(hCalendar.getChecksum());
		Long id = microformatsDao.insert(microformatsDto);

		/* -- Table:hCalendar -- */
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		HCalendarDto hCalendarDto = new HCalendarDto();
		hCalendarDto.setMicroformatsId(id);
		hCalendarDto.copyElements(hCalendar);

		/* -- Table:hCalendarCategory -- */
		if (!hCalendar.getCategory().isEmpty()) {
			HCalendarCategoryDao categoryDao = DaoFactory.getHCalendarCategoryDao();

			for(Iterator itrCategory = hCalendar.getCategory().iterator(); itrCategory.hasNext(); ) {
				HCalendarCategoryDto categoryDto = new HCalendarCategoryDto();
				categoryDto.setHcalendarId(id);
				categoryDto.copyElements((HCalendarCategory) itrCategory.next());
				logger.info(" store - [HCalendarCategor] : " + categoryDto);
				categoryDao.insert(categoryDto);
			}
		}

		logger.info(" [hCalendar] : " + hCalendarDto);
		hCalendarDao.insert(hCalendarDto);

	}

	public List findByTodayEventRandom(Integer count) {
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		List list = hCalendarDao.findByEventRandom(count, DateUtil.getToday());
		logger.info(" [hCalendar Today's event] : " + list.size());
		return list;
	}

	public Long findByTodayEventCount() {
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		Long count = hCalendarDao.findByEventCount(DateUtil.getToday());
		logger.info(" [hCalendar Today Event] : " + count);
		return count;
	}

	public List findByTomorrowEventRandom(Integer count) {
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		List list = hCalendarDao.findByEventRandom(count, DateUtil.getTomorrow());
		logger.info(" [hCalendar Tomorrow's event] : " + list.size());
		return list;
	}

	public Long findByTomorrowEventCount() {
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		Long count = hCalendarDao.findByEventCount(DateUtil.getTomorrow());
		logger.info(" [hCalendar Tommorrow Event] : " + count);
		return count;
	}

	public List findByLatestEventRandom(Integer count) {
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		List list = hCalendarDao.findByEventRandom(count);
		logger.info(" [hCalendar Latest event] : " + list.size());
		return list;
	}

	public Long findByEventCount() {
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		Long count = hCalendarDao.findByEventCount();
		logger.info(" [hCalendar All Event] : " + count);
		return count;
	}

	public List findByCondition(HCalendarParameter paramater) {
		HCalendarDao hCalendarDao = DaoFactory.getHCalendarDao();
		HCalendarSearchDto hCalendarSearch = new HCalendarSearchDto();

		try {
			BeanUtils.copyProperties(hCalendarSearch, paramater);

		} catch (IllegalAccessException e) {
			logger.fatal(e);
		} catch (InvocationTargetException e) {
			logger.fatal(e);
			// TODO copyPropertiesはよく使うからエラーのラッパーいるかも
		}

		List list = hCalendarDao.findByCondition(hCalendarSearch);
		logger.info(" [hCalendar Event] : " + list.size());
		if(list != null && !list.isEmpty()) {
			HCalendarCategoryDao hCalendarCategoryDao  = DaoFactory.getHCalendarCategoryDao();

			for(Iterator itrList = list.iterator(); itrList.hasNext(); ) {
				HCalendarDto hCalendar = (HCalendarDto) itrList.next();
				List categories = hCalendarCategoryDao.findByHCalendarId(hCalendar.getMicroformatsId());
				logger.info(" [hCalendarCategory ] : " + categories.size());
				logger.info(" [hCalendarCategory ] : " + categories.toString());

				hCalendar.setCategories(categories);
			}
		}
		return list;
	}
}