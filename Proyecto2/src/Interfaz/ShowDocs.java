package Interfaz;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;

import Logica.patron.factory.ParserFactory;
import Logica.patron.parsers.DOCXManager;
import Logica.patron.parsers.FileParser;
import Logica.patron.parsers.ParserId;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowDocs {

	private XWPFDocument docx;
	private XWPFWordExtractor we;
	
	PDFTextStripper pdfStripper;
    PDDocument pdDoc;
    File file;
    PDFParser parser;
    String parsedText;
    
    public ParserFactory parserfactory;
    public ParserId parserId;
    public FileParser fileParser;
        
	private BufferedReader br;
	
	public ParserId gettingT(String id) {

		
		// Convierto la extension a un ParserId
		if(id.equals(".txt")) {
			parserId = ParserId.TXT;
		}else if(id.equals(".pdf")) {
			parserId = ParserId.PDF;
		}else if(id.equals(".docx")) {
			parserId = ParserId.DOCX;
		}else {
			return null;
		}
		return parserId;
	}
	
	public void showText(String url, String word, String searchWord) throws Exception {
		
		
		parserfactory= new ParserFactory();
		
		if(word.equals(".docx")) {
			File file= new File(url);
			
			Desktop.getDesktop().open(file);

			
		}else if(word.equals(".pdf")) {
			
			File file= new File(url);
			Desktop.getDesktop().open(file);
			
			
		}else if(word.equals(".txt")) {
			File file= new File(url);
			Desktop.getDesktop().open(file);
		}
	}
	
	public void ShowTextAux(String url, String word, String searchWord) {
		Stage stage= new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		ScrollPane rootScroll= new ScrollPane();
		Pane root= new Pane();
		Scene scene= new Scene(rootScroll);
		
		Label label= new Label("No hay texto definido");
		
		
		parserfactory= new ParserFactory();
		
		label.setLayoutX(20);
		label.setLayoutY(20);
		
		Button close= new Button("Listo");
		close.setOnAction(e -> stage.close());
		close.setLayoutX(200);
		close.setLayoutY(170);
		close.setStyle("-fx-background-color:\n" + 
				"        linear-gradient(#8399E4, #5678EC),\n" + 
				"        radial-gradient(center 50% -40%, radius 200%, #A09AC8 45%, #D2CEEB 50%);\n" + 
				"    -fx-background-radius: 6, 5;\n" + 
				"    -fx-background-insets: 0, 1;\n" + 
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" + 
				"    -fx-text-fill: #132665;"); 
		
		root.getChildren().addAll(label, close);
		
		rootScroll.setVmax(440);
		rootScroll.setPrefSize(800, 450);
		root.setPrefSize(1900, 1200);
		
		rootScroll.setContent(label);
		root.getChildren().addAll();
		root.setStyle("-fx-background-color: #FFFFFF");
		
		stage.setTitle("Document properties");
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	
	public static XWPFRun createRun(XWPFParagraph paragraph){
	    XWPFRun run = paragraph.createRun();
	    run.setFontSize(12);
	    run.setFontFamily("Times New Roman");
	    return run;
	}

	

}
