package jp.co.drecom.mfps.model.module;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;

abstract class MicroformatsModule {

	/**
	 * ë∂ç›Ç∑ÇÈÇ©Ç«Ç§Ç©ÇämîFÇ∑ÇÈ
	 * @param location
	 * @param hCard
	 * @return
	 */
	public boolean isEntity(final String location, String checksum) {
		MicroformatsDao microformatsDao = DaoFactory.getMicroformatsDao();
		MicroformatsDto microformatsDto = microformatsDao.findByChecksum(location, checksum);
		return microformatsDto.isNotEmpty();
	}

	public abstract void save(final String location, final Microformats microformats);
}
