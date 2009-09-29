package jp.co.drecom.mfps.parser;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import jp.co.drecom.mfps.log.Logger;
import jp.co.drecom.mfps.microformats.Microformats;

import org.htmlparser.Parser;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.util.ParserException;

/**
 * HTMLから Microformatsを解析する規定クラス
 *
 * @author Akiko Asami
 */
abstract class MicroformatsParser {

	private Logger logger = new Logger(getClass());

	/** 解析対象コネクション */
	private URLConnection connection = null;

	/** 解析対象CLASS */
	protected String markClass = null;

	/** 解析対象テキスト */
	private String text = null;

	/** 結果クラス */
	protected List<Microformats> resultList = new ArrayList<Microformats>();

	public MicroformatsParser(String markClass, String text) {
		this.markClass = markClass;
		this.text = text;
		this.connection = null;
	}

	public MicroformatsParser(URLConnection connection) {
		this.connection = connection;
		this.markClass = null;
		this.text = null;
	}

	/**
	 * Parser を取得
	 * 解析対象がない場合などは null がかえる。
	 * @return
	 * @throws ParserException
	 */
	protected Parser getParser() throws ParserException {
		if(this.connection != null) {
			logger.debug("parse for url: " + this.connection.getURL());
			return new Parser(this.connection.getURL().toString());

		} else if(this.text != null) {
			logger.debug("parse for text: " + this.text);
			return new Parser(new Lexer(this.text));

		} else {
			return null;
		}
	}

	/**
	 * parse処理実行。
	 * @throws ParserException
	 */
	abstract protected void parser() throws ParserException;

	protected List<Microformats> getResultList() {
		return resultList;
	}


}
