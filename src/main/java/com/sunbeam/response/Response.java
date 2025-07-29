package com.sunbeam.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

enum AppStatus {
	success, error
}

public class Response<T> {
	private AppStatus status;
	private T data;
	private String message;
	
	public Response(AppStatus status, T data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
	public AppStatus getStatus() {
		return status;
	}


	public T getData() {
		return data;
	}


	public String getMessage() {
		return message;
	}


	public static <T> Response<T> success(T data) {
		Response<T> res = new Response<T>(AppStatus.success, data, null);
		return res;
	}
	public static <T> Response<T> success(T data, String message) {
		Response<T> res = new Response<T>(AppStatus.success, data, message);
		return res;
	}
	public static <T> Response<T> error(String message) {
		Response<T> res = new Response<T>(AppStatus.error, null, message);
		return res;
	}
}
