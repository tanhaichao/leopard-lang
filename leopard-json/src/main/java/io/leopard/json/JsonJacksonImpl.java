package io.leopard.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.type.TypeFactory;

public class JsonJacksonImpl implements IJson {
	private static ObjectMapper mapper = new ObjectMapper(); // can reuse, share
	private static ObjectMapper mapperIgnoreUnknownField = new ObjectMapper(); // 忽略不存在的字段.
	private static ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

	static {
		mapperIgnoreUnknownField.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 将对象转成json.
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	@Override
	public String toFormatJson(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return writer.writeValueAsString(obj);
		}
		catch (Exception e) {
			throw new JsonException(e.getMessage(), e);
		}

	}

	/**
	 * 将对象转成json.
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	@Override
	public String toJson(Object obj) {
		try {
			String json;
			if (obj == null) {
				json = null;
			}
			else {
				json = mapper.writeValueAsString(obj);
			}
			return json;
		}
		catch (Exception e) {
			throw new JsonException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public <T> List<T> toListObject(String json, Class<T> clazz) {
		if (json == null || json.length() == 0) {
			return null;
		}
		try {
			return mapper.readValue(json, TypeFactory.collectionType(List.class, clazz));
		}
		catch (Exception e) {
			throw new JsonException(e.getMessage(), e);
		}

	}

	// public static <T> List<T> toObject(List<String> jsonList, Class<T> valueType) {
	// return toObject(jsonList, valueType, false);
	// }

	// public static <T> List<T> toObject(List<String> jsonList, Class<T> valueType, boolean ignoreUnknownField) {
	// if (jsonList == null || jsonList.isEmpty()) {
	// return null;
	// }
	// List<T> list = new ArrayList<T>();
	// for (String json : jsonList) {
	// list.add(JsonJacksonImpl.toObject(json, valueType, ignoreUnknownField));
	// }
	// return list;
	// }

	// @SuppressWarnings("unchecked")
	// public static Map<String, Object> toMap(String content) {
	// return JsonJacksonImpl.toObject(content, Map.class);
	// }
	//
	// @SuppressWarnings("unchecked")
	// public static Set<Object> toSet(String content) {
	// return JsonJacksonImpl.toObject(content, Set.class);
	// }
	//
	// @SuppressWarnings("unchecked")
	// public static <T> Map<String, T> toMap(String json, Class<T> clazz) {
	// return JsonJacksonImpl.toObject(json, Map.class);
	// }
	//
	// @SuppressWarnings("unchecked")
	// public static <T> Set<T> toSet(String json, Class<T> clazz) {
	// return JsonJacksonImpl.toObject(json, Set.class);
	// }

	// @SuppressWarnings("unchecked")
	// public static Map<String, Object> toNotNullMap(String json) {
	// Map<String, Object> map = JsonJacksonImpl.toObject(json, Map.class);
	// if (map == null) {
	// map = new LinkedHashMap<String, Object>();
	// }
	// return map;
	// }

	// @SuppressWarnings("unchecked")
	// public static <T> Map<String, T> toNotNullMap(String json, Class<T> clazz) {
	// Map<String, T> map = JsonJacksonImpl.toObject(json, Map.class);
	// if (map == null) {
	// map = new LinkedHashMap<String, T>();
	// }
	// return map;
	// }

	// @SuppressWarnings("unchecked")
	// public static <T> Set<T> toNotNullSet(String json, Class<T> clazz) {
	// Set<T> set = JsonJacksonImpl.toObject(json, Set.class);
	// if (set == null) {
	// set = new LinkedHashSet<T>();
	// }
	// return set;
	// }

	/**
	 * 将Json转换成对象.
	 * 
	 * @param json
	 * @param valueType
	 * @return
	 */
	@Override
	public <T> T toObject(String json, Class<T> clazz) {
		return toObject(json, clazz, false);
	}

	/**
	 * 将Json转换成对象.
	 * 
	 * @param json
	 * @param clazz
	 * @param ignoreUnknownField
	 *            是否忽略不存在的字段?
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> clazz, boolean ignoreUnknownField) {
		if (json == null || json.length() == 0) {
			return null;
		}

		try {
			if (ignoreUnknownField) {
				return mapperIgnoreUnknownField.readValue(json, clazz);
			}
			else {
				return mapper.readValue(json, clazz);
			}
		}
		catch (Exception e) {
			throw new JsonException(e.getMessage(), e);
		}
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
