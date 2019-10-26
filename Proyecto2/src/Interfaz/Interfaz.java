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
	HBox search;
	HBox archivesContainer;
	ListView<String> archives;		// Biblioteca de la interfaz
	ListView<String> searchResults;
	Pane table;
	FileManager fileManager;
	Lista ocurrenceSearchList;
	Algoritmos ordenamiento;
	
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
		
		root = new VBox(30);
		
		search = new HBox(10);
		
		fileManager = new FileManager();
		
		archives = new ListView<String>();
		archives.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		archivesContainer = new HBox(10);
		
		archives.setMaxWidth(300);
		
		table = new Pane();
		table.setMinSize(300, 200);
		
		searchResults = new ListView<>();
		
		// TextField con el que se busca la palabra

		TextField searchField = new TextField();
		searchField.setPromptText("Buscar");
		search.getChildren().add(searchField);

		
		//----------------------------- Buttons --------------------------------------
		Image addImage= new Image(getClass().getResource("addD.jpeg").toExternalForm());
		Button addFileButton = new Button();
		addFileButton.setGraphic(new ImageView(addImage));
		
		addFileButton.setOnAction(e -> {
			
			this.fileManager.addAFile(this.archives);
			
		});
		
		search.getChildren().add(addFileButton);
		
		Button addFolderButton = new Button("Add New Folder");
		addFolderButton.setOnAction(e -> {
			
			this.fileManager.addFolder(archives);
			
		});
		
		search.getChildren().add(addFolderButton);
		
		Button deleteFileButton = new Button("Delete files");
		deleteFileButton.setOnAction(e -> {
			
			this.fileManager.deleteFile(archives);
			
		});
		
		search.getChildren().add(deleteFileButton);
		
		Button viewDate = new Button("View Date");
		viewDate.setOnAction(e -> {
			fileManager.getProperties(this.archives.getSelectionModel().getSelectedItems());
		});
		search.getChildren().add(viewDate);
		
		Button searchButton = new Button("Search");
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
		
		//---------------------- ChechBox de las opciones de ordenamiento-----------------------------
		
		ChoiceBox<String> ordenar= new ChoiceBox<> ();
		ordenar.getItems().addAll("Nombre", "Fecha", "Tamaño"); //Se inserta las opciones de la choiceBox
		ordenar.setValue("Nombre");
		
		ordenamiento= new Algoritmos(); //Se inicializa el atributo de la clase
		Button revChoice= new Button("Ordenar");
		
		revChoice.setOnAction(e -> {
				ordenamiento.ordenar(ordenar, ocurrenceSearchList, searchResults);
				makeResultList();
				}
				);	
				
		search.getChildren().add(searchButton);
		
		
		
		// ---------------- Se annaden los elementos para mostrarlos en pantalla ---------------------
		
		table.getChildren().add(searchResults);
		
		archivesContainer.getChildren().add(archives);
		archivesContainer.getChildren().addAll(table, ordenar, revChoice);
		
		root.getChildren().addAll(search, archivesContainer);
		
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
	

	
	

