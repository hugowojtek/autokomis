package pl.sda.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
class Cars extends BaseModel{
    @Column
    private String year_production;
    @Column
    private String manufacturer;
    @Column
    private String model;
    @Column
    private String nr_oc;
    @Column
    private String nr_car_reg;
    @Column
    private String fuel_type;
    @Column
    private String milage;
    @Column
    private Long engine_volume;
    @Column
    private Long engine_power;
    @Column
    private String gear_box;
    @Column
    private String description;
    @Column
    private Long test_drives;

    public String getYear_production() {
        return year_production;
    }

    public void setYear_production(String year_production) {
        this.year_production = year_production;
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

    public String getNr_oc() {
        return nr_oc;
    }

    public void setNr_oc(String nr_oc) {
        this.nr_oc = nr_oc;
    }

    public String getNr_car_reg() {
        return nr_car_reg;
    }

    public void setNr_car_reg(String nr_car_reg) {
        this.nr_car_reg = nr_car_reg;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getMilage() {
        return milage;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public Long getEngine_volume() {
        return engine_volume;
    }

    public void setEngine_volume(Long engine_volume) {
        this.engine_volume = engine_volume;
    }

    public Long getEngine_power() {
        return engine_power;
    }

    public void setEngine_power(Long engine_power) {
        this.engine_power = engine_power;
    }

    public String getGear_box() {
        return gear_box;
    }

    public void setGear_box(String gear_box) {
        this.gear_box = gear_box;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTest_drives() {
        return test_drives;
    }

    public void setTest_drives(Long test_drives) {
        this.test_drives = test_drives;
    }
}
