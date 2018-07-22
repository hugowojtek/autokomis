package pl.sda.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.model.Cars;
import pl.sda.model.DtoBuyCar;

import java.util.List;

public interface CarsRepository extends CrudRepository<Cars,Long>{

}
