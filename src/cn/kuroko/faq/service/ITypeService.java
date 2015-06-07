package cn.kuroko.faq.service;

import cn.kuroko.faq.bean.Type;

/**
 * @author Kuroko
 * @time 2014-1-22 上午10:47:39
 */

public interface ITypeService<T extends Type> extends IService<T> {

	public T getTypeByName(String name);

}
