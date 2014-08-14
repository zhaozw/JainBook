/*package org.jainbooks.ebook.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jainbooks.ebook.authorization.AdminAuthorization;
import org.jainbooks.ebook.domain.Logo;
import org.jainbooks.ebook.form.LogoStatusChangeForm;
import org.jainbooks.ebook.form.SearchForm;
import org.jainbooks.ebook.service.LogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController extends AdminAuthorization {
	
	@Autowired
	private LogoService logoService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@ModelAttribute SearchForm searchForm, HttpServletRequest request, ModelMap map) {
		
		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		String searchStr = searchForm.getSearchString();
		
		List<Logo> list = logoService.getLogosBySearchString(new StringBuilder(searchStr));
		
		HttpSession session = request.getSession(false);
		session.setAttribute("logoList", list);
		
		LogoStatusChangeForm changeForm = new LogoStatusChangeForm();
		map.addAttribute("logoStatusChangeForm", changeForm);
		
		return "adminHome";
	}

	public LogoService getLogoService() {
		return logoService;
	}

	public void setLogoService(LogoService logoService) {
		this.logoService = logoService;
	}
}
*/