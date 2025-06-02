/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelmanagement.Payment;

import hotelmanagement.dashboard_main.Service_pay_menu;
import hotelmanagement.entity.Current_User;
import hotelmanagement.entity.Room_pay;
import hotelmanagement.entity.Service_pay;
import hotelmanagement.entity.dba_connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 84352
 */
public class Invoice_print extends javax.swing.JFrame {

    /**
     * Creates new form Cash_service
     */
    private Service_pay_menu parent;
    ArrayList<Room_pay> room_list = new ArrayList<>();
    ArrayList<Service_pay> service_list = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    public Invoice_print(){
        
    }
    public Invoice_print(Service_pay_menu parent) {
        initComponents();
        this.parent = parent;
        Print();
    }
    public void Print(){
        System.out.println(Current_User.phonenumber);
        dba_connection connect = new dba_connection();
        String sql_khachhang = "SELECT * FROM KHACHHANG WHERE SDT = '" + Current_User.phonenumber + "'";
        String sql_room = "select MAHD, HOADON.MADVP, SLSD, TONGTIEN FROM HOADON "
                    + "JOIN KHACHHANG ON HOADON.MAKH = KHACHHANG.MAKH "
                    + "JOIN DVPHONG ON HOADON.MADVP = DVPHONG.MADVP "                    
                    + "WHERE KHACHHANG.SDT = '" + Current_User.phonenumber + "' AND TINHTRANGTT IN ('Chưa thanh toán', 'Đã thanh toán')"; 
        
        String sql_service = "select MAHD, HOADON.MADVTI, HOADON.MADVP, TENDVTI, TONGTIEN FROM HOADON "
                    + "JOIN KHACHHANG ON HOADON.MAKH = KHACHHANG.MAKH "
                    + "LEFT JOIN DVTIENICH ON HOADON.MADVTI = DVTIENICH.MADVTI "                    
                    + "WHERE KHACHHANG.SDT = '" + Current_User.phonenumber + "' AND TINHTRANGTT = 'Chưa thanh toán'";
        
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        df.setGroupingUsed(false);
        try {
            Class.forName(connect.driver);
            Connection con = DriverManager.getConnection(connect.url, connect.username, connect.password);
            PreparedStatement pst = null;
            ResultSet rs = null;
            
            pst = con.prepareStatement(sql_khachhang);
//            pst.setString(1, Current_User.phonenumber);
            rs = pst.executeQuery();
            if(rs.next()){
                Name.setText(rs.getString("HOTEN"));
                Phone.setText(rs.getString("SDT"));
                Address.setText("DIACHI");
                Name.setEditable(false);
                Phone.setEditable(false);
                Address.setEditable(false);
            }
            
            pst.close();
            rs.close();
            
            pst = con.prepareStatement(sql_room);
            rs = pst.executeQuery();
            while(rs.next()){
                Room_pay r = new Room_pay();
                r.setMahd(rs.getString("MAHD"));
                r.setMadvp(rs.getString("MADVP"));
                r.setSongay(rs.getInt("SLSD"));
                r.setGia(rs.getDouble("TONGTIEN"));
                
                room_list.add(r);
            }
            model =  (DefaultTableModel) room_tab.getModel();
            model.setRowCount(0);
            for(Room_pay r : room_list){
                model.addRow(new Object[]{
                    r.mahd,
                    r.madvp,
                    r.songay,
                    r.tongtien
                });
            }
             
            pst.close();
            rs.close();
            
            pst = con.prepareStatement(sql_service);
            rs = pst.executeQuery();
            while(rs.next()){
                Service_pay s = new Service_pay();
                s.setMahd(rs.getString("MAHD"));
                s.setMadvti(rs.getString("MADVTI"));
                s.setMadvp(rs.getString("MADVP"));
                s.setName(rs.getString("TENDVTI"));
                s.setGia(rs.getDouble("TONGTIEN"));
                
                service_list.add(s);
            }
            model =  (DefaultTableModel) service_tab.getModel();
            model.setRowCount(0);
            for(Service_pay s : service_list){
                model.addRow(new Object[]{
                    s.mahd,
                    s.madvti,
                    s.madvp,
                    s.name,
                    s.tongtien
                });
            }
            
            double service_sum = 0;
            for(Service_pay s : service_list){
                service_sum += s.tongtien;
            }
            String sum_service_Str = df.format(service_sum);
            
            service_total.setText(sum_service_Str);
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Invoice_print.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        room_tab = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        service_tab = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        Phone = new javax.swing.JTextField();
        Address = new javax.swing.JTextField();
        service_total = new javax.swing.JTextField();
        btninPay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(46, 121, 130));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KHÁCH SẠN HTTP");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name: ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Phone number: ");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Address: ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Checkout room");

        room_tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Invoice id", "Room id", "Number of day", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(room_tab);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Need to purchase");

        service_tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Invoice id", "Service id", "Room id", "Service name", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(service_tab);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Service amount:");

        btninPay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btninPay.setText("Confirm");
        btninPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Phone))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(service_total, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(165, 165, 165)
                            .addComponent(btninPay, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(67, 67, 67)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(service_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btninPay)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btninPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninPayActionPerformed
        String sql_update = "UPDATE HOADON SET TINHTRANGTT = 'Vô hiệu hoá', NGAYTHANHTOAN = SYSDATE "
                + "WHERE MAHD IN (SELECT MAHD FROM HOADON "
                + "JOIN KHACHHANG ON KHACHHANG.MAKH = HOADON.MAKH "
                + "WHERE TINHTRANGTT IN('Đã thanh toán', 'Chưa thanh toán') AND SDT = '" + Phone.getText() + "')";
        dba_connection connect = new dba_connection();
        try {
            Class.forName(connect.driver);
            Connection con = DriverManager.getConnection(connect.url, connect.username, connect.password);
            PreparedStatement pst = con.prepareStatement(sql_update);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pay successfully!");
            this.dispose();
            parent.dispose();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Invoice_print.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btninPayActionPerformed

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
            java.util.logging.Logger.getLogger(Invoice_print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Invoice_print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Invoice_print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Invoice_print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Invoice_print().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Address;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Phone;
    private javax.swing.JButton btninPay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable room_tab;
    private javax.swing.JTable service_tab;
    private javax.swing.JTextField service_total;
    // End of variables declaration//GEN-END:variables
}
