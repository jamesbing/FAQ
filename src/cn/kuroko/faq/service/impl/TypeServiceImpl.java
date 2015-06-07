package cn.kuroko.faq.service.impl;

import java.util.List;

import cn.kuroko.faq.bean.Type;
import cn.kuroko.faq.service.ITypeService;

/**
 * @author Kuroko
 * @time 2014-1-22 上午10:48:51
 */

public class TypeServiceImpl<T extends Type> extends ServiceImp<T> implements
		ITypeService<T> {

	@Override
	public T getTypeByName(String name) {
		List<T> lstType = this.dao.list("select t from Type t where t.name='" + name + "'");
		if(null != lstType && !lstType.isEmpty())
			return lstType.get(0);
		return null;
	}

	@Override
	public void create(T baseBean) {
		// TODO Auto-generated method stub
		
	}

}
