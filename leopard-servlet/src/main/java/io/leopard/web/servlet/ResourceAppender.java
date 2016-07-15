package io.leopard.web.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;

public interface ResourceAppender {

	void append(HttpServletRequest request, String path, Resource resource);

}
