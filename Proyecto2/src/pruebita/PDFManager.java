package pruebita;

import java.io.FileInputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * @author Jose y fatima
 * Clase manager de los archivos pdf
 * que se encarga de leer los archivos 
 * con esta extension
 *
 */
public class PDFManager {
	private XWPFDocument docx;
	private XWPFWordExtractor we;

	public void tratar(String url) {
		
		try {
			docx= new XWPFDocument(new FileInputStream(url));
			we = new XWPFWordExtractor(docx);
			System.out.println(we.getText());
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
}
