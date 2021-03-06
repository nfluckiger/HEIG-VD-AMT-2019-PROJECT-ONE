package ch.heigvd.amt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
    private long id;
    private String name;
    private String address;
    private String zip;
    private String city;

    public Team(long id, String name, String address, String zip, String city){
        this(name, address, zip, city);
        this.id = id;
    }

    public Team(String name, String address, String zip, String city){
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
    }
}
