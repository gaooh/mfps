package jp.co.drecom.mfps.parser;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HReview;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.model.module.HCardModule;
import jp.co.drecom.mfps.model.module.HReviewModule;

import org.htmlparser.util.ParserException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class HReviewProcessHandler extends ProcessHandler {

	/** logger */
	private Logger logger = new Logger(getClass());

	public HReviewProcessHandler(String location) {
		super(location);
	}


	@Override
	protected List<Microformats> parse() {

		Crawler crawler = new Crawler(super.getLocation());
		HReviewParser parser = new HReviewParser(crawler.accession());

		try {
			parser.parser();
		} catch (ParserException e) {
			logger.warn(e);
		}
		return parser.getResultList();
	}

	@Override
	protected void save(List<Microformats> resultList) {

		final HReviewModule hReviewManager = new HReviewModule();
		final HCardModule hCardManager = new HCardModule();

		for(Iterator itrResultList = resultList.iterator() ; itrResultList.hasNext(); ) {

			final HReview hReview = (HReview) itrResultList.next();

			if(hReviewManager.isEntity(location, hReview.getChecksum())) {
				logger.info("this checkum date exsit. : " + hReview);
				// TODO ハッシュ値の判別なので完璧ではない。本当なら個別テーブルを詳細検索するべき
			} else {
				logger.info("this microformts perpetuate : " + hReview);

				final PlatformTransactionManager tranManager = DaoFactory.getTransactionManager();
				TransactionTemplate tranTemplate = new TransactionTemplate(tranManager);

				tranTemplate.execute(new TransactionCallbackWithoutResult() {

					protected void doInTransactionWithoutResult(TransactionStatus status) {
						hReviewManager.save(location, hReview);
						hCardManager.save(location, hReview.getHcard());
					}
				});
			}
		}
	}

}
