package org.jainbooks.ebook.dto;

public class ManageUnlocksDto {

	private String email;
	private String deviceId;
	private int count;
	
	public String getEmail() {
		return email;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public int getCount() {
		return count;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
