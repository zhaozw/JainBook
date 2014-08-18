package com.jainbooks.model;

import com.google.gson.annotations.Expose;

public class TACommonStatusResponseModel {
	
	@Expose
	private int error;
	@Expose
	private String status;
	@Expose
	private String message;
	
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getStatus() {
		if (status == null) {
			return "";
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		if (message == null) {
			return "";
		}
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Tells if the response has status OK or not
	 * @return true if there is no error i.e error equals 0
	 */
	public boolean isStatusOK(){
		if(error==0){
			return true;
		}else{
			return false;
		}
	}

}
