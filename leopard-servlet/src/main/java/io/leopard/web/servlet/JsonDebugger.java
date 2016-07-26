package io.leopard.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * Json调试工具
 * 
 * @author 谭海潮
 *
 */
public class JsonDebugger {
	public static void setDebug(Object obj) {
		HttpServletRequest request = RequestUtil.getCurrentRequest();
		if (request == null) {
			return;
		}
		request.setAttribute("debug", obj);
	}
}
