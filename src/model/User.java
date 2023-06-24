/**
 *
 * @author Ihsan Ghozi Zulfikar
 * User. Store user data from database
 */

package model;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * User. User data
 */

public class User {
    private int id;
    private String username;
    private int score;
    private int standing;

    public User(){
        //konstruktor
    }
    public User(String username, int score, int standing){
        //konstruktor
        this.username = username;
        this.score = score;
        this.standing = standing;
    }
    
    public void setId(int id){
        //set id player
        this.id = id;
    }

    public int getId(){
        //return id player
        return this.id;
    }
    
    public void setUsername(String username){
        //set username
        this.username = username;
    }

    public String getUsername(){
        //return username
        return this.username;
    }
    
    public void setScore(int score){
        //set score
        this.score = score;
    }

    public int getScore(){
        //return score
        return this.score;
    }
    
    public void setStanding(int standing){
        //mengset standing
        this.standing = standing;
    }

    public int getStanding(){
        //mengembalikan standing
        return this.standing;
    }
}
