package com.lwrs.bean.dto.resp;

import com.lwrs.bean.BaseBean;
import com.lwrs.enums.RespCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

/**
 * Created by zsk on 16/9/15.
 */
public class BaseRespDto extends BaseBean{

	private String resCode;
	private String resMsg;

	public BaseRespDto() {
		this.resCode = RespCodeEnum.OK.getCode();
		this.resMsg = RespCodeEnum.OK.getMsg();
	}

	public BaseRespDto(RespCodeEnum respCodeEnum, String msg) {
		this.resCode = respCodeEnum.getCode();
		this.resMsg = String.format("%s%s", respCodeEnum.getMsg(),
				StringUtils.isEmpty(msg)? "" : ", "+ msg);
	}

	public String getResCode() {
		return resCode;
	}

	public String getResMsg() {
		return resMsg;
	}
}
