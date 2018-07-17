package pl.sda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Cars extends BaseModel implements Serializable {
    @Column
    private String yearProduction;
    @Column
    private String manufacturer;
    @Column
    private String model;
    @Column
    private String nrOc;
    @Column
    private String nrCarReg;
    @Column
    private String fuel;
    @Column
    private String milage;
    @Column
    private Integer engineVolume;
    @Column
    private Integer enginePower;
    @Column
    private String gearBox;
    @Column
    private String description;
    @Column
    private Long testDrives;

    @Column
    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    public String getYearProduction() {
        return yearProduction;
    }

    public void setYearProduction(String yearProduction) {
        this.yearProduction = yearProduction;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNrOc() {
        return nrOc;
    }

    public void setNrOc(String nrOc) {
        this.nrOc = nrOc;
    }

    public String getNrCarReg() {
        return nrCarReg;
    }

    public void setNrCarReg(String nrCarReg) {
        this.nrCarReg = nrCarReg;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getMilage() {
        return milage;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public Integer getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Integer engineVolume) {
        this.engineVolume = engineVolume;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTestDrives() {
        return testDrives;
    }

    public void setTestDrives(Long testDrives) {
        this.testDrives = testDrives;
    }
}
