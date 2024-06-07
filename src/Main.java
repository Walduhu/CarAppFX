/**
 * Hauptklasse der CarApp
 * @author Benjamin Schwarz
 * @version 17.05.24
 */

import javafx.application.Application;
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // globale Variablen für die Eingabefelder, die Auto-ArrayList und die anzuzeigende Autoliste
    private TextField idField = new TextField();
    private TextField brandField = new TextField();
    private TextField modelField = new TextField();
    private TextField horsepowerField = new TextField();
    private ArrayList<Car> carList = new ArrayList<>();
    private ListView<Car> carListView = new ListView<>();

    // Icon für Haupt- und Alarmfenster
    private Image carIcon = new Image(Objects.
            requireNonNull(Main.class.getResourceAsStream("blue-car-icon.png")));

    public static void main(String[] args) {
        launch(args);
    }

    // Start-Methode zur Erstellung der GUI
    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-font-family: Calibri; -fx-font-size: 17; " +
                "-fx-padding: 10; -fx-background-color: darkgrey;");

        HBox idBox = createInputBox("ID:", idField);
        HBox brandBox = createInputBox("Marke:", brandField);
        HBox modelBox = createInputBox("Modell:", modelField);
        HBox horsepowerBox = createInputBox("Leistung (PS):", horsepowerField);

        // Erzeugen und stylen der Buttons, Aufruf der jeweiligen Methode beim Klicken
        Button addButton = new Button("Hinzufügen");
        addButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        addButton.setOnAction(event -> addCar());
        Button deleteButton = new Button("Löschen");
        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        deleteButton.setOnAction(event -> deleteCar());
        HBox buttonBox = new HBox(10, addButton, deleteButton);

        vbox.getChildren().addAll(idBox, brandBox, modelBox, horsepowerBox, buttonBox, carListView);

        Scene scene = new Scene(vbox, 400, 500);
        primaryStage.setTitle("Car Manager FX");
        primaryStage.getIcons().add(carIcon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Hilfsmethode zur Erzeugung der Eingabefelder mit vorangehender Bezeichnung
    private HBox createInputBox(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setPrefWidth(100);
        return new HBox(10, label, textField);
    }

    // Methode zum Hinzufügen von Autos zur ArrayList und der Listenanzeige
    private void addCar() {
        try {

            int id = Integer.parseInt(idField.getText());
            String brand = brandField.getText();
            String model = modelField.getText();
            int horsepower = Integer.parseInt(horsepowerField.getText());

            Car auto = new Car(id, brand, model, horsepower);
            carList.add(auto);
            carListView.getItems().clear();
            carListView.getItems().addAll(carList);

            idField.clear();
            brandField.clear();
            modelField.clear();
            horsepowerField.clear();

        } catch (NumberFormatException e) {  // Alarmfenster, wenn Eingabewerte den falschen Datentyp haben
            showAlert("Ungültige Eingabe", "Bitte geben Sie gültige Werte in die Textfelder ein.");
        }
    }

    // Methode zum Löschen von Autos aus der ArrayList und der Listenanzeige
    private void deleteCar() {
        Car selectedCar = carListView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            carList.remove(selectedCar);
            carListView.getItems().clear();
            carListView.getItems().addAll(carList);
        } else {
            showAlert("Kein Auto ausgewählt", "Bitte wählen Sie das zu löschende Auto.");
        }
    }

    // Methode zum Erzeugen eines Alarmfensters
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(carIcon);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
