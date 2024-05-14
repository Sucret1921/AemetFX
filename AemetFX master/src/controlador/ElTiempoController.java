package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import modelo.DatosINE;
import modelo.TiempoCiudad;


public class ElTiempoController {


    @FXML 
    private TextField txt_ciudad;
    
    @FXML 
    private Label lbl_Fecha;
    
    @FXML 
    private Label lbl_TempMax;
    
    @FXML 
    private Label lbl_TempMin;
    
    @FXML 
    private Label lbl_estadoCielo;
    
    @FXML
    private ComboBox<String> box_idiomas;
    
    @FXML
    private ComboBox<?> box_co;

    @FXML
    private ComboBox<?> box_mun;

    @FXML
    private ComboBox<?> box_pro;

   
    @FXML
    private Button boton_salir;
    
    
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
    

    @FXML
    void actualizarTiempo(KeyEvent event) {

    	//El tiempo se actualiza al pulsar ENTER
    	if (event.getCode() == KeyCode.ENTER){

    		//Obtener el código de la ciudad
    		String ciudad = this.datosINE.obtenerCodigoCiudad( txt_ciudad.getText() );

    		//Si existe la ciudad seleccionada...
    		if (ciudad != null) {
    			
    			//Obtener datos meteorológicos de una ciudad		
        		TiempoCiudad tiempo = new TiempoCiudad(ciudad);

        		//1.- Fecha				
        		String fechaString = tiempo.getFecha(0);

        		//Pasar de String a Date
        		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");		
        		Date fechaUsuario = null;
        		try {
        			fechaUsuario = formatoDelTexto.parse( fechaString );
        		} catch (ParseException e) {	
        			e.printStackTrace();
        		}

        		//Cambiar el formato de le fecha
        		formatoDelTexto = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        		lbl_Fecha.setText(formatoDelTexto.format(fechaUsuario));

        		//2.- Resto de datos meteorologicos
        		lbl_TempMax.setText(temperatura(tiempo.getTempMax(0)));
        		lbl_TempMin.setText(temperatura(tiempo.getTempMin(0)));
        		lbl_estadoCielo.setText(tiempo.getEstadoCielo(0));	
    		}    		
    	}    	
    }

    private String temperatura(String t) {
    	return t + "ºC";
    }


    
    public void llenarCombo(ComboBox<String> llenarCombo, ObservableList<String> infocombo) {
        llenarCombo.setItems(infocombo);
        
    	}
    
    
	ObservableList<String> rolesList = FXCollections.observableArrayList("CASTELLANO","VALENCIÁ","ENGLISH");
	
	public void listarRoles(Event event) {
		
		llenarCombo(box_idiomas ,rolesList);
		
	}



}
