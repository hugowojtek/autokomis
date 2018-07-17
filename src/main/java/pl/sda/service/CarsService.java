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

    public List<DtoShowCar> showCars(){
        List<Cars> cars = (List<Cars>) carsRepository.findAll();
        List<BuyingContracts> buyingContracts = (List<BuyingContracts>) buyingContractsRepository.findAll();
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

            list.add(dtoShowCar);

        }

           return list;
    }

    public List<DtoShowCar> showAvailableCars(){
        List<DtoShowCar> list = showCars();
        List<SellingContracts> sellingContracts = (List<SellingContracts>) sellingContractsRepository.findAll();

        for (SellingContracts sc:sellingContracts){
            for (DtoShowCar dtoShowCar:list){
                    if ((sc.getCars().getId().equals(dtoShowCar.getId()))){
                    list.remove(dtoShowCar);
                    break;
                }
            }
        }
        return list;
    }

    public List <DtoShowCar> showSoldCars(){
        List<DtoShowCar> listIn = showCars();
        List<DtoShowCar> listOut = new ArrayList<>();
        List<SellingContracts> sellingContracts = (List<SellingContracts>) sellingContractsRepository.findAll();
        for (SellingContracts sc:sellingContracts){
            for (DtoShowCar dtoShowCar:listIn){
                if ((sc.getCars().getId().equals(dtoShowCar.getId()))){
                    listOut.add(dtoShowCar);
                    break;
                }
            }
        }
        return listOut;
    }
}
