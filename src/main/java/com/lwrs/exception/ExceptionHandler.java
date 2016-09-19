package com.lwrs.exception;

import com.lwrs.utils.LocalConstant;
import com.lwrs.utils.Logger;
import com.sun.jersey.api.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by zsk on 16/9/18.
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<RuntimeException>{

	@Context
	private HttpServletRequest request;

	@Override
	public Response toResponse(RuntimeException exception) {
		Logger.error(this, String.format("exception in request=%s", request.getRequestURL()), exception);

		String host = request.getServerName();
		int port = request.getServerPort();

		if(exception instanceof NotFoundException){
			String redirectURI = String.format("http://%s:%s/%s", host, port, LocalConstant.ERROR_PAGE_PATH);
			return Response.status(Response.Status.SEE_OTHER).header(HttpHeaders.LOCATION, redirectURI).build();
		}

		//else handler other Exception


		String redirectURI = String.format("http://%s:%s/%s", host, port, LocalConstant.HOME_PATH);
		return Response.status(Response.Status.SEE_OTHER).header(HttpHeaders.LOCATION, redirectURI).build();
	}
}
