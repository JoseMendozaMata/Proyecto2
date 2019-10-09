package Interfaz;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import Logica.patron.factory.ParserFactory;
import Logica.patron.parsers.*;
import Logica.tree.Tree;
import Logica.tree.TreeNode;

/**
 * Busca y agrega los archivos a la lista visual con la que puede interactuar 
 * @author Fatima y Jose
 *
 */

public class FileManager {

	/**
	 * Se encarga de annadir los archivos seleccionados a la lista de la interfaz, ademas de la logica del arbol
	 * @param archives: ListView encargada de mostrar los elementos cargados en el programa
	 */
	
	ParserFactory factory = new ParserFactory();
	Tree tree= new Tree();
	
	public void addAFile(ListView<String> archives) {
		
		FileChooser fc = new FileChooser();	// Cuadro de dialogo para abrir los archivos
		
		System.out.println(archives);

		
		// Lista que contenera los diferentes archivos que quiera cargar
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
	 *	Funcion que se encarga de annadir un folder al programa 
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
	 * Obtiene el nombre, fecha y tamanno del archivo
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
	 * Se encarga de parsear los archivos cargados al programa
	 * @param previousList: Lista Observable que contiene los elementos que estaban en la lista
	 * @throws IOException 
	 */
	
	public void parse(ObservableList<String> list) throws IOException {
		
		//Obtiene el url del documento
		for(String url:list) {
			
			FileParser Textparser = factory.getParser(this.getExtension(url));
			
			//Separa el documento por lineas
			String[] lines = Textparser.getLines(url);
			
			//Obtiene las palabras de cada linea
			for(int i = 0; i < lines.length; i++) {
				
				String line = lines[i];
				String[] words= line.split(" ");
				
				System.out.println("Linea " + i + " es: " + line);
				System.out.println("-----------------------------------------------");
				
				//Obtiene las posiciones de palabras de una linea 
				for(int j=0; j < words.length; j++) {
					String word= words[j];
					System.out.println("La palabra " +j+ " es: " + word);
					
					//La palabra no existe y se inserta al arbol
					if(tree.getNode(word)== null) {
						System.out.println("Anade un elemento al arbol");
						tree.Insert(word);
						
					//La palabra existe en el arbol, se agrega la ocurrencia
					}else {
						TreeNode node= tree.getNode(word);
						//TODO: insertar a lista de ocurrencias
						System.out.println("Se agrega el nodo a la lista de ocurrencias");
					}
				}
				System.out.println("Tamano del arbol: " + tree.lenght);
			}
			
			System.out.println("===============================================");
			
		}
		
	}
	
	/**
	 * Convierte una extension del string a un ParserId
	 * @param url: es la direccion en donde se encuentra el archivo 
	 * @return :retorna la extension del tipo de archivo
	 */
	public ParserId getExtension(String url) {
		
		ParserId Pextension = null;
		
		int lastIndexOf = url.lastIndexOf(".");
		
		String extension = url.substring(lastIndexOf);	//Obtengo la extension del archivo
		
		// COnvierto la extension a un ParserId
		if(extension.equals(".txt")) {
			Pextension = ParserId.TXT;
		}else if(extension.equals(".pdf")) {
			Pextension = ParserId.PDF;
		}else if(extension.equals(".docx")) {
			Pextension = ParserId.DOCX;
		}else {
			return null;
		}
		
		return Pextension;
	}
	
}
