/**
 * Klasse zum Erzeugen von Car-Objekten
 * @author Benjamin Schwarz
 * @version 19.06.24
 */

public class Car extends Main {

    private final int id;
    private final String brand;
    private final String model;
    private final int horsepower;

    public Car(int carID, String brand, String model, int horsepower) {
        this.id = carID;
        this.brand = brand;
        this.model = model;
        this.horsepower = horsepower;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    // jedes Car-Object gibt seine Werte als String zurück
    @Override
    public String toString() {
        return id + " | " + brand + " " + model + " | " + horsepower + " PS";
    }
}
