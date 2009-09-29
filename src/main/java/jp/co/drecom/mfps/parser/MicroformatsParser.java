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
 * HTML���� Microformats����͂���K��N���X
 *
 * @author Akiko Asami
 */
abstract class MicroformatsParser {

	private Logger logger = new Logger(getClass());

	/** ��͑ΏۃR�l�N�V���� */
	private URLConnection connection = null;

	/** ��͑Ώ�CLASS */
	protected String markClass = null;

	/** ��͑Ώۃe�L�X�g */
	private String text = null;

	/** ���ʃN���X */
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
	 * Parser ���擾
	 * ��͑Ώۂ��Ȃ��ꍇ�Ȃǂ� null ��������B
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
	 * parse�������s�B
	 * @throws ParserException
	 */
	abstract protected void parser() throws ParserException;

	protected List<Microformats> getResultList() {
		return resultList;
	}


}
