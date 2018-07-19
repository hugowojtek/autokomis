package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;

@Service
public class RaportsService {
    BuyingContractsRepository buyingContractsRepository;
    CarsRepository carsRepository;
    SellingContractsRepository sellingContractsRepository;

    public RaportsService(BuyingContractsRepository buyingContractsRepository, CarsRepository carsRepository, SellingContractsRepository sellingContractsRepository) {
        this.buyingContractsRepository = buyingContractsRepository;
        this.carsRepository = carsRepository;
        this.sellingContractsRepository = sellingContractsRepository;
    }


}
