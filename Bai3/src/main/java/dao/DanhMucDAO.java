package dao;

import java.util.List;
import java.util.Optional;

import modal.DanhMuc;

public interface DanhMucDAO {
	public List<DanhMuc> getAllDanhMuc();

	public Optional<DanhMuc> getDanhMuc(int maDanhMuc);

	public boolean addDanhMuc(DanhMuc dm);

	public boolean updateDanhMuc(DanhMuc dm);

	public boolean deleteDanhMuc(int maDanhMuc);
}
