package io.leopard.lang.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class BeanUtil {

	public static <T> List<T> convert(List<?> list, Class<T> clazz) {
		if (list == null) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		for (Object obj : list) {
			result.add(convert(obj, clazz));
		}
		return result;
	}

	public static <T> T convert(Object obj, Class<T> clazz) {
		if (obj == null) {
			return null;
		}
		T target;
		try {
			target = clazz.newInstance();
		}
		catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		BeanUtils.copyProperties(obj, target);
		return target;
		// String json = Json.toJson(obj);
		// return Json.toObject(json, clazz, true);
	}
}
