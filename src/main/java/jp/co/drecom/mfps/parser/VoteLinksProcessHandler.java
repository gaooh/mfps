package jp.co.drecom.mfps.parser;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.microformats.VoteLinks;
import jp.co.drecom.mfps.model.module.VoteLinksModule;

import org.htmlparser.util.ParserException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class VoteLinksProcessHandler extends ProcessHandler {

	private Logger logger = new Logger(getClass());

	public VoteLinksProcessHandler(String location) {
		super(location);
	}

	@Override
	protected List<Microformats> parse() {

		Crawler crawler = new Crawler(super.getLocation());
		VoteLinksParser parser = new VoteLinksParser(crawler.accession());

		try {
			parser.parser();
		} catch (ParserException e) {
			logger.warn(e);
		}
		return parser.getResultList();
	}

	@Override
	protected void save(List<Microformats> resultList) {

		final VoteLinksModule voteLinksManager = new VoteLinksModule();

		for(Iterator itrResultList = resultList.iterator() ; itrResultList.hasNext(); ) {

			final VoteLinks voteLinks = (VoteLinks) itrResultList.next();

			if(voteLinksManager.isEntity(location, voteLinks.getChecksum())) {
				logger.info("this checkum date exsit. : " + voteLinks);
				// TODO ハッシュ値の判別なので完璧ではない。本当なら個別テーブルを詳細検索するべき
			} else {
				logger.info("this microformts perpetuate : " + voteLinks);

				final PlatformTransactionManager tranManager = DaoFactory.getTransactionManager();
				TransactionTemplate tranTemplate = new TransactionTemplate(tranManager);

				tranTemplate.execute(new TransactionCallbackWithoutResult() {

					protected void doInTransactionWithoutResult(TransactionStatus status) {
						voteLinksManager.save(location, voteLinks);
					}
				});
			}
		}
	}

}
