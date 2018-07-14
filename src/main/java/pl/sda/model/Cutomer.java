package pl.sda.model;



import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Cutomer extends BaseModel implements Serializable{

    @ManyToOne
    @JoinColumn(name = "id_cars")
    private Cars cars;

    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private String adres;
    @Column
    private String nip;
    @Column
    private String pesel;


    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
