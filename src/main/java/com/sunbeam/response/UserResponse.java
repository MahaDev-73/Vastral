package com.sunbeam.response;


public class UserResponse<T> {
	private AppStatus status;
	private T data;
	private String message;
	
	public UserResponse(AppStatus status, T data, String message) {
		this.status=status;
		this.data=data;
		this.message=message;
		
	}

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

	public AppStatus getStatus() {
		return status;
	}

	public void setStatus(AppStatus status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		
	}
	public static <T> UserResponse<T> success(T data) {
		UserResponse<T> res = new UserResponse<T>(AppStatus.success, data, null);
		return res;
	}
	public static <T> UserResponse<T> success(T data, String message) {
		UserResponse<T> res = new UserResponse<T>(AppStatus.success, data, message);
		return res;
	}
	public static <T> UserResponse<T> error(String message) {
		UserResponse<T> res = new UserResponse<T>(AppStatus.error, null, message);
		return res;
	}
	
	

}
