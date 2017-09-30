package User;

import java.io.Serializable;

/**
 * Created by HP on 28.9.2017 г..
 */

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String username;
    private String e_mail;
    private String last_achievement;
    private String current_stage;
    private int points;

    public User(String username, String e_mail, String current_stage, int points) {
        this.username = username;
        this.e_mail = e_mail;
        this.last_achievement = "Все още нямаш Постижения";
        this.points = points;
        this.current_stage = current_stage;
    }


    public String getCurrent_stage() {
        return current_stage;
    }

    public void setCurrent_stage(String current_stage) {
        this.current_stage = current_stage;
    }


    public String getUsername() {
        return username;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getLast_achievement() {
        return last_achievement;
    }

    public String getPoints() {
        return Integer.toString(points);
    }
}
