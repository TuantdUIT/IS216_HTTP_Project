/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;

/**
 *
 * @author 84352
 */
public class Service_pay {
    public String mahd, madvti, name;
    public double tongtien;

    public Service_pay() {
    }
    
    public Service_pay(String hd, String dvti, String n, double t){
        mahd = hd;
        madvti = dvti;
        name = n;
        tongtien = t;
    }
    
    public void setMahd(String hd){
        mahd = hd;
    }
    public void setMadvti(String dvp){
        madvti = dvp;
    }
    public void setName(String so){
        name = so;
    }
    public void setGia(double g){
        tongtien = g;
    } 
}
