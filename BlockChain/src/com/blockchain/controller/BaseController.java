package com.blockchain.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.blockchain.util.GsonUtil;

public class BaseController {

	public void outJson(HttpServletResponse response, Object object) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			String json = GsonUtil.getGson().toJson(object);
			response.setContentType("text/html;charset=utf-8");
			pw.println(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void outString(HttpServletResponse response, String msg) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			pw.write(msg);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到HTTP request
	 * */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到HTTP response
	 * */
	protected HttpServletResponse getResponse() {
		HttpServletResponse respone = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		return respone;
	}

	/**
	 * 得到session
	 * */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

}