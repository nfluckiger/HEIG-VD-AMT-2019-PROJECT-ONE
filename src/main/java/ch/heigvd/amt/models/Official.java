package ch.heigvd.amt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Official {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private int level;
    private Team team;

    public Official(long id, String firstname, String lastname, String email, String password, int level, Team team){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.level = level;
        this.team = team;
    }

    public Official(String firstname, String lastname, String email, String password, int level, Team team){

        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.level = level;
        this.team = team;
    }
}
