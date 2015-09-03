package io.leopard.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonFastJsonImpl implements IJson {

	public JsonFastJsonImpl() {
		JSON.toJSON(null);// 当fastjson包没有引入时，让初始化失败.
	}

	@Override
	public String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	@Override
	public String toFormatJson(Object obj) {
		return JSON.toJSONString(obj, true);
	}

	@Override
	public <T> T toObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	@Override
	public <T> List<T> toListObject(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}

	@Override
	public <T> List<T> toListObject(List<String> jsonList, Class<T> clazz) {
		if (jsonList == null || jsonList.isEmpty()) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (String json : jsonList) {
			list.add(this.toObject(json, clazz));
		}
		return list;
	}

}
