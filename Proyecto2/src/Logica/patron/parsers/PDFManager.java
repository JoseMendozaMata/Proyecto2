package Logica.patron.parsers;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class PDFManager implements FileParser{

	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	private String Text;
	private String filePath;
	private File file;
	
	/** 
	 * Metodo que retorna el string Text que es el archivo pdf ya leido
	 * El metodo llama a otro metodo toText() y simplemente guarda lo obtenido
	 * en la variable de retorno
	 * 
	 * @return Texto
	 */
	public String getText(String url) {
		String Texto = "None";
		System.out.println("El url es: " + url);
		
		setFilePath(url);
		
		try {
			Texto= toText();
			System.out.println(Texto);
		}catch(Exception e) {
			System.out.print(e.getMessage());
			Texto= "Error en el sistema";
		}
		
		return Texto;
	}
	
	/**
	 * Este metodo es el encargado de leer el contenido del archivo pdf
	 * y lo retorna en la variable Text
	 * 
	 * @return Text
	 * @throws IOException
	 */
	
	public String toText() throws IOException{
		this.pdfStripper= null;
		this.pdDoc= null;
		this.cosDoc= null;
		
		file= new File(filePath);
		parser= new PDFParser(new RandomAccessFile(file, "r"));
		parser.parse();
		cosDoc= parser.getDocument();
		pdfStripper= new PDFTextStripper();
		pdDoc= new PDDocument(cosDoc);
		pdDoc.getNumberOfPages();
		pdfStripper.setStartPage(0);
		pdfStripper.setEndPage(pdDoc.getNumberOfPages());
		Text= pdfStripper.getText(pdDoc);
		return Text;
	}

	public void setFilePath(String filePath) {
		this.filePath= filePath;
	}
	
	public PDDocument getPDDoc() {
		return pdDoc;
	}
	
	
}