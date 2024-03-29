package Interfaz;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.contentstream.operator.state.SetGraphicsStateParameters;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import Logica.lista.Lista;
import Logica.lista.ListaNode;
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
	
	public ParserFactory factory = new ParserFactory();
	public Tree tree= new Tree();
	
	public void addAFile(ListView<String> archives) {
		
		FileChooser fc = new FileChooser();	// Cuadro de dialogo para abrir los archivos
		
		
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
				
				//Validacion de extension
				if(extension.equals(".docx") || extension.equals(".pdf") || extension.equals(".txt")) {
					
					//Annadir la vara en la lista de forma visual
					archives.getItems().add(file.getPath());
					
					//Para agregar imagenes en la listView con los documentos cargados
					archives.setCellFactory(param -> new ListCell<String>() {
			            
			            public void updateItem(String friend, boolean empty) {
			                super.updateItem(friend, empty);
			                Image addFi= new Image(getClass().getResource("doc.png").toExternalForm());
			        		ImageView img1= new ImageView(addFi);
			        		img1.setFitHeight(40);
			        		img1.setFitWidth(40);
			                if (empty) {
			                    setText(null);
			                    setGraphic(null);
			                } else {
			                    setGraphic(img1);
			                    setText(friend);
			                }
			            }
			                   
			        });
					
					
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
		
		// Para obtener carpeta seleccionada
		File folder = dc.showDialog(null);
		
		if(folder == null) {	// No selecciona el folder
			
			System.out.println("No selecciono un folder para cargar");
		
		} else {
			
			String[] files = folder.list();	// hago una lista con los documentos
			
			for(int i = 1; i < files.length; i++) {	// Recorro con un for para cargar todas los documentos
				
				
				String file = folder + "/"  + files[i];	// Obtengo el url del documento
				
				File validation = new File(file);
				
				System.out.println(file);
				
				if(validation.isFile()) {
					
					archives.getItems().add(file);
				
				}else {
					System.out.println("No es documento");
				}
				
			}

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
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
			String name = f.getName();
			
			Date lastModifiedDate = new Date( lastModified );

			
			float lenght = f.length();	//Longitud en bytes del archivo
			
		}
		
	}
	
	/**
	 * Se encarga de parsear los archivos cargados al programa
	 * @param previousList: Lista Observable que contiene los elementos que estaban en la lista
	 * @throws IOException 
	 */
	
	public void parse(ObservableList<String> list) throws IOException {
		
		this.tree= new Tree();
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
						TreeNode node = tree.getNode(word);
						node.lista.add_Last(url, i, j);
						
					//La palabra existe en el arbol, se agrega la ocurrencia
					}else {
						TreeNode node= tree.getNode(word);

						node.lista.add_Last(url, i, j);
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
	
	public TreeNode search(String word, ObservableList<String> ListaArchivos) throws IOException {
		
		String[] listWord= word.split(" ");
		parse(ListaArchivos); //Se crea el arbol
		
		TreeNode nodeW= this.tree.getNode(listWord[0]);	// Obtengo el nodo de la palabra buscada
		if(nodeW==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en la búsqueda");
			alert.setContentText("Ooops, la palabra buscada no existe en los documentos!");

			alert.showAndWait();
			System.out.println("No se encontro la palabra");
			return null;
		}else {
			return nodeW;			// Retorno la lista de archivos que contienen la palabra
		}
	}
	
	
	
	/** Obtiene un extracto de la palabra buscada
	 * @param nodo
	 * @return
	 */
	public String getExtract(ListaNode nodo) {
		
		FileParser parser = factory.getParser(this.getExtension(nodo.getUrl()));
		String[] nodeLines = parser.getLines(nodo.getUrl());
		String extract = nodeLines[nodo.getLine()];
		
		return extract;
		
	}
	
	
}
