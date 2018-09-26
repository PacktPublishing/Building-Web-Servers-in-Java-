package Product5.HttpServerWithDb.Controller;

import Product5.HttpServerWithDb.Model.Vehicle;
import Product5.HttpServerWithDb.Persistence.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class VehicleController {

    @Autowired
    private VehicleRepository userRepository;

    @RequestMapping(path = "/vehicles", method = GET)
    public List<Vehicle> getAllVehicles() {
        Iterable<Vehicle> allVehicles = userRepository.findAll();
        List<Vehicle> vehicleList;

        vehicleList = StreamSupport
            .stream(allVehicles.spliterator(), false)
            .collect(Collectors.toList());

        return vehicleList;
    }

    @RequestMapping(path = "/vehicles/make/{make}", method = GET)
    public List<Vehicle> getVehiclesByMake(@PathVariable(value = "make") String make) {
        Iterable<Vehicle> allVehicles = userRepository.findAll();
        List<Vehicle> vehicleList;

        vehicleList = StreamSupport
            .stream(allVehicles.spliterator(), false)
            .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make))
            .collect(Collectors.toList());

        return vehicleList;
    }

    @RequestMapping(path = "/vehicles/{vehicleID}", method = GET)
    public ResponseEntity<Vehicle> getVehicle(@PathVariable(value = "vehicleID") String vehicleID) {
        Optional<Vehicle> vehicle = userRepository.findById(Integer.valueOf(vehicleID));

        return vehicle.map(vehicle1 -> new ResponseEntity<>(vehicle1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/vehicles/{vehicleID}", method = POST)
    public ResponseEntity<?> updateVehicle(@PathVariable(value = "vehicleID") String vehicleID, @RequestBody Vehicle vehicle) {
        if (Integer.valueOf(vehicleID) != vehicle.getVehicle_id()) {
            return new ResponseEntity<>("Trying to change Vehicle ID not allowed", HttpStatus.BAD_REQUEST);
        }

        Optional<Vehicle> currentVehicle = userRepository.findById(Integer.valueOf(vehicleID));

        if (!currentVehicle.isPresent()) {
            return new ResponseEntity<>("Vehicle doesnt exist", HttpStatus.NOT_FOUND);
        }

        userRepository.save(vehicle);

        return new ResponseEntity<>("Vehicle Updated!", HttpStatus.OK);
    }

    @RequestMapping(path = "/vehicles/{vehicleID}", method = DELETE)
    public ResponseEntity<?> deleteVehicle(@PathVariable(value = "vehicleID") String vehicleID) {
        Optional<Vehicle> currentVehicle = userRepository.findById(Integer.valueOf(vehicleID));

        if (!currentVehicle.isPresent()) {
            return new ResponseEntity<>("Vehicle doesnt exist", HttpStatus.NOT_FOUND);
        }

        userRepository.delete(currentVehicle.get());

        return new ResponseEntity<>("Vehicle Deleted", HttpStatus.OK);
    }

}
