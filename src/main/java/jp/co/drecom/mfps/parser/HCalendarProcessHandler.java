package jp.co.drecom.mfps.parser;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HCalendar;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.model.module.HCalendarModule;

import org.htmlparser.util.ParserException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class HCalendarProcessHandler extends ProcessHandler {

	/** logger */
	private Logger logger = new Logger(getClass());

	public HCalendarProcessHandler(String location) {
		super(location);
	}

	@Override
	protected List<Microformats> parse() {
		HCalendarParser parser = null;
		Crawler crawler = new Crawler(super.getLocation());
		parser = new HCalendarParser(crawler.accession());
		try {
			parser.parser();
		} catch (ParserException e) {
			logger.warn(e);
		}
		return parser.getResultList();
	}

	@Override
	protected void save(List<Microformats> resultList) {
		final HCalendarModule hCardManager = new HCalendarModule();

		for(Iterator itrResultList = resultList.iterator() ; itrResultList.hasNext(); ) {

			final HCalendar hCalendar = (HCalendar) itrResultList.next();

			if(hCardManager.isEntity(location, hCalendar.getChecksum())) {
				logger.info("this checkum date exsit. : " + hCalendar);
				// TODO ハッシュ値の判別なので完璧ではない。本当なら個別テーブルを詳細検索するべき
			} else {
				logger.info("this microformts perpetuate : " + hCalendar);

				final PlatformTransactionManager tranManager = DaoFactory.getTransactionManager();
				TransactionTemplate tranTemplate = new TransactionTemplate(tranManager);

				tranTemplate.execute(new TransactionCallbackWithoutResult() {

					protected void doInTransactionWithoutResult(TransactionStatus status) {
						hCardManager.save(location, hCalendar);
					}
				});
			}
		}

	}

}
