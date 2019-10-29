package Interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
    
	private BufferedReader br;
	
	public void showText(String url, String word) throws Exception {
		
		Stage stage= new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		ScrollPane rootScroll= new ScrollPane();
		Pane root= new Pane();
		Scene scene= new Scene(rootScroll);
		
		Label label= new Label("No hay texto definido");
		
		//int line2= Integer.parseInt(line);
		
		
		
		
		if(word.equals(".docx")) {
			label.setText(getDocx(url));
		}else if(word.equals(".pdf")) {
			label.setText(getPdf(url));
			
		}else if(word.equals(".txt")) {
			label.setText(getTxt(url));
		}
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
		
		rootScroll.setContent(root);
		root.getChildren().addAll();
		root.setStyle("-fx-background-color: #FFFFFF");
		
		stage.setTitle("Document properties");
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	public String getDocx(String url) {

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
	
	public String getPdf(String url) throws Exception{
        file = new File(url);
        parser = new PDFParser((RandomAccessRead) new FileInputStream(file));
        
        parser.parse();
        try (COSDocument cosDoc = parser.getDocument()) {
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);
            parsedText = pdfStripper.getText(pdDoc);
        }
        
        return parsedText;
 
	}

	public String getTxt(String url) {
		
		String text = "";		// Donde se guardara el texto
		
		try {	// Intenta abrir el archivo para saber si existe
			
			File archivo = new File (url);
			FileReader fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			
			String linea;
			
			// Para obtener el contenido en lineas del txt
			while((linea = br.readLine()) != null) {
				text += linea + "\n";
			}
		
		} catch (Exception e) {				// Cuando no puede abrir el archivo
			text = null;
			System.out.println("No existe el archivo");
		}
		
		return text;
	}

}
