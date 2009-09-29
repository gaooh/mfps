package jp.co.drecom.mfps.recept;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpc;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;
import org.junit.Test;

import jp.co.drecom.mfps.common.TestBase;
import jp.co.drecom.mfps.util.UnitTestUtil;

public class WebLogUpdatesHandlerTest extends TestBase {

	/**
	 * Ping 受信成功
	 */
//	@Test
//	public void sucessPing() {
//		WebLogUpdatesHandler hundler = new WebLogUpdatesHandler("127.0.0.1");
//		Hashtable result = hundler.ping("drecom", "http://www.drecom.co.jp/");
//
//		assertEquals(result.get("message"), "recept ping. Thanks");
//		assertEquals(result.get("error"), Boolean.FALSE);
//
//	}

	/**
	 * Ping 受信失敗 URLのフォーマットがおかしい
	 */
	@Test
	public void failPing() {
		WebLogUpdatesHandler hundler = new WebLogUpdatesHandler("127.0.0.1");
		Hashtable result = hundler.ping("drecom", "not url");

		assertEquals(result.get("message"), "This formats error. [not url]");
		assertEquals(result.get("error"), Boolean.TRUE);
	}

	/**
	 * Ping 受信失敗 name が長すぎる
	 */
	@Test
	public void failLongNamePing() {
		String longName = UnitTestUtil.generatLongValue(1025);

		WebLogUpdatesHandler hundler = new WebLogUpdatesHandler("127.0.0.1");
		Hashtable result = hundler.ping(longName, "http://www.drecom.co.jp/");

		assertEquals(result.get("message"), "name of site limit of 1,024");
		assertEquals(result.get("error"), Boolean.TRUE);
	}

	/**
	 * name をチェックし問題があったらエラーメッセージを返す name が長すぎる
	 */
	@Test
	public void succesValidName() {
		String lognName = UnitTestUtil.generatLongValue(1025);

		WebLogUpdatesHandler hundler = new WebLogUpdatesHandler("127.0.0.1");
		String errorKey = hundler.validName(lognName);

		assertEquals(errorKey, "name of site limit of 1,024");
	}

	@Test
	public void testping() {
		String name = "ebisensen";
		String url = "http://onk.blog.drecom.jp/";

		XmlRpcClient client = null;
		Hashtable result_hash = new Hashtable();

		try{
			XmlRpc.setEncoding("UTF-8");
			client = new XmlRpcClient ("http://localhost/mfps/xmlrpc");

		} catch(MalformedURLException e){
			System.err.println(e);
		}

		Vector<String> vec = new Vector<String>();
		vec.addElement(name);
		vec.addElement(url);

		try {
			result_hash = (Hashtable)client.execute ("weblogUpdates.ping", vec);
			if (result_hash == null) {
				System.out.println("No Result Back from execute");
			}

			System.out.println(result_hash);
		}catch (IOException e){
			System.out.println("IOException" + e);
		}catch (XmlRpcException e){
			System.out.println("XML-RPC Exception" + e);
		}


	}

}
