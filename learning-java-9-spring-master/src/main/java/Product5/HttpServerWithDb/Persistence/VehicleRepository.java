package Product5.HttpServerWithDb.Persistence;

import Product5.HttpServerWithDb.Model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

}
