package pl.sda.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.model.SellingContracts;

@Repository
public interface SellingContractsRepository extends CrudRepository<SellingContracts,Long>{
}
