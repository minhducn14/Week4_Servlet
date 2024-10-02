package controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.Product;
import ultis.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.ProductDAO;
import daoImpl.ProductDaoImpl;

/**
 * Servlet implementation class ProductController
 */
@WebServlet(urlPatterns = { "/product" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EntityManagerFactoryUtil entityManageFactory;
	private ProductDAO productDAO;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.entityManageFactory = new EntityManagerFactoryUtil();
		this.productDAO = new ProductDaoImpl(this.entityManageFactory.getEnManager());
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setAttribute("products", productDAO.findAll());
		request.getRequestDispatcher("views/product/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
