package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;
import pl.sda.model.Cars;
import pl.sda.model.DtoShowCar;
import pl.sda.model.SellingContracts;

import java.util.List;

import static java.lang.Math.round;

@Service
public class RaportsService {


    CarsService carsService;
    final BuyingContractsRepository buyingContractsRepository;
    final CarsRepository carsRepository;
    final SellingContractsRepository sellingContractsRepository;

    public RaportsService(CarsService carsService, BuyingContractsRepository buyingContractsRepository, CarsRepository carsRepository, SellingContractsRepository sellingContractsRepository) {
        this.carsService = carsService;
        this.buyingContractsRepository = buyingContractsRepository;
        this.carsRepository = carsRepository;
        this.sellingContractsRepository = sellingContractsRepository;
    }


    public List<DtoShowCar> ShowSoldCarsWithMarginAndProfit() {
        List<DtoShowCar> soldCars = carsService.showSoldCars();
        List<DtoShowCar> boughtCars = carsService.showBoughtCars();
        for (DtoShowCar sc : soldCars) {
            for (DtoShowCar bc : boughtCars) {
                if (sc.getId().equals(bc.getId())) {
                    Long profit = sc.getCarPrice() - bc.getCarPrice();
                    sc.setProfit(profit);
                    float margin = (((float)profit / sc.getCarPrice()) * 100);
                    float margin_round = round(margin);
                    sc.setMargin(margin_round);
                }
            }
        }
        return soldCars;
    }

    public Long CalculateAllProfit(){
        List<DtoShowCar> soldCars = carsService.showSoldCars();
        List<DtoShowCar> boughtCars = carsService.showBoughtCars();
        Long value=0L;
        for (DtoShowCar sc : soldCars) {
            for (DtoShowCar bc : boughtCars) {
                if (sc.getId().equals(bc.getId())) {
                    Long profit = sc.getCarPrice() - bc.getCarPrice();
                    sc.setProfit(profit);
                    float margin = (((float)profit / sc.getCarPrice()) * 100);
                    sc.setMargin(margin);
                    value+=sc.getCarPrice();
                }
            }
        }
        return value;
    }




}
