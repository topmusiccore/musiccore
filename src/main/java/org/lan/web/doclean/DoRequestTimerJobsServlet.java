package org.lan.web.doclean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lan.web.service.DoRequestTimerJobs;

public class DoRequestTimerJobsServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    }

	@Override
	public void init() throws ServletException {
		super.init();
		DoRequestTimerJobs.start();
	}
}