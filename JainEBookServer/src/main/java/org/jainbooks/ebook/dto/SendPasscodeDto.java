package org.jainbooks.ebook.dto;

public class SendPasscodeDto {
	private String deviceId;
	private String model;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
