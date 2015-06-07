package cn.kuroko.faq.bean;

/**
 * @author Kuroko
 * @time 2014-1-18 下午12:28:58
 */

public class Pager extends BaseBean {

	private int nowPage;
	private int maxPage;
	private String baseUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getUrl(int page) {
		return this.baseUrl.replaceAll("np=" + this.nowPage, "np=" + page);
	}

}
