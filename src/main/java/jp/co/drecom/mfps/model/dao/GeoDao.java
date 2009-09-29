package jp.co.drecom.mfps.model.dao;

import jp.co.drecom.mfps.model.dto.GeoDto;

import org.springframework.dao.DataAccessException;

/**
*
* Model: geo
*
* @author Akiko Asami
*/
public interface GeoDao {

	/**
	 * ë}ì¸èàóù
	 * @param record
	 * @throws DataAccessException
	 */
	public void insert(GeoDto record) throws DataAccessException;

}
