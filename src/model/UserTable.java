package model;

import java.sql.SQLException;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * UserTable. Query to connect user to database
 */

public class UserTable extends DB {
    public UserTable() throws Exception, SQLException{
        //konstruktor
        super();
    }
    public void getUser(){
        // execute query to get all data in user table, ordered by score
        try{
            String query = "SELECT * FROM tscore ORDER BY score DESC";
            createQuery(query);
        }catch(Exception e){
            //tampilkan kesalahan jika terjadi kesalahan
            System.out.println("eror UserTable");
            System.out.println(e.toString());
        }
    }
    public void getUserByUsername(String username){
        // execute query to get data by username
        try{
            String query = "SELECT * FROM tscore WHERE username = '"+username+"'";
            createQuery(query);
        }catch(Exception e){
            //tampilkan kesalahan jika terjadi kesalahan
            System.out.println("eror UserTable");
            System.out.println(e.toString());
        }
    }
    public void insertUser(User user){
        // execute query to insert new user to table
        String username = user.getUsername();
        int score = user.getScore();
        int standing = user.getStanding();
        try{
            String query = "INSERT INTO tscore(username, score, standing) value('"+username+"','"+score+"','"+standing+"')";
            createUpdate(query);
        }catch(Exception e){
            //tampilkan kesalahan jika terjadi kesalahan
            System.out.println("eror UserTable");
            System.out.println(e.toString());
        }
    }
    public void updateScore(User user){
        // execute query to update the score of user
        String username = user.getUsername();
        int score = user.getScore();
        int standing = user.getStanding();
        //mengeksekusi query untuk mengambil semua data pada tabel pengguna
        try{
            String query = "UPDATE tscore SET score='"+score+"', standing='"+standing+"' where username='"+username+"'";
            createUpdate(query);
        }catch(Exception e){
            //tampilkan kesalahan jika terjadi kesalahan
            System.out.println("eror UserTable");
            System.out.println(e.toString());
        }
    }
}
