package io.leopard.web.servlet;

import io.leopard.httpnb.Httpnb;
import io.leopard.jetty.JettyServer;

import org.junit.Test;

public class TestServletTest {

	@Test
	public void TestServlet() throws Exception {
		JettyServer.start("src/test/webapp");
		String result = Httpnb.doGet("http://localhost//passport/test.leo");
		System.out.println("result:" + result);
	}

}