package com.lwrs.source;


import com.google.gson.Gson;
import com.lwrs.bean.dto.resp.BaseRespDto;
import com.lwrs.enums.RespCodeEnum;
import com.lwrs.utils.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by zsk on 16/9/15.
 */
@Path("/picc")
public class mainSource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	public String test(@QueryParam("param") String param){
		Logger.info(this, "param=" + param);

		String s = new BaseRespDto(RespCodeEnum.OK, param).toString();
		return s;

	}

}
