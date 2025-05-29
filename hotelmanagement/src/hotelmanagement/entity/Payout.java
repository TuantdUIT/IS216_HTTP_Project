/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;


public class Payout {
    public String mahd, loaiphong, tenphong, ngaybatdau, ngayketthuc;
    public String tinhtrangtt;
    
    public Payout() {
    }
    
    public Payout(String hd, String dvp, String dvti, String bd, String kt, String tt){      
        loaiphong = dvp;
        tenphong = dvti;
        ngaybatdau = bd;
        ngayketthuc = kt;
        mahd = hd;
        tinhtrangtt = tt;
    }
    public void setLoaiphong(String dvp){
        loaiphong = dvp;
    }
    public void setMahd(String hd){
        mahd = hd;
    }
    public void setNgaybd(String bd){
        ngaybatdau = bd;
    }
    public void setNgaykt(String kt){
        ngayketthuc = kt;
    }
    
    public void setTenphong(String tpString){
        tenphong = tpString;
    }
    public void setTinhtrang(String tt){
        tinhtrangtt = tt;
    }
    
//    @Override
//    public String toString(){
//        return 0;
//    }
}
