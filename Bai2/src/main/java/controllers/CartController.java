package controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.CartBeanItem;
import ultis.EntityManagerFactoryUtil;
import dao.ProductDAO;
import daoImpl.ProductDaoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@WebServlet(urlPatterns = { "/cart", "/cart*" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EntityManagerFactoryUtil entityManageFactory;

	private ProductDAO productDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.entityManageFactory = new EntityManagerFactoryUtil();
		this.productDAO = new ProductDaoImpl(this.entityManageFactory.getEnManager());
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";

		switch (action) {
		case "buy":
			handleBuy(request, response);
			break;
		case "remove":
			handleRemove(request, response);
			break;
		case "clear":
			handleClear(request, response);
			break;
		default:
			displayCart(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		switch (action) {
		case "update":
			handleUpdate(request, response);
			break;
		case "clear":
			handleClear(request, response);
			break;
		case "remove":
			handleRemove(request, response);
			break;
		default:
			doGet(request, response);
			break;
		}
	}

	/**
	 * Display Cart
	 */
	private void displayCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/cart/index.jsp").forward(request, response);
	}

	/**
	 * Handle adding product to cart
	 */
	private void handleBuy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<CartBeanItem> cart = getCartFromSession(session);

		int productId = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("number"));

		int index = isProductExisting(productId, cart);
		if (index == -1) {
			cart.add(new CartBeanItem(productDAO.getById(productId), quantity));
		} else {
			int updatedQuantity = cart.get(index).getQuantity() + quantity;
			cart.get(index).setQuantity(updatedQuantity);
		}

		session.setAttribute("cart", cart);
		response.sendRedirect("cart");
	}

	/**
	 * Handle removing product from cart
	 */
	private void handleRemove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<CartBeanItem> cart = getCartFromSession(session);

		int productId = Integer.parseInt(request.getParameter("id"));
		int index = isProductExisting(productId, cart);
		if (index != -1) {
			cart.remove(index);
		}

		session.setAttribute("cart", cart);
		response.sendRedirect("cart");
	}

	/**
	 * Handle updating product quantity in cart
	 */
	private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<CartBeanItem> cart = getCartFromSession(session);

		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("ProductId: " + productId + ", Quantity: " + quantity);

		int index = isProductExisting(productId, cart);
		if (index != -1) {
			if (quantity <= 0) {
				cart.remove(index);
			} else {
				cart.get(index).setQuantity(quantity);
			}
		}

		System.out.println("Updated Cart: " + cart);

		session.setAttribute("cart", cart);
		response.sendRedirect("cart");
	}

	/**
	 * Handle clearing the cart
	 */
	private void handleClear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		response.sendRedirect("cart");
	}

	/**
	 * Get cart from session or create new cart if not exists
	 */
	private List<CartBeanItem> getCartFromSession(HttpSession session) {
		List<CartBeanItem> cart = (List<CartBeanItem>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<CartBeanItem>();
		}
		return cart;
	}

	/**
	 * Check if product exists in cart
	 */
	private int isProductExisting(int id, List<CartBeanItem> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
