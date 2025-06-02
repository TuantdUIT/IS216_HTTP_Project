
package hotelmanagement.entity;


public class dba_connection {
    public String driver;
    public String url;
    public String username;
    public String password;

    public dba_connection() {
        driver = "oracle.jdbc.OracleDriver";
        url = "jdbc:oracle:thin:@localhost:1521:orcltdt";
        username = "java01";
        password = "java01";
    }
     
}
