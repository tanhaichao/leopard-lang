package io.leopard.httpnb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class HttpnbRequest {

	private String cookies;
	private List<Param> paramList = new ArrayList<Param>();
	List<Entry<String, String>> fileList = new ArrayList<Entry<String, String>>();

	public void setParamater(String name, Object value) {
		Param param = new Param();
		param.setName(name);
		param.setValue(value);
		this.paramList.add(param);
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public void setFile(String name, String path) {
		Entry<String, String> fileEntry = new SimpleEntry<String, String>(name, path);
		fileList.add(fileEntry);
	}

	public String execute(String url) {
		HttpHeader header = new HttpHeaderPostImpl(-1);
		if (cookies != null) {
			header.setCookie(cookies);
		}
		try {
			HttpURLConnection conn = header.openConnection(url);
			HttpUpload.upload(conn, fileList, paramList);
			return Httpnb.execute(conn);
		}
		catch (IOException e) {
			throw new HttpException(e, header);
		}

	}
}
