/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;

/**
 *
 * @author takar
 */
public class UserService {
    
    private String MADVTI;
    private String TenDVTI;
    private String MoTa;
    private String NGAYBDSD;
    private String NGAYKTSD;
    private String DonGia;
    private String TongTien;
    
    
    
    public UserService(){}
    
    public UserService(String MADVTI, String TenDVTI, String MoTa, String NGAYBDSD, String NGAYKTSD, String DonGia, String TongTien)
    {
        this.MADVTI = MADVTI;
        this.TenDVTI = TenDVTI;
        this.MoTa = MoTa;
        this.NGAYBDSD = NGAYBDSD;
        this.NGAYKTSD = NGAYKTSD;
        this.DonGia = DonGia;
        this.TongTien = TongTien;
    }
    
    public String getMADVTI() {
        return MADVTI;
    }

    public void setMADVTI(String MADVTI) {
        this.MADVTI = MADVTI;
    }

    public String getTenDVTI() {
        return this.TenDVTI;
    }

    public void setTenDVTI(String TenDVTI) {
        this.TenDVTI = TenDVTI;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getNgayBDSD() {
        return NGAYBDSD;
    }

    public void setNgayBDSD(String NGAYBDSD) {
        this.NGAYBDSD = NGAYBDSD;
    }

    public String getNgayKTSD() {
        return NGAYKTSD;
    }

    public void setNgayKTSD(String NGAYKTSD) {
        this.NGAYKTSD = NGAYKTSD;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String TongTien) {
        this.TongTien = TongTien;
    }
}
