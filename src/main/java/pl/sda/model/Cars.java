package pl.sda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
class Cars extends BaseModel implements Serializable {
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
    private String fuelType;
    @Column
    private String milage;
    @Column
    private Long engineVolume;
    @Column
    private Long enginePower;
    @Column
    private String gearBox;
    @Column
    private String description;
    @Column
    private Long testDrives;

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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getMilage() {
        return milage;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public Long getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Long engineVolume) {
        this.engineVolume = engineVolume;
    }

    public Long getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Long enginePower) {
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