package modelo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TiempoCiudad {

	//Atributos

	 private String codigo;

	    public TiempoCiudad(String codigo) {
	        this.codigo = codigo;
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

	public void cargarSensacionTermicaMinima(Element e) {
	    NodeList listaSensacionTermica = e.getElementsByTagName("sens_termica");

	    for (int iSensacion = 0; iSensacion < listaSensacionTermica.getLength(); iSensacion++) {
	        Node nodoSensacion = listaSensacionTermica.item(iSensacion);

	        if (nodoSensacion.getNodeType() == Node.ELEMENT_NODE) {
	            Element elementoSensacion = (Element) nodoSensacion;

	            // Obtener la mínima sensación térmica
	            Node nodoMinima = elementoSensacion.getElementsByTagName("minima").item(0);
	            String sensacionMinima = nodoMinima.getTextContent();

	            // Imprimir la información de la sensación térmica mínima
	            System.out.println("\tSensación térmica mínima: " + sensacionMinima);
	        }
	    }
	}
	
	public void cargarSensacionTermicaMaxima(Element e) {
	    NodeList listaSensacionTermica = e.getElementsByTagName("sens_termica");

	    for (int iSensacion = 0; iSensacion < listaSensacionTermica.getLength(); iSensacion++) {
	        Node nodoSensacion = listaSensacionTermica.item(iSensacion);

	        if (nodoSensacion.getNodeType() == Node.ELEMENT_NODE) {
	            Element elementoSensacion = (Element) nodoSensacion;

	            // Obtener la máxima sensación térmica
	            Node nodoMaxima = elementoSensacion.getElementsByTagName("maxima").item(0);
	            String sensacionMaxima = nodoMaxima.getTextContent();

	            // Imprimir la información de la sensación térmica máxima
	            System.out.println("\tSensación térmica máxima: " + sensacionMaxima);
	        }
	    }
	}
	
	
	public void cargarHumedad(Element e) {
	    NodeList listaHumedad = e.getElementsByTagName("humedad_relativa");

	    for (int iHumedad = 0; iHumedad < listaHumedad.getLength(); iHumedad++) {
	        Node nodoHumedad = listaHumedad.item(iHumedad);

	        if (nodoHumedad.getNodeType() == Node.ELEMENT_NODE) {
	            Element elementoHumedad = (Element) nodoHumedad;

	            // Obtener la humedad mínima
	            Node nodoMinima = elementoHumedad.getElementsByTagName("minima").item(0);
	            String humedadMinima = nodoMinima.getTextContent();

	            // Obtener la humedad máxima
	            Node nodoMaxima = elementoHumedad.getElementsByTagName("maxima").item(0);
	            String humedadMaxima = nodoMaxima.getTextContent();

	            // Imprimir la información de la humedad
	            System.out.println("\tHumedad relativa:");
	            System.out.println("\t\tMínima: " + humedadMinima);
	            System.out.println("\t\tMáxima: " + humedadMaxima);
	        }
	    }
	}
	
	
	
	public void cargarRayosUV(Element e) {
	    NodeList listaRayosUV = e.getElementsByTagName("uv_max");

	    for (int iUV = 0; iUV < listaRayosUV.getLength(); iUV++) {
	        Node nodoUV = listaRayosUV.item(iUV);

	        if (nodoUV.getNodeType() == Node.ELEMENT_NODE) {
	            Element elementoUV = (Element) nodoUV;

	            // Con getAttribute accedemos al dato del atributo "periodo" de la etiqueta "uv_max"
	            // Ejemplo: <uv_max>6</uv_max> obtenemos el valor "6"
	            System.out.print("\tÍndice UV máximo: ");

	            // Con getTextContent() accedemos al dato que contiene la etiqueta "uv_max"
	            // Ejemplo: <uv_max>6</uv_max> obtenemos el valor "6"
	            System.out.println(elementoUV.getTextContent());
	        }
	    }
	}

	public void cargarRachaViento(Element e) {
	    NodeList listaRachas = e.getElementsByTagName("racha_max");

	    for (int iRacha = 0; iRacha < listaRachas.getLength(); iRacha++) {
	        Node nodoRacha = listaRachas.item(iRacha);

	        if (nodoRacha.getNodeType() == Node.ELEMENT_NODE) {
	            Element elementoRacha = (Element) nodoRacha;

	            // Con getAttribute accedemos al dato del atributo "periodo" de la etiqueta "racha_max"
	            // Ejemplo: <racha_max periodo="12-24">... obtenemos el dato "12-24"
	            System.out.print("\tRacha de viento (" + elementoRacha.getAttribute("periodo") + "): ");

	            // Con getTextContent() accedemos al dato que contiene la etiqueta "racha_max"
	            // Ejemplo: <racha_max periodo="12-24"> 45 </racha_max> obtenemos el dato "45"
	            System.out.println(elementoRacha.getTextContent());
	        }
	    }
	}

	
	public void cargarProbabilidadLluvia(Element e)  { 
		NodeList listaLLuvia = e.getElementsByTagName("prob_precipitacion");

		for (int iLluvia = 0; iLluvia < listaLLuvia.getLength(); iLluvia++) {
			Node nodoLluvia = listaLLuvia.item(iLluvia);

			if(nodoLluvia.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoLluvia = (Element) nodoLluvia;

				// Con getAttribute accedemos al dato del atributo "periodo" de la etiqueta "prob_precipitacion"
				// Ejemplo: <prob_precipitacion periodo="06-12">... obtenemos el dato "06-12"
				System.out.print("\tProbabilidad precipitación (" + elementoLluvia.getAttribute("periodo") +"): ");

				// Con getTextContent() accedemos al dato que contiene la etiqueta "prob_precipitacion"
				// Ejemplo: <prob_precipitacion periodo="06-12"> 15 </prob_precipitacion> obtenemos el dato "15"
				System.out.println(elementoLluvia.getTextContent());
			}
		}
	}

	public void cargarEstadoCielo(Element e)  {
		
		NodeList listaCielo = e.getElementsByTagName("estado_cielo");

		for (int iCielo = 0; iCielo < listaCielo.getLength(); iCielo++) {
			Node nodoCielo = listaCielo.item(iCielo);

			if(nodoCielo.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoCielo = (Element) nodoCielo;

				System.out.print("\tEstado del Cielo (" + elementoCielo.getAttribute("periodo") +"): ");

				System.out.println(elementoCielo.getAttribute("descripcion"));
			}
		}
	}

	public void leerDatos() {
		
		
		String ruta = "https://www.aemet.es/xml/municipios/localidad_46029.xml";		
		
		try {
			// Preparamos los objetos necesarios para cargar el documento XML
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			URL url = new URL(ruta);
			InputStream stream = url.openStream();
			
			// Parseamos el documento con el objeto Document y le pasamos el stream de la ruta
			Document doc = db.parse(stream);
			
			doc.getDocumentElement().normalize(); // Normalizamos el documento (opcional)

			// Obtenemos la lista de nodos referente a la etiqueta "dia" de nuestro xml
			NodeList listaNodosDias = doc.getElementsByTagName("dia");

			// Recorremos la lista de dias para cargar los datos que contiene cada día
			for (int iDias = 0; iDias < listaNodosDias.getLength(); iDias++) {
				Node nodoDia = listaNodosDias.item(iDias);
				
				if(nodoDia.getNodeType() == Node.ELEMENT_NODE) { // Comprobamos que es un Nodo
					// Convertimos cada nodo en un elemento para poder acceder
					// a los métodos de acceso a los datos (getAttribute, getElementsByTagName, etc)
					Element elementoDia = (Element) nodoDia;
					
					// Con getAttribute accedemos al dato que hay en el atributo del elemento
					// Por ejemplo: <dia fecha="2024-05-13"> accedemos a "2025-05-13"
					String s= elementoDia.getAttribute("fecha");
					Dia dia = new Dia(s);
					dia.getFechaDia();
					
					// Modularizamos lo mejor posible el código para cargar los datos (es un ejemplo)
					cargarProbabilidadLluvia(elementoDia);
					cargarEstadoCielo(elementoDia);
					cargarRachaViento(elementoDia);
					cargarRayosUV(elementoDia);
					cargarHumedad(elementoDia);
					cargarSensacionTermicaMaxima(elementoDia);
					cargarSensacionTermicaMinima(elementoDia);
					
				}
				System.out.println();
			}
			
		} catch(Exception e) { 
			System.out.println("Error en la lectura de datos");
		}
	}
	

}	
	
