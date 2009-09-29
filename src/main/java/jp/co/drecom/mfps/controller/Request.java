package jp.co.drecom.mfps.controller;

import jp.co.drecom.mfps.model.dto.WebLogUpdateDto;
import jp.co.drecom.mfps.model.module.WebLogUpdateModule;
import jp.co.drecom.mfps.parser.HCalendarProcessHandler;
import jp.co.drecom.mfps.parser.HCardProcessHandler;
import jp.co.drecom.mfps.parser.HReviewProcessHandler;
import jp.co.drecom.mfps.parser.ProcessHandler;
import jp.co.drecom.mfps.parser.VoteLinksProcessHandler;
import jp.co.drecom.mfps.parser.XFNProcessHandler;

public class Request {

	private final WebLogUpdateDto webLogUpdateDto;

    public Request(WebLogUpdateDto webLogUpdateDto) {
        this.webLogUpdateDto = webLogUpdateDto;
    }
    public void execute() {
    	/* -- âêÕé¿çs -- */
    	ProcessHandler cardHanlder = new HCardProcessHandler(webLogUpdateDto.getUrl());
		ProcessHandler reviewHandler = new HReviewProcessHandler(webLogUpdateDto.getUrl());
		ProcessHandler calendarHandler = new HCalendarProcessHandler(webLogUpdateDto.getUrl());
		ProcessHandler voteLinksHandler = new VoteLinksProcessHandler(webLogUpdateDto.getUrl());
		ProcessHandler xfnHandler = new XFNProcessHandler(webLogUpdateDto.getUrl());

		cardHanlder.setNext(reviewHandler).setNext(calendarHandler).setNext(voteLinksHandler).setNext(xfnHandler);
		cardHanlder.request();

		/* -- èàóùèIóπ -- */
		WebLogUpdateModule updateModuls = new WebLogUpdateModule();
		updateModuls.complet(webLogUpdateDto);
    }

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";

	    String retValue = "";

	    retValue = "Request ( "
	        + super.toString() + TAB
	        + "webLogUpdateDto = " + this.webLogUpdateDto + TAB
	        + " )";

	    return retValue;
	}


}
