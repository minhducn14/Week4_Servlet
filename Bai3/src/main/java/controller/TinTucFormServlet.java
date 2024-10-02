package controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.DanhMuc;
import modal.TinTuc;
import service.DanhSachDanhMucQuanLy;
import service.DanhSachTinTucQuanLy;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class TinTucFormServlet
 */
public class TinTucFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/quanlydanhmuc")
	private DataSource dataSource;

	private DanhSachTinTucQuanLy danhSachTinTucQuanLy;
	private DanhSachDanhMucQuanLy danhSachDanhMucQuanLy;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public TinTucFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		danhSachTinTucQuanLy = new DanhSachTinTucQuanLy(this.dataSource);
		danhSachDanhMucQuanLy = new DanhSachDanhMucQuanLy(this.dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<DanhMuc> danhSachDanhMuc = danhSachDanhMucQuanLy.layTatCaDanhMuc();
		request.setAttribute("danhSachDanhMuc", danhSachDanhMuc);
		request.getRequestDispatcher("/TinTucForm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int maTinTuc = Integer.parseInt(request.getParameter("maTinTuc"));
		String tieuDe = request.getParameter("tieuDe");
		String noiDungTT = request.getParameter("noiDungTT");
		String lienKet = request.getParameter("lienKet");
		int maDanhMuc = Integer.parseInt(request.getParameter("maDanhMuc"));
		System.out.println(maDanhMuc);

		DanhMuc danhMuc = danhSachDanhMucQuanLy.layDanhMucTheoMa(maDanhMuc);

		TinTuc tinTuc = new TinTuc(maTinTuc, tieuDe, noiDungTT, lienKet, danhMuc);

		boolean result = danhSachTinTucQuanLy.themTinTuc(tinTuc);

		if (result) {
			response.sendRedirect(request.getContextPath() + "/DanhSachTinTuc");
		} else {
			response.sendRedirect(request.getContextPath() + "/TinTucForm");
		}

	}

}
