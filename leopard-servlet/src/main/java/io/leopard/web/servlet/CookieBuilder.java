package io.leopard.web.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieBuilder {

	private String name;
	private String value;
	private String domain;
	private int maxAge = -1;
	private boolean httpOnly;

	private HttpServletResponse response;

	public CookieBuilder(String name, long value, HttpServletResponse response) {
		this(name, Long.toString(value), response);
	}

	public CookieBuilder(String name, int value, HttpServletResponse response) {
		this(name, Integer.toString(value), response);
	}

	public CookieBuilder(String name, String value, HttpServletResponse response) {
		this.name = name;
		this.value = value;
		this.response = response;
	}

	public CookieBuilder setMaxAge(int maxAge, boolean remember) {
		if (remember) {
			this.maxAge = maxAge;
		}
		return this;
	}

	public CookieBuilder setMaxAge(int maxAge) {
		this.maxAge = maxAge;
		return this;
	}

	public CookieBuilder setDomain(String domain) {
		this.domain = domain;
		return this;
	}

	public CookieBuilder setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
		return this;
	}

	public CookieBuilder build() {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("cookie名称不能为空.");
		}
		// if (value == null || value.length() == 0) {
		// throw new IllegalArgumentException("cookie[" + name + "]值不能为空.");
		// }
		Cookie cookie = new Cookie(name, value);

		if (maxAge > -1) {
			cookie.setMaxAge(maxAge);
		}
		if (domain != null && domain.length() > 0) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		if (httpOnly) {
			cookie.setHttpOnly(true);
		}
		// response.addHeader("P3P", "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");

		response.addCookie(cookie);
		return this;
	}
}
