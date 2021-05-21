package Model;

import java.sql.Date;

public class ThietBi {
	private String maTB, tenTB, maPhong, maTinhTrang;
	private Date ngayNhapTB, hanBT;
	
	public ThietBi() {
		maTB = new String("");
		tenTB = new String("");
		maPhong = new String("");
		maTinhTrang = new String("");
		ngayNhapTB = new Date(0);
		hanBT = new Date(0);
	}
	public ThietBi(String maTB, String tenTB, String maPhong,
			String maTinhTrang, Date ngayNhapTB, Date hanBT) {
		this.maTB = new String(maTB);
		this.tenTB = new String(tenTB);
		this.maPhong = new String(maPhong);
		this.maTinhTrang = new String(maTinhTrang);
		this.ngayNhapTB = new Date(ngayNhapTB.getTime());
		this.hanBT = new Date(hanBT.getTime());
	}
	public String getMaTB() {
		return maTB;
	}
	public void setMaTB(String maTB) {
		this.maTB = maTB;
	}
	public String getTenTB() {
		return tenTB;
	}
	public void setTenTB(String tenTB) {
		this.tenTB = tenTB;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaTinhTrang() {
		return maTinhTrang;
	}
	public void setMaTinhTrang(String maTinhTrang) {
		this.maTinhTrang = maTinhTrang;
	}
	public Date getNgayNhapTB() {
		return ngayNhapTB;
	}
	public void setNgayNhapTB(Date ngayNhapTB) {
		this.ngayNhapTB = ngayNhapTB;
	}
	public Date getHanBT() {
		return hanBT;
	}
	public void setHanBT(Date hanBT) {
		this.hanBT = hanBT;
	}
	
	
}
