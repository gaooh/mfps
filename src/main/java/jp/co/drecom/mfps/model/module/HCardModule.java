package jp.co.drecom.mfps.model.module;

import jp.co.drecom.mfps.common.DaoFactory;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Geo;
import jp.co.drecom.mfps.microformats.HCard;
import jp.co.drecom.mfps.microformats.Microformats;
import jp.co.drecom.mfps.model.dao.AdrDao;
import jp.co.drecom.mfps.model.dao.GeoDao;
import jp.co.drecom.mfps.model.dao.HCardDao;
import jp.co.drecom.mfps.model.dao.MicroformatsDao;
import jp.co.drecom.mfps.model.dao.NDao;
import jp.co.drecom.mfps.model.dao.OrgDao;
import jp.co.drecom.mfps.model.dto.AdrDto;
import jp.co.drecom.mfps.model.dto.GeoDto;
import jp.co.drecom.mfps.model.dto.HCardDto;
import jp.co.drecom.mfps.model.dto.MicroformatsDto;
import jp.co.drecom.mfps.model.dto.NDto;
import jp.co.drecom.mfps.model.dto.OrgDto;
import jp.co.drecom.mfps.util.TimestampUtil;

public class HCardModule extends MicroformatsModule {

	private Logger logger = new Logger(getClass());

	/**
	 * HCard‚ÌMicroformatsŠÖ˜A‚ð•Û‘¶‚·‚é
	 * @param location
	 * @param hCard
	 */
	public void save(final String location, final Microformats microformats) {

		HCard hCard = (HCard)microformats;

		MicroformatsDao microformatsDao = DaoFactory.getMicroformatsDao();
		final MicroformatsDto microformatsDto = microformatsDao.findByChecksum(location, hCard.getChecksum());

		/* -- Table:Microformats -- */
		microformatsDto.setUrl(location);
		microformatsDto.setRegistDate(TimestampUtil.getNowForSQL());
		microformatsDto.setUpdateDate(TimestampUtil.getNowForSQL());
		microformatsDto.setType(Microformats.Type.hCard.ordinal());
		microformatsDto.setChecksum(hCard.getChecksum());
		Long id = microformatsDao.insert(microformatsDto);

		/* -- Table:hcard -- */
		HCardDao hCardDao = DaoFactory.getHCardDao();
		HCardDto hCardDto = new HCardDto();
		hCardDto.setMicroformatsId(id);
		hCardDto.copyElements(hCard);

		/* -- Table:geo -- */
		Geo geo = hCard.getGeo();
		if (geo.isNotEmpty()) {
			/* -- Table:geo:microforamts -- */
			MicroformatsDto geoMicroformats = microformatsDao.findByChecksum(location, geo.getChecksum());

			if (geoMicroformats.isNotEmpty()) {
				logger.info("this checkum date exsit. : " + geo);
				hCardDto.setGeoId(geoMicroformats.getId());

			} else {
				geoMicroformats.setUrl(location);
				geoMicroformats.setRegistDate(TimestampUtil.getNowForSQL());
				geoMicroformats.setUpdateDate(TimestampUtil.getNowForSQL());
				geoMicroformats.setType(Microformats.Type.geo.ordinal());
				geoMicroformats.setChecksum(geo.getChecksum());

				Long geoId = microformatsDao.insert(geoMicroformats);

				GeoDao geoDao = DaoFactory.getGeoDao();
				GeoDto geoDto = new GeoDto();
				geoDto.setMicroformatsId(geoId);
				geoDto.copyElements(geo);

				logger.info(" store - [Geo] : " + geoDto);
				geoDao.insert(geoDto);

				hCardDto.setGeoId(geoId);
			}

		}

		/* -- Table:Adr -- */
		if (hCard.getAdr().isNotEmpty()) {
			AdrDao adrDao = DaoFactory.getAdrDao();
			AdrDto adrDto = new AdrDto();
			adrDto.copyElements(hCard.getAdr());

			logger.info(" store - [Adr] : " + adrDto);
			Long adrId = adrDao.insert(adrDto);

			hCardDto.setAdrId(adrId);
		}

		/* -- Table:N -- */
		if (hCard.getN().isNotEmpty()) {
			NDao nDao = DaoFactory.getNDao();
			NDto nDto = new NDto();
			nDto.copyElements(hCard.getN());

			logger.info(" store - [N] : " + nDto);
			Long nId = nDao.insert(nDto);

			hCardDto.setNId(nId);
		}

		/* -- Table:Org -- */
		if (hCard.getOrg().isNotEmpty()) {
			OrgDao orgDao = DaoFactory.getOrgDao();
			OrgDto orgDto = new OrgDto();
			orgDto.copyElements(hCard.getOrg());

			logger.info(" store - [Org] : " + orgDto);
			Long orgId = orgDao.insert(orgDto);

			hCardDto.setOrgId(orgId);
		}

		logger.info(" store - [HCard] : " + hCardDto);
		hCardDao.insert(hCardDto);

	}
}
