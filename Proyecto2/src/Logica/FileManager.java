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
	 * Se encarga de a�adir los archivos seleccionados a la lista de la interfaz, adem�s de la l�gica del arbol
	 * @param archives: ListView encargada de mostrar los elementos cargados en el programa
	 */
	
	public void addAFile(ListView<String> archives) {
		
		FileChooser fc = new FileChooser();	// Cuadro de dialogo para abrir los archivos
		
		System.out.println(archives);
		
		
		// Lista que contener� los diferentes archivos que quiera cargar
		List<File> selectedFile = fc.showOpenMultipleDialog(null);
		
		fc.getExtensionFilters().addAll(new ExtensionFilter("Todos los archivos","*"),
				new ExtensionFilter("PDF Files","*pdf"), 
				new ExtensionFilter("Docx Files","*docx"), 
				new ExtensionFilter("TXT Files","*txt")
				);
		
		if(selectedFile != null) {	//Si hay archivos seleccionados 
			
			for(File file:selectedFile) {
				
				String name = file.getName();
				
				int lastIndexOf = name.lastIndexOf(".");
				
				String extension = name.substring(lastIndexOf);	//Obtengo la extensi�n del archivo
				
				System.out.println(extension);
				
				//Validaci�n de extensi�n
				if(extension.equals(".docx") || extension.equals(".pdf") || extension.equals(".txt")) {
					
					//A�adir la vara en la lista de forma visual
					archives.getItems().add(file.getPath());
					//A�adir la vara al arbol
					
					
				} else {
					
					System.out.println("Archivo no disponible");
					
				}
				
			}
			
		} else {
			System.out.println("file is not valid");
		}
		
	}
	
	/**
	 *	Funci�n que se encarga de a�adir un folder al programa 
	 */
	
	public void addFolder(ListView<String> archives) {
		
		//Para seleccionar un directorio a cargar al programa
		DirectoryChooser dc = new DirectoryChooser();
		
		File folder = dc.showDialog(null);
		
		if(folder == null) {
			System.out.println("No seleccion� un folder para cargar");
		} else {
			archives.getItems().add(folder.getPath());
		}
		
	}
	
	/**
	 * Elimina elementos de la lista observable
	 * @param archives: Elementos los cuales quiero eliminar
	 */
	public void deleteFile(ListView<String> archives) {
		
		//Elimina los elementos de la ListView
		archives.getItems().removeAll(archives.getSelectionModel().getSelectedItems());
		
	}
	
	/**
	 * Obtiene el nombre, fecha y tama�o del archivo
	 * @param files: Archivos a los cuales quiero ver sus propiedades
	 */
	public void getProperties(ObservableList<String> files) {
		
		for(int i = 0; i < files.size(); i++) {
			
			File f = new File(files.get(i));
			long lastModified = f.lastModified(); 
			System.out.println(lastModified);
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
			String name = f.getName();
			System.out.println("The name is: " + name);
			
			Date lastModifiedDate = new Date( lastModified );

			System.out.println( "The file " + f + " was last modified on " + simpleDateFormat.format( lastModifiedDate ) );
			
			float lenght = f.length();	//Longitud en bytes del archivo
			System.out.println( "The file " + f + " have " + lenght + " bytes " );
			
		}
		
	}
	
	/**
	 * Se encarga de actualizar los archivos cargados al programa
	 * @param previousList: Lista Observable que contiene los elementos que estaban en la lista
	 */
	
}
