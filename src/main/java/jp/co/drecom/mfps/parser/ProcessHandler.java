package jp.co.drecom.mfps.parser;

import java.util.List;

import jp.co.drecom.mfps.microformats.Microformats;

/**
 * << Chain of Responsibilit>>
 *
 * 順番にパースを実行し、次のParserに渡していく
 *
 * @author Akiko Asami
 */
public abstract class ProcessHandler {

	/** 次の Parser */
	private ProcessHandler nextParser;

	/** parse 先 */
	protected final String location;

	public ProcessHandler(String location) {
		this.location = location;
	}

	/**
	 * 次のパーサを設定し、返す
	 * @param nextParser
	 * @return
	 */
	public ProcessHandler setNext(ProcessHandler nextParser) {
		this.nextParser = nextParser;
		return this.nextParser;
	}

	/**
	 * 連鎖を行う
	 * @param location
	 */
	public void request() {
		List<Microformats> resultList = parse();
		save(resultList);
		if(this.nextParser != null) {
			nextParser.request();
		}
	}

	public String getLocation() {
		return location;
	}

	/**
	 * パースをして取得した結果を返す。
	 * @return
	 */
	protected abstract List<Microformats> parse();

	/**
	 * パースした結果を保存する。
	 * @param resultList
	 */
	protected abstract void save(List<Microformats> resultList);
}
