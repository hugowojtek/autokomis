package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;
import pl.sda.model.BuyingContracts;
import pl.sda.model.Cars;
import pl.sda.model.DtoShowCar;
import pl.sda.model.SellingContracts;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
                    float margin_round = round(margin* 10.0f) / 10.0f;
                    sc.setMargin(margin_round);
                    value += sc.getCarPrice();
                }
            }
        }
        return value;
    }

    public List<DtoShowCar> SaleFilter(Date dateFrom, Date dateTo) {
        List<DtoShowCar> listIn = carsService.showSoldCars();
        List<DtoShowCar> listOut = new ArrayList<>();
        dateTo.setHours(23);
        dateTo.setMinutes(59);
        dateTo.setSeconds(59);

        for (DtoShowCar dtoShowCar : listIn) {
            Date date = dtoShowCar.getSaleDate();
            if ((date.after(dateFrom))) {
                if ((date.before(dateTo))) {

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

    public List<DtoShowCar> PurchaseFilter(Date dateFrom, Date dateTo) {
        List<DtoShowCar> listIn = carsService.showBoughtCars();
        List<DtoShowCar> listOut = new ArrayList<>();
        dateTo.setHours(23);
        dateTo.setMinutes(59);
        dateTo.setSeconds(59);

        for (DtoShowCar dtoShowCar : listIn) {
            Date date = dtoShowCar.getPurchaseDate();
            if ((date.after(dateFrom))) {
                if ((date.before(dateTo))) {

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

    public Long CalculateProfit2(Cars car, Long purchasePrice){
        Long profit=0L;
        profit = car.getPrice()-purchasePrice;
        return profit;
    }

    public float CalculateMargin(Cars car, Long purchasePrice){
        Long profit=CalculateProfit2(car,purchasePrice);
        float margin = (((float) profit / car.getPrice()) * 100);
        float margin_round = round(margin* 10.0f) / 10.0f;
        return margin_round;

    }


    public BigDecimal CalculateTax(Long profit) {
        BigDecimal valueIn = BigDecimal.valueOf(profit);
        BigDecimal tax = new BigDecimal(0.19);
        BigDecimal valueOut = valueIn.multiply(tax).round(new MathContext(6, RoundingMode.HALF_UP));
        return valueOut;

    }
}





