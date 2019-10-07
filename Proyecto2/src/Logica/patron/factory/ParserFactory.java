package Logica.patron.factory;

import Logica.patron.parsers.DOCXManager;
import Logica.patron.parsers.FileParser;
import Logica.patron.parsers.PDFManager;
import Logica.patron.parsers.ParserId;
import Logica.patron.parsers.TXTParser;

/**
 * Esta clase retorna el parser que se encargará de leer el tipo de archivo indicado
 * @author Fatima y Jose
 *
 */

public class ParserFactory {

	/**
	 * Retorna el parser que se usara
	 * @param id: El tipo de parser para leer el documento 
	 * @return El parser que se usará, conforme el enum ParserId
	 */
	
	public FileParser getParser(ParserId id) {
		
		if(id == ParserId.TXT) {
			return new TXTParser();
		}else if (id == ParserId.PDF) {
			System.out.println("Retorno un pdf");
			return new PDFManager();
		}else if (id == ParserId.DOCX) {
			return new DOCXManager();
		}else {
			return null;
		}		
		
	}
	
}
