package cn.kuroko.faq.util;

import java.security.MessageDigest;

/**
 * @author Kuroko
 * @time 2013-12-23 上午11:46:30
 */

public class MD5Util {

	public static String calc(String base) {
		if (null == base || "".equals(base))
			return "";
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] bstr = base.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bstr);
			byte[] bresult = md.digest();
			int length = bresult.length;
			char[] str = new char[length * 2];
			int i = 0;
			for (int j = 0; j < length; j++) {
				str[i++] = hexDigits[bresult[j] >>> 4 & 0xf];
				str[i++] = hexDigits[bresult[j] & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
