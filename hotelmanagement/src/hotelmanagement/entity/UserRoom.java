/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;

/**
 *
 * @author takar
 */
public class UserRoom {
    private String MADVP;
    private String LoaiPhong;
    private String MoTa;
    private String NGAYBDSD;
    private String NGAYKTSD;
    private String DonGia;
    private String TongTien;
    
    
    
    public UserRoom(){}
    
    public UserRoom(String MADVP, String LoaiPhong, String MoTa, String NGAYBDSD, String NGAYKTSD, String DonGia, String TongTien)
    {
        this.MADVP = MADVP;
        this.LoaiPhong = LoaiPhong;
        this.MoTa = MoTa;
        this.NGAYBDSD = NGAYBDSD;
        this.NGAYKTSD = NGAYKTSD;
        this.DonGia = DonGia;
        this.TongTien = TongTien;
    }
    
    public String getMADVP() {
        return MADVP;
    }

    public void setMADVP(String MADVP) {
        this.MADVP = MADVP;
    }

    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String LoaiPhong) {
        this.LoaiPhong = LoaiPhong;
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
