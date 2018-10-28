package com.test.servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.utils.SystemProperty;

public class HolaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger LOG = Logger.getLogger(getServletName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		Properties properties = System.getProperties();

		response.setContentType("text/plain");
		response.getWriter().println("Hello App Engine - Standard using " + SystemProperty.version.get() + " Java "
				+ properties.get("java.specification.version"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		LOG.info("doPost");
		resp.getWriter().println("Holaaa!");

	}

}
