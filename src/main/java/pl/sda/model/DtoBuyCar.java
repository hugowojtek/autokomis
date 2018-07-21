package pl.sda.model;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DtoBuyCar {

    @NotNull
    @Size(min = 1, message = "za mało znaków co najmniej 1 znak")
    private String carNrChassis;

    @NotNull
    @Size(min = 4, message = "zła data min 4 znaki")
    private String carYearProduction;

    @NotNull
    @Size(min = 2, message = "za mało znaków min 2 znaki")
    private String carManufacturer;

    @NotNull
    @Size(min = 2, message = "za mało znaków min 2 znaki")
    private String carModel;

    private String carNrOc;

    private String carNrCarReg;

    private String carFuel;

    @NotNull
    @Size(min = 1, message = "brak przebiegu co najmniej 1 znak")
    private String carMilage;


    private Integer carEngineVolume;


    private Integer carEnginePower;


    private String carGearBox;




    @NotNull
    @Size(min = 1, message = "brak opisu co najmniej jeden znak")
    private String carDescription;


    private Long carTestDrives;

    //@NotEmpty
    @NotNull(message = "nie może być zerem i nie tylko")
    @DecimalMin(value="5000.00", message = "za mała wartość co najmniej 5000zł")
    private Long carPrice;

//    private Date buyingContractsDate;
//@NotEmpty
    @NotNull(message = "nie może być zerem i nie tylko")
    @DecimalMin(value="1.00", message = "za mała wartość co najmniej 1zł")
    private Long buyingContractsPrice;


    public Long getCarPrice(){
        return this.carPrice;
    }

    public void setCarPrice(Long carPrice){
        this.carPrice = carPrice;
    }

    public String getCarNrChassis() {
        return carNrChassis;
    }

    public void setCarNrChassis(String carNrChassis) {
        this.carNrChassis = carNrChassis;
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

//    public Date getBuyingContractsDate() {
//        return buyingContractsDate;
//    }
//
//    public void setBuyingContractsDate(Date buyingContractsDate) {
//        this.buyingContractsDate = buyingContractsDate;
//    }

    public Long getBuyingContractsPrice() {
        return buyingContractsPrice;
    }

    public void setBuyingContractsPrice(Long buyingContractsPrice) {
        this.buyingContractsPrice = buyingContractsPrice;
    }
}
