package io.leopard.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResourceHandler {

	boolean doHandler(String uri, HttpServletRequest request, HttpServletResponse response);
}
