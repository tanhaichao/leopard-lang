package io.leopard.web.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

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
		if (value == null || value.length() == 0) {
			throw new IllegalArgumentException("cookie值不能为空.");
		}
		Cookie cookie = new Cookie(name, value);

		if (maxAge > -1) {
			cookie.setMaxAge(maxAge);
		}
		if (StringUtils.isEmpty(domain)) {
			cookie.setDomain(domain);
		}
		if (httpOnly) {
			cookie.setPath("/");
			cookie.setHttpOnly(true);
		}

		response.addCookie(cookie);
		return this;
	}
}
