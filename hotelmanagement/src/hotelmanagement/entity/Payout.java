/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;


public class Payout {
    public String mahd, loaiphong, tendvti, ngaybatdau, ngayketthuc, mota;
    public String tinhtrangtt;
    
    public Payout() {
    }
    
    public Payout(String hd, String dvp, String dvti, String bd, String kt, String tt, String mt){      
        loaiphong = dvp;
        tendvti = dvti;
        ngaybatdau = bd;
        ngayketthuc = kt;
        mahd = hd;
        tinhtrangtt = tt;
        mota = mt;
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
    
    public void setTendvti(String tpString){
        tendvti = tpString;
    }
    public void setTinhtrang(String tt){
        tinhtrangtt = tt;
    }
    public void setMota(String mt){
        mota = mt;
    }
//    @Override
//    public String toString(){
//        return 0;
//    }
}
