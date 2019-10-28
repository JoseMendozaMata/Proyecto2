package Interfaz;

import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;

import Logica.lista.Lista;
import Logica.tree.TreeNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
		TextField searchField = new TextField();
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
		
		//propiedades del archivo
		Button viewDate = new Button("View Properties");
		viewDate.setOnAction(e -> {
			Properties ele= new Properties();
			//fileManager.getProperties(this.archives.getSelectionModel().getSelectedItems());
			ele.SeeProperties(this.archives.getSelectionModel().getSelectedItems());
		});
		viewDate.setLayoutX(650);
		viewDate.setLayoutY(70);
		viewDate.setStyle(designButton);
		
		
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
				
				if(result == null) {	// Si la palabra no existe 
					
					System.out.println("La palabra no existe");
					this.searchResults.getItems().removeAll(searchResults.getItems());

					
				} else {	// Si la palabra existe, obtengo las ocurrencias y las pongo en una lista
					
					this.ocurrenceSearchList = result.lista;
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
				}
				);
		revChoice.setStyle(designButton);
				
		first.getChildren().addAll(addFileButton, addFolderButton, deleteFileButton, viewDate, searchField, searchButton);
		first.setStyle("-fx-background-color: linear-gradient(from 10% 10% to 100% 100%, #746A9B, #B9BDF0);");
		
		// ---------------- Se annaden los elementos para mostrarlos en pantalla ---------------------
		
		table.getChildren().add(searchResults);
		table.setLayoutX(300);
		table.setLayoutY(10);
		archivesContainer.getChildren().add(archives);
		archivesContainer.getChildren().addAll(table, ordenar, revChoice);
		archivesContainer.setStyle("-fx-background-color: #EAE3EA");
		archivesContainer.setMinSize(900, 500);
		
		root.getChildren().addAll(first, archivesContainer);
		
		scene = new Scene(root, 900,600);
		
		window.setScene(scene);
		window.show();
		
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

				System.out.println(ocurrenceSearchList.getIndex(i).getName());
				searchResults.getItems().add(ocurrenceSearchList.getIndex(i).getName());

			}
		
	}
	
	public void ButtonDesign() {
		
	}
	
}
	

	
	

