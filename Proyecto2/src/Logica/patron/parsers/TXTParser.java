package Logica.patron.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Parser con los metodos necesarios para obtener los datos del txt
 * @author Jose y Fátima
 *
 */
public class TXTParser implements FileParser {

	/**
	 * Obtiene el contenido del txt en forma de string
	 */
	
	public String getText(String url) {
		
		String text = "";		// Donde se guardara el texto
		
		try {	// Intenta abrir el archivo para saber si existe
			
			File archivo = new File (url);
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			
			String linea;
			
			// Para obtener el contenido en lineas del txt
			while((linea = br.readLine()) != null) {
				text += linea;
			}
		
		} catch (Exception e) {				// Cuando no puede abrir el archivo
			text = null;
			System.out.println("No existe el archivo");
		}
		
		return text;
	}

}
