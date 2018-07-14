package pl.sda.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.model.Cars;

public interface CarsRepository extends CrudRepository<Cars,Long>{
}
