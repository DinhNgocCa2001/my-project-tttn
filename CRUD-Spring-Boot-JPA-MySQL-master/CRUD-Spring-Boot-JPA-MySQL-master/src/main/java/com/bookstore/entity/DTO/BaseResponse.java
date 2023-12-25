package com.bookstore.entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BaseResponse {
	private String code;
    private String message;
    private boolean success;
    
    
	public BaseResponse(String code, String message, boolean success) {
		super();
		this.code = code;
		this.message = message;
		this.success = success;
	}
	
	public BaseResponse() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
    
    
}
