package org.jainbooks.ebook.rest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.domain.Category;
import org.jainbooks.ebook.domain.EBook;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.dto.AddUserEBookDTO;
import org.jainbooks.ebook.dto.CategoryDTO;
import org.jainbooks.ebook.dto.EBookCategoriesDTO;
import org.jainbooks.ebook.dto.EBookLibraryDTO;
import org.jainbooks.ebook.dto.JainBookResponse;
import org.jainbooks.ebook.dto.UserAuthenticationDTO;
import org.jainbooks.ebook.dto.UserLibraryDTO;
import org.jainbooks.ebook.dto.UserValidationDTO;
import org.jainbooks.ebook.service.EBookService;
import org.jainbooks.ebook.service.UserService;
import org.jainbooks.ebook.util.PropertiesFileReaderUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Path("/jainbook-service")
public class JainBookService {

	private static final Logger logger = Logger
			.getLogger(JainBookService.class);

	ApplicationContext appContext = new ClassPathXmlApplicationContext(
			"../applicationContext.xml");

	@Path("/text")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String text() {
		return "Hello Jersey";
	}

	@POST
	@Path("/registerUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(User user) {
		JainBookResponse jainBookResponse = new JainBookResponse();
		UserService userService = (UserService) appContext
				.getBean("userService");
		try {
			UserValidationDTO userValidationDTO = userService
					.validateUser(user);
			if (null == userValidationDTO) {
				User existingUser = userService.getUserByEmail(user.getEmail());
				if (null == existingUser) {
					user = userService.saveUser(user, "user");
					jainBookResponse.setStatusCode("SUCCESS_001");
					jainBookResponse.setMessage("User Created Successfully");
					jainBookResponse
							.setAuthToken(user.getAuthenticationToken());
				} else if (!user.getLoginSource().equalsIgnoreCase("direct")) {
					jainBookResponse.setStatusCode("SUCCESS_002");
					jainBookResponse.setMessage("User Already Present");
					jainBookResponse.setAuthToken(existingUser
							.getAuthenticationToken());
				} else {
					jainBookResponse.setStatusCode("ERROR_005");
					jainBookResponse
							.setMessage("A User is already present with this email id.");
				}

			} else {
				jainBookResponse.setStatusCode(userValidationDTO
						.getStatusCode());
				jainBookResponse.setMessage(userValidationDTO.getMessage());
			}
		} catch (Exception e) {
			logger.fatal(e.getStackTrace());
			jainBookResponse.setStatusCode("err001");
			jainBookResponse.setMessage(PropertiesFileReaderUtil
					.getPropertyValue("err001"));
			return Response.status(500).entity(jainBookResponse).build();
		}
		return Response.status(200).entity(jainBookResponse).build();

	}

	@POST
	@Path("/authenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(UserAuthenticationDTO userAuthDTO) {
		JainBookResponse jainBookResponse = new JainBookResponse();

		UserService userService = (UserService) appContext
				.getBean("userService");
		try {
			User user = new User();
			user.setEmail(userAuthDTO.getEmail());

			user.setPassword(userAuthDTO.getPassword());

			user.setLoginSource(userAuthDTO.getLoginSource());
			UserValidationDTO userValidationDTO = userService
					.validateUser(user);
			if (null == userValidationDTO) {
				User existingUser = userService.authinticateUser(
						userAuthDTO.getEmail(), userAuthDTO.getPassword());
				if (null != existingUser) {
					jainBookResponse.setStatusCode("SUCCESS_003");
					jainBookResponse.setMessage("User Login Successful");
					jainBookResponse
							.setAuthToken(user.getAuthenticationToken());
				} else {
					jainBookResponse.setStatusCode("ERROR_007");
					jainBookResponse.setMessage("User Login Unsuccessful");
				}
			} else {
				jainBookResponse.setStatusCode(userValidationDTO
						.getStatusCode());
				jainBookResponse.setMessage(userValidationDTO.getMessage());
			}
		} catch (Exception e) {
			logger.fatal(e.getStackTrace());
			jainBookResponse.setStatusCode("err001");
			jainBookResponse.setMessage(PropertiesFileReaderUtil
					.getPropertyValue("err001"));
			return Response.status(500).entity(jainBookResponse).build();
		}
		return Response.status(200).entity(jainBookResponse).build();
	}

	@POST
	@Path("/get-ebook-library")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEBookLibrary() {
		EBookService ebookService = (EBookService) appContext
				.getBean("ebookService");
		EBookLibraryDTO eBookLibraryDTO = new EBookLibraryDTO();
		List<EBook> ebooks = ebookService.getEBookLibrary();
		eBookLibraryDTO.setEbooks(ebooks);
		return Response.status(200).entity(eBookLibraryDTO).build();
	}

	@GET
	@Path("/get-ebook-categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEBookCategories() {
		EBookService ebookService = (EBookService) appContext
				.getBean("ebookService");
		EBookCategoriesDTO eBookCategoriesDTO = new EBookCategoriesDTO();
		List<CategoryDTO> categoryDTOList = new ArrayList<>();
		eBookCategoriesDTO.setCategories(categoryDTOList);
		Map<Category, List<EBook>> categoryMap = ebookService
				.getEBooksByCategory();

		Iterator<Category> iterator = categoryMap.keySet().iterator();
		while (iterator.hasNext()) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTOList.add(categoryDTO);
			categoryDTO.setCategory(iterator.next());
			categoryDTO.setEbooks(categoryMap.get(iterator.next()));
		}

		return Response.status(200).entity(eBookCategoriesDTO).build();
	}

	@GET
	@Path("/get-ebook-categories-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEBooksForCategory(
			@QueryParam("categoryId") String categoryId) {
		EBookService ebookService = (EBookService) appContext
				.getBean("ebookService");
		EBookCategoriesDTO eBookCategoriesDTO = new EBookCategoriesDTO();
		List<CategoryDTO> categoryDTOList = new ArrayList<>();
		eBookCategoriesDTO.setCategories(categoryDTOList);
		CategoryDTO categoryDTO = ebookService.getEBooksForCategory(categoryId);
		categoryDTOList.add(categoryDTO);

		return Response.status(200).entity(eBookCategoriesDTO).build();
	}

	@GET
	@Path("/get-user-library")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserLibrary(@QueryParam("email") String email) {
		EBookService ebookService = (EBookService) appContext
				.getBean("ebookService");
		UserLibraryDTO userLibraryDTO = new UserLibraryDTO();
		List<EBook> ebooks = ebookService.getUserLibrary(email);
		userLibraryDTO.setEbooks(ebooks);

		return Response.status(200).entity(userLibraryDTO).build();
	}

	@POST
	@Path("/add-user-ebook")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEBookToUserLibrary(AddUserEBookDTO addUserEBookDTO) {
		JainBookResponse jainBookResponse = new JainBookResponse();
		EBookService ebookService = (EBookService) appContext
				.getBean("ebookService");
		try {
			ebookService.addEBookToUserLibrary(addUserEBookDTO.getEmail(),
					addUserEBookDTO.getEbookId());
			jainBookResponse.setStatusCode("SUCCESS_003");
			jainBookResponse.setMessage("EBook Successfully Added to User Account");
		} catch (Exception e) {
			logger.fatal(e.getStackTrace());
			jainBookResponse.setStatusCode("err001");
			jainBookResponse.setMessage(PropertiesFileReaderUtil
					.getPropertyValue("err001"));
			return Response.status(500).entity(jainBookResponse).build();
		}
		return Response.status(200).entity(jainBookResponse).build();
	}
}
