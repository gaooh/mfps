package jp.co.drecom.mfps.model.module;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HReview;
import jp.co.drecom.mfps.microformats.Item;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.model.dao.HCardDao;
import jp.co.drecom.mfps.model.dao.HReviewDao;
import jp.co.drecom.mfps.model.dao.ItemDao;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dto.HCardDto;
import jp.co.drecom.mfps.model.dto.HReviewDto;
import jp.co.drecom.mfps.model.dto.ItemDto;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;
import jp.co.drecom.mfps.util.TimestampUtil;

public class HReviewModule extends MicroformatsModule {

	private Logger logger = new Logger(getClass());

	/**
	 * hReviewのMicroformats関連を保存する
	 * @param location
	 * @param hReview
	 */
	public void save(final String location, final Microformats microformats) {
		HReview hReview = (HReview)microformats;

		MicroformatsDao microformatsDao = DaoFactory.getMicroformatsDao();
		final MicroformatsDto microformatsDto = microformatsDao.findByChecksum(location, hReview.getChecksum());

		/* -- Table:Microformats -- */
		microformatsDto.setUrl(location);
		microformatsDto.setRegistDate(TimestampUtil.getNowForSQL());
		microformatsDto.setUpdateDate(TimestampUtil.getNowForSQL());
		microformatsDto.setType(Microformats.Type.hReview.ordinal());
		microformatsDto.setChecksum(hReview.getChecksum());
		Long id = microformatsDao.insert(microformatsDto);

		/* -- Table:hReview -- */
		HReviewDao hReviewDao = DaoFactory.getHReviewDao();
		HReviewDto hReviewDto = new HReviewDto();
		hReviewDto.setMicroformatsId(id);
		hReviewDto.copyElements(hReview);

		/* -- Table:item -- */
		Item item = hReview.getItem();
		if (item.isNotEmpty()) {

			ItemDao itemDao = DaoFactory.getItemDao();
			ItemDto itemDto = new ItemDto();
			itemDto.copyElements(item);

			logger.info(" [Item] : " + itemDto);
			Long itemId = itemDao.insert(itemDto);

			hReviewDto.setItemId(itemId);

		}

		logger.info(" [hReview] : " + hReviewDto);
		hReviewDao.insert(hReviewDto);

	}

	/**
	 * 最新のレビューを指定数分取得する。
	 * なおもしレビューワーの情報があればそれも同時に取得する。
	 * @param count
	 * @return
	 */
	public List findByLatestReview(Integer count) {

		HReviewDao hReviewDao = DaoFactory.getHReviewDao();
		List latestHReview = hReviewDao.findByLatestReview(count);

		logger.info(" [hReview Latest] : " + latestHReview.size());

		/*-- reviewr 情報があれば取得 --*/
		HCardDao hCardDao = DaoFactory.getHCardDao();
		for(Iterator itrLatestHReview = latestHReview.iterator(); itrLatestHReview.hasNext(); ) {
			HReviewDto hReviewDto = (HReviewDto) itrLatestHReview.next();
			HCardDto hCardDto = hCardDao.findByMicroformatsId(hReviewDto.getReviewerId());

			if(hCardDto == null) {
				hReviewDto.setHcard(new HCardDto());
			} else {
				hReviewDto.setHcard(hCardDto);
			}

		}
		return latestHReview;
	}


	/**
	 * レビュー総数を返す
	 * @param count
	 * @return
	 */
	public Long findByReviewCount() {
		HReviewDao hReviewDao = DaoFactory.getHReviewDao();
		Long count = hReviewDao.findByReviewCount();

		logger.info(" [hReview Count] : " + count);
		return count;
	}


}
