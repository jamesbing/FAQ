package cn.kuroko.faq.service.impl;

import cn.kuroko.faq.bean.ExpertType;
import cn.kuroko.faq.service.IExpertTypeService;

/**
 * @author Kuroko
 * @time 2014-1-22 上午10:48:51
 */

public class ExpertTypeServiceImpl<T extends ExpertType> extends ServiceImp<T> implements
		IExpertTypeService<T> {

	@Override
	public void create(T baseBean) {
		this.dao.create(baseBean);
	}
}
