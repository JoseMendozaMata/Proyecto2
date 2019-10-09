package Logica.patron.parsers;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;



public class DOCXManager implements FileParser{

	private XWPFDocument docx;
	private XWPFWordExtractor we;
	
	@Override
	public String getText(String url) {

		String text = "";
		
		try {
			docx = new XWPFDocument(new FileInputStream(url));
			we = new XWPFWordExtractor(docx);
			text = we.getText();
			
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		
		return text;
	}

	@Override
	public String[] getLines(String url) {
		String txt= getText(url);
		String[] lineas;
		lineas= txt.split(System.getProperty("line.separator"));
		
		return lineas;
	}

	
	
}
