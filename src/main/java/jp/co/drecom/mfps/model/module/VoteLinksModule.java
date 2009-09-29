package jp.co.drecom.mfps.model.module;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.microformats.VoteLinks;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dao.VoteLinksDao;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;
import jp.co.drecom.mfps.model.dto.VoteLinksDto;
import jp.co.drecom.mfps.util.TimestampUtil;

public class VoteLinksModule extends MicroformatsModule {

	private Logger logger = new Logger(getClass());

	@Override
	public void save(String location, Microformats microformats) {
		VoteLinks voteLinks = (VoteLinks)microformats;

		MicroformatsDao microformatsDao = DaoFactory.getMicroformatsDao();
		final MicroformatsDto microformatsDto = microformatsDao.findByChecksum(location, voteLinks.getChecksum());

		/* -- Table:Microformats -- */
		microformatsDto.setUrl(location);
		microformatsDto.setRegistDate(TimestampUtil.getNowForSQL());
		microformatsDto.setUpdateDate(TimestampUtil.getNowForSQL());
		microformatsDto.setType(Microformats.Type.voteLinks.ordinal());
		microformatsDto.setChecksum(voteLinks.getChecksum());
		Long id = microformatsDao.insert(microformatsDto);

		/* -- Table:VoteLiks -- */
		VoteLinksDao voteLinksDao = DaoFactory.getVoteLinksDao();
		VoteLinksDto voteLinksDto = new VoteLinksDto();
		voteLinksDto.setMicroformatsId(id);
		voteLinksDto.copyElements(voteLinks);

		logger.info(" store - [VoteLinks] : " + voteLinksDto);
		voteLinksDao.insert(voteLinksDto);
	}

}
