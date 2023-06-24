package model;  //package model/kelas yang meakses basis data

// import konektor
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

// kelas DB
public class DB{
    private String ConAddress = "jdbc:mysql://localhost:3306/db_keepstanding";
    private Statement stmt = null;      // koneksi query
    private ResultSet rs = null;        // hasil query
    private Connection conn = null;     // koneksi MySql dan basis data

    public DB() throws Exception, SQLException{
        /*
         * Method DB
         * Konstruktor : melakukan koneksi ke MySQL dan basis data
         * Menerima masukkan berupa string alamat koneksi ke MySQL dan basis data
        */
        try{
            // membuat driver MySQL
            Class.forName("com.mysql.jdbc.Driver");
            // membuat koneksi MySQL dan basis data
            conn = DriverManager.getConnection(ConAddress, "root", "");
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        }
        catch(SQLException es){
            // mengeluarkan pesan error jka koneksi gagal
            throw es;
        }
    }
    public void createQuery(String Query) throws Exception, SQLException{
        /*
         * Method createQuery
         * Mengeksekusi query tanpa mengubah isi data
         * Menerima masukan berupa string query
         */
        try{
            stmt = conn.createStatement();
            // eksekusi query
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)){
                // ambil hasil query
                rs = stmt.getResultSet();
            }
        }
        catch(SQLException es){
            // eksepsi jika query gagal dieksekusi
            throw es;
        }
    }
    public void createUpdate(String Query) throws Exception, SQLException{
        /*
         * Method createQuery
         * Mengeksekusi query yang mengubah isi data (update, insert, delete)
         * Menerima masukkan berupa string query
         */
        try{
            stmt = conn.createStatement();
            // eksekusi query
            stmt.executeUpdate(Query);
        }
        catch(SQLException es){
            System.out.println("error db");
            // eksepsi jika query gagal deksekusi
            throw es;
        }
    }
    public ResultSet getResult() throws Exception{
        /*
         * Method getResult
         * Memberikan hasil query
         */
        ResultSet Temp = null;
        try{
            return rs;
        }
        catch(Exception ex){
            // eksepsi jika gasil tidak dapat dikembalikan
            return Temp;
        }
    }
    public void closeResult() throws SQLException, Exception{
        /*
         * Method closeResult
         * Menutup hubungan dari eksekusi query
         */
        if(rs != null){
            try{
                rs.close();
            }
            catch(SQLException sqlEx){
                rs = null;
                throw sqlEx;
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            }
            catch(SQLException sqlEx){
                stmt = null;
                throw sqlEx;
            }
        }
    }
    public void closeConnection()throws SQLException, Exception{
        /*
         * Method closeConnection
         * Menutup hubungan dengan MySQL dan basis data
         */
        if(conn != null){
            try{
                conn.close();
            }
            catch(SQLException sqlEx){
                conn = null;
            }
        }
    }
}