package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;
import pl.sda.model.BuyingContracts;
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
    SellingContractsRepository sellingContractsRepository;
    BuyingContractsRepository buyingContractsRepository;

    public RaportsService(CarsService carsService, SellingContractsRepository sellingContractsRepository, BuyingContractsRepository buyingContractsRepository) {
        this.carsService = carsService;
        this.sellingContractsRepository = sellingContractsRepository;
        this.buyingContractsRepository = buyingContractsRepository;
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
                    float margin_round = round(margin * 10.0f) / 10.0f;
                    sc.setMargin(margin_round);
                    break;
                }
            }
        }
        return soldCars;
    }

    public List<DtoShowCar> ShowSoldCarsWithMarginAndProfit2(List<DtoShowCar> listIn) {
        List<DtoShowCar> listOut = new ArrayList<>();
        List<DtoShowCar> boughtCar = carsService.showBoughtCars();
        for (DtoShowCar c : listIn) {
            for (DtoShowCar b : boughtCar) {
                if (c.getId().equals(b.getId())) {
                    Long profit = c.getCarPrice() - b.getCarPrice();
                    c.setProfit(profit);
                    float margin = (((float) profit / c.getCarPrice()) * 100);
                    float margin_round = round(margin * 10.0f) / 10.0f;
                    c.setMargin(margin_round);
                    break;
                }
            }
            listOut.add(c);
        }
        return listOut;
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

    public Long CalculateSaleFilterValue(List<DtoShowCar> list) {
        Long value = 0L;
        for (DtoShowCar dtoShowCar : list) {
            value += dtoShowCar.getCarPrice();
        }
        return value;
    }

    public List<DtoShowCar> PurchaseFilter(Date DateBefore, Date DateAfter) {
        List<DtoShowCar> listIn = carsService.showBoughtCars();
        List<DtoShowCar> listOut = new ArrayList<>();
        for (DtoShowCar dtoShowCar : listIn) {
            Date date = dtoShowCar.getPurchaseDate();
            if ((DateBefore.before(date))) {
                if ((DateAfter.after(date))) {
                    listOut.add(dtoShowCar);
                }
            }
        }
        return listOut;
    }

    public Long CalculatePurchaseFilterValue(List<DtoShowCar> list) {
        List<BuyingContracts> buyingContracts = (List<BuyingContracts>) buyingContractsRepository.findAll();
        Long value = 0L;
        for (DtoShowCar dtoShowCar : list) {
            for (BuyingContracts bc : buyingContracts) {
                if (bc.getCars().getId().equals(dtoShowCar.getId())) {
                    value += bc.getPrice();
                }

            }

        }
        return value;
    }

    public Long CalculateProfit(List<DtoShowCar> list) {
        long value = 0L;
        for (DtoShowCar c : list) {
            value += c.getProfit();
        }
        return value;
    }


}





