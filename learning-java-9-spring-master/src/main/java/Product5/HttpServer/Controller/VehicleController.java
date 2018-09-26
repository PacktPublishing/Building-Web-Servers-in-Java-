package Product5.HttpServer.Controller;

import Product5.HttpServer.Model.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class VehicleController {
    @RequestMapping(path = "/vehicles", method = GET)
    public List<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("Ford", "Fiesta", "Red", 1.0, 123));

        return vehicleList;
    }

    @RequestMapping(path = "/vehicles/make/{make}", method = GET)
    public List<Vehicle> getVehiclesByMake(@PathVariable(value = "make") String make) {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(make, "Fiesta", "Red", 1.0, 123));

        return vehicleList;
    }

    @RequestMapping(path = "/vehicles/{vehicleID}", method = GET)
    public Vehicle getVehicle(@PathVariable(value = "vehicleID") String vehicleID) {
        return new Vehicle("Ford", "Fiesta", "Red", 1.0, Integer.valueOf(vehicleID));
    }

    @RequestMapping(path = "/vehicles/{vehicleID}", method = POST)
    public ResponseEntity<?> updateVehicle(@PathVariable(value = "vehicleID") String vehicleID, @RequestBody Vehicle vehicle) {
        if (Integer.valueOf(vehicleID) != vehicle.getVehicle_id()) {
            return new ResponseEntity<>("Trying to change Vehicle ID not allowed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Vehicle Updated!", HttpStatus.OK);
    }

    @RequestMapping(path = "/vehicles/{vehicleID}", method = DELETE)
    public ResponseEntity<?> deleteVehicle(@PathVariable(value = "vehicleID") String vehicleID) {
        return new ResponseEntity<>("Vehicle Deleted", HttpStatus.OK);
    }

}
