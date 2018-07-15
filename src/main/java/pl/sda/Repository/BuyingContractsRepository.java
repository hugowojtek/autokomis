package pl.sda.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.model.BuyingContracts;

public interface BuyingContractsRepository extends CrudRepository<BuyingContracts,Long> {
}
