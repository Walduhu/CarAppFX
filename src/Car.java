/**
 * Klasse zum Erzeugen von Car-Objekten
 * @author Benjamin Schwarz
 * @version 17.05.24
 */

public class Car extends Main {

    private int id;
    private String brand;
    private String model;
    private int horsepower;

    public Car(int carID, String brand, String model, int horsepower) {
        this.id = carID;
        this.brand = brand;
        this.model = model;
        this.horsepower = horsepower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    // jedes Car-Object gibt seine Werte als String zurück
    @Override
    public String toString() {
        return id + " | " + brand + " " + model + " | " + horsepower + " PS";
    }
}
