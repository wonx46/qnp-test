package com.qnp.iwan.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class MessageResponse implements Serializable{

	
	private static final long serialVersionUID = -2343222778340632272L;
	public final static int ERROR_CODE_410 = 410;
    public final static int ERROR_CODE_411 = 411;
    public final static int ERROR_CODE_412 = 412; 
    public final static int ERROR_CODE_INTERNAL_SERVER = 500;
    public final static int ERROR_CODE_400 = 400;
    public final static int ERROR_CODE_404 = 404;
    public final static int CODE_SUCCESS = 200;
    public final static int CODE_SUCCESS_CREATED = 201;
    public final static int CODE_USER_NOT_FOUND = 400;
    
    public final static String OK = "OK";
    public final static String USER_NOT_FOUND = "User not found";
    public final static String INVALID_PAYLOAD = "Invalid payload";
  

    private long timestamp = System.currentTimeMillis();
    private Integer status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageDetail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<?> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<?> errors;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;

    public MessageResponse() {
    }

    public MessageResponse(Integer status) {
        this.setStatus(status);
    }

    public MessageResponse(Integer status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }

    public MessageResponse(Integer status, String message, List<?> data) {
        this.setStatus(status);
        this.setMessage(message);
        this.setData(data);
    }

    public MessageResponse(Integer status, String message, List<?> data, int total) {
        this.setStatus(status);
        this.setMessage(message);
        this.setData(data);
        this.setTotal(total);
    }


}
