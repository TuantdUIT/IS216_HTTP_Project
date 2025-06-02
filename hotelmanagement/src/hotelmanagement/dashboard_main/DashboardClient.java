/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelmanagement.dashboard_main;

import hotelmanagement.Payment.Bank_BookRoom;
import hotelmanagement.entity.UserService;
import hotelmanagement.entity.UserRoom;
import hotelmanagement.entity.Room;
import hotelmanagement.entity.Service;
import hotelmanagement.entity.dba_connection;
import hotelmanagement.entity.Current_User;
import hotelmanagement.entity.Payout;
import java.sql.PreparedStatement;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.sql.Date;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class DashboardClient extends javax.swing.JFrame {

    /**
     * Creates new form DashboardClient
     */
    
    
    public DashboardClient() {
        initComponents();
        pnlCard.add(CardMyRooms, "MyRooms");
        pnlCard.add(CardMyServices, "MyServices");
        pnlCard.add(CardBookRooms, "BookRooms");
        pnlCard.add(CardBookServices, "BookServices");
        
        
        //Lấy thông tin khách hàng để hiển thị
        
        String sql1 = "SELECT * FROM KHACHHANG WHERE trim(SDT) = trim(?)";
        dba_connection connect = new dba_connection();
        try {
            Class.forName(connect.driver);
            Connection con = DriverManager.getConnection(connect.url, connect.username, connect.password);
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, Current_User.phonenumber);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String hoTen = rs.getString("HOTEN");
                lblHoTen.setText("Hi, " + hoTen + "!");
                Current_User.HoTen = hoTen;
                String MaKH = rs.getString("MAKH");
                Current_User.MaKH = MaKH;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "Cannot load user infomation " + ex.getMessage());
        }
        //Hiển thị thông tin vô bảng phòng trống
        Reload_Table_Rooms();
        Reload_Table_Services();
        Load_Table_UserRooms();
        Load_Table_UserServices();
    }
    

    
    private void Reload_Table_Rooms()
    {
        dba_connection connect = new dba_connection();
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.clear();
        
        String sql = "SELECT * FROM DVPHONG ORDER BY MADVP ASC";
        try {
            Class.forName(connect.driver);
            Connection con = DriverManager.getConnection(connect.url, connect.username, connect.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Room room = new Room();
                room.setRoomID(rs.getString("MADVP"));
                room.setLoaiPhong(rs.getString("LOAIPHONG"));
                room.setMoTa(rs.getString("MOTA")); 
                room.setDonGia(rs.getInt("DONGIA"));
                rooms.add(room);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "Cannot load rooms infomation " + ex.getMessage());
        }
        
        DefaultTableModel model = (DefaultTableModel) tabRooms.getModel();
        model.setRowCount(0); 

        for (Room r : rooms) {
            model.addRow(new Object[] {
            r.getRoomID(),
            r.getLoaiPhong(),
            r.getMoTa(),
            r.getDonGia()
            });
        }
    }
    private void Reload_Table_Services()
    {
        dba_connection connect = new dba_connection();
        ArrayList<Service> services = new ArrayList<>();
        services.clear();
        
        String sql = "SELECT * FROM DVTIENICH ORDER BY MADVTI ASC";
        try {
            Class.forName(connect.driver);
            Connection con = DriverManager.getConnection(connect.url, connect.username, connect.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Service srv = new Service();
                srv.setMaDV(rs.getString("MADVTI"));
                srv.setTenDV(rs.getString("TENDVTI"));
                srv.setMoTa(rs.getString("MOTA")); 
                srv.setDonGia(rs.getInt("DONGIA"));
                services.add(srv);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null, "Cannot load Services infomation " + ex.getMessage());
        }
        
        DefaultTableModel model = (DefaultTableModel) tabServices.getModel();
        model.setRowCount(0); 

        for (Service r : services) {
            model.addRow(new Object[] {
            r.getMaDV(),
            r.getTenDV(),
            r.getMoTa(),
            r.getDonGia()
            });
        }
    }
    
    public void Load_Table_UserRooms()
    {
        dba_connection connect = new dba_connection();
        ArrayList<UserRoom> UserRooms = new ArrayList<>();
        UserRooms.clear();
        
        String sql = "select P.MADVP, P.LOAIPHONG, P.MOTA, HD.NGAYBD, HD.NGAYKT, P.DONGIA, HD.TONGTIEN "
                    + "from DVPHONG P join HOADON HD on P.MADVP = HD.MADVP "
                    + "join KHACHHANG KH on KH.MAKH = HD.MAKH "
                    + "where HD.TINHTRANGTT IN (N'Chưa thanh toán', N'Đã thanh toán' ) and KH.SDT = " + Current_User.phonenumber 
                    + " order by P.MADVP ASC, HD.NGAYKT ASC";
        
        Connection con;
        try{
            Class.forName(connect.driver);
            con = DriverManager.getConnection(connect.url, connect.username, connect.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserRoom ur = new UserRoom();
                ur.setMADVP(rs.getString("MADVP"));
                ur.setLoaiPhong(rs.getString("LOAIPHONG"));
                ur.setMoTa(rs.getString("MOTA"));
                ur.setNgayBDSD(rs.getString("NGAYBD"));
                ur.setNgayKTSD(rs.getString("NGAYKT"));
                ur.setDonGia(rs.getString("DONGIA"));
                ur.setTongTien(rs.getString("TONGTIEN"));
                UserRooms.add(ur);
            }
        }
        catch(SQLException ex){} catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Cannot load rooms infomation " + ex.getMessage());
        }
        
        DefaultTableModel model = (DefaultTableModel) tabUserRooms.getModel();
        model.setRowCount(0); 

        for (UserRoom ur : UserRooms) {
            model.addRow(new Object[] {
                ur.getMADVP(),
                ur.getLoaiPhong(),
                ur.getMoTa(),
                ur.getNgayBDSD(),
                ur.getNgayKTSD(),
                ur.getDonGia(),
                ur.getTongTien()
            });
        }
    }

    private void Load_Table_UserServices()
    {
        dba_connection connect = new dba_connection();
        ArrayList<UserService> UserServices = new ArrayList<>();
        UserServices.clear();
        
        String sql = "select TI.MADVTI, TI.TENDVTI, TI.MOTA, HD.NGAYBD, HD.NGAYKT, TI.DONGIA, HD.TONGTIEN "
                    + "from DVTIENICH TI join HOADON HD on TI.MADVTI = HD.MADVTI "
                    + "join KHACHHANG KH on KH.MAKH = HD.MAKH "
                    + "where (HD.TINHTRANGTT = N'Chưa thanh toán' or HD.NGAYKT > SYSDATE) and trim(KH.SDT) = " + Current_User.phonenumber
                    + " order by TI.MADVTI ASC";
        
        Connection con;
        try{
            Class.forName(connect.driver);
            con = DriverManager.getConnection(connect.url, connect.username, connect.password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserService us = new UserService();
                us.setMADVTI(rs.getString("MADVTI"));
                us.setTenDVTI(rs.getString("TENDVTI"));
                us.setMoTa(rs.getString("MOTA"));
                us.setNgayBDSD(rs.getString("NGAYBD"));
                us.setNgayKTSD(rs.getString("NGAYKT"));
                us.setDonGia(rs.getString("DONGIA"));
                us.setTongTien(rs.getString("TONGTIEN"));
                UserServices.add(us);
            }
        }
        catch(SQLException ex){} catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Cannot load services infomation " + ex.getMessage());
        }
        
        DefaultTableModel model = (DefaultTableModel) tabUserServices.getModel();
        model.setRowCount(0); 

        for (UserService us : UserServices) {
            model.addRow(new Object[] {
                us.getMADVTI(),
                us.getTenDVTI(),
                us.getMoTa(),
                us.getNgayBDSD(),
                us.getNgayKTSD(),
                us.getDonGia(),
                us.getTongTien()
            });
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlButton = new javax.swing.JPanel();
        panelMyInfo = new javax.swing.JPanel();
        btnMyRooms = new javax.swing.JButton();
        btnMyServices = new javax.swing.JButton();
        panelBook = new javax.swing.JPanel();
        btnBookRooms = new javax.swing.JButton();
        btnBookServices = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        pnlCard = new javax.swing.JPanel();
        CardMyRooms = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabUserRooms = new javax.swing.JTable();
        Title2 = new javax.swing.JLabel();
        btnExtendR = new javax.swing.JButton();
        btnReloadURs = new javax.swing.JButton();
        CardMyServices = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabUserServices = new javax.swing.JTable();
        Title4 = new javax.swing.JLabel();
        CardBookRooms = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        labCheckindate = new javax.swing.JLabel();
        labCheckoutdate = new javax.swing.JLabel();
        datePickerCheckin = new com.github.lgooddatepicker.components.DatePicker();
        datePickerCheckout = new com.github.lgooddatepicker.components.DatePicker();
        labCheckoutdate1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabRooms = new javax.swing.JTable();
        btnBookR = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        CardBookServices = new javax.swing.JPanel();
        labTotalUsage = new javax.swing.JPanel();
        Title1 = new javax.swing.JLabel();
        labStartDate = new javax.swing.JLabel();
        labEndDate = new javax.swing.JLabel();
        datePickerStartDate = new com.github.lgooddatepicker.components.DatePicker();
        datePickerEndDate = new com.github.lgooddatepicker.components.DatePicker();
        labChooseSer = new javax.swing.JLabel();
        scrlServices = new javax.swing.JScrollPane();
        tabServices = new javax.swing.JTable();
        btnBookS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(46, 121, 130));
        jPanel1.setPreferredSize(new java.awt.Dimension(1142, 665));

        pnlButton.setBackground(new java.awt.Color(46, 121, 130));

        panelMyInfo.setBackground(new java.awt.Color(46, 121, 130));
        panelMyInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnMyRooms.setText("My Rooms");
        btnMyRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyRoomsActionPerformed(evt);
            }
        });

        btnMyServices.setText("My Services");
        btnMyServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyServicesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMyInfoLayout = new javax.swing.GroupLayout(panelMyInfo);
        panelMyInfo.setLayout(panelMyInfoLayout);
        panelMyInfoLayout.setHorizontalGroup(
            panelMyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMyInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMyServices, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMyRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMyInfoLayout.setVerticalGroup(
            panelMyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMyInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMyRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMyServices, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBook.setBackground(new java.awt.Color(46, 121, 130));
        panelBook.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnBookRooms.setText("Book rooms");
        btnBookRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookRoomsActionPerformed(evt);
            }
        });

        btnBookServices.setText("Book services");
        btnBookServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookServicesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBookLayout = new javax.swing.GroupLayout(panelBook);
        panelBook.setLayout(panelBookLayout);
        panelBookLayout.setHorizontalGroup(
            panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBookRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBookServices, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelBookLayout.setVerticalGroup(
            panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBookRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBookServices, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/client_icon.png"))); // NOI18N

        lblHoTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoTen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHoTen.setForeground(new java.awt.Color(255, 255, 255));

        btnExit.setText("Exit");
        btnExit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 51, 51));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonLayout.createSequentialGroup()
                            .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonLayout.createSequentialGroup()
                            .addComponent(panelMyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)))
                    .addComponent(panelBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelMyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(panelBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addGap(70, 70, 70))
        );

        pnlCard.setLayout(new java.awt.CardLayout());

        CardMyRooms.setBackground(new java.awt.Color(255, 255, 255));

        tabUserRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MADVP", "TYPE", "DESCRIPTION", "NGAYBD", "NGAYKT", "PRICE / DAY", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabUserRooms);

        Title2.setText("MY ROOMS");
        Title2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N

        btnExtendR.setText("Extend room");
        btnExtendR.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnExtendR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtendRActionPerformed(evt);
            }
        });

        btnReloadURs.setText("Reload rooms");
        btnReloadURs.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReloadURs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadURsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CardMyRoomsLayout = new javax.swing.GroupLayout(CardMyRooms);
        CardMyRooms.setLayout(CardMyRoomsLayout);
        CardMyRoomsLayout.setHorizontalGroup(
            CardMyRoomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardMyRoomsLayout.createSequentialGroup()
                .addGroup(CardMyRoomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardMyRoomsLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(btnExtendR, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReloadURs, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CardMyRoomsLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(Title2))
                    .addGroup(CardMyRoomsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CardMyRoomsLayout.setVerticalGroup(
            CardMyRoomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CardMyRoomsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Title2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(CardMyRoomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReloadURs, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExtendR, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pnlCard.add(CardMyRooms, "card2");

        CardMyServices.setBackground(new java.awt.Color(255, 255, 255));

        tabUserServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MADVTI", "TENDVTI", "DESCRIPTION", "NGAYBD", "NGAYKT", "PRICE / DAY", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabUserServices);

        Title4.setText("MY SERVICES");
        Title4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N

        javax.swing.GroupLayout CardMyServicesLayout = new javax.swing.GroupLayout(CardMyServices);
        CardMyServices.setLayout(CardMyServicesLayout);
        CardMyServicesLayout.setHorizontalGroup(
            CardMyServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardMyServicesLayout.createSequentialGroup()
                .addGroup(CardMyServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardMyServicesLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CardMyServicesLayout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(Title4)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        CardMyServicesLayout.setVerticalGroup(
            CardMyServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardMyServicesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Title4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlCard.add(CardMyServices, "card3");

        CardBookRooms.setBackground(new java.awt.Color(255, 255, 255));
        CardBookRooms.setPreferredSize(new java.awt.Dimension(837, 577));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Title.setText("BOOK ROOM");
        Title.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N

        labCheckindate.setText("Checkin Date:");
        labCheckindate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        labCheckoutdate.setText("Checkout Date:");
        labCheckoutdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        labCheckoutdate1.setText("Pick available rooms:");
        labCheckoutdate1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        tabRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "RoomID", "Room Type", "Description", "Price for 1 night"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabRooms);

        btnBookR.setText("Book");
        btnBookR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBookR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookRActionPerformed(evt);
            }
        });

        jButton1.setText("Check");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labCheckoutdate1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labCheckoutdate)
                                .addGap(18, 18, 18)
                                .addComponent(datePickerCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labCheckindate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datePickerCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Title)
                        .addGap(335, 335, 335))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnBookR, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(324, 324, 324))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Title)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCheckindate)
                    .addComponent(datePickerCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCheckoutdate)
                    .addComponent(datePickerCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labCheckoutdate1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBookR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout CardBookRoomsLayout = new javax.swing.GroupLayout(CardBookRooms);
        CardBookRooms.setLayout(CardBookRoomsLayout);
        CardBookRoomsLayout.setHorizontalGroup(
            CardBookRoomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CardBookRoomsLayout.setVerticalGroup(
            CardBookRoomsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardBookRoomsLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCard.add(CardBookRooms, "card4");

        CardBookServices.setBackground(new java.awt.Color(255, 255, 255));

        Title1.setText("BOOK SERVICE");
        Title1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N

        labStartDate.setText("Start date:");
        labStartDate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        labEndDate.setText("End date:");
        labEndDate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        labChooseSer.setText("Choose service:");
        labChooseSer.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        tabServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ServiceID", "Service Name", "Description", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlServices.setViewportView(tabServices);

        btnBookS.setText("Book");
        btnBookS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout labTotalUsageLayout = new javax.swing.GroupLayout(labTotalUsage);
        labTotalUsage.setLayout(labTotalUsageLayout);
        labTotalUsageLayout.setHorizontalGroup(
            labTotalUsageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labTotalUsageLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(labTotalUsageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labChooseSer)
                    .addGroup(labTotalUsageLayout.createSequentialGroup()
                        .addGroup(labTotalUsageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labEndDate)
                            .addComponent(labStartDate))
                        .addGap(164, 164, 164)
                        .addGroup(labTotalUsageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(datePickerStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datePickerEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(scrlServices, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labTotalUsageLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Title1)
                .addGap(311, 311, 311))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labTotalUsageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBookS, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(410, 410, 410))
        );
        labTotalUsageLayout.setVerticalGroup(
            labTotalUsageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labTotalUsageLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Title1)
                .addGap(42, 42, 42)
                .addGroup(labTotalUsageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labStartDate)
                    .addComponent(datePickerStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(labTotalUsageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labEndDate)
                    .addComponent(datePickerEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(labChooseSer)
                .addGap(18, 18, 18)
                .addComponent(scrlServices, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnBookS, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CardBookServicesLayout = new javax.swing.GroupLayout(CardBookServices);
        CardBookServices.setLayout(CardBookServicesLayout);
        CardBookServicesLayout.setHorizontalGroup(
            CardBookServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labTotalUsage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CardBookServicesLayout.setVerticalGroup(
            CardBookServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labTotalUsage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlCard.add(CardBookServices, "card5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMyRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyRoomsActionPerformed
        CardLayout layout = (CardLayout) pnlCard.getLayout();
        layout.show(pnlCard, "MyRooms");
        Load_Table_UserRooms();
    }//GEN-LAST:event_btnMyRoomsActionPerformed

    private void btnMyServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyServicesActionPerformed
        CardLayout layout = (CardLayout) pnlCard.getLayout();
        layout.show(pnlCard, "MyServices");
        Load_Table_UserServices();
    }//GEN-LAST:event_btnMyServicesActionPerformed

    private void btnBookRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookRoomsActionPerformed
        CardLayout layout = (CardLayout) pnlCard.getLayout();
        layout.show(pnlCard, "BookRooms");
    }//GEN-LAST:event_btnBookRoomsActionPerformed

    private void btnBookServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookServicesActionPerformed
        CardLayout layout = (CardLayout) pnlCard.getLayout();
        layout.show(pnlCard, "BookServices");
    }//GEN-LAST:event_btnBookServicesActionPerformed
    
    public boolean isRoomAvailable(Connection conn, String maPhong, LocalDate ngayCheckin, LocalDate ngayCheckout) throws SQLException {
    String sql = "SELECT 1 FROM HOADON WHERE MADVP = ? AND ((? >= NGAYBD AND ? <= NGAYKT) OR (? >= NGAYBD AND ? <= NGAYKT))";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, maPhong);
        pstmt.setDate(2, java.sql.Date.valueOf(ngayCheckout));
        pstmt.setDate(3, java.sql.Date.valueOf(ngayCheckout));
        pstmt.setDate(4, java.sql.Date.valueOf(ngayCheckin));
        pstmt.setDate(5, java.sql.Date.valueOf(ngayCheckin));
        
        try (ResultSet rs = pstmt.executeQuery()) {
            return !rs.next(); 
        }
    }
}
    private void btnBookRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookRActionPerformed
        if(datePickerCheckin.getDate() == null || datePickerCheckout.getDate() == null)
        {
            JOptionPane.showMessageDialog(this, "Please enter checkin and checkout date!");
                datePickerCheckin.requestFocus();
                datePickerCheckout.requestFocus();
        }
        else
        if(datePickerCheckin.getDate().compareTo(datePickerCheckout.getDate())>=0) {
            JOptionPane.showMessageDialog(this, "The checkin date must before the checkout date!");
        }
        else
        {
            dba_connection connect = new dba_connection();
            Connection con;
            try {
                con = DriverManager.getConnection(connect.url, connect.username, connect.password);
                
                int selectedRow = tabRooms.getSelectedRow();
                if (selectedRow != -1) {
                String MaDVP = tabRooms.getValueAt(selectedRow, 0).toString();              
                
                if(!isRoomAvailable(con,MaDVP,datePickerCheckin.getDate(),datePickerCheckout.getDate()))
                {
                    JOptionPane.showMessageDialog(null, "Sorry, this room is already booked during your selected dates. Please try another date range!");
                }
                else {
                //Them 1 hoa don moi
                    String sql = "INSERT INTO HOADON (MAKH, MADVP, MADVTI, MAFB, NGUOIXACNHAN, NGAYBD, NGAYKT, NGAYTHANHTOAN, TINHTRANGTT) "
                            + "VALUES ('" + Current_User.MaKH + "', trim(?), NULL, NULL, NUll, ?, ?, NULL, 'Đã thanh toán')";
                    PreparedStatement pst1 = con.prepareStatement(sql);

                    //Lay cot MADVTI tu` dong duoc chon trong bang
                    pst1.setString(1, MaDVP);

                    LocalDate ngayBD = datePickerCheckin.getDate();
                    Date ngayBDsql = Date.valueOf(ngayBD);
                    pst1.setDate(2, ngayBDsql);

                    LocalDate ngayKT = datePickerCheckout.getDate();
                    Date ngayKTsql = Date.valueOf(ngayKT);
                    pst1.setDate(3, ngayKTsql);

                    pst1.executeUpdate();
                    
                    
                    String sql1 = "SELECT MAHD FROM HOADON WHERE MAKH='" + Current_User.MaKH + "' AND MADVP='" + MaDVP + "' AND NGAYBD = ? AND NGAYKT = ?";
                    PreparedStatement pst2 = con.prepareStatement(sql1);
                    pst2.setDate(1, ngayBDsql);
                    pst2.setDate(2, ngayKTsql);
                    
                    
                    String MaHD = "";
                    ResultSet rs = pst2.executeQuery();
                    if(rs.next())
                    {
                         MaHD = rs.getString("MAHD");
                    }
                    
                    //Reload lai bang rooms available
                    Reload_Table_Rooms();
                    Load_Table_UserRooms();
                    Bank_BookRoom frame = new Bank_BookRoom(MaHD);
                    frame.setVisible(true);
                    }
                }
                else {
                JOptionPane.showMessageDialog(null, "Please choose a room to book!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot connect to ROOMS database " + ex.getMessage());
            }
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBookRActionPerformed

    private void btnBookSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookSActionPerformed
        // TODO add your handling code here:
        if(datePickerStartDate.getDate() == null || datePickerEndDate.getDate() == null)
        {
            JOptionPane.showMessageDialog(this, "Please enter checkin and checkout date!");
                datePickerStartDate.requestFocus();
                datePickerEndDate.requestFocus();
        }
        else
        if(datePickerStartDate.getDate().compareTo(datePickerEndDate.getDate())>=0) {
            JOptionPane.showMessageDialog(this, "The checkin date must before the checkout date!");
        }
        else
        {
            dba_connection connect = new dba_connection();
            Connection con;
            try {
                con = DriverManager.getConnection(connect.url, connect.username, connect.password);
                
                //Them 1 hoa don moi
                int selectedRow = tabServices.getSelectedRow();
                if (selectedRow != -1) {
                String sql = "INSERT INTO HOADON (MAKH, MADVP, MADVTI, MAFB, NGUOIXACNHAN, NGAYBD, NGAYKT, NGAYTHANHTOAN, TINHTRANGTT) "
                        + "VALUES ('" + Current_User.MaKH + "', NULL, trim(?), NULL, NUll, ?, ?, NULL, 'Chưa thanh toán')";
                PreparedStatement pst = con.prepareStatement(sql);
                
                //Lay cot MADVTI tu` dong duoc chon trong bang
                String MaDVTI = tabServices.getValueAt(selectedRow, 0).toString();
                pst.setString(1, MaDVTI);
                
                LocalDate ngayBD = datePickerStartDate.getDate();
                Date ngayBDsql = Date.valueOf(ngayBD);
                pst.setDate(2, ngayBDsql);
                
                LocalDate ngayKT = datePickerEndDate.getDate();
                Date ngayKTsql = Date.valueOf(ngayKT);
                pst.setDate(3, ngayKTsql);
                
//              int slsd = (int) spinTotalUsage.getValue();
//              pst.setInt(4, slsd);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Your booking was successful!");
                
                //Reload lai bang rooms available
                Reload_Table_Services();
                Load_Table_UserServices();
                } 
                else {
                JOptionPane.showMessageDialog(null, "Please choose a service to book!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot connect to INVOICE database " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnBookSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = tabRooms.getSelectedRow();
        if (selectedRow != -1) {
            String MaDVP = tabRooms.getValueAt(selectedRow, 0).toString();
            CheckRoomAvailable frame = new CheckRoomAvailable(MaDVP);
            frame.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please choose a room to check!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnExtendRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtendRActionPerformed
        int selectedRow = tabUserRooms.getSelectedRow();
        if (selectedRow != -1) {
            String MaDVP = tabUserRooms.getValueAt(selectedRow, 0).toString();
            String NgayBD = tabUserRooms.getValueAt(selectedRow, 4).toString(); //NGAYKT CUA HD CU LA NGAYBD CUA HDON MOI
            ExtendRoom frame = new ExtendRoom(MaDVP, NgayBD);
            frame.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please choose a room to extend!");
        }
    }//GEN-LAST:event_btnExtendRActionPerformed

    private void btnReloadURsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadURsActionPerformed
        // TODO add your handling code here:
        Load_Table_UserRooms();
    }//GEN-LAST:event_btnReloadURsActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
        new Main_Menu().setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardBookRooms;
    private javax.swing.JPanel CardBookServices;
    private javax.swing.JPanel CardMyRooms;
    private javax.swing.JPanel CardMyServices;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Title1;
    private javax.swing.JLabel Title2;
    private javax.swing.JLabel Title4;
    private javax.swing.JButton btnBookR;
    private javax.swing.JButton btnBookRooms;
    private javax.swing.JButton btnBookS;
    private javax.swing.JButton btnBookServices;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExtendR;
    private javax.swing.JButton btnMyRooms;
    private javax.swing.JButton btnMyServices;
    private javax.swing.JButton btnReloadURs;
    private com.github.lgooddatepicker.components.DatePicker datePickerCheckin;
    private com.github.lgooddatepicker.components.DatePicker datePickerCheckout;
    private com.github.lgooddatepicker.components.DatePicker datePickerEndDate;
    private com.github.lgooddatepicker.components.DatePicker datePickerStartDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labCheckindate;
    private javax.swing.JLabel labCheckoutdate;
    private javax.swing.JLabel labCheckoutdate1;
    private javax.swing.JLabel labChooseSer;
    private javax.swing.JLabel labEndDate;
    private javax.swing.JLabel labStartDate;
    private javax.swing.JPanel labTotalUsage;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JPanel panelBook;
    private javax.swing.JPanel panelMyInfo;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JScrollPane scrlServices;
    private javax.swing.JTable tabRooms;
    private javax.swing.JTable tabServices;
    private javax.swing.JTable tabUserRooms;
    private javax.swing.JTable tabUserServices;
    // End of variables declaration//GEN-END:variables
}
