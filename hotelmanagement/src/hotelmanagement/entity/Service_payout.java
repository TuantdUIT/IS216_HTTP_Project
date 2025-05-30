/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;


public class Service_payout {
    public String mahd, madvti, tendvti;
    public double gia;
    
    public Service_payout() {
    }
    
    public Service_payout(String hd, String dvti, String ten, double g){      
        madvti = dvti;
        mahd = hd;
        gia = g;
        tendvti = ten;
    }
    public void setMati(String dvti){
        madvti = dvti;
    }
    public void setMahd(String hd){
        mahd = hd;
    }
    public void setTenti(String ten){
        tendvti = ten;
    }
    
    public void setGia(double g){
        gia = g;
    }
    
//    @Override
//    public String toString(){
//        return 0;
//    }
}
