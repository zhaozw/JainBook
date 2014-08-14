package org.jainbooks.ebook.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.dto.ForgetPasswordDto;
import org.jainbooks.ebook.form.ForgetPasswordForm;
import org.jainbooks.ebook.service.UserService;
import org.jainbooks.ebook.util.EmailUtil;
import org.jainbooks.ebook.util.PropertiesFileReaderUtil;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgetPasswordController {

	private static final Logger logger = Logger
			.getLogger(ForgetPasswordController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public ModelAndView getForgetPasswordPage() {

		ModelAndView modelAndView = new ModelAndView("forgetPassword");
		ForgetPasswordForm form = new ForgetPasswordForm();
		modelAndView.addObject("forgetPasswordForm", form);
		return modelAndView;
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String getPasswordForEmail(
			@Valid ForgetPasswordForm forgetPasswordForm, BindingResult result,
			ModelMap map, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "forgetPassword";
		}
		String email = forgetPasswordForm.getEmail();
		try {
			User user = userService.getUserByEmail(email);
			if (user != null) {
				ForgetPasswordDto forgetPasswordDto = new ForgetPasswordDto();
				String password = user.getPassword();
				if (password != null && !password.isEmpty()) {
					forgetPasswordDto.setPassword(JainBookUtil
							.passwordDecrypter(password));
					EmailUtil
							.sendEmail(
									PropertiesFileReaderUtil
											.getVelocityTemplateProperties("resend.password.email.subject"),
									user.getEmail(),
									PropertiesFileReaderUtil
											.getVelocityTemplateProperties("resend.password"),
									forgetPasswordDto, null);
					map.addAttribute("passwordSent", "true");
					return "forgetPassword";
				} else {
					result.rejectValue("email", "password.not.present");
					return "forgetPassword";
				}
			} else {
				result.rejectValue("email", "email.not.registered");
				return "forgetPassword";
			}
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("email", "err001");
			return "forgetPassword";
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
