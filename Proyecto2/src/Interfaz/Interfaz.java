package Interfaz;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;

import Logica.lista.Lista;
import Logica.patron.parsers.DOCXManager;
import Logica.patron.parsers.PDFManager;
import Logica.patron.parsers.TXTParser;
import Logica.tree.TreeNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


/** Clase la cual se encarga de interactuar con el usuario
 * @author Jose y Fatima
 *
 */
public class Interfaz extends Application{

	Stage window;
	Scene scene;
	VBox root;
	
	
	Pane archivesContainer;
	ListView<String> archives;		// Biblioteca de la interfaz
	ListView<String> searchResults;
	Pane table;
	FileManager fileManager;
	Lista ocurrenceSearchList;
	Algoritmos ordenamiento;
	Pane first;
	TextField searchField;
	
	public int variable;
	
	public static void main(String[]args) {
		
		launch();
		
	}
	
	
	/** 
	 *  Muestra la ventana e inicializa la ventana
	 */
	
	public void start(Stage primaryStage) throws Exception {
	
		window = primaryStage;
		window.setTitle("Text Finder");
		
		//-------------------- Start Variables ------------------------------------
		
		root = new VBox();
		
		first= new Pane();
		first.setMinSize(900, 110);
		
		fileManager = new FileManager();
		archivesContainer = new Pane();
		
		archives = new ListView<String>();
		archives.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				
		archives.setMaxWidth(300);
		archives.setLayoutX(10);
		archives.setLayoutY(10);
		
		table = new Pane();
		table.setMinSize(300, 200);
		
		searchResults = new ListView<>();
		
		// TextField con el que se busca la palabra
		searchField = new TextField();
		searchField.setPromptText("Search for a word");
		searchField.setLayoutX(600);
		searchField.setLayoutY(13);
		
		//----------------------------- Buttons --------------------------------------
		
		String designButton= "-fx-background-color: \r\n" + 
				"        #c3c4c4,\r\n" + 
				"        linear-gradient(#d6d6d6 50%, white 100%),\r\n" + 
				"        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\r\n" + 
				"    -fx-background-radius: 30;\r\n" + 
				"    -fx-background-insets: 0,1,1;\r\n" + 
				"    -fx-text-fill: black;\r\n" + 
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
		
		//Disenno del boton de agregar archivos
		Image addFi= new Image(getClass().getResource("add1.png").toExternalForm());
		ImageView img1= new ImageView(addFi);
		img1.setFitHeight(20);
		img1.setFitWidth(20);
		Button addFileButton = new Button("Add File", img1);		
		addFileButton.setStyle(designButton);
		
		addFileButton.setOnAction(e -> {
			this.fileManager.addAFile(this.archives);
			
		});
		addFileButton.setLayoutX(20);
		addFileButton.setLayoutY(30);
		
		//Disenno del boton de agregar folder
		Image addFo= new Image(getClass().getResource("add2.png").toExternalForm());
		ImageView img2= new ImageView(addFo);
		img2.setFitHeight(20);
		img2.setFitWidth(20);
		Button addFolderButton = new Button("Add Folder", img2);
		addFolderButton.setOnAction(e -> {
			
			this.fileManager.addFolder(archives);
			
		});
		addFolderButton.setLayoutX(170);
		addFolderButton.setLayoutY(30);
		addFolderButton.setStyle(designButton);
		
		
		//Disenno del boton de borrar archivos
		Image delete= new Image(getClass().getResource("add3.png").toExternalForm());
		ImageView img3= new ImageView(delete);
		img3.setFitHeight(20);
		img3.setFitWidth(20);
		Button deleteFileButton = new Button("Delete file", img3);
		deleteFileButton.setOnAction(e -> {
			
			this.fileManager.deleteFile(archives);
		});
		deleteFileButton.setLayoutX(340);
		deleteFileButton.setLayoutY(30);
		deleteFileButton.setStyle(designButton);
		
		//boton de las propiedades del archivo
		Button viewDate = new Button("View Properties");
		viewDate.setOnAction(e -> {
			Properties ele= new Properties();
			ele.SeeProperties(this.searchResults.getSelectionModel().getSelectedItems());
		});
		viewDate.setLayoutX(650);
		viewDate.setLayoutY(70);
		viewDate.setStyle(designButton);
		
		//Boton para mostrar el archivo
		ShowDocs read= new ShowDocs();
		
		Button showText= new Button("Show Text");
		showText.setOnAction(e -> {
			Properties prop= new Properties();
			try {
				int lastIndexOf = prop.getProperties(this.searchResults.getSelectionModel().getSelectedItems(), "path").lastIndexOf(".");
				String extension = prop.getProperties(this.searchResults.getSelectionModel().getSelectedItems(), "path").substring(lastIndexOf);
				read.showText(prop.getProperties(this.searchResults.getSelectionModel().getSelectedItems(), "path")
						, extension, searchField.getText());
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Error en la selección");
				alert.setContentText("Ooops, no se ha selecionado un documento para leer! "
						+ "\n"
						+ "\n"
						+ "Por favor seleccione un documento!");

				alert.showAndWait();
			}
		});
		showText.setLayoutX(600);
		showText.setLayoutY(100);
		showText.setStyle(designButton);
		
		
		//Boton de buscar elementos
		Image searchF= new Image(getClass().getResource("4.png").toExternalForm());
		ImageView img4= new ImageView(searchF);
		img4.setFitHeight(20);
		img4.setFitWidth(20);
		Button searchButton = new Button();
		searchButton.setGraphic(img4);
		searchButton.setOnAction(e -> {
			try {
				
				TreeNode result = fileManager.search(searchField.getText(),this.archives.getItems());
				ocurrenceSearchList= new Lista();
				if(result == null) {	// Si la palabra no existe 
					
					System.out.println("La palabra no existe");
					this.searchResults.getItems().removeAll(searchResults.getItems());

					
				} else {	// Si la palabra existe, obtengo las ocurrencias y las pongo en una lista
					
					look(result);					
					
					makeResultList();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		searchButton.setStyle(designButton);
		searchButton.setLayoutX(780);
		searchButton.setLayoutY(10);
		//---------------------- ChechBox de las opciones de ordenamiento-----------------------------
		
		ChoiceBox<String> ordenar= new ChoiceBox<> ();
		ordenar.getItems().addAll("Nombre", "Fecha", "Tamaño"); //Se inserta las opciones de la choiceBox
		ordenar.setValue("Nombre");
		ordenar.setStyle(designButton);
		ordenar.setLayoutX(600);
		ordenar.setLayoutY(15);
		
		ordenamiento= new Algoritmos(); //Se inicializa el atributo de la clase
		Button revChoice= new Button("Ordenar");
		revChoice.setLayoutX(615);
		revChoice.setLayoutY(50);
		
		revChoice.setOnAction(e -> {
				ordenamiento.ordenar(ordenar, ocurrenceSearchList, searchResults);
				makeResultList();
				});
		
		revChoice.setStyle(designButton);
				
		first.getChildren().addAll(addFileButton, addFolderButton, deleteFileButton, viewDate, searchField, searchButton);
		first.setStyle("-fx-background-color: linear-gradient(from 10% 10% to 100% 100%, #746A9B, #B9BDF0);");
		
		// ---------------- Se annaden los elementos para mostrarlos en pantalla ---------------------
		
		table.getChildren().add(searchResults);
		table.setLayoutX(300);
		table.setLayoutY(10);
		archivesContainer.getChildren().add(archives);
		archivesContainer.getChildren().addAll(table, ordenar, revChoice, showText);
		archivesContainer.setStyle("-fx-background-color: #EAE3EA");
		archivesContainer.setMinSize(900, 500);
		
		root.getChildren().addAll(first, archivesContainer);
		
		scene = new Scene(root, 900,600);
		
		window.setScene(scene);
		window.show();
		
	}
	
	/**
	 * Metodo que permite buscar la palabra que se está buscando en los documentos cargados
	 * @param result: es el nodo del arbol
	 */
	public void look(TreeNode result) {
		Lista ocurrencias= result.lista;
		for(int i=0; i < ocurrencias.size; i++) {
			FileManager litFileManager= new FileManager();
			String line= litFileManager.getExtract(ocurrencias.getIndex(i));
			String[] partsLine= line.split(" ");
			String[] partsSearch= searchField.getText().split(" ");
			
			if(partsSearch.length==1) {
				ocurrenceSearchList.add_Last(result.lista.getIndex(i).getUrl(), 
						result.lista.getIndex(i).getLine(), 
						result.lista.getIndex(i).getPosLine());
			}else{
				for(int j=0; j< partsLine.length; j++) {
					if(partsLine[j].equals(partsSearch[0])) {
						if(IsEqual(partsLine, partsSearch, j)) {
							ocurrenceSearchList.add_Last(result.lista.getIndex(i).getUrl(), 
									result.lista.getIndex(i).getLine(), 
									result.lista.getIndex(i).getPosLine());
							j=900;
						}
					}
				}
			}
		}	
	}
	
	public boolean IsEqual(String[] partsLine, String[] partsSearch, int posLine) {
		boolean response=true;
		for(int i=0; i<partsSearch.length; i++) {
			while(response && i<partsSearch.length) {
				if(partsSearch[i].equals(partsLine[posLine])) {
					posLine++;
					i++;
				}else {
					response= false;
				}
			}
		}
		
		return response;
	}
	
	/**
	 * Se encarga de annadir los files en la lista de resultados de busqueda 
	 */
	public void makeResultList() {
		
		if(this.searchResults.getItems().isEmpty()) {
			System.out.println("esta vacia");
			
		}else {
		
			this.searchResults.getItems().removeAll(searchResults.getItems());
		
		}
			System.out.println("-------------------------------------------");
			for(int i = 0; i < ocurrenceSearchList.size; i++) {
				System.out.println("el size de la lista de ocurrencias "+ocurrenceSearchList.size);
				File file= new File(ocurrenceSearchList.getIndex(i).getUrl());
				
				searchResults.getItems().add(file.getPath());
				
				variable= i;
				
				
				//Para agregar imagenes en la listView con los documentos cargados
				searchResults.setCellFactory(param -> new ListCell<String>() {
					
					public void updateItem(String friend, boolean empty) {
						
						super.updateItem(friend, empty);
						
						System.out.println("La variable es: "+variable+ "\n Friend es "+ friend);
						
		                if (empty) {
		                    setText(null);
		                    setGraphic(null);
		                } else {
		                	if(friend!=null) {
		                		TextFlow text= UnderLine(ocurrenceSearchList.getIndex(variable).getUrl(),
										ocurrenceSearchList.getIndex(variable).getLine());
		                		setText(friend);
			                    setGraphic(text);
		                	}
		                    
		                }
		            }
		        }); 
			}
			
		
	}
	
	
	/**
	 * Metodo que obtiene el texto de la linea donde se encuentra
	 * la palabra 
	 * 
	 * @param url: la direccion del archivo
	 * @param line: la linea en donde se encuentra
	 * @return el texto de la linea donde se encuentra la palabra
	 */
	public String getLine(String url, int line) {
		
		int lastIndexOf = url.lastIndexOf(".");
		String extension = url.substring(lastIndexOf);
		String text= "No sé";
		if(extension.equals(".docx")) {
			DOCXManager man= new DOCXManager();
			man.getLines(url);
			text= man.getLine(line);
		}else if(extension.equals(".txt")) {
			TXTParser man= new TXTParser();
			man.getLines(url);
			text= man.getLine(line);
		}else if(extension.equals(".pdf")) {
			PDFManager man= new PDFManager();
			man.getLines(url);
			text= man.getLine(line);
			
		}
		return text;
	}
	
	public TextFlow UnderLine(String url, int line) {
		String text= getLine(url, line);

		String[] partition= text.split(" ");

		//Los elementos para agregar al texto
		TextFlow textFlow;
		String[] searchResult= searchField.getText().split(" ");
		
		if(partition[0].equals(searchResult[0])) {
			textFlow= FirstPosition(searchResult, partition);
		}else {
			textFlow= HalfPosition(searchResult, partition);
		}
		return textFlow;
	}
	
	/**
	 * @param lenght: es el lenght de la palabra que se está buscando
	 * @param searchResult
	 * @param line
	 */
	public TextFlow FirstPosition(String[] searchResult, String[] line) {
		Text text1;
		String str1="";
		Text text2;
		String str2="";
		TextFlow textFlow;
		for(int i=0; i< searchResult.length; i++) {
			if(line[i].equals(searchResult[i])) {
				str1+= line[i] + " ";
			}else {
				while(i < line.length) {
					str2 += " "+line[i];
					i++;
				}
			}
		}
		text1= new Text(str1);
		text1.setFill(Color.LIGHTCORAL);
		text1.setUnderline(true);
		
		text2= new Text(str2);
		textFlow= new TextFlow(text1, text2);
		
		return textFlow;
	}
	
	public TextFlow HalfPosition(String[] searchResult, String[] line) {
		Text text1;
		String str1="";
		Text text2;
		String str2="";
		Text text3;
		String str3= "";
		TextFlow textFlow;
		
		for(int i=0; i< line.length; i++) {
			
			//mientras que no se haya encontrado donde aparece el resultado
			while(!line[i].equals(searchResult[0])) {
				str1+= line[i]+ " ";
				i++;
			}
			text1= new Text(str1);
			
			//Ya se encontro el resultado y se va a recorrer
			for(int j=0; j< searchResult.length; j++) {
				str2+= searchResult[j] + " ";
				i++;
			}
			text2= new Text(str2);
			text2.setFill(Color.LIGHTCORAL);
			text2.setUnderline(true);
			try{
				while(i< line.length) {
					str3+= line[i]+ " ";
					i++;
				}
				text3= new Text(str3);
				textFlow= new TextFlow(text1, text2, text3);
				return textFlow;
			}catch(Exception e) {
				textFlow= new TextFlow(text1, text2);
				return textFlow;
			}
		}
		return null;
	}
	
}
	

	
	

