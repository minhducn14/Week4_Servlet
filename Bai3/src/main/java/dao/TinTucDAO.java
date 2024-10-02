package dao;

import java.util.List;
import java.util.Optional;

import modal.TinTuc;

public interface TinTucDAO {
	public boolean addTinTuc(TinTuc tt);

	public boolean updateTinTuc(TinTuc tt);

	public boolean deleteTinTuc(long maTinTuc);

	public Optional<TinTuc> getTinTuc(long maTinTuc);

	public List<TinTuc> getAllTinTuc();

	public List<TinTuc> getTinTucByMaDanhMuc(int maDanhMuc);
}
