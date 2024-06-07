# CarManagerFX - Eine JavaFX-Anwendung zum Erstellen einer Fahrzeugdaten-Tabelle

<p>Der User kann Fahrzeuge mit je vier Eigenschaften (ID, Marke, Modell und Leistung)
einer Liste hinzufügen, die Fahrzeuge bearbeiten oder diese wieder aus der Liste entfernen.</p>
<p>Außerdem verfügt die Anwendung über einen Toggle-Button zum Hin- und Herschalten zwischen hellem und dunklem Modus.</p>

# Anleitung zum Ausführen des Programms mit IntelliJ
<ol>
  <p>
<li>) OpenJFX-SDK auf <a href="https://gluonhq.com/products/javafx/">https://gluonhq.com/products/javafx/</a> runterladen und auf Laufwerk C: entpacken </li>
 </p>
  <p>
<li>) Normales Java Projekt erstellen </li>
 </p>
   <p>
<li>) Erstellen Sie eine Bibliothek <br>
Gehen Sie zu File -> Project Structure -> Libraries und fügen Sie das 
SDK als Java-Bibliothek zu Ihrem Projekt hinzu. Zeigen Sie 
auf den Ordner lib.</li> 
</p>
   <p>
<li>) VM-Optionen hinzufügen <br>
Die Programmausführung wirft Fehler. Um das Problem zu lösen, klicken Sie 
mit rechts auf die Main.java -> Modify Run Configurations -> Modify 
options. Fügen Sie folgendes den VM-Optionen hinzu:<br>

--module-path "\path\to\javafx-sdk-xx\lib" --add-modules 
javafx.controls,javafx.fxml <br>

Ersetzen Sie „path/to/…“ durch den korrekten Pfad zur fx-Library und 
zeigen sie auf den lib-Ordner. Beispiel: „C:\javafx-sdk-22\lib“ </li>
</p>
</ol>
