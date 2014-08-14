package org.jainbooks.ebook.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.form.LoginForm;
import org.jainbooks.ebook.service.UserService;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(ModelMap map) {
		LoginForm form = new LoginForm();
		map.addAttribute("loginForm", form);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authenticateUser(@Valid LoginForm loginForm,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "login";
		}

		String email = loginForm.getEmail();
		String password = JainBookUtil.passwordEncoder(loginForm
				.getPassword().trim()).replace("\n", "");
		
		/*String email = "a@b.com";
		String password = "qwerty";*/

		User user = userService.authinticateUser(email, password);
		if (user == null) {
			map.addAttribute("loginFailed", "true");
			return "login";
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "redirect:/adminHome.htm";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
