package cn.kuroko.faq.service;

import java.util.List;

import cn.kuroko.faq.bean.BaseBean;

/**
 * @author james
 * @time 2014-5-13 上午11:13:51
 */

public interface IAdmsetService<T extends BaseBean> extends IService<T> {
	public List<T> getPrefix();
}
