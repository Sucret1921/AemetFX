package modelo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TiempoCiudad {

	//Atributos
	private ArrayList<TiempoDia> tiempoDia;
	private String codigo;
	
	//Constructor
	public TiempoCiudad(String codigo) {

		this.tiempoDia = new ArrayList<TiempoDia>();
		this.codigo = codigo;
		
	
		this.descargar_crear_XML();
		this.leerInformacionXML();
	}


	public void descargar_crear_XML() {

		try {			
			URL url   = new URL("http://www.aemet.es/xml/municipios/localidad_" + this.codigo + ".xml");
			Scanner s = new Scanner( url.openStream() );
			String fichero = "localidad_" + this.codigo + ".xml";
			PrintWriter pw = new PrintWriter(new File(fichero));
			String linea;
			while(s.hasNext()) {
				linea = s.nextLine();
				pw.println( linea );				   
			}

			pw.close();
		}
		catch(IOException ex) { 
			ex.printStackTrace();
		}
	}

	public void leerInformacionXML() {

		try {
			File fXmlFile = new File("localidad_" + this.codigo + ".xml");			

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			//Documento a recorrer con los datos del fichero XML
			Document doc = dBuilder.parse(fXmlFile);
			
			
			doc.getDocumentElement().normalize();

			
			NodeList listaDias = doc.getElementsByTagName("dia");
									
		    // Recorrer lista de los elementos dia
			for (int i = 0; i < listaDias.getLength(); i++) {
				
				Node elementoDia = listaDias.item(i);
				Element eElement = (Element) elementoDia;
				

					//1.- dia
					System.out.println(eElement.getAttribute("fecha") );

					//2.- Estado del cielo
					NodeList cieloLista = doc.getElementsByTagName("estado_cielo");
					Node cieloNodo = cieloLista.item(2);
					Element cieloElement = (Element) cieloNodo;

					System.out.println(cieloElement.getAttribute("descripción"));
					 					
					//3.- Temperatura
					NodeList nTemp = doc.getElementsByTagName("temperatura");
					for (int j = 0; j < nTemp.getLength(); j++) {
						
						Node temperatura = nTemp.item(j);
						Element eTemp= (Element) temperatura;
					}
			}
		}
					catch(IOException | ParserConfigurationException | SAXException ex) {
						ex.printStackTrace();}
				}
		 
	

	public String getFecha(int dia) {

		return this.tiempoDia.get(dia).getFecha();
	}

	public String getEstadoCielo(int dia) {

		return this.tiempoDia.get(dia).getEstadoCielo();
	}

	public String getTempMax(int dia) {

		return this.tiempoDia.get(dia).getTempMax();
	}

	public String getTempMin(int dia) {

		return this.tiempoDia.get(dia).getTempMin();
	}	
	
}