package jp.co.drecom.mfps.model.dto;

import static org.junit.Assert.assertEquals;
import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.HCard;
import jp.co.drecom.mfps.microformats.HCard.HCardElement;

import org.junit.Test;

public class HCardDtoTest extends TestBase {

	private Logger logger = new Logger(getClass());

	/**
	 * HCardDto ‚Ì CopyElements ‚ÌƒeƒXƒg
	 */
	@Test
	public void sucesshCardDtoCopyElements() {

		HCard hCard = new HCard();
		hCard.setElementValue(HCardElement.TITLE, "ebisen title");
		hCard.setElementValue(HCardElement.NICKNAME, "‚¦‚Ñ‚¹‚ñ");

		HCardDto hCardDto = new HCardDto();
		hCardDto.copyElements(hCard);

		logger.debug("Title:" + hCardDto.getTitle());

		assertEquals(hCardDto.getTitle(), "ebisen title");
		assertEquals(hCardDto.getNickname(), "‚¦‚Ñ‚¹‚ñ");
	}
}
