package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;
import pl.sda.controller.ShowCars;
import pl.sda.model.BuyingContracts;
import pl.sda.model.Cars;
import pl.sda.model.DtoShowCar;
import pl.sda.model.SellingContracts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarsService {

    private final CarsRepository carsRepository;
    private final BuyingContractsRepository buyingContractsRepository;
    private final SellingContractsRepository sellingContractsRepository;

    public CarsService(CarsRepository carsRepository, BuyingContractsRepository buyingContractsRepository, SellingContractsRepository sellingContractsRepository) {
        this.carsRepository = carsRepository;
        this.buyingContractsRepository = buyingContractsRepository;
        this.sellingContractsRepository = sellingContractsRepository;
    }

    public List<DtoShowCar> showCars() {
        List<Cars> cars = (List<Cars>) carsRepository.findAll();
        List<BuyingContracts> buyingContracts = (List<BuyingContracts>) buyingContractsRepository.findAll();
        List<SellingContracts> sellingContracts = (List<SellingContracts>) sellingContractsRepository.findAll();
        List<DtoShowCar> list = new ArrayList<>();
        for (Cars c : cars) {

            DtoShowCar dtoShowCar = new DtoShowCar();
            dtoShowCar.setId(c.getId());
            dtoShowCar.setCarManufacturer(c.getManufacturer());
            dtoShowCar.setCarModel(c.getModel());
            dtoShowCar.setCarYearProduction(c.getYearProduction());
            dtoShowCar.setCarMilage(c.getMilage());
            dtoShowCar.setCarNrChassis(c.getNrChassis());
            dtoShowCar.setCarPrice(c.getPrice());

            for (BuyingContracts bc : buyingContracts) {
                if (c.getId().equals((bc.getDate()))) {
                    dtoShowCar.setPurchaseDate(bc.getDate());
                    break;
                }
            }

            for (SellingContracts sc : sellingContracts) {
                if (c.getId().equals(sc.getCars().getId())) {
                    dtoShowCar.setSaleDate(sc.getDate());
                    break;
                }
            }


            list.add(dtoShowCar);

        }

        return list;
    }

    public List<DtoShowCar> showAvailableCars() {
        List<DtoShowCar> list = showCars();
        List<SellingContracts> sellingContracts = (List<SellingContracts>) sellingContractsRepository.findAll();

        for (SellingContracts sc : sellingContracts) {
            for (DtoShowCar dtoShowCar : list) {
                if ((sc.getCars().getId().equals(dtoShowCar.getId()))) {
                    list.remove(dtoShowCar);
                    break;
                }
            }
        }
        return list;
    }

    public List<DtoShowCar> showSoldCars() {
        List<DtoShowCar> listIn = showCars();
        List<DtoShowCar> listOut = new ArrayList<>();
        List<SellingContracts> sellingContracts = (List<SellingContracts>) sellingContractsRepository.findAll();
        for (SellingContracts sc : sellingContracts) {
            for (DtoShowCar dtoShowCar : listIn) {
                if ((sc.getCars().getId().equals(dtoShowCar.getId()))) {
                    listOut.add(dtoShowCar);
                    break;
                }
            }
        }
        return listOut;
    }

    public List<DtoShowCar> showBoughtCars() {
        List<DtoShowCar> list = showCars();
        List<BuyingContracts> buyingContracts = (List<BuyingContracts>) buyingContractsRepository.findAll();

        for (BuyingContracts bc : buyingContracts) {
            for (DtoShowCar dtoShowCar : list) {
                if (bc.getCars().getId().equals(dtoShowCar.getId())) {
                    dtoShowCar.setCarPrice(bc.getPrice());
                    Date date = bc.getDate();
                    dtoShowCar.setPurchaseDate(date);
                    break;
                }
            }
        }
        return list;
    }
}
