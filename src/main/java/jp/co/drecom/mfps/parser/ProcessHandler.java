package jp.co.drecom.mfps.parser;

import java.util.List;

import jp.co.drecom.mfps.microformats.Microformats;

/**
 * << Chain of Responsibilit>>
 *
 * ���ԂɃp�[�X�����s���A����Parser�ɓn���Ă���
 *
 * @author Akiko Asami
 */
public abstract class ProcessHandler {

	/** ���� Parser */
	private ProcessHandler nextParser;

	/** parse �� */
	protected final String location;

	public ProcessHandler(String location) {
		this.location = location;
	}

	/**
	 * ���̃p�[�T��ݒ肵�A�Ԃ�
	 * @param nextParser
	 * @return
	 */
	public ProcessHandler setNext(ProcessHandler nextParser) {
		this.nextParser = nextParser;
		return this.nextParser;
	}

	/**
	 * �A�����s��
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
	 * �p�[�X�����Ď擾�������ʂ�Ԃ��B
	 * @return
	 */
	protected abstract List<Microformats> parse();

	/**
	 * �p�[�X�������ʂ�ۑ�����B
	 * @param resultList
	 */
	protected abstract void save(List<Microformats> resultList);
}
