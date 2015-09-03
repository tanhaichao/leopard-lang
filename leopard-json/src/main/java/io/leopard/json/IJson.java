package io.leopard.json;

import java.util.List;

public interface IJson {

	/**
	 * 将对象转成json.
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	String toJson(Object obj);

	/**
	 * 将对象转成json.
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	String toFormatJson(Object obj);

	/**
	 * 将Json转换成对象.
	 * 
	 * @param json
	 * @param valueType
	 * @return
	 */
	<T> T toObject(String json, Class<T> clazz);

	<T> List<T> toListObject(String json, Class<T> clazz);

	<T> List<T> toListObject(List<String> jsonList, Class<T> clazz);

}
