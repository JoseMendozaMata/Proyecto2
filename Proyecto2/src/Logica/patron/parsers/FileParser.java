package Logica.patron.parsers;

import java.io.IOException;

/**
 * Los m�todos que podr� ejecutar el parser
 * @author F�tima y Jos�
 *
 */
public interface FileParser {
	/**
	 * Obtiene el texto del archivo indicado por el url
	 * @param url: Direccion del archivo
	 * @return String que guarda el contenido del archivo 
	 * @throws IOException 
	 */
	public String getText(String url) throws IOException;		// Obtiene el contenido de los documentos en String

	public String[] getLines(String url);
	
}
