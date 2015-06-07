package cn.kuroko.faq.util;

/**
 * @author Kuroko
 * @time 2014-1-17 下午5:44:11
 */

public class StringUtil {

	public static boolean isNullOrEmpty(String str) {
		return null == str || "".equals(str);
	}
}
