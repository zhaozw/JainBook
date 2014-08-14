/*package org.jainbooks.ebook.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.authorization.AdminAuthorization;
import org.jainbooks.ebook.domain.Logo;
import org.jainbooks.ebook.domain.LogoType;
import org.jainbooks.ebook.dto.ManageUnlocksDto;
import org.jainbooks.ebook.dto.OffersDetailDto;
import org.jainbooks.ebook.exception.YourKeyException;
import org.jainbooks.ebook.form.AddLogoForm;
import org.jainbooks.ebook.form.AddOfferForm;
import org.jainbooks.ebook.form.EditLogoForm;
import org.jainbooks.ebook.form.EditOfferForm;
import org.jainbooks.ebook.form.LogoStatusChangeForm;
import org.jainbooks.ebook.form.ManageUnlocksForm;
import org.jainbooks.ebook.form.SearchForm;
import org.jainbooks.ebook.service.DeviceUnlockTimeService;
import org.jainbooks.ebook.service.LogoService;
import org.jainbooks.ebook.service.LogoTypeService;
import org.jainbooks.ebook.service.OffersService;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({ "activeLogoId", "activeOfferId" })
public class AdminController extends AdminAuthorization {

	private static final Logger logger = Logger
			.getLogger(AdminController.class);

	@Autowired
	private LogoTypeService logoTypeService;

	@Autowired
	private LogoService logoService;

	@Autowired
	private OffersService offersService;

	@Autowired
	private DeviceUnlockTimeService deviceUnlockTimeService;

	
	 * @InitBinder protected void initBinder(WebDataBinder binder) {
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	 * binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
	 * false)); }
	 

	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String prepareAdminHome(ModelMap map, HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		SearchForm form = new SearchForm();
		map.addAttribute("searchForm", form);

		LogoStatusChangeForm changeForm = new LogoStatusChangeForm();
		map.addAttribute("logoStatusChangeForm", changeForm);

		List<Logo> list = logoService.getAllLogosWithOrignalName();
		HttpSession session = request.getSession();
		session.setAttribute("logoList", list);
		return "adminHome";
	}

	@RequestMapping(value = "/showAddLogoForm", method = RequestMethod.GET)
	public ModelAndView showAddLogoForm(HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return new ModelAndView("redirect:/login.htm");
		}

		ModelAndView modelAndView = new ModelAndView("addLogo");

		AddLogoForm form = new AddLogoForm();
		form.setLogoTypes(getLogoTypeList());
		modelAndView.addObject("addLogoForm", form);

		return modelAndView;
	}

	@RequestMapping(value = "/addLogo", method = RequestMethod.POST)
	public String addLogo(
			@Valid @ModelAttribute("addLogoForm") AddLogoForm addLogoForm,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		if (result.hasErrors()) {
			addLogoForm.setLogoTypes(getLogoTypeList());
			return "addLogo";
		}

		try {
			logoService.addLogo(addLogoForm);
		} catch (FileNotFoundException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "file.not.found");
			return "addLogo";
		} catch (IOException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "io.exception");
			return "addLogo";
		}

		return "redirect:/adminHome.htm";
	}

	@RequestMapping(value = "/showEditLogoForm", method = RequestMethod.GET)
	public ModelAndView showEditLogoForm(@RequestParam Long logoId,
			HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return new ModelAndView("redirect:/login.htm");
		}

		ModelAndView modelAndView = new ModelAndView("editLogo");

		Logo logo = logoService.getLogo(logoId);

		EditLogoForm form = new EditLogoForm();
		form.setLogoId(logo.getId());
		form.setLogoName(JainBookUtil.getOrignalLogoName(logo.getName()));
		form.setImagePath(logo.getImagePath());
		form.setLogoType(logo.getLogoType().getId());
		form.setLogoTypes(getLogoTypeList());
		modelAndView.addObject("editLogoForm", form);

		return modelAndView;
	}

	@RequestMapping(value = "/editLogo", method = RequestMethod.POST)
	public String editLogo(
			@Valid @ModelAttribute("editLogoForm") EditLogoForm editLogoForm,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		if (result.hasErrors()) {
			editLogoForm.setLogoTypes(getLogoTypeList());
			return "editLogo";
		}

		try {
			logoService.updateLogo(editLogoForm);
		} catch (FileNotFoundException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "file.not.found");
			return "addLogo";
		} catch (IOException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "io.exception");
			return "addLogo";
		}

		return "redirect:/adminHome.htm";
	}

	@RequestMapping(value = "/changeLogostatus", method = RequestMethod.GET)
	public String changeLogostatus(@RequestParam Long logoId,
			@RequestParam boolean statusToSet, HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		Logo logo = logoService.getLogo(logoId);

		logo.setActive(statusToSet);

		logoService.saveLogoForLogoType(logo, logo.getLogoType().getId());

		return "redirect:/adminHome.htm";
	}

	@RequestMapping(value = "/changeLogostatus1", method = RequestMethod.POST)
	public String changeLogostatus1(
			@ModelAttribute("logoStatusChangeForm") LogoStatusChangeForm form,
			HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		logoService.updateLogoStatus(form);

		return "redirect:/adminHome.htm";
	}

	@RequestMapping(value = "/manageOffers", method = RequestMethod.GET)
	public ModelAndView showManageOffersPage(@RequestParam Long logoId,
			HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return new ModelAndView("redirect:/login.htm");
		}

		ModelAndView modelAndView = new ModelAndView("manageOffers");

		List<OffersDetailDto> list = offersService
				.getAllOffersForLogoToDisplay(logoId);

		HttpSession session = request.getSession();
		session.setAttribute("offerList", list);

		modelAndView.addObject("activeLogoId", logoId);

		return modelAndView;

	}

	@RequestMapping(value = "/showAddOfferForm", method = RequestMethod.GET)
	public ModelAndView showAddOfferForm(
			@ModelAttribute("activeLogoId") Long logoId,
			HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return new ModelAndView("redirect:/login.htm");
		}

		ModelAndView modelAndView = new ModelAndView("addOffer");

		AddOfferForm form = new AddOfferForm();
		// form.setLogoId(logoId);
		form.setOfferTypes(offersService.getOfferTypes());
		modelAndView.addObject("addOfferForm", form);

		return modelAndView;
	}

	@RequestMapping(value = "/addOffer", method = RequestMethod.POST)
	public String addOffer(@ModelAttribute("activeLogoId") Long logoId,
			@Valid @ModelAttribute("addOfferForm") AddOfferForm addOfferForm,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		if (result.hasErrors()) {
			addOfferForm.setOfferTypes(offersService.getOfferTypes());
			return "addOffer";
		}

		try {
			offersService.saveOfferForLogo(addOfferForm, logoId);
		} catch (FileNotFoundException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "file.not.found");
			return "addOffer";
		} catch (IOException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "io.exception");
			return "addOffer";
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		}

		return "redirect:/manageOffers.htm?logoId=" + logoId;
	}

	@RequestMapping(value = "/offerDetails", method = RequestMethod.GET)
	public ModelAndView showOfferDetails(@RequestParam Long offerId,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("offerDetails");
		OffersDetailDto offerDTO = offersService.getOffer(offerId);
		modelAndView.addObject("offerDTO", offerDTO);

		modelAndView.addObject("activeOfferId", offerId);

		return modelAndView;
	}

	@RequestMapping(value = "/showEditOfferForm", method = RequestMethod.GET)
	public ModelAndView showEditOfferForm(
			@ModelAttribute("activeOfferId") Long offerId,
			HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return new ModelAndView("redirect:/login.htm");
		}

		ModelAndView modelAndView = new ModelAndView("editOffer");

		OffersDetailDto detailDto = offersService.getOffer(offerId);

		EditOfferForm form = new EditOfferForm();
		form.setVideoPath(detailDto.getVideoPath());
		form.setOfferType(detailDto.getOfferType());
		form.setDescription(detailDto.getDescription());
		form.setOfferTypes(offersService.getOfferTypes());
		form.setValidityDate(detailDto.getValidUpto());
		form.setActive(detailDto.isActive());
		modelAndView.addObject("editOfferForm", form);

		return modelAndView;
	}

	@RequestMapping(value = "/editOffer", method = RequestMethod.POST)
	public String editOffer(
			@ModelAttribute("activeLogoId") Long logoId,
			@ModelAttribute("activeOfferId") Long offerId,
			@Valid @ModelAttribute("editOfferForm") EditOfferForm editOfferForm,
			BindingResult result, HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		if (result.hasErrors()) {
			editOfferForm.setOfferTypes(offersService.getOfferTypes());
			return "editOffer";
		}

		try {
			offersService.updateLogo(editOfferForm, logoId, offerId);
		} catch (FileNotFoundException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "file.not.found");
			return "editOffer";
		} catch (IOException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			result.rejectValue("file", "io.exception");
			return "editOffer";
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		}

		return "redirect:/manageOffers.htm?logoId=" + logoId;
	}

	@RequestMapping(value = "/changeOfferStatus", method = RequestMethod.GET)
	public @ResponseBody
	String changeOfferStatus(@RequestParam("offerId") Long offerId,
			@RequestParam("statusToSet") Boolean statusToSet,
			@ModelAttribute("activeLogoId") Long logoId,
			HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return "redirect:/login.htm";
		}

		String returnText = "";

		if (statusToSet && !offersService.isOfferDateValid(offerId)) {
			returnText = "Offer date is not valid.Please edit the offer and enter the valid date";
		} else {
			offersService.changeOfferStatus(offerId, logoId, statusToSet);
			returnText = "Offer status is successfully changed";
		}
		return returnText;
	}

	@RequestMapping(value = "/showManageUnlocksForm.htm", method = RequestMethod.GET)
	ModelAndView showManageUnlocksForm(HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return new ModelAndView("redirect:/login.htm");
		}

		ModelAndView modelAndView = new ModelAndView("manageUnlocks");
		ManageUnlocksForm manageUnlocksForm = new ManageUnlocksForm();
		modelAndView.addObject("manageUnlcokForm", manageUnlocksForm);

		return modelAndView;
	}

	@RequestMapping(value = "/manageUnlocks", method = RequestMethod.POST)
	public ModelAndView manageUnlocks(
			@Valid @ModelAttribute("manageUnlcokForm") ManageUnlocksForm manageUnlocksForm,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (!adminAuthorization(request)) {
			return new ModelAndView("redirect:/login.htm");
		}

		if (result.hasErrors()) {
			return new ModelAndView("manageUnlocks");
		} 

		String fromDateStr = manageUnlocksForm.getFromDate();
		String toDateStr = manageUnlocksForm.getToDate();

		Date fromDate = JainBookUtil.convertStringToDate(fromDateStr,
				"MM/dd/yyyy h:m a");
		Date toDate = JainBookUtil.convertStringToDate(toDateStr,
				"MM/dd/yyyy h:m a");
		
		if(fromDate.compareTo(toDate) > 0) {
			result.rejectValue("fromDate", "Error.manageUnlcokForm.fromDate");
			return new ModelAndView("manageUnlocks");
		}

		int totalUnlocks = deviceUnlockTimeService.getTotalDeviceUnlocks(
				fromDate, toDate);
		List<ManageUnlocksDto> list = deviceUnlockTimeService
				.getTotalUnlocksForAllUsers(fromDate, toDate);

		ModelAndView modelAndView = new ModelAndView("unlockDetails");
		modelAndView.addObject("totalUnlocks", totalUnlocks);
		modelAndView.addObject("userUnlocksList", list);
		modelAndView.addObject("fromDate", fromDateStr);
		modelAndView.addObject("toDate", toDateStr);

		return modelAndView;
	}

	private List<LogoType> getLogoTypeList() {
		return logoTypeService.getAllLogoType();
	}

	public LogoTypeService getLogoTypeService() {
		return logoTypeService;
	}

	public void setLogoTypeService(LogoTypeService logoTypeService) {
		this.logoTypeService = logoTypeService;
	}

	public LogoService getLogoService() {
		return logoService;
	}

	public void setLogoService(LogoService logoService) {
		this.logoService = logoService;
	}

	public OffersService getOffersService() {
		return offersService;
	}

	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	public DeviceUnlockTimeService getDeviceUnlockTimeService() {
		return deviceUnlockTimeService;
	}

	public void setDeviceUnlockTimeService(
			DeviceUnlockTimeService deviceUnlockTimeService) {
		this.deviceUnlockTimeService = deviceUnlockTimeService;
	}
}
*/