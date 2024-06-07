/**
 * Hauptklasse des Car-Managers
 * @author Benjamin Schwarz
 * @version 07.06.24
 */

import javafx.application.Application;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Comparator;
import javafx.scene.Node;
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

    // erzeugen globaler Buttons
    private Button addButton = new Button("Hinzufügen");
    private ToggleButton modeButton = new ToggleButton("Dunkler Modus");

    // globale VBox
    private VBox vbox = new VBox(15);

    // Icon für Haupt- und Alarmfenster
    private Image carIcon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("blue-car-icon.png")));

    public static void main(String[] args) {
        launch(args);
    }

    // Start-Methode zur Erstellung der GUI
    @Override
    public void start(Stage primaryStage) {
        vbox.setStyle("-fx-font-family: Verdana; -fx-font-size: 16; -fx-padding: 10; -fx-background-color: grey;");

        HBox idBox = createInputBox("ID:", idField);
        HBox brandBox = createInputBox("Marke:", brandField);
        HBox modelBox = createInputBox("Modell:", modelField);
        HBox horsepowerBox = createInputBox("Leistung (PS):", horsepowerField);

        // carListView im hellen Modus initialisieren
        carListView.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        // Erzeugen und stylen der Buttons, Aufruf der jeweiligen Methode beim Klicken
        addButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        addButton.setOnAction(event -> addCar());
        Button deleteButton = new Button("Löschen");
        deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        deleteButton.setOnAction(event -> deleteCar());
        Button editButton = new Button("Bearbeiten");
        editButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        editButton.setOnAction(event -> editCar());
        modeButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        modeButton.setOnAction(event -> mode());
        HBox buttonBox = new HBox(10, addButton, deleteButton, editButton, modeButton);

        vbox.getChildren().addAll(idBox, brandBox, modelBox, horsepowerBox, buttonBox, carListView);

        Scene scene = new Scene(vbox, 505, 606);
        primaryStage.setTitle("CarManagerFX");
        primaryStage.getIcons().add(carIcon);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Anwendung standardmäßig im hellen Modus starten
        modeButton.setSelected(false);
        mode();
    }

    // Hilfsmethode zur Erzeugung der Eingabefelder mit vorangehender Bezeichnung
    private HBox createInputBox(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setPrefWidth(150);
        label.setStyle("-fx-text-fill: black;"); // Standardfarbe für hellen Modus
        textField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
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

            // sortieren nach ID
            carList.sort(Comparator.comparingInt(Car::getId));

            carListView.getItems().clear();
            carListView.getItems().addAll(carList);

            idField.clear();
            brandField.clear();
            modelField.clear();
            horsepowerField.clear();

        } catch (NumberFormatException e) { // Alarmfenster, wenn Eingabewerte den falschen Datentyp haben
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

    // Methode zum Bearbeiten eines Fahrzeug-Eintrags
    private void editCar() {
        Car selectedCar = carListView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {

            // aktuelle Werte des ausgewählten Autos in die Eingabefelder laden
            idField.setText(String.valueOf(selectedCar.getId()));
            brandField.setText(selectedCar.getBrand());
            modelField.setText(selectedCar.getModel());
            horsepowerField.setText(String.valueOf(selectedCar.getHorsepower()));

            // altes Auto aus der Liste entfernen
            carList.remove(selectedCar);
            carListView.getItems().clear();
            carListView.getItems().addAll(carList);

            // neue Werte aus den Eingabefeldern lesen und neues Auto erstellen
            addButton.setOnAction(event -> addCar());
        } else {
            showAlert("Kein Auto ausgewählt", "Bitte wählen Sie das zu bearbeitende Auto.");
        }
    }

    // Methode zum Wechseln zwischen Dark- und Light-Mode
    private void mode() {
        if (modeButton.isSelected()) {
            // dunkler Modus
            modeButton.setText("Heller Modus");
            modeButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            vbox.setStyle("-fx-font-family: Verdana; -fx-font-size: 16; -fx-padding: 10; -fx-background-color: #252525;");
            idField.setStyle("-fx-background-color: #505050; -fx-text-fill: white;");
            brandField.setStyle("-fx-background-color: #505050; -fx-text-fill: white;");
            modelField.setStyle("-fx-background-color: #505050; -fx-text-fill: white;");
            horsepowerField.setStyle("-fx-background-color: #505050; -fx-text-fill: white;");
            carListView.setStyle("-fx-background-color: #505050;");

            // Labels im dunklen Modus
            for (Node node : vbox.getChildren()) {
                if (node instanceof HBox) {
                    for (Node subNode : ((HBox) node).getChildren()) {
                        if (subNode instanceof Label) {
                            ((Label) subNode).setStyle("-fx-text-fill: white;");
                        }
                    }
                }
            }
        } else {
            // heller Modus
            modeButton.setText("Dunkler Modus");
            modeButton.setStyle("-fx-background-color: #252525; -fx-text-fill: white;");
            vbox.setStyle("-fx-font-family: Verdana; -fx-font-size: 16; -fx-padding: 10; -fx-background-color: grey;");
            idField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            brandField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            modelField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            horsepowerField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            carListView.setStyle("-fx-background-color: white;");

            // Labels im hellen Modus
            for (Node node : vbox.getChildren()) {
                if (node instanceof HBox) {
                    for (Node subNode : ((HBox) node).getChildren()) {
                        if (subNode instanceof Label) {
                            ((Label) subNode).setStyle("-fx-text-fill: black;");
                        }
                    }
                }
            }
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
