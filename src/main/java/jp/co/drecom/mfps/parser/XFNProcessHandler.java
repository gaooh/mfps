package jp.co.drecom.mfps.parser;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.microformats.XFN;
import jp.co.drecom.mfps.model.module.XFNModule;

import org.htmlparser.util.ParserException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class XFNProcessHandler extends ProcessHandler {

	public XFNProcessHandler(String location) {
		super(location);
	}

	private Logger logger = new Logger(getClass());

	@Override
	protected List<Microformats> parse() {

		Crawler crawler = new Crawler(super.getLocation());
		XFNParser parser = new XFNParser(crawler.accession());

		try {
			parser.parser();
		} catch (ParserException e) {
			logger.warn(e);
		}
		return parser.getResultList();
	}

	@Override
	protected void save(List<Microformats> resultList) {

		final XFNModule xfnManager = new XFNModule();

		for(Iterator itrResultList = resultList.iterator() ; itrResultList.hasNext(); ) {

			final XFN xfn = (XFN) itrResultList.next();

			if(xfnManager.isEntity(location, xfn.getChecksum())) {
				logger.info("this checkum date exsit. : " + xfn);
				// TODO ハッシュ値の判別なので完璧ではない。本当なら個別テーブルを詳細検索するべき
			} else {
				logger.info("this microformts perpetuate : " + xfn);

				final PlatformTransactionManager tranManager = DaoFactory.getTransactionManager();
				TransactionTemplate tranTemplate = new TransactionTemplate(tranManager);

				tranTemplate.execute(new TransactionCallbackWithoutResult() {

					protected void doInTransactionWithoutResult(TransactionStatus status) {
						xfnManager.save(location, xfn);
					}
				});
			}
		}
	}

}
