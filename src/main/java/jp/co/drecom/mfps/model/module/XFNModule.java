package jp.co.drecom.mfps.model.module;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.microformats.XFN;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dao.XFNDao;
import jp.co.drecom.mfps.model.dao.XFNRegDao;
import jp.co.drecom.mfps.model.dao.XFNToRegDao;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;
import jp.co.drecom.mfps.model.dto.XFNDto;
import jp.co.drecom.mfps.model.dto.XFNRegDto;
import jp.co.drecom.mfps.model.dto.XFNToRegDto;
import jp.co.drecom.mfps.util.TimestampUtil;

public class XFNModule extends MicroformatsModule {

	private Logger logger = new Logger(getClass());

	@Override
	public void save(String location, Microformats microformats) {
		XFN xfn = (XFN)microformats;

		MicroformatsDao microformatsDao = DaoFactory.getMicroformatsDao();
		final MicroformatsDto microformatsDto = microformatsDao.findByChecksum(location, xfn.getChecksum());

		/* -- Table:Microformats -- */
		microformatsDto.setUrl(location);
		microformatsDto.setRegistDate(TimestampUtil.getNowForSQL());
		microformatsDto.setUpdateDate(TimestampUtil.getNowForSQL());
		microformatsDto.setType(Microformats.Type.hCard.ordinal());
		microformatsDto.setChecksum(xfn.getChecksum());

		logger.info(" store - [Microformats] : " + microformatsDto);
		Long id = microformatsDao.insert(microformatsDto);

		/* -- Table:XFN -- */
		XFNDao xfnDao = DaoFactory.getXFNDao();
		XFNDto xfnDto = new XFNDto();
		xfnDto.setMicroformatsId(id);
		xfnDto.copyElements(xfn);

		logger.info(" store - [XFN] : " + xfnDto);
		xfnDao.insert(xfnDto);

		String[] rels = xfn.getRels();
		for(int i = 0; i < rels.length ; i ++ ) {
			XFNRegDao xfnRegDao = DaoFactory.getXFNRegDao();
			XFNRegDto xfnRegDto = xfnRegDao.findByName(rels[i]);

			if(xfnRegDto != null) {
				/* -- Table: XFN_TO_REG -- */
				XFNToRegDao xfnToRegDao = DaoFactory.getXFNToRegDao();
				XFNToRegDto xfnToRegDto = new XFNToRegDto();
				xfnToRegDto.setRegId(id);
				xfnToRegDto.setXfnId(xfnRegDto.getId());

				logger.info(" store - [XFNToReg] : " + xfnRegDto);
				xfnToRegDao.insert(xfnToRegDto);
			}

		}
	}

}
