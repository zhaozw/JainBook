/*package org.jainbooks.ebook.web;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jainbooks.ebook.authorization.UserAuthorization;
import org.jainbooks.ebook.domain.Device;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.dto.SendPasscodeDto;
import org.jainbooks.ebook.service.DeviceService;
import org.jainbooks.ebook.util.EmailUtil;
import org.jainbooks.ebook.util.PropertiesFileReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends UserAuthorization {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String prepareUserHome(ModelMap map, HttpServletRequest request) {
		if (!userAuthorization(request)) {
			return "redirect:/userLogin.htm";
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Device> devices = deviceService
				.getDevicesForUser(user.getUserId());
		session.setAttribute("deviceList", devices);
		return "userHome";

	}

	@RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
	public @ResponseBody
	String updateDeviceInfo(@ModelAttribute(value = "device") Device device,
			BindingResult result, ModelMap map, HttpServletRequest request) {
		if (!userAuthorization(request)) {
			return "redirect:/userLogin.htm";
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String returnText;
		if (!result.hasErrors()) {
			Device devicefromDb = deviceService.getCurrentDeviceForUser(device
					.getDeviceId().trim(), user.getEmail());
			if (devicefromDb != null) {
				devicefromDb.setUpdated(new Date());
				devicefromDb.setMessage(device.getMessage());
				devicefromDb.setStatus(device.isStatus());
				deviceService.saveDevice(devicefromDb);
				returnText = "Information updated successfully";
			} else {
				returnText = "Device not found";
			}

		} else {
			returnText = "Sorry, an error has occured,please try later";
		}
		return returnText;
	}

	@RequestMapping(value = "/sendPasscode", method = RequestMethod.POST)
	public @ResponseBody
	String sendPasscode(@ModelAttribute(value = "device") Device device,
			BindingResult result, ModelMap map, HttpServletRequest request) {
		if (!userAuthorization(request)) {
			return "redirect:/userLogin.htm";
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String returnText;
		if (!result.hasErrors()) {
			SendPasscodeDto sendPasscodeDto = new SendPasscodeDto();
			String email = user.getEmail().trim();
			Device devicefromDb = deviceService.getCurrentDeviceForUser(device
					.getDeviceId().trim(), user.getEmail());
			if (devicefromDb != null) {
				String deviceId = devicefromDb.getDeviceId().trim();
				String rootFolderPath = PropertiesFileReaderUtil
						.getApplicationProperty("passcode.images.location")
						+ "/" + email + deviceId;
				String userPasscodeImgPath = rootFolderPath + "/passcode.png";
				File file = new File(userPasscodeImgPath);
				sendPasscodeDto.setDeviceId(deviceId);
				sendPasscodeDto.setModel(devicefromDb.getModel());
				if (file.exists()) {
					EmailUtil
							.sendEmail(
									PropertiesFileReaderUtil
											.getVelocityTemplateProperties("send.passcode.email.subject"),
									email,
									PropertiesFileReaderUtil
											.getVelocityTemplateProperties("send.passcode"),
											sendPasscodeDto, userPasscodeImgPath);
					returnText = "An email has been sent to the registered email id.";
				} else {
					returnText = "could not find passcode,Please contact administrator";
				}
			}else{
				returnText = "Device not found,Please contact administrator";
			}

		} else {
			returnText = "Sorry, an error has occured,please try later";
		}
		return returnText;
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

}
*/