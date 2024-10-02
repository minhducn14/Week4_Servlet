package service;

import java.util.List;

import javax.sql.DataSource;

import dao.TinTucDAO;
import daoImpl.TinTucDAOImpl;
import jakarta.annotation.Resource;
import modal.TinTuc;

public class DanhSachTinTucQuanLy {

	private DataSource dataSource;
	private TinTucDAO tinTucDAO;

	public DanhSachTinTucQuanLy(DataSource dataSource) {
		this.dataSource = dataSource;
		this.tinTucDAO = new TinTucDAOImpl(dataSource);
	}

	public List<TinTuc> layDanhSachTinTuc() {
		return tinTucDAO.getAllTinTuc();
	}

	public List<TinTuc> layTinTucTheoDanhMuc(int maDM) {
		return tinTucDAO.getTinTucByMaDanhMuc(maDM);
	}

	public boolean themTinTuc(TinTuc tinTuc) {
		return tinTucDAO.addTinTuc(tinTuc);
	}

	public boolean xoaTinTuc(int maTT) {
		return tinTucDAO.deleteTinTuc(maTT);
	}

	public boolean suaTinTuc(TinTuc tinTuc) {
		return tinTucDAO.updateTinTuc(tinTuc);
	}

	public TinTuc layTinTucTheoMa(int maTT) {
		return tinTucDAO.getTinTuc(maTT).get();
	}

}
