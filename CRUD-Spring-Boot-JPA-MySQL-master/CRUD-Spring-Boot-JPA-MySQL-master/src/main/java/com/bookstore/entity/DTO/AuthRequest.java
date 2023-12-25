package com.bookstore.entity.DTO;

import com.bookstore.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthRequest {
    private String tendangnhap;
    private String matkhau;
    private User user;
	private String loaiTaiKhoan;
    
	public AuthRequest() {
		super();
	}
	public AuthRequest(String tendangnhap, String matkhau, User user) {
		super();
		this.tendangnhap = tendangnhap;
		this.matkhau = matkhau;
		this.user = user;
	}

	public AuthRequest(String tendangnhap, String matkhau, User user, String loaiTaiKhoan) {
		this.tendangnhap = tendangnhap;
		this.matkhau = matkhau;
		this.user = user;
		this.loaiTaiKhoan = loaiTaiKhoan;
	}

	public String getTendangnhap() {
		return tendangnhap;
	}
	public void setTendangnhap(String tendangnhap) {
		this.tendangnhap = tendangnhap;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}

	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}
}
