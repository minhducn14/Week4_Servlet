package modal;

public class TinTuc {
	private long maTinTuc;
	private String tieuDe;
	private String noiDungTT;
	private String lienKet;
	private DanhMuc danhMuc;

	public long getMaTinTuc() {
		return maTinTuc;
	}

	public void setMaTinTuc(long maTinTuc) {
		this.maTinTuc = maTinTuc;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getNoiDungTT() {
		return noiDungTT;
	}

	public void setNoiDungTT(String noiDungTT) {
		this.noiDungTT = noiDungTT;
	}

	public String getLienKet() {
		return lienKet;
	}

	public void setLienKet(String lienKet) {
		this.lienKet = lienKet;
	}

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	public TinTuc(long maTinTuc, String tieuDe, String noiDungTT, String lienKet, DanhMuc danhMuc) {
		super();
		this.maTinTuc = maTinTuc;
		this.tieuDe = tieuDe;
		this.noiDungTT = noiDungTT;
		this.lienKet = lienKet;
		this.danhMuc = danhMuc;
	}

	public TinTuc() {
		super();
	}

	@Override
	public String toString() {
		return "TinTuc [maTinTuc=" + maTinTuc + ", tieuDe=" + tieuDe + ", noiDungTT=" + noiDungTT + ", lienKet="
				+ lienKet + ", danhMuc=" + danhMuc + "]";
	}

}
