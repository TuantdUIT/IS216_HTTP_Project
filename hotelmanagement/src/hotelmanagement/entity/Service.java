
package hotelmanagement.entity;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Service {
    
    private String maDV;
    private String tenDV;
    private String moTa;
    private int donGia;
    private String tinhTrang;

    // Constructor mặc định (không tham số)
    public Service() {
    }

    // Constructor có tham số

    public Service(String maDV, String tenDV, String moTa, int donGia, String tinhTrang) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.moTa = moTa;
        this.donGia = donGia;
        this.tinhTrang = tinhTrang;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
}