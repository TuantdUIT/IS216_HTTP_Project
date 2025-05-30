
package hotelmanagement.entity;

import java.util.ArrayList;


public class Room {
    
    private String roomID;
    private String loaiPhong;
    private String moTa;
    private String tinhTrang;
    private int donGia;
    
    public Room(){
        
    }
    
    public Room(String roomID, String loaiPhong, String moTa, int donGia, String tinhTrang){
        this.roomID = roomID;
        this.loaiPhong = loaiPhong;
        this.moTa = moTa;
        this.donGia = donGia;
        this.tinhTrang = tinhTrang;
    }
    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
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
