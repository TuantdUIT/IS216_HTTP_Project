
package hotelmanagement.entity;

import java.util.ArrayList;


public class Invoice {
    
    private String maHD;
    private String maKH;
    private String maDVP;
    private String maDVTI;
    private String maFB;
    private String maNV;
    private String ngayTao;
    private String ngayBD;
    private String ngayKT;
    private String ngayThanhToan;
    private int TongTien;
    private String tinhtrangTT;
    private int SLSD;
    
    public Invoice(){}

    public Invoice(String maHD, String maKH, String maDVP, String maDVTI, String maFB, String maNV, String ngayTao, String ngayBD, String ngayKT, String ngayThanhToan, int TongTien, String tinhtrangTT, int SLSD) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maDVP = maDVP;
        this.maDVTI = maDVTI;
        this.maFB = maFB;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.ngayThanhToan = ngayThanhToan;
        this.TongTien = TongTien;
        this.tinhtrangTT = tinhtrangTT;
        this.SLSD = SLSD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaDVP() {
        return maDVP;
    }

    public void setMaDVP(String maDVP) {
        this.maDVP = maDVP;
    }

    public String getMaDVTI() {
        return maDVTI;
    }

    public void setMaDVTI(String maDVTI) {
        this.maDVTI = maDVTI;
    }

    public String getMaFB() {
        return maFB;
    }

    public void setMaFB(String maFB) {
        this.maFB = maFB;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public String getTinhTrangTT() {
        return tinhtrangTT;
    }

    public void setTinhTrangTT(String tinhtrangTT) {
        this.tinhtrangTT = tinhtrangTT;
    }

    public int getSLSD() {
        return SLSD;
    }

    public void setSLSD(int SLSD) {
        this.SLSD = SLSD;
    }
    
    

}   // Constructor mặc định (không tham số)
    