package controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.TinTuc;
import service.DanhSachDanhMucQuanLy;
import service.DanhSachTinTucQuanLy;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class QuanLyFormServlet
 */
public class QuanLyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/quanlydanhmuc")
	private DataSource dataSource;

	private DanhSachTinTucQuanLy danhSachTinTucQuanLy;
	private DanhSachDanhMucQuanLy danhSachDanhMucQuanLy;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		danhSachTinTucQuanLy = new DanhSachTinTucQuanLy(this.dataSource);
		danhSachDanhMucQuanLy = new DanhSachDanhMucQuanLy(this.dataSource);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuanLyFormServlet() {
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

		List<TinTuc> tinTucs = danhSachTinTucQuanLy.layDanhSachTinTuc();
		request.setAttribute("tinTucs", tinTucs);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("QuanLyForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String maTinTuc = request.getParameter("maTinTuc");
		if (maTinTuc != null) {
			danhSachTinTucQuanLy.xoaTinTuc(Integer.parseInt(maTinTuc));
		}
		doGet(request, response);
	}

}
