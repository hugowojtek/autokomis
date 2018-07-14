package pl.sda.model;



import javax.persistence.*;


@Entity
public class Cutomer extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "nr_chasis")
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


}
