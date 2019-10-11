package Interfaz;

import java.io.IOException;

import org.apache.pdfbox.pdfparser.PDFParser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	ListView<String> archives;
	Pane table;
	FileManager fileManager;
	
	
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
		
		root = new VBox(10);
		
		search = new HBox(10);
		
		fileManager = new FileManager();
		
		archives = new ListView<String>();
		archives.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		archivesContainer = new HBox(10);
		
		archives.setMaxWidth(300);
		
		table = new Pane();
		table.setMinSize(300, 200);
		
		// TextField con el que se busca la palabra

		TextField searchField = new TextField();
		searchField.setPromptText("Buscar");
		search.getChildren().add(searchField);

		
		//----------------------------- Buttons --------------------------------------
		
		Button addFileButton = new Button("Add New File");
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
				fileManager.search(searchField.getText(),this.archives.getItems());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		search.getChildren().add(searchButton);
		
		// ---------------- Se annaden los elementos para mostrarlos en pantalla ---------------------
		
		
		table.getChildren().add(new Rectangle(10,10));
		
		archivesContainer.getChildren().add(archives);
		archivesContainer.getChildren().add(table);
		
		root.getChildren().addAll(search, archivesContainer);
		
		scene = new Scene(root);
		
		window.setScene(scene);
		window.show();
		
	}
	
	

}
