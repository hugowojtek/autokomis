package pl.sda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SellingContracts extends BaseModel implements Serializable{
    @Column
    private Date date;
    @Column
    private Long price;
    @OneToOne
    @JoinColumn(name="idcars")
    private Cars cars;
    @OneToOne
    @JoinColumn(name="idcustomers")
    private Customers customers;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
}
