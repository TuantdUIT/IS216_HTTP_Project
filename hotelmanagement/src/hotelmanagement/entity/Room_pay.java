/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.entity;

/**
 *
 * @author 84352
 */
public class Room_pay {
    public String mahd, madvp;
    public int songay;
    public double tongtien;

    public Room_pay() {
    }
    
    public Room_pay(String hd, String dvp, int so, double t){
        mahd = hd;
        madvp = dvp;
        songay = so;
        tongtien = t;
    }
    
    public void setMahd(String hd){
        mahd = hd;
    }
    public void setMadvp(String dvp){
        madvp = dvp;
    }
    public void setSongay(int so){
        songay = so;
    }
    public void setGia(double g){
        tongtien = g;
    } 
}
