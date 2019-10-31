package ch.heigvd.amt.models;

//import lombok.Getter;

//@Getter
public class Team {
    private final long id;
    private String name;
    private String address;
    private String zip;
    private String city;

    public Team(long id, String name, String address, String zip, String city){
        this.id = id;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
    }
}
