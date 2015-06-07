package cn.kuroko.faq.service.impl;

import java.util.List;
import cn.kuroko.faq.bean.BaseBean;
import cn.kuroko.faq.service.IAdmsetService;

/**
 * @author james
 * @time 2014-5-13 上午11:12:30
 */

public class AdmsetServiceImpl<T extends BaseBean> extends ServiceImp<T>
		implements IAdmsetService<T>{

	@Override
	public void create(T baseBean) {
		// TODO Auto-generated method stub
		
	}
	public List<T> getPrefix() {
		return this.dao
				.list("select a from Admset a");
	}


}
