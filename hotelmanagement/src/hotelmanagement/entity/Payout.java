/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;


public class Payout {
    public String makh, mahd, madvp, madvti, ngaybatdau, ngayketthuc;
    public double gia;

    public Payout() {
    }
    
    public Payout(String kh, String hd, String dvp, String dvti, String bd, String kt, double g){
        makh = kh;
        mahd = hd;
        madvp = dvp;
        madvti = dvti;
        ngaybatdau = bd;
        ngayketthuc = kt;
        gia = g;
    }
    
//    @Override
//    public String toString(){
//        return 0;
//    }
}
