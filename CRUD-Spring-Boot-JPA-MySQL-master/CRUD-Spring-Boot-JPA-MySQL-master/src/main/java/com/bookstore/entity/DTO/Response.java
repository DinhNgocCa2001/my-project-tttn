package com.bookstore.entity.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
public class Response<T> extends BaseResponse {
	private T result;

    public Response() {
        super();
    }

    public Response(T result) {
        super();
        this.result = result;
    }

    public Response(String code, String message, T result, boolean success) {
        super(code, message, success);
        this.result = result;
    }
    
    public Response(String code, String message, boolean success, T result) {
        super(code, message, success);
        this.result = result;
    }
}
