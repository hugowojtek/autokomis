package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;
import pl.sda.model.Cars;
import pl.sda.model.DtoShowCar;
import pl.sda.model.SellingContracts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.round;

@Service
public class RaportsService {


    CarsService carsService;


    public RaportsService(CarsService carsService) {
        this.carsService = carsService;

    }


    public List<DtoShowCar> ShowSoldCarsWithMarginAndProfit() {
        List<DtoShowCar> soldCars = carsService.showSoldCars();
        List<DtoShowCar> boughtCars = carsService.showBoughtCars();
        for (DtoShowCar sc : soldCars) {
            for (DtoShowCar bc : boughtCars) {
                if (sc.getId().equals(bc.getId())) {
                    Long profit = sc.getCarPrice() - bc.getCarPrice();
                    sc.setProfit(profit);
                    float margin = (((float) profit / sc.getCarPrice()) * 100);
                    float margin_round = round(margin);
                    sc.setMargin(margin_round);
                }
            }
        }
        return soldCars;
    }

    public Long CalculateAllSumOfBoughtCars() {
        List<DtoShowCar> soldCars = carsService.showBoughtCars();
        Long value = 0L;
        for (DtoShowCar sc : soldCars) {
            value += sc.getCarPrice();
        }
        return value;
    }

    public Long CalculateAllSumOfSoldCars() {
        List<DtoShowCar> soldCars = carsService.showSoldCars();
        List<DtoShowCar> boughtCars = carsService.showBoughtCars();
        Long value = 0L;
        for (DtoShowCar sc : soldCars) {
            for (DtoShowCar bc : boughtCars) {
                if (sc.getId().equals(bc.getId())) {
                    Long profit = sc.getCarPrice() - bc.getCarPrice();
                    sc.setProfit(profit);
                    float margin = (((float) profit / sc.getCarPrice()) * 100);
                    sc.setMargin(margin);
                    value += sc.getCarPrice();
                }
            }
        }
        return value;
    }

    public List<DtoShowCar> SaleFilter(Date DateBefore, Date DateAfter) {
        List<DtoShowCar> listIn = carsService.showSoldCars();
        List<DtoShowCar> listOut = new ArrayList<>();
        for (DtoShowCar dtoShowCar : listIn) {
            Date date = dtoShowCar.getSaleDate();
            if ((DateBefore.before(date))) {
                if ((DateAfter.after(date))) {
                    listOut.add(dtoShowCar);
                }
            }
        }
        return listOut;
    }

    public Long CalculateSaleFilterValue(List<DtoShowCar> list){
        Long value=0L;
        for (DtoShowCar dtoShowCar:list){
            value+=dtoShowCar.getCarPrice();
        }
        return value;
    }


}





