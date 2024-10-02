package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import dao.DanhMucDAO;
import modal.DanhMuc;

public class DanhMucDAOImpl implements DanhMucDAO {

	private DataSource datasource;

	public DanhMucDAOImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	@Override
	public List<DanhMuc> getAllDanhMuc() {
		// TODO Auto-generated method stub
//		DANHMUC (MADM, TENDANHMUC, NGUOIQUANLY, GHICHU)
		String sql = "SELECT * FROM DANHMUC";
		List<DanhMuc> list = new ArrayList<DanhMuc>();

		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				int maDanhMuc = rs.getInt("MADM");
				String tenDanhMuc = rs.getString("TENDANHMUC");
				String nguoiquanly = rs.getString("NGUOIQUANLY");
				String ghiChu = rs.getString("GHICHU");
				list.add(new DanhMuc(maDanhMuc, tenDanhMuc, nguoiquanly, ghiChu));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Optional<DanhMuc> getDanhMuc(int maDanhMuc) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM DANHMUC WHERE MADM = ?";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setInt(1, maDanhMuc);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					DanhMuc dm = new DanhMuc();
					dm.setmaDM(rs.getInt("MADM"));
					dm.setTenDanhMuc(rs.getString("TENDANHMUC"));
					dm.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
					dm.setGhiChu(rs.getString("GHICHU"));
					return Optional.of(dm);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public boolean addDanhMuc(DanhMuc dm) {
		// TODO Auto-generated method stub
//		DANHMUC (MADM, TENDANHMUC, NGUOIQUANLY, GHICHU)
		String sql = "INSERT INTO DANHMUC(MADM, TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES(?,?,?,?)";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setInt(1, dm.getmaDM());
			ps.setString(2, dm.getTenDanhMuc());
			ps.setString(3, dm.getNguoiQuanLy());
			ps.setString(4, dm.getGhiChu());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDanhMuc(DanhMuc dm) {
		// TODO Auto-generated method stub
		String sql = "UPDATE DANHMUC SET TENDANHMUC = ?, NGUOIQUANLY = ?, GHICHU = ? WHERE MADM = ?";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setString(1, dm.getTenDanhMuc());
			ps.setString(2, dm.getNguoiQuanLy());
			ps.setString(3, dm.getGhiChu());
			ps.setInt(4, dm.getmaDM());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDanhMuc(int maDanhMuc) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM DANHMUC WHERE MADM = ?";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setInt(1, maDanhMuc);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
