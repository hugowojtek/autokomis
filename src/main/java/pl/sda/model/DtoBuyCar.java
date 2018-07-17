package pl.sda.model;


import javax.persistence.Column;
import java.util.Date;

public class DtoBuyCar {

    private String carYearProduction;

    private String carManufacturer;

    private String carModel;

    private String carNrOc;

    private String carNrCarReg;

    private String carFuel;

    private String carMilage;

    private Integer carEngineVolume;

    private Integer carEnginePower;

    private String carGearBox;

    private String carDescription;

    private Long carTestDrives;

    private Long carPrice;

    private Date buyingContractsDate;

    private Long buyingContractsPrice;


    public Long getCarPrice(){
        return this.carPrice;
    }

    public void setCarPrice(Long carPrice){
        this.carPrice = carPrice;
    }

    public String getCarYearProduction() {
        return carYearProduction;
    }

    public void setCarYearProduction(String carYearProduction) {
        this.carYearProduction = carYearProduction;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNrOc() {
        return carNrOc;
    }

    public void setCarNrOc(String carNrOc) {
        this.carNrOc = carNrOc;
    }

    public String getCarNrCarReg() {
        return carNrCarReg;
    }

    public void setCarNrCarReg(String carNrCarReg) {
        this.carNrCarReg = carNrCarReg;
    }

    public String getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(String carFuel) {
        this.carFuel = carFuel;
    }

    public String getCarMilage() {
        return carMilage;
    }

    public void setCarMilage(String carMilage) {
        this.carMilage = carMilage;
    }

    public Integer getCarEngineVolume() {
        return carEngineVolume;
    }

    public void setCarEngineVolume(Integer carEngineVolume) {
        this.carEngineVolume = carEngineVolume;
    }

    public Integer getCarEnginePower() {
        return carEnginePower;
    }

    public void setCarEnginePower(Integer carEnginePower) {
        this.carEnginePower = carEnginePower;
    }

    public String getCarGearBox() {
        return carGearBox;
    }

    public void setCarGearBox(String carGearBox) {
        this.carGearBox = carGearBox;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public Long getCarTestDrives() {
        return carTestDrives;
    }

    public void setCarTestDrives(Long carTestDrives) {
        this.carTestDrives = carTestDrives;
    }

    public Date getBuyingContractsDate() {
        return buyingContractsDate;
    }

    public void setBuyingContractsDate(Date buyingContractsDate) {
        this.buyingContractsDate = buyingContractsDate;
    }

    public Long getBuyingContractsPrice() {
        return buyingContractsPrice;
    }

    public void setBuyingContractsPrice(Long buyingContractsPrice) {
        this.buyingContractsPrice = buyingContractsPrice;
    }
}
