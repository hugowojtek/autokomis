package pl.sda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class BuyingContracts extends BaseModel implements Serializable{
    @Column
    private Date date;
    @Column
    private Long price;

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
}
