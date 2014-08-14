package org.jainbooks.ebook.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.form.UserLoginForm;
import org.jainbooks.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserLoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String getUserLoginPage(ModelMap map) {
		UserLoginForm form = new UserLoginForm();
		map.addAttribute("userLoginForm", form);
		return "userLogin";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String authenticateUser(@Valid UserLoginForm loginForm,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "userLogin";
		}

		String email = loginForm.getEmail();

		User user = userService.getUserByEmail(email);
		if (user == null) {
			result.rejectValue("email", "email.not.found");
			return "userLogin";
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "redirect:/userHome.htm";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
