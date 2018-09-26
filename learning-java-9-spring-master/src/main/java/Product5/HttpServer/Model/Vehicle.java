package Product5.HttpServer.Model;

public class Vehicle {
    private String make;
    private String model;
    private String colour;
    private double engine_size;
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
