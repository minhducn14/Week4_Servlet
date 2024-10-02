package controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.PageContext;
import modal.DanhMuc;
import modal.TinTuc;
import service.DanhSachDanhMucQuanLy;
import service.DanhSachTinTucQuanLy;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class DanhSachTinTucServlet
 */
public class DanhSachTinTucServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/quanlydanhmuc")
	private DataSource dataSource;

	private DanhSachTinTucQuanLy danhSachTinTucQuanLy;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DanhSachTinTucServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		danhSachTinTucQuanLy = new DanhSachTinTucQuanLy(this.dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<TinTuc> tinTucs = danhSachTinTucQuanLy.layDanhSachTinTuc();
//		String dataRows = "";
//		for (TinTuc tinTuc : tinTucs) {
//			dataRows += "<tr>";
//			dataRows += "<td>" + tinTuc.getMaTinTuc() + "</td>";
//			dataRows += "<td>" + tinTuc.getTieuDe() + "</td>";
//			dataRows += "<td>" + tinTuc.getnoiDungTT() + "</td>";
//			dataRows += "<td>" + tinTuc.getLienKet() + "</td>";
//			dataRows += "<td>" + tinTuc.getMaDanhMuc() + "</td>";
//			dataRows += "</tr>";
//		}
//		request.setAttribute("dataRows", dataRows);
		request.setAttribute("tinTucs", tinTucs);

		request.getRequestDispatcher("/DanhSachTinTuc.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
