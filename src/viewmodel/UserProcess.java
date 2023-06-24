package viewmodel;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserTable;
import model.User;

public class UserProcess {
    private String error;
    private UserTable userTable;
    private ArrayList<User> data;

    public UserProcess(){
        //konstruktor
        try{
            userTable = new UserTable();    //instansiasi PlayerTable
            data = new ArrayList<User>();
        }catch(Exception e){
            error = e.toString();
             System.out.println(error);
        }
    }
    public void processUserData(){
        // fetch data from UserTable based on username
        try{
            userTable.getUser();
            data =  new ArrayList<User>();

            while(userTable.getResult().next()){
                //ambil hasil query
                User user = new User();   //instansiasi objek player untuk setiap data player

                user.setUsername(userTable.getResult().getString(1));
                user.setScore(userTable.getResult().getInt(2));
                user.setStanding(userTable.getResult().getInt(3));

                data.add(user);   //tambahkan data player ke dalam list
            }
        }catch(Exception e){
            // memproses error
            System.out.println("eror UserProses");
            error = e.toString();
            System.out.println(error);
        }
    }
    
    //tutup koneksi
    public void close(){
        try {
            userTable.closeResult();
            userTable.closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void processUserDataByUsername(String username){
        // fetch data from UserTable based on username
        userTable.getUserByUsername(username);
        data = new ArrayList<User>();
        try {
            if(userTable.getResult().next()){
                User user = new User();   //instansiasi objek player untuk setiap data player
                user.setUsername(userTable.getResult().getString(1));
                user.setScore(userTable.getResult().getInt(2));
                user.setStanding(userTable.getResult().getInt(3));
                data.add(user);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // insert user to UserTable
    public void insertUser(String username){
        processUserDataByUsername(username);
        if(getSize()==0){
            User user = new User();
            user.setUsername(username);
            user.setScore(0);
            user.setStanding(0);
            userTable.insertUser(user);
        }
    }
    
    // Update user score in UserTable
    public void updateScore(String username, int score, int standing){
        User user = new User();
        user.setUsername(username);
        user.setScore(score);
        user.setStanding(standing);
        userTable.updateScore(user);
    }

    public int getId(int i){
        //mengembalikan id player dengan indeks ke i
        return data.get(i).getId();
    }

    public String getUsername(int i){
        //mengembalikan username player dengan indeks ke i
        return data.get(i).getUsername();
    }

    public int getScore(int i){
        //mengembalikan score player dengan indeks ke i
        return data.get(i).getScore();
    }

    public int getStanding(int i){
        //mengembalikan standing player dengan indeks ke i
        return data.get(i).getStanding();
    }

    public int getSize(){
        //mengembalikan banyaknya data player yang masuk ke dalam list
        return data.size();
    }

    public String getError(){
        //mengemablikan error
        return this.error;
    }
}
