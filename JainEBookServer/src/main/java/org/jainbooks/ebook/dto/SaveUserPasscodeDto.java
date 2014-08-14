package org.jainbooks.ebook.dto;

import java.util.ArrayList;
import java.util.List;

public class SaveUserPasscodeDto {

	private String email;
	private String deviceId;
	private List<String> passcode = new ArrayList<String>();

	public String getEmail() {
		return email;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<String> getPasscode() {
		return passcode;
	}

	public void setPasscode(List<String> passcode) {
		this.passcode = passcode;
	}
}
