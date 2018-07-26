package pl.sda.model;

import java.util.Date;

public class DtoShowCar {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    private String carYearProduction;

    private String carManufacturer;

    private String carModel;

    private String carMilage;

    private String carNrChassis;

    public String getCarNrChassis() {
        return carNrChassis;
    }

    public void setCarNrChassis(String carNrChassis) {
        this.carNrChassis = carNrChassis;
    }

    private String carDescription;

    private Long carPrice;

    public boolean isCarVisibility() {
        return carVisibility;
    }

    public void setCarVisibility(boolean carVisibility) {
        this.carVisibility = carVisibility;
    }

    private boolean carVisibility;

    private Float margin;

    private Long profit;

    private Date saleDate;

    private Date purchaseDate;


    //    private Long buyingContractsPrice;


    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Float getMargin() {
        return margin;
    }

    public void setMargin(Float margin) {
        this.margin = margin;
    }

    public Long getProfit() {
        return profit;
    }

    public Long getProfit(long l) {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public Long getCarPrice(){
        return this.carPrice;
    }

    public void setCarPrice(Long carPrice){
        this.carPrice = carPrice;
    }
    public String getCarMilage() {
        return carMilage;
    }

    public void setCarMilage(String carMilage) {
        this.carMilage = carMilage;
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


    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

//    public Long getBuyingContractsPrice() {
//        return buyingContractsPrice;
//    }
//
//    public void setBuyingContractsPrice(Long buyingContractsPrice) {
//        this.buyingContractsPrice = buyingContractsPrice;
//    }




}
