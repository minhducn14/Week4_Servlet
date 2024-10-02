package service;

import java.util.List;

import javax.sql.DataSource;

import dao.DanhMucDAO;
import daoImpl.DanhMucDAOImpl;
import modal.DanhMuc;

public class DanhSachDanhMucQuanLy {

	private DataSource dataSource;
	private DanhMucDAO danhMucDAO;

	public DanhSachDanhMucQuanLy(DataSource dataSource) {
		this.dataSource = dataSource;
		danhMucDAO = new DanhMucDAOImpl(dataSource);
	}

	public boolean themDanhMuc(DanhMuc danhMuc) {
		return danhMucDAO.addDanhMuc(danhMuc);
	}

	public boolean suaDanhMuc(DanhMuc danhMuc) {
		return danhMucDAO.updateDanhMuc(danhMuc);
	}

	public boolean xoaDanhMuc(int maDM) {
		return danhMucDAO.deleteDanhMuc(maDM);
	}

	public DanhMuc layDanhMucTheoMa(int maDM) {
		return danhMucDAO.getDanhMuc(maDM).get();
	}

	public List<DanhMuc> layTatCaDanhMuc() {
		return danhMucDAO.getAllDanhMuc();
	}

}
