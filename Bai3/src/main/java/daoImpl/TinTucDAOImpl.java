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
import dao.TinTucDAO;
import modal.TinTuc;

public class TinTucDAOImpl implements TinTucDAO {

	private DataSource datasource;
	private DanhMucDAO danhMucDAO;

	public TinTucDAOImpl(DataSource datasource) {
		this.datasource = datasource;
		this.danhMucDAO = new DanhMucDAOImpl(datasource);
	}

	@Override
	public boolean addTinTuc(TinTuc tt) {
		// TODO Auto-generated method stub
//		TINTUC (MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM)
		String sql = "INSERT INTO TINTUC (MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?, ?)";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setLong(1, tt.getMaTinTuc());
			ps.setString(2, tt.getTieuDe());
			ps.setString(3, tt.getNoiDungTT());
			ps.setString(4, tt.getLienKet());
			ps.setInt(5, tt.getDanhMuc().getmaDM());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateTinTuc(TinTuc tt) {
		// TODO Auto-generated method stub
		String sql = "UPDATE TINTUC SET TIEUDE = ?, NOIDUNGTT = ?, LIENKET = ?, MADM = ? WHERE MATT = ?";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setString(1, tt.getTieuDe());
			ps.setString(2, tt.getNoiDungTT());
			ps.setString(3, tt.getLienKet());
			ps.setInt(4, tt.getDanhMuc().getmaDM());
			ps.setLong(5, tt.getMaTinTuc());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteTinTuc(long maTinTuc) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM TINTUC WHERE MATT = ?";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setLong(1, maTinTuc);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Optional<TinTuc> getTinTuc(long maTinTuc) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM TINTUC WHERE MATT = ?";
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			if (rs.next()) {
				TinTuc tt = new TinTuc();
				tt.setMaTinTuc(rs.getLong("MATT"));
				tt.setTieuDe(rs.getString("TIEUDE"));
				tt.setNoiDungTT(rs.getString("NOIDUNGTT"));
				tt.setLienKet(rs.getString("LIENKET"));
				tt.setDanhMuc(danhMucDAO.getDanhMuc(rs.getInt("MADM")).get());
				return Optional.of(tt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<TinTuc> getAllTinTuc() {
		String sql = "SELECT tt.MADM, tt.MATT, tt.TIEUDE, tt.NOIDUNGTT, tt.LIENKET \r\n"
				+ "FROM tintuc tt \r\n"
				+ "ORDER BY tt.MADM DESC";
		List<TinTuc> list = new ArrayList<TinTuc>();
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				TinTuc tt = new TinTuc();
				tt.setMaTinTuc(rs.getLong("MATT"));
				tt.setTieuDe(rs.getString("TIEUDE"));
				tt.setNoiDungTT(rs.getString("NOIDUNGTT"));
				tt.setLienKet(rs.getString("LIENKET"));
				tt.setDanhMuc(danhMucDAO.getDanhMuc(rs.getInt("MADM")).get());
				list.add(tt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TinTuc> getTinTucByMaDanhMuc(int maDanhMuc) {
		String sql = "SELECT * FROM TINTUC WHERE MADM = ?";
		List<TinTuc> list = new ArrayList<TinTuc>();
		try (Connection con = this.datasource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				TinTuc tt = new TinTuc();
				tt.setMaTinTuc(rs.getLong("MATT"));
				tt.setTieuDe(rs.getString("TIEUDE"));
				tt.setNoiDungTT(rs.getString("NOIDUNGTT"));
				tt.setLienKet(rs.getString("LIENKET"));
				tt.setDanhMuc(danhMucDAO.getDanhMuc(rs.getInt("MADM")).get());
				list.add(tt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
