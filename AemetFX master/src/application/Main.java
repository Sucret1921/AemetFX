package application;
	
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Locale locale = new Locale("es","ES");
			ResourceBundle bundle = ResourceBundle.getBundle("traduccion", locale);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/ElTiempo.fxml"),bundle);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/vista/css/estilos.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
