
package hotelmanagement.entity;


public class Invoice {
    private String mahd;
    private String makh;
    private String madvp;
    private String madvti;
    private String mafb;
    private String nguoixacnhan;
    private String ngaytao;
    private String ngaybd;
    private String ngaykt;
    private String ngaythanhtoan;
    private int tongtien;
    private String tinhtrangtt;
    private int slsd;

    // Constructor mặc định (không tham số)
    public Invoice() {
    }

    // Constructor có tham số
    public Invoice(String mahd, String makh, String madvp, String madvti, String mafb, String nguoixacnhan,
                   String ngaytao, String ngaybd, String ngaykt, String ngaythanhtoan,
                   int tongtien, String tinhtrangtt, int slsd) {
        this.mahd = mahd;
        this.makh = makh;
        this.madvp = madvp;
        this.madvti = madvti;
        this.mafb = mafb;
        this.nguoixacnhan = nguoixacnhan;
        this.ngaytao = ngaytao;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.ngaythanhtoan = ngaythanhtoan;
        this.tongtien = tongtien;
        this.tinhtrangtt = tinhtrangtt;
        this.slsd = slsd;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMadvp() {
        return madvp;
    }

    public void setMadvp(String madvp) {
        this.madvp = madvp;
    }

    public String getMadvti() {
        return madvti;
    }

    public void setMadvti(String madvti) {
        this.madvti = madvti;
    }

    public String getMafb() {
        return mafb;
    }

    public void setMafb(String mafb) {
        this.mafb = mafb;
    }

    public String getNguoixacnhan() {
        return nguoixacnhan;
    }

    public void setNguoixacnhan(String nguoixacnhan) {
        this.nguoixacnhan = nguoixacnhan;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(String ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getTinhtrangtt() {
        return tinhtrangtt;
    }

    public void setTinhtrangtt(String tinhtrangtt) {
        this.tinhtrangtt = tinhtrangtt;
    }

    public int getSlsd() {
        return slsd;
    }

    public void setSlsd(int slsd) {
        this.slsd = slsd;
    }
    
    
    
}
