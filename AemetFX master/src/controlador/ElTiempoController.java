package controlador;


import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.*;



public class ElTiempoController {


	 @FXML
	    private Button boton_salir;

	    @FXML
	    private ComboBox<?> box_co;

	    @FXML
	    private ComboBox<String> box_idiomas;

	    @FXML
	    private ComboBox<?> box_mun;

	    @FXML
	    private ComboBox<?> box_pro;

	    @FXML
	    private Label dia_Domingo;

	    @FXML
	    private Label dia_Jueves;

	    @FXML
	    private Label dia_Miercoles;

	    @FXML
	    private Label dia_Sabado;

	    @FXML
	    private Label dia_Viernes;

	    @FXML
	    private Label dia_lunes;

	    @FXML
	    private Label dia_martes;

	    @FXML
	    private TextField txt_ciudad;

    
    
    //Atributos del controlador
    private DatosINE datosINE;
    
    //ACCIONES
    @FXML
    void clickSalir(ActionEvent event) {
    	 System.exit(1);
    }
    
    
    @FXML
    void initialize() {

    	  // Leer datos de los ficheros XML
        this.datosINE = new DatosINE();

        // Obtener la lista de nombres de municipios
        TreeSet<String> listaNombresMunicipios = new TreeSet<>(this.datosINE.getMunicipios());

        // Configurar la auto-completación al escribir en el TextField
        txt_ciudad.setOnKeyReleased(event -> {
            String input = txt_ciudad.getText().toLowerCase();
            if (!input.isEmpty()) {
                TreeSet<String> sugerencias = new TreeSet<>();
                for (String municipio : listaNombresMunicipios) {
                    if (municipio.toLowerCase().startsWith(input)) {
                        sugerencias.add(municipio);
                    }
                }
                if (!sugerencias.isEmpty()) {
                    // Muestra la primera sugerencia si hay coincidencias
                    String primeraSugerencia = sugerencias.first();
                    txt_ciudad.setText(primeraSugerencia);
                    txt_ciudad.positionCaret(input.length());
                    txt_ciudad.selectRange(input.length(), primeraSugerencia.length());
                }
            }
        });
    }
    

    public void llenarCombo(ComboBox<String> llenarCombo, ObservableList<String> infocombo) {
        llenarCombo.setItems(infocombo);
        
    	}
    
    
	ObservableList<String> rolesList = FXCollections.observableArrayList("CASTELLANO","VALENCIÁ","ENGLISH");
	
	public void listarRoles(Event event) {
		
		llenarCombo(box_idiomas ,rolesList);
		
	}
	
	 @FXML
	    void buscar(ActionEvent event) {
		 TiempoCiudad tiempoCiudad= new TiempoCiudad("46029");
		 tiempoCiudad.descargar_crear_XML();
		 tiempoCiudad.leerDatos();
		// dia_lunes.setText();
	    }

		
}
