package jp.co.drecom.mfps.parser;

import java.util.Iterator;
import java.util.List;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.controller.Crawler;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.microformats.RelTag;
import jp.co.drecom.mfps.model.module.RelTagModule;

import org.htmlparser.util.ParserException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class RelTagProcessHandler extends ProcessHandler {

	private Logger logger = new Logger(getClass());

	public RelTagProcessHandler(String location) {
		super(location);
	}

	@Override
	protected List<Microformats> parse() {

		Crawler crawler = new Crawler(super.getLocation());
		RelTagParser parser = new RelTagParser(crawler.accession());

		try {
			parser.parser();
		} catch (ParserException e) {
			logger.warn(e);
		}
		return parser.getResultList();
	}

	@Override
	protected void save(List<Microformats> resultList) {
		final RelTagModule relTagManager = new RelTagModule();

		for(Iterator itrResultList = resultList.iterator() ; itrResultList.hasNext(); ) {

			final RelTag relTag = (RelTag) itrResultList.next();

			if(relTagManager.isEntity(location, relTag.getChecksum())) {
				logger.info("this checkum date exsit. : " + relTag);
				// TODO �n�b�V���l�̔��ʂȂ̂Ŋ����ł͂Ȃ��B�{���Ȃ�ʃe�[�u�����ڍ׌�������ׂ�
			} else {
				logger.info("this microformts perpetuate : " + relTag);

				final PlatformTransactionManager tranManager = DaoFactory.getTransactionManager();
				TransactionTemplate tranTemplate = new TransactionTemplate(tranManager);

				tranTemplate.execute(new TransactionCallbackWithoutResult() {

					protected void doInTransactionWithoutResult(TransactionStatus status) {
						relTagManager.save(location, relTag);
					}
				});
			}
		}
	}

}
