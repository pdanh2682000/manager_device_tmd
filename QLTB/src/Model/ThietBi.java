package Model;

import java.sql.Date;

public class ThietBi {
	private String maTB, tenTB, maPhong, maTinhTrang, model, country, company;
	private Date ngayNhapTB, hanBT;
	private int namSX, namSD;
	private double giaTien;
	
	
	
	public ThietBi() {
		super();
		// TODO Auto-generated constructor stub
		maTB = new String("");
		tenTB = new String("");
		maPhong = new String("");
		maTinhTrang = new String("");
		model = new String("");
		country = new String("");
		company = new String("");
		ngayNhapTB = new Date(0);
		hanBT = new Date(0);
		namSX = 0;
		namSD = 0;
		giaTien = 0;
		
	}
	public ThietBi(String maTB, String tenTB, String maPhong, String maTinhTrang, String model, String country,
			String company, Date ngayNhapTB, Date hanBT, int namSX, int namSD, double giaTien) {
		super();
		this.maTB = maTB;
		this.tenTB = tenTB;
		this.maPhong = maPhong;
		this.maTinhTrang = maTinhTrang;
		this.model = model;
		this.country = country;
		this.company = company;
		this.ngayNhapTB = ngayNhapTB;
		this.hanBT = hanBT;
		this.namSX = namSX;
		this.namSD = namSD;
		this.giaTien = giaTien;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public int getNamSX() {
		return namSX;
	}
	public void setNamSX(int namSX) {
		this.namSX = namSX;
	}
	public int getNamSD() {
		return namSD;
	}
	public void setNamSD(int namSD) {
		this.namSD = namSD;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

}
