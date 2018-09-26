package Product5.HttpServer;

import Product5.HttpServer.Model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllVehiclesIncludesFord() {
        List<Map<String, String>> response = this.restTemplate.getForObject("http://localhost:" + port + "/vehicles", List.class);
        assertThat(response.get(0).get("make")).isEqualToIgnoringCase("Ford");
    }

    @Test
    public void getFordsReturnsFords() {
        List<Map<String, String>> response = this.restTemplate.getForObject("http://localhost:" + port + "/vehicles/make/ford", List.class);
        response.forEach(vehicle -> assertThat(vehicle.get("make")).isEqualToIgnoringCase("Ford"));
    }

    @Test
    public void getVehicleReturnsVehicleWithId() {
        Vehicle vehicle = this.restTemplate.getForObject("http://localhost:" + port + "/vehicles/123", Vehicle.class);
        assertThat(vehicle.getVehicle_id()).isEqualTo(123);
    }
}
