package Product5.HttpServerWithDb.Model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Vehicle {
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "colour")
    private String colour;
    @Column(name = "engine_size")
    private double engine_size;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public Vehicle(String make, String model, String colour, double engine_size, int id) {
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.engine_size = engine_size;
        this.id = id;
    }

    public Vehicle() {
    }

    public int getVehicle_id() {
        return id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.id = vehicle_id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getEngine_size() {
        return engine_size;
    }

    public void setEngine_size(double engine_size) {
        this.engine_size = engine_size;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
