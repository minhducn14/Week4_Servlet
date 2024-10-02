package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EntityManagerFactoryUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.annotation.Resource;
import javax.sql.DataSource;

import daoImpl.UserDAOImpl;
import entities.Gender;
import entities.User;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EntityManagerFactoryUtil entityManageFactory;
	private UserDAOImpl userDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		this.entityManageFactory = new EntityManagerFactoryUtil();
		this.userDAO = new UserDAOImpl(this.entityManageFactory.getEnManager());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String reEmail = request.getParameter("reEmail");
		String password = request.getParameter("password");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String gender = request.getParameter("gender");

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);

		if (day != null && !day.isEmpty() && month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
			try {
				String formattedDate = year + "-" + month + "-" + day;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date birthday = sdf.parse(formattedDate);
				user.setBirthday(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (gender != null) {
			user.setGender(Gender.valueOf(gender.toUpperCase()));

			System.out.println(user);

		}

		User addUser = userDAO.addUser(user);

		if (addUser == null) {
			response.setContentType("application/json");
			response.getWriter().write("User not created");
			response.setStatus(400);
			return;
		} else {
			response.setContentType("application/json");
			response.getWriter().write("User created successfully");
			response.getWriter().write(user.toString());
			response.setStatus(201);
		}

	}

}
