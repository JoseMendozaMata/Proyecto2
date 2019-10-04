package Logica;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManager {

	/**
	 * Se encarga de añadir los archivos seleccionados a la lista de la interfaz, además de la lógica del arbol
	 * @param archives: ListView encargada de mostrar los elementos cargados en el programa
	 */
	
	public void addAFile(ListView<String> archives) {
		
		FileChooser fc = new FileChooser();	// Cuadro de dialogo para abrir los archivos
		
		System.out.println(archives);
		
		fc.getExtensionFilters().addAll(new ExtensionFilter("Todos los archivos","*"),
										new ExtensionFilter("PDF Files","*pdf"), 
										new ExtensionFilter("Docx Files","*docx"), 
										new ExtensionFilter("TXT Files","*txt")
										);
		
		// Lista que contenerá los diferentes archivos que quiera cargar
		List<File> selectedFile = fc.showOpenMultipleDialog(null);
		
		if(selectedFile != null) {	//Si hay archivos seleccionados 
			
			for(File file:selectedFile) {
				
				String name = file.getName();
				
				int lastIndexOf = name.lastIndexOf(".");
				
				String extension = name.substring(lastIndexOf);
				
				System.out.println(extension);
				
				//Validación de extensión
				if(extension.equals(".docx") || extension.equals(".pdf") || extension.equals(".txt")) {
					
					//Añadir la vara en la lista de forma visual
					archives.getItems().add(file.getPath());
					//Añadir la vara al arbol
					
					
				} else {
					
					System.out.println("Archivo no disponible");
					
				}
				
			}
			
		} else {
			System.out.println("file is not valid");
		}
		
	}
	
	/**
	 *	Función que se encarga de añadir un folder al programa 
	 */
	
	public void addFolder(ListView<String> archives) {
		
		DirectoryChooser dc = new DirectoryChooser();
		
		File folder = dc.showDialog(null);
		
		if(folder == null) {
			System.out.println("No seleccionó un folder para cargar");
		} else {
			archives.getItems().add(folder.getPath());
		}
		
	}

	public void deleteFile(ListView<String> archives) {
		
		//Elimina los elementos de la ListView
		archives.getItems().removeAll(archives.getSelectionModel().getSelectedItems());
		
	}

	public void getProperties(ObservableList<String> files) {
		
		for(int i = 0; i < files.size(); i++) {
			
			File f = new File(files.get(i));
			long lastModified = f.lastModified(); 
			System.out.println(lastModified);
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
			Date lastModifiedDate = new Date( lastModified );

			System.out.println( "The file " + f + " was last modified on " + simpleDateFormat.format( lastModifiedDate ) );
			
			float lenght = f.length();	//Longitud en bytes del archivo
			System.out.println( "The file " + f + " have " + lenght + " bytes " );
			
		}
		
	}
	
}
